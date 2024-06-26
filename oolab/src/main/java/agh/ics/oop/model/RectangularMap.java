package agh.ics.oop.model;

import java.util.UUID;

public class RectangularMap extends AbstractWorldMap{
    private final int width;
    private final int height;

    public RectangularMap(int width, int height){
        super(UUID.randomUUID());
        this.height=height;
        this.width=width;
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(new Vector2d(0,0))
                && position.precedes(new Vector2d(width-1,height-1))
                && !isOccupied(position);
    }
}
