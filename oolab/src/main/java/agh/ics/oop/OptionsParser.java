package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import java.util.ArrayList;
import java.util.List;

public class OptionsParser {
    public static List<MoveDirection> parse(String[] args) {
        List<MoveDirection> mov_dir = new ArrayList<>();
        for (String arg : args) {
            switch (arg) {
                case "f" -> mov_dir.add(MoveDirection.FORWARD);
                case "b" -> mov_dir.add(MoveDirection.BACKWARD);
                case "r" -> mov_dir.add(MoveDirection.RIGHT);
                case "l" -> mov_dir.add(MoveDirection.LEFT);
                default -> throw new IllegalArgumentException(arg+ " is not legal move specification");
            }
        }
        return mov_dir;
    }
}
