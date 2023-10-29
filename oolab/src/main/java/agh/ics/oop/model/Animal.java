package agh.ics.oop.model;
import agh.ics.oop.model.Vector2d;

public class Animal {
    private MapDirection orientation;
    private Vector2d posision;

    public Animal(){
        posision = new Vector2d(2,2);
        orientation = MapDirection.NORTH;
    }

    public Animal(Vector2d posision){
        this.posision = posision;
        this.orientation = MapDirection.NORTH;
    }

    @Override
    public String toString(){
        return "current posision and orientation: %s %s".formatted(posision.toString(), orientation.toString());
    }
    public boolean isAt(Vector2d posision){
        return this.posision.equals(posision);
    }

    public void move(MoveDirection direction){
        Vector2d map_border_down = new Vector2d(0,0);
        Vector2d map_border_up = new Vector2d(4,4);
        switch (direction){

            case FORWARD -> {
                Vector2d next_posis_predict=posision.add(orientation.toUnitVector());
                if(next_posis_predict.precedes(map_border_up) && next_posis_predict.follows(map_border_down)){
                    posision = next_posis_predict;
                }
            }
            case BACKWARD -> {
                Vector2d next_posis_predict=posision.subtract(orientation.toUnitVector());
                if(next_posis_predict.precedes(map_border_up) && next_posis_predict.follows(map_border_down)){
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
}
