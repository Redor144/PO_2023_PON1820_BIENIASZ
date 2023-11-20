package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

import java.util.*;

import static java.lang.Math.*;

public class GrassField extends AbstractWorldMap{
    private final Map<Vector2d,WorldElement> grassField;
    public GrassField(int grassNum) {
        grassField = new HashMap<>();
        setGrassOnMap(grassNum);
    }
    public void setGrassOnMap(int grassNum){
        int maxGrass = (int) sqrt(grassNum*10);
        while (grassField.size() < grassNum){
            Random random = new Random();
            int x = random.nextInt(maxGrass+1);
            int y = random.nextInt((maxGrass+1));
            Vector2d newposition = new Vector2d(x,y);
            Grass newGrass = new Grass(newposition);
            grassField.put(newGrass.getPosition(),newGrass);
        }
    }
    public void placeGrass(Grass grass) throws PositionAlreadyOccupiedException{
        if(!isOccupied(grass.getPosition())){
            grassField.put(grass.getPosition(),grass);
        }
        else throw new PositionAlreadyOccupiedException(grass.getPosition());
    }
    boolean isOccupiedByAnimal(Vector2d position){
        return animals.containsKey(position);
    }
    @Override
    public WorldElement objectAt(Vector2d position) {
        if(isOccupiedByAnimal(position))return animals.get(position);
        else return grassField.get(position);
    }

    @Override
    public Boundary getCurrentBounds() {
        int minX=Integer.MAX_VALUE;
        int minY=Integer.MAX_VALUE;
        int maxX=Integer.MIN_VALUE;
        int maxY=Integer.MIN_VALUE;
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

    @Override
    public boolean canMoveTo(Vector2d position){
        if(!isOccupied(position))return true;
        else return objectAt(position).getClass()!= Animal.class;
    }
}