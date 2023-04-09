--liquibase formatted sql
--changeset your.name:create-tables
CREATE TABLE link (
id INTEGER PRIMARY KEY AUTOINCREMENT,
user_id INTEGER NOT NULL,
url TEXT NOT NULL
);

--changeset your.name:create-chat-table
CREATE TABLE chat (
id INTEGER PRIMARY KEY AUTOINCREMENT,
user_id INTEGER NOT NULL
);

--changeset your.name:create-link-tracking-table
CREATE TABLE link_tracking (
id INTEGER PRIMARY KEY AUTOINCREMENT,
link_id INTEGER NOT NULL,
chat_id INTEGER NOT NULL,
FOREIGN KEY (link_id) REFERENCES link(id),
FOREIGN KEY (chat_id) REFERENCES chat(id)
);