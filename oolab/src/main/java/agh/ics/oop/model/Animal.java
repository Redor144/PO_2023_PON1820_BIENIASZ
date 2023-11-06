package agh.ics.oop.model;
import agh.ics.oop.model.Vector2d;

public class Animal implements MoveValidator {
    private MapDirection orientation;
    private Vector2d posision;

    public Animal(){
        posision = new Vector2d(2,2);
        orientation = MapDirection.NORTH;
    }

    public Vector2d getPosision() {
        return posision;
    }

    public Animal(Vector2d posision){
        this.posision = posision;
        this.orientation = MapDirection.NORTH;
    }

    @Override
    public String toString(){
        return orientation.toString();
    }
    public boolean isAt(Vector2d posision){
        return this.posision.equals(posision);
    }

    public void move(MoveDirection direction,MoveValidator validator){
        switch (direction){
            case FORWARD -> {
                Vector2d next_posis_predict=posision.add(orientation.toUnitVector());
                if(validator.canMoveTo(next_posis_predict)){
                    posision = next_posis_predict;
                }
            }
            case BACKWARD -> {
                Vector2d next_posis_predict=posision.subtract(orientation.toUnitVector());
                if(validator.canMoveTo(next_posis_predict)){
                    posision = next_posis_predict;
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
