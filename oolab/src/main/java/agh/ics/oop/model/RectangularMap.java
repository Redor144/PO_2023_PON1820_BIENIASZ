package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap {
    private Map<Vector2d, Animal> animals = new HashMap<>();
    private final int width;
    private final int height;

    public RectangularMap(int width, int height){
        this.height=height;
        this.width=width;
    }

    @Override
    public boolean place(Animal animal){
        if (canMoveTo(animal.getPosition())) {
            animals.put(animal.getPosition(),animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d prevPosition = animal.getPosition();
        animal.move(direction,this);
        if(!prevPosition.equals(animal.getPosition())){
            animals.remove(prevPosition,animal);
            animals.put(animal.getPosition(),animal);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position){
        return animals.containsKey(position);
    }

    @Override
    public Animal objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(new Vector2d(0,0))
                && position.precedes(new Vector2d(width-1,height-1))
                && !isOccupied(position);
    }

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        Vector2d downLeft = new Vector2d(0,0);
        Vector2d upRight = new Vector2d(width-1,height-1);
        return visualizer.draw(downLeft,upRight);
    }
}
