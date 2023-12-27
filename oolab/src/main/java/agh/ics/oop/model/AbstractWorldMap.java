package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public abstract class AbstractWorldMap implements WorldMap {
    protected Map<Vector2d, Animal> animals = new HashMap<>();
    protected Map<Vector2d, Grass> grassField = new HashMap<>();
    protected List<MapChangeListener> observers = new ArrayList<>();

    protected final UUID id;

    protected AbstractWorldMap(UUID id) {
        this.id = id;
    }

    public void addObserver(MapChangeListener observer){
        observers.add(observer);
    }
    public void removeObserver(MapChangeListener observer){
        observers.remove(observer);
    }
    protected void notifyObservers(String message) {
        for (MapChangeListener observer : observers) {
            observer.mapChanged(this, message);
        }
    }
    public String toString(){
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(getCurrentBounds().lowerLeft(),getCurrentBounds().upperRight());
    }
    @Override
    public Boundary getCurrentBounds(){
        int minX=Integer.MAX_VALUE,
                minY=Integer.MAX_VALUE,
                maxX=Integer.MIN_VALUE,
                maxY=Integer.MIN_VALUE;
        for(Vector2d key : animals.keySet()){
            minX=min(key.getX(), minX);
            minY=min(key.getY(), minY);
            maxX=max(key.getX(), maxX);
            maxY=max(key.getY(), maxY);
        }
        return new Boundary(new Vector2d(minX,minY),new Vector2d(maxX,maxY));
    }
    @Override
    public Collection<WorldElement> getElements() {
        List<WorldElement> elements = new ArrayList<>();
        elements.addAll(animals.values());
        elements.addAll(grassField.values());
        return elements;
    }
    @Override
    public void place(Animal animal) throws PositionAlreadyOccupiedException{
        if (canMoveTo(animal.getPosition())) {
            animals.put(animal.getPosition(), animal);
            this.notifyObservers("Animal placed on " + animal.getPosition().toString());
        }
        else throw new PositionAlreadyOccupiedException(animal.getPosition());
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d oldPosition = animal.getPosition();
        MapDirection oldDirection = animal.getOrientation();
        animal.move(direction, this);
        Vector2d newPosition = animal.getPosition();
        MapDirection newDirection = animal.getOrientation();
        if (!oldPosition.equals(newPosition)) {
            animals.remove(oldPosition);
            animals.put(newPosition, animal);
            this.notifyObservers("Animal moved from " + oldPosition.toString() + " to " + newPosition.toString());
        } else if (!oldDirection.equals(newDirection)) {
            this.notifyObservers("Animal changed orientation from " + oldDirection.toString() + " to " + newDirection.toString());
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    boolean isOccupiedByAnimal(Vector2d position){
        return animals.containsKey(position);
    }
    public boolean canMoveTo(Vector2d position){
        return !isOccupiedByAnimal(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        if (animals.containsKey(position)) {
            return animals.get(position);
        } else {
            return grassField.get(position);
        }
    }
    public UUID getId(){
        return id;
    }
}
