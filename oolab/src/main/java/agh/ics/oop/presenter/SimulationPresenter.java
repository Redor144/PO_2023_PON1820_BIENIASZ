package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import javax.swing.text.LabelView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SimulationPresenter implements MapChangeListener {
    private WorldMap map;
    @FXML
    private Label messageLabel;
    @FXML
    private TextField movementTextField;
    @FXML
    private GridPane mapString;

    public void setMap(WorldMap map) {
        this.map = map;
    }

    private Label createLabel(String text){
        Label label = new Label(text);
        GridPane.setHalignment(label, HPos.CENTER);
        return label;
    }
    public void drawMap(WorldMap map){
        clearWindow();
        Boundary boundary = map.getCurrentBounds();
        Vector2d topRight = boundary.upperRight();
        Vector2d bottomLeft = boundary.lowerLeft();

        int gridWidth = topRight.getX() - bottomLeft.getX() + 2;
        int gridHeight = topRight.getY() - bottomLeft.getY() + 2;
        mapString.add(createLabel("y \\ x"), 0, 0);

        int CELL_WIDTH = 40;
        mapString.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
        int CELL_HEIGHT = 40;
        mapString.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));

        int leftStart = bottomLeft.getX();

        for (int i = 1; i < gridWidth; i++) {
            mapString.add(createLabel(String.valueOf(leftStart)), i, 0);
            mapString.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
            leftStart++;
        }

        int upperStart = topRight.getY();
        for (int i = 1; i < gridHeight; i++) {
            mapString.add(createLabel(String.valueOf(upperStart)), 0, i);
            mapString.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));
            upperStart--;
        }

        for (int i = bottomLeft.getY(); i <= topRight.getY(); i++) {
            for (int j = bottomLeft.getX(); j <= topRight.getX(); j++) {
                Vector2d currPoint = new Vector2d(j, i);
                if (map.isOccupied(currPoint)) {
                    int columnInd = j - bottomLeft.getX() + 1;
                    int rowInd = gridHeight - (i - bottomLeft.getY()) - 1;
                    mapString.add(createLabel(map.objectAt(currPoint).toString()),
                            columnInd,
                            rowInd);
                }
            }
        }
    }
    private void clearWindow(){
        mapString.getChildren().retainAll(mapString.getChildren().get(0));
        mapString.getColumnConstraints().clear();
        mapString.getRowConstraints().clear();
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            drawMap(worldMap);
            messageLabel.setText(message);
        });
    }

    @FXML
    public void onSimulationStartClicked() throws InterruptedException {
        List<MoveDirection> moves = OptionsParser.parse(movementTextField.getText().split(" "));
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        WorldMap map = new GrassField(10);
        setMap(map);
        map.addObserver(this);
        Simulation simulation = new Simulation(moves,positions,map);
        ArrayList<Simulation> simulations = new ArrayList<>();
        simulations.add(simulation);
        SimulationEngine simulationEngine = new SimulationEngine(simulations);
        simulationEngine.runAsyncInThreadPool();
    }
}
