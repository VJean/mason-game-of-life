package model;

import javafx.scene.control.Control;
import sim.engine.SimState;
import sim.engine.Stoppable;
import sim.field.grid.ObjectGrid2D;
import sim.util.Int2D;

public class Beings extends SimState {
    private static int GRID_SIZE = 100;

    private ObjectGrid2D grid = new ObjectGrid2D(Config.GRID_SIZE_W, Config.GRID_SIZE_H);

    public Beings(long seed) {
        super(seed);
    }

    private int numLiveCells;

    public void start() {
        System.out.println("Simulation started");
        super.start();
        grid.clear();
        Config.load();
        setNumLiveCells(0);
        addCells();
    }



    private void addCells() {
        for (int i = 0; i < Config.GRID_SIZE_W; i++) {
            for (int j = 0; j < Config.GRID_SIZE_H; j++) {
                Int2D location = new Int2D(i, j);

                Cell cell = new Cell(location, CellState.DEAD);

                Stoppable stoppable = schedule.scheduleRepeating(cell);

                cell.setStoppable(stoppable);

                grid.set(cell.getLocation().getX(), cell.getLocation().getY(), cell);
            }
        }

        for (Int2D loc: Config.liveCellsAtStart) {
            ((Cell) grid.get(loc.x, loc.y)).setState(CellState.ALIVE);
        }
        incrementLiveCells(Config.liveCellsAtStart.size());
    }

    public int getNumLiveCells() {
        return numLiveCells;
    }

    public void setNumLiveCells(int numLiveCells) {
        this.numLiveCells = numLiveCells;
    }

    public int incrementLiveCells(int nb) {
        if (nb <= 0)
            return numLiveCells;

        numLiveCells += nb;

        return numLiveCells;
    }

    public int incrementLiveCells() {
        return incrementLiveCells(1);
    }

    public int decrementLiveCells(int nb) {
        if (nb <= 0)
            return numLiveCells;
        if (nb >= numLiveCells)
            numLiveCells = 0;

        numLiveCells -= nb;

        return numLiveCells;
    }

    public int decrementLiveCells() {
        return decrementLiveCells(1);
    }

    public ObjectGrid2D getGrid() {
        return grid;
    }
}
