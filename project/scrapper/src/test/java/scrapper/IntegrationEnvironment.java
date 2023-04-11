package scrapper;

import java.io.File;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

public class IntegrationEnvironment {
    static final PostgreSQLContainer<?> POSTGRES_CONTAINER =
        new PostgreSQLContainer<>(DockerImageName.parse("postgres:14"))
            .withDatabaseName("scrapper")
            .withUsername("polina")
            .withPassword("123")
            .withInitScript("tables.sql")
            .withEnv("LANG", "en_US.utf8");
    

    private static boolean started = false;

    static void startContainer() {
        if (!started) {
            POSTGRES_CONTAINER.start();
            started = true;
            migrate();
        }
    }

    private static void migrate() {
        try (Connection connection = getConnection();
             Database database = DatabaseFactory.getInstance()
                 .findCorrectDatabaseImplementation(new JdbcConnection(connection))) {
            Liquibase liquibase = new Liquibase("migrations/master.xml",
                                                new ClassLoaderResourceAccessor(),
                                                database);
            liquibase.update(new Contexts(), new LabelExpression());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            POSTGRES_CONTAINER.getJdbcUrl(),
            POSTGRES_CONTAINER.getUsername(),
            POSTGRES_CONTAINER.getPassword());
    }

    static {
        startContainer();
    }
}