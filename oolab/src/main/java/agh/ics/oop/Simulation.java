package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class Simulation implements Runnable{
    private final List<Animal> animals;
    private final List<MoveDirection> moves;
    private WorldMap map;
    
    public Simulation(List<MoveDirection> moves, List<Vector2d> positions,WorldMap map){
        List<Animal> animals = new ArrayList<>();
        for (Vector2d position : positions) {
            try{
            Animal animal = new Animal(position);
            map.place(animal);
            animals.add(animal);
            }catch (PositionAlreadyOccupiedException ex){
                System.out.println(ex.getMessage());
            }
        }
        this.animals = animals;
        this.moves = moves;
        this.map=map;
    }
    public void run(){
        for(int i =0 ;i < moves.size(); i++){
            Animal currAnimal = animals.get(i % animals.size());
            map.move(currAnimal,moves.get(i));
            try {
                Thread.sleep(500);
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }
    }

}