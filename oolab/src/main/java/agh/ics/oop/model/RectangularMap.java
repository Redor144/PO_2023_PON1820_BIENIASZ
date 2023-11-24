package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap extends AbstractWorldMap{
    private final int width;
    private final int height;

    public RectangularMap(int width, int height){
        this.height=height;
        this.width=width;
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
