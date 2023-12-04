package agh.ics.oop.model;

import java.util.*;

import static java.lang.Math.*;

public class GrassField extends AbstractWorldMap{
    public GrassField(int grassNum) {
        super(UUID.randomUUID());
        grassField = new HashMap<>();
        setGrassOnMap(grassNum);
    }
    public void setGrassOnMap(int grassNum){
        int maxGrass = (int) sqrt(grassNum*10);
        Random random = new Random();
        while (grassField.size() < grassNum){
            int x = random.nextInt(maxGrass+1);
            int y = random.nextInt(maxGrass+1);
            Vector2d newPosition = new Vector2d(x,y);
            Grass newGrass = new Grass(newPosition);
            grassField.put(newGrass.getPosition(),newGrass);
        }
    }
    boolean isOccupiedByGrass(Vector2d position){
        return grassField.containsKey(position);
    }
    public void placeGrass(Grass grass) throws PositionAlreadyOccupiedException{
        if(!isOccupiedByGrass(grass.getPosition())){
            grassField.put(grass.getPosition(),grass);
        }
        else throw new PositionAlreadyOccupiedException(grass.getPosition());
    }
    @Override
    public WorldElement objectAt(Vector2d position) {
        if(isOccupiedByAnimal(position))return animals.get(position);
        else return grassField.get(position);
    }

    @Override
    public Boundary getCurrentBounds() {
        int minX=Integer.MAX_VALUE,
            minY=Integer.MAX_VALUE,
            maxX=Integer.MIN_VALUE,
            maxY=Integer.MIN_VALUE;
        for(Vector2d key : grassField.keySet()){
            minX=min(key.getX(), minX);
            minY=min(key.getY(), minY);
            maxX=max(key.getX(), maxX);
            maxY=max(key.getY(), maxY);
        }
        for(Vector2d key : animals.keySet()){
            minX=min(key.getX(), minX);
            minY=min(key.getY(), minY);
            maxX=max(key.getX(), maxX);
            maxY=max(key.getY(), maxY);
        }
        return new Boundary(new Vector2d(minX,minY),new Vector2d(maxX,maxY));
    }
}
