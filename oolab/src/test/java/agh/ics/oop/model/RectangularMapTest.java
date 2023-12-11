package agh.ics.oop.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RectangularMapTest {
    @Test

    void testAnimalMovement() throws PositionAlreadyOccupiedException {
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
    void testAnimalCollision() throws PositionAlreadyOccupiedException {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(2, 2));

        map.place(animal1);
        try{
            map.place(animal2);
        }catch (PositionAlreadyOccupiedException e){
            assertEquals(e.getMessage(), "Position (2,2) is already occupied.");
        }
    }
    @Test
    void testObjectAt() throws PositionAlreadyOccupiedException {
        GrassField map = new GrassField(5);
        Animal animal = new Animal(new Vector2d(2, 2));
        Grass grass = new Grass(new Vector2d(3, 3));

        map.place(animal);
        map.placeGrass(grass);

        assertEquals(animal, map.objectAt(animal.getPosition()));
        assertEquals(grass, map.objectAt(grass.getPosition()));
    }
}
