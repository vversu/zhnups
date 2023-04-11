package scrapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

    private static IntegrationEnvironment instance;

    private final PostgreSQLContainer<?> POSTGRES_CONTAINER =
            new PostgreSQLContainer<>(DockerImageName.parse("postgres:14"))
                    .withDatabaseName("scrapper")
                    .withUsername("polina")
                    .withPassword("123")
                    .withInitScript("tables.sql")
                    .withEnv("LANG", "en_US.utf8");

    private static boolean started = false;

    private IntegrationEnvironment() {
        POSTGRES_CONTAINER.start();
        migrate();
    }

    public static IntegrationEnvironment getInstance() {
        if (instance == null) {
            instance = new IntegrationEnvironment();
        }
        return instance;
    }

    private void migrate() {
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

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                POSTGRES_CONTAINER.getJdbcUrl(),
                POSTGRES_CONTAINER.getUsername(),
                POSTGRES_CONTAINER.getPassword());
    }

    public void stopContainer() {
        POSTGRES_CONTAINER.stop();
        started = false;
    }

    public boolean isStarted() {
        return started;
    }

    static {
        getInstance();
    }
}
