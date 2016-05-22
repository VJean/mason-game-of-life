package gui;

import model.Beings;
import model.Cell;
import sim.display.Controller;
import sim.display.Display2D;
import sim.display.GUIState;
import sim.engine.SimState;
import sim.portrayal.Inspector;
import sim.portrayal.grid.ObjectGridPortrayal2D;
import sim.portrayal.simple.OvalPortrayal2D;

import javax.swing.*;
import java.awt.*;

public class BeingsWithUI extends GUIState {
    public static int FRAME_SIZE = 600;
    public Display2D display;
    public JFrame displayFrame;
    ObjectGridPortrayal2D gridPortrayal = new ObjectGridPortrayal2D();

    public BeingsWithUI(SimState state) {
        super(state);
    }

    public static String getName() {
        return "Cells simulation";
    }

    public void start() {
        super.start();
        setupPortrayals();
    }

    public void load(SimState state) {
        super.load(state);
        setupPortrayals();
    }

    public void setupPortrayals() {
        Beings beings = (Beings) state;
        gridPortrayal.setField(beings.getGrid());
        gridPortrayal.setPortrayalForClass(Cell.class, getCellPortrayal());

        display.reset();
        display.setBackdrop(Color.BLACK);
        display.repaint();
    }

    private CellPortrayal2D getCellPortrayal() {
        CellPortrayal2D r = new CellPortrayal2D();

        return r;
    }

    public void init(Controller c) {
        super.init(c);
        display = new Display2D(FRAME_SIZE, FRAME_SIZE, this);
        display.setClipping(false);
        displayFrame = display.createFrame();
        displayFrame.setTitle("Game of Life");
        c.registerFrame(displayFrame); // so the frame appears in the "Display" list
        displayFrame.setVisible(true);
        display.attach(gridPortrayal, "Grid");
    }

    public Object getSimulationInspectedObject() {
        return state;
    }

    public Inspector getInspector() {
        Inspector i = super.getInspector();
        i.setVolatile(true);
        return i;
    }
}
