package model;

import sim.engine.SimState;
import sim.engine.Steppable;
import sim.engine.Stoppable;
import sim.util.Int2D;

public class Cell implements Steppable {
    private CellState state;

    private final Int2D location;
    private Stoppable stoppable;

    public Cell(Int2D location, CellState state) {
        this.location = location;
        this.state = state;
        this.stoppable = stoppable;
    }

    @Override
    public void step(SimState simState) {
        Beings beings = (Beings) simState;
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