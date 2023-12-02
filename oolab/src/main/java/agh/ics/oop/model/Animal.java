package agh.ics.oop.model;
import agh.ics.oop.model.Vector2d;

public class Animal implements WorldElement {
    private MapDirection orientation;
    private Vector2d position;

    public MapDirection getOrientation() {
        return orientation;
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }

    public Animal(Vector2d position) {
        this.position = position;
        orientation = MapDirection.NORTH;
    }

    @Override
    public String toString(){
        return orientation.toString();
    }
    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }
    public void move(MoveDirection direction,MoveValidator validator){
        switch (direction){
            case FORWARD -> {
                position = canForwardOrBackward(validator, MoveDirection.FORWARD);
            }
            case BACKWARD -> {
                position = canForwardOrBackward(validator,MoveDirection.BACKWARD);
            }
            case RIGHT -> {
                orientation = orientation.next();
            }
            case LEFT -> {
                orientation = orientation.previous();
            }
        }
    }
    private Vector2d canForwardOrBackward(MoveValidator validator, MoveDirection move){
        Vector2d nextPos = position.add(move == MoveDirection.FORWARD ? orientation.toUnitVector() : orientation.toUnitVector().opposite());
        return validator.canMoveTo(nextPos) ? nextPos : position;
    }
}
