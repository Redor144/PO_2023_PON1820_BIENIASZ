package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private List<Animal> animals;
    private List<MoveDirection> moves;

    public Simulation(List<MoveDirection> moves, List<Vector2d> posisions){
        List<Animal> animals = new ArrayList<>();
        for(int i =0;i<posisions.size();i++){
            animals.add(new Animal(posisions.get(i)));
        }
        this.animals = animals;
        this.moves = moves;
    }
    public void run(){
        for(int i =0 ;i < moves.size(); i++){
            Animal curr_animal = animals.get(i % animals.size());
            curr_animal.move(moves.get(i));
            System.out.println("Zwierzę: " + i % animals.size()+" " + curr_animal.toString());
        }
    }

}
