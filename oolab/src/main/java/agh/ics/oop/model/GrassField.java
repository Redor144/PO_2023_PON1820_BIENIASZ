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

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(getLower(),getUpper());
    }
    private Vector2d getLower(){
        int minX=0;
        int minY=0;
        for(Vector2d key : grassField.keySet()){
            minX=min(key.getX(), minX);
            minY=min(key.getY(), minY);
        }
        for(Vector2d key : animals.keySet()){
            minX=min(key.getX(), minX);
            minY=min(key.getY(), minY);
        }
        return new Vector2d(minX,minY);
    }

    private Vector2d getUpper(){
        int maxX=0;
        int maxY=0;
        for(Vector2d key : grassField.keySet()){
            maxX=max(key.getX(), maxX);
            maxY=max(key.getY(), maxY);
        }
        for(Vector2d key : animals.keySet()){
            maxX=max(key.getX(), maxX);
            maxY=max(key.getY(), maxY);
        }
        return new Vector2d(maxX,maxY);
    }
    public boolean placeGrass(Grass grass){
        if(!isOccupied(grass.getPosition())){
            grassField.put(grass.getPosition(),grass);

        }
        return false;
    }
    @Override
    public WorldElement objectAt(Vector2d position) {
        if(animals.containsKey(position))return animals.get(position);
        else return grassField.get(position);
    }

}
