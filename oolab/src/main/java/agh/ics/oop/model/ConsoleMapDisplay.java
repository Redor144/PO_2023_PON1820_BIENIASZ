package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener {

    private int changesNum;

    public ConsoleMapDisplay() {
        this.changesNum =0;
    }

    @Override
    public synchronized void mapChanged(WorldMap worldMap, String message) {
        changesNum++;
        System.out.println("Update no: " + changesNum);
        System.out.println("Message "+message);
        System.out.println("Map ID: " + worldMap.getId());
        System.out.println(worldMap);
    }
}
