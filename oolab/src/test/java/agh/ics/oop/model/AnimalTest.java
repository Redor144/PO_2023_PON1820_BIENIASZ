//package agh.ics.oop.model;
//
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class AnimalTest {
//
//    @Test
//    void move(){
//        //orientation and movement check
//
//        Animal animal = new Animal();
//
//        animal.move(MoveDirection.FORWARD);
//        assertEquals(animal.toString(), "current posision and orientation: (2,3) NORTH");
//
//        animal.move(MoveDirection.LEFT);
//        assertEquals(animal.toString(), "current posision and orientation: (2,3) WEST");
//
//        animal.move(MoveDirection.BACKWARD);
//        assertEquals(animal.toString(), "current posision and orientation: (3,3) WEST");
//
//        animal.move(MoveDirection.RIGHT);
//        assertEquals(animal.toString(), "current posision and orientation: (3,3) NORTH");
//
//        //map border check
//        Animal animal1 = new Animal(new Vector2d(4, 4));
//
//        animal1.move(MoveDirection.FORWARD);
//        assertEquals(animal1.toString(), "current posision and orientation: (4,4) NORTH");
//    }
//}
