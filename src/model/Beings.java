package model;

import sim.engine.SimState;
import sim.engine.Stoppable;
import sim.field.grid.ObjectGrid2D;
import sim.util.Int2D;

public class Beings extends SimState {
    private static int GRID_SIZE = 100;

    private ObjectGrid2D grid = new ObjectGrid2D(GRID_SIZE, GRID_SIZE);

    public Beings(long seed) {
        super(seed);
    }

    private int numLiveCells;

    public void start() {
        System.out.println("Simulation started");
        super.start();
        grid.clear();
        setNumLiveCells(0);
        addCells();
    }

    private void addCells() {

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                Int2D location = new Int2D(i, j);

                Cell cell = new Cell(location, CellState.DEAD);

                Stoppable stoppable = schedule.scheduleRepeating(cell);

                cell.setStoppable(stoppable);

                grid.set(cell.getLocation().getX(), cell.getLocation().getY(), cell);
            }
        }
    }

    public int getNumLiveCells() {
        return numLiveCells;
    }

    public void setNumLiveCells(int numLiveCells) {
        this.numLiveCells = numLiveCells;
    }

    public ObjectGrid2D getGrid() {
        return grid;
    }
}
