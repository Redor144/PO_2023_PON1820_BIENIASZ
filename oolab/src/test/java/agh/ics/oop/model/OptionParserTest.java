package agh.ics.oop.model;

import agh.ics.oop.OptionsParser;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class OptionParserTest {
    @Test
    void parse() {
        String[] args1 = {"f", "b", "l", "r"};
        String[] args2 = {"f", "b", "asd", "r"};

        assertEquals(new ArrayList<MoveDirection>(List.of(new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.RIGHT})), OptionsParser.parse(args1));
        assertEquals(new ArrayList<MoveDirection>(List.of(new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT})), OptionsParser.parse(args2));
    }
}
