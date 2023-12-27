package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener {

    private int changesNum;

    public ConsoleMapDisplay() {
        this.changesNum =0;
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        System.out.println(message);
        System.out.println(worldMap);
        changesNum++;
        System.out.println(changesNum);
    }
}
