package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SimulationEngine{

    private ArrayList<Simulation> simulations;
    private List<Thread> threads;
    public SimulationEngine(ArrayList<Simulation> simulations) {
        this.simulations = simulations;
        threads = new ArrayList<>();
    }

    public void runSync(){
        for(Simulation simulation : simulations){
            simulation.run();
        }
    }
    public void runAsync() throws InterruptedException{
        for(Simulation simulation : simulations){
            Thread thread = new Thread(simulation);
            thread.start();
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }
    public void awaitSimulationsEnd() throws InterruptedException{
        while(!allSimulationsFinished()){
            wait();
        }
        System.out.println("System zakończył działanie");
    }
    private boolean allSimulationsFinished(){
        for(Thread thread : threads){
            if(thread.isAlive()){
                return false;
            }
        }
        return true;
    }
    public void runAsyncInThreadPool(){
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for(Simulation simulation : simulations){
            executorService.submit(simulation);
        }
        shutdownExecutor(executorService);
    }

    private void shutdownExecutor(ExecutorService executorService){
        executorService.shutdown();
        try{
            if(!executorService.awaitTermination(10, TimeUnit.SECONDS)){
                executorService.shutdownNow();
            }
        }catch (InterruptedException e){
            executorService.shutdownNow();
        }
    }
}
