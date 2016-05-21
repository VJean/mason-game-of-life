package model;

import sim.engine.SimState;
import sim.engine.Steppable;
import sim.engine.Stoppable;
import sim.field.grid.Grid2D;
import sim.field.grid.ObjectGrid2D;
import sim.util.Int2D;

public class Cell implements Steppable {
    private CellState state;

    private final Int2D location;
    private Stoppable stoppable;

    public Cell(Int2D location, CellState state) {
        this.location = location;
        this.state = state;
    }

    @Override
    public void step(SimState simState) {
        Beings beings = (Beings) simState;

        ObjectGrid2D grid = beings.getGrid();

        grid.getMooreNeighbors(location.x, location.y, 1, Grid2D.TOROIDAL, false);

    }

    public Stoppable getStoppable() {
        return stoppable;
    }

    public final Int2D getLocation() {
        return location;
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    public void setStoppable(Stoppable stoppable) {
        this.stoppable = stoppable;
    }
}