package agh.ics.oop.model;

import agh.ics.oop.OptionsParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class OptionParserTest {
    @Test
    void parse() {
        String[] args1 = {"f", "b", "l", "r"};
        String[] args2 = {"f", "b", "asd", "r"};

        assertArrayEquals(new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.RIGHT}, OptionsParser.parse(args1));
        assertArrayEquals(new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT}, OptionsParser.parse(args2));
    }
}
