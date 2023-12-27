package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class World {
    public static void run(List<MoveDirection> directions){
        for(MoveDirection direction:directions){
            switch(direction){
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tyłu");
                case RIGHT -> System.out.println("Zwierzak idzie w prawo");
                case LEFT -> System.out.println("Zwierzak idzie w lewo");
            }
        }
    }
    public static void main(String[] args){
        try{
            List<MoveDirection> directions = new ArrayList<>();
            directions = OptionsParser.parse(args);
            List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(2,2));
            WorldMap map = new GrassField(10);
            ConsoleMapDisplay observer = new ConsoleMapDisplay();
            map.addObserver(observer);
            Simulation simulation = new Simulation(directions,positions,map);
            simulation.run();
        }catch (IllegalArgumentException ex){
            System.out.println("Caught an exception: " + ex.getMessage());
        }
    }
}
