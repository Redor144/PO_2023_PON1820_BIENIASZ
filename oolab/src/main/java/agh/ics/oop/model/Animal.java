package agh.ics.oop.model;
import agh.ics.oop.model.Vector2d;

public class Animal implements MoveValidator {
    private MapDirection orientation;
    private Vector2d position;

    public Animal(){
        position = new Vector2d(2,2);
        orientation = MapDirection.NORTH;
    }

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
                Vector2d next_posit_predict=position.add(orientation.toUnitVector());
                if(validator.canMoveTo(next_posit_predict)){
                    position = next_posit_predict;
                }
            }
            case BACKWARD -> {
                Vector2d next_posis_predict=position.subtract(orientation.toUnitVector());
                if(validator.canMoveTo(next_posis_predict)){
                    position = next_posis_predict;
                }
            }
            case RIGHT -> {
                orientation = orientation.next();
            }
            case LEFT -> {
                orientation = orientation.previous();
            }
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return false;
    }
}
