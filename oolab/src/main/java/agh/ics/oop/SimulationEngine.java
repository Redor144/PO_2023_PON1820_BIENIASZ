package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SimulationEngine{

    private List<Simulation> simulations;
    private CountDownLatch latch;
    private ExecutorService executor;
    private List<Thread> threads;
    public SimulationEngine(ArrayList<Simulation> simulations) {
        this.simulations = simulations;
        latch = new CountDownLatch(simulations.size());
        executor = Executors.newFixedThreadPool(4);
    }

    public void runSync(){
        for(Simulation simulation : simulations){
            simulation.run();
        }
    }
    public void runAsync() throws InterruptedException{
        for (Simulation simulation : simulations) {
            Thread thread = new Thread(() -> {
                simulation.run();
                latch.countDown();
            });
            thread.start();
        }
    }
    public void awaitSimulationsEnd() throws InterruptedException{
        if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
            executor.shutdownNow();
        }
    }
    public void runAsyncInThreadPool(){
        executor = Executors.newFixedThreadPool(4);
        for (Simulation simulation : simulations) {
            executor.submit(simulation);
        }
    }
}
