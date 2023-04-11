package scrapper;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

public class IntegrationEnvironmentTest {

    @Test
    public void testContainerStartup() {
    	PostgreSQLContainer<?> container = IntegrationEnvironment.POSTGRES_CONTAINER;

        // Проверяем, что контейнер запущен
        assertTrue(container.isRunning());
    }
}