package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class World {
    public static void run(MoveDirection[] directions){
        for(MoveDirection direction:directions){
            switch(direction){
                case f -> System.out.println("Zwierzak idzie do przodu");
                case b -> System.out.println("Zwierzak idzie do tyłu");
                case r -> System.out.println("Zwierzak idzie w prawo");
                case l -> System.out.println("Zwierzak idzie w lewo");
            }
        }
    }
    public static void main(String[] args){
        System.out.println("System wystartował");
        MoveDirection[] moves = OptionsParser.parse(args);
        run(moves);
        System.out.println("System zakończył działanie");
    }
}
