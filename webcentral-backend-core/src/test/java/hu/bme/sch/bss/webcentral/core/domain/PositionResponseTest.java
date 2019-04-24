package hu.bme.sch.bss.webcentral.core.domain;

import hu.bme.sch.bss.webcentral.core.model.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionResponseTest {

    private static final String NAME = "name";

    private PositionResponse underTest;

    @Test
    void testConstructorAndGetters() {
        // GIVEN
        Position position = Position.builder()
            .withName(NAME)
            .build();

        // WHEN
        underTest = new PositionResponse(position);

        // THEN
        assertEquals(position.getId(), underTest.getId());
        assertEquals(position.getName(), underTest.getName());
    }
}