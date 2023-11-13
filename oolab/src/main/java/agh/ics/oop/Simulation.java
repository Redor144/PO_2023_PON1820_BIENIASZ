package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private List<Animal> animals;
    private List<MoveDirection> moves;
    private WorldMap map;
    
    public Simulation(List<MoveDirection> moves, List<Vector2d> positions,WorldMap map){
        List<Animal> animals = new ArrayList<>();
        for (Vector2d position : positions) {
            animals.add(new Animal(position));
        }
        this.animals = animals;
        this.moves = moves;
        this.map=map;
    }
    public void run(){
        for(int i =0 ;i < moves.size(); i++){
            Animal curr_animal = animals.get(i % animals.size());
            map.move(curr_animal,moves.get(i));
            System.out.println(map);
        }
    }

}