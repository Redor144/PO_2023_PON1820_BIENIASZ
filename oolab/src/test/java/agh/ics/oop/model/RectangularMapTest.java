package agh.ics.oop.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RectangularMapTest {
    @Test

    void testAnimalMovement() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(3, 3));

        map.place(animal1);
        map.place(animal2);

        animal1.move(MoveDirection.FORWARD, map);
        animal2.move(MoveDirection.BACKWARD, map);
        animal1.move(MoveDirection.RIGHT, map);

        assertEquals(new Vector2d(2, 3), animal1.getPosition());
        assertEquals(new Vector2d(3, 2), animal2.getPosition());
    }

    @Test
    void testAnimalCollision() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(2, 2));

        map.place(animal1);
        assertFalse(map.place(animal2));
    }
}
