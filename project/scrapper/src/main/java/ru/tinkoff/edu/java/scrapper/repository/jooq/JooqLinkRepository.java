package ru.tinkoff.edu.java.scrapper.repository.jooq;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.JSONB;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.exception.DBException;
import ru.tinkoff.edu.java.scrapper.model.Link;
import ru.tinkoff.edu.java.scrapper.model.jooq.tables.records.LinkRecord;
import ru.tinkoff.edu.java.scrapper.repository.LinkRepository;

import java.time.OffsetDateTime;
import java.time.temporal.TemporalAmount;
import java.util.List;
import java.util.Optional;

import static ru.tinkoff.edu.java.scrapper.model.jooq.tables.ChatLink.CHAT_LINK;
import static ru.tinkoff.edu.java.scrapper.model.jooq.tables.Link.LINK;


@Primary
@Repository
@RequiredArgsConstructor
public class JooqLinkRepository implements LinkRepository {
    private final DSLContext dsl;

    public List<Link> findAll() {
        return dsl.selectFrom(LINK).fetch(this::mapToLink);
    }


    @Override
    public List<Link> findAllByChat(long chatId) {
        return dsl.select(LINK.fields())
                .from(LINK)
                .join(CHAT_LINK)
                .on(LINK.ID.eq(CHAT_LINK.LINK_ID))
                .where(CHAT_LINK.CHAT_ID.eq(chatId))
                .fetchGroups(LINK)
                .keySet().stream().map(this::mapToLink).toList();
    }

    @Override
    public Optional<Link> findByUrl(String url) {
        return dsl.selectFrom(LINK)
                .where(LINK.URL.eq(url))
                .fetchOptional(this::mapToLink);
    }

    @Override
    public Optional<Link> findById(long id) {
        return dsl.selectFrom(LINK)
                .where(LINK.ID.eq(id))
                .fetchOptional(this::mapToLink);
    }

    @Override
    public List<Link> findLeastRecentlyUpdated(TemporalAmount delta) {
        return dsl.selectFrom(LINK)
                .where(LINK.UPDATED_AT.lessThan(OffsetDateTime.now().minus(delta)))
                .fetch(this::mapToLink);
    }

    @Override
    public Link save(Link link) {
        if (link.getId() == null) {
            return dsl.insertInto(LINK, LINK.URL)
                    .values(link.getUrl())
                    .returning(LINK.fields())
                    .fetchOne(this::mapToLink);
        }

        return dsl.update(LINK)
                .set(LINK.URL, link.getUrl())
                .set(LINK.UPDATE_DATA, JSONB.valueOf(link.getUpdateData()))
                .set(LINK.UPDATED_AT, link.getUpdatedAt())
                .where(LINK.ID.eq(link.getId()))
                .returning(LINK.fields())
                .fetchOptional(this::mapToLink)
                .orElseThrow(() -> new DBException("Failed to update link"));
    }

    @Override
    public boolean removeById(long id) {
        return dsl.deleteFrom(LINK).where(LINK.ID.eq(id)).execute() == 1;
    }

    @Override
    public boolean addToChat(long chatId, long linkId) {
        var ifExists = dsl.selectFrom(CHAT_LINK)
                .where(CHAT_LINK.CHAT_ID.eq(chatId), CHAT_LINK.LINK_ID.eq(linkId))
                .fetchOptional().isEmpty();
        return ifExists && dsl.insertInto(CHAT_LINK, CHAT_LINK.fields())
                .values(chatId, linkId)
                .execute() == 1;
    }

    @Override
    public boolean removeFromChat(long chatId, long linkId) {
        return dsl.deleteFrom(CHAT_LINK)
                .where(CHAT_LINK.CHAT_ID.eq(chatId), CHAT_LINK.LINK_ID.eq(linkId))
                .execute() == 1;
    }

    private Link mapToLink(LinkRecord link) {
        return new Link()
                .setId(link.getId())
                .setUrl(link.getUrl())
                .setUpdatedAt(link.getUpdatedAt())
                .setUpdateData(link.getUpdateData().toString());
    }
}
