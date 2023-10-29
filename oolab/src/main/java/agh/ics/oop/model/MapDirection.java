package agh.ics.oop.model;

public enum MapDirection {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public MapDirection next(){
        return values()[(this.ordinal()+1)%4];
    }
    public MapDirection previous(){
        return values()[Math.abs((this.ordinal()+3))%4];
    }
    public Vector2d toUnitVector(){
        return switch(this){

            case NORTH -> new Vector2d(0,1);
            case SOUTH -> new Vector2d(0,-1);
            case WEST -> new Vector2d(-1,0);
            case EAST -> new Vector2d(1,0);
        };
    }
}
