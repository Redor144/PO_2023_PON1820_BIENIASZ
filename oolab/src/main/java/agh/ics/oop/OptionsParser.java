package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;
import java.util.Arrays;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args) {
        MoveDirection[] mov_dir = new MoveDirection[args.length];
        int index=0;
        for (String arg : args) {
            switch (arg) {
                case "f" -> {
                    mov_dir[index] = MoveDirection.f;
                    index++;
                }
                case "b" -> {
                    mov_dir[index] = MoveDirection.b;
                    index++;
                }
                case "r" -> {
                    mov_dir[index] = MoveDirection.r;
                    index++;
                }
                case "l" -> {
                    mov_dir[index] = MoveDirection.l;
                    index++;
                }
            }
        }
        MoveDirection[] directions = Arrays.copyOfRange(mov_dir,0,index);
        return directions;
    }
}
