package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class World {
    public static void main(String[] args){
        try{
            List<MoveDirection> directions = OptionsParser.parse(args);
            List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
            ConsoleMapDisplay observer = new ConsoleMapDisplay();
            ArrayList<Simulation> simulations = new ArrayList<>();
            int numberOfSimulations = 20000;

            for(int i = 0; i<numberOfSimulations;i++){
                WorldMap grassField = new GrassField(10);
                grassField.addObserver(observer);

                Simulation simulation = new Simulation(directions,positions,grassField);
                simulations.add(simulation);
            }

            SimulationEngine simulationEngine = new SimulationEngine(simulations);
            simulationEngine.runAsyncInThreadPool();
            simulationEngine.awaitSimulationsEnd();
        }catch (IllegalArgumentException | InterruptedException ex){
            System.out.println("Caught an exception: " + ex.getMessage());
        }
    }
}
