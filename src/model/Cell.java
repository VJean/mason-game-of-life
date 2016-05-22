package model;

import sim.engine.SimState;
import sim.engine.Steppable;
import sim.engine.Stoppable;
import sim.field.grid.Grid2D;
import sim.field.grid.ObjectGrid2D;
import sim.util.Bag;
import sim.util.Int2D;

import java.util.Collection;

public class Cell implements Steppable {
    private CellState state;

    private final Int2D location;
    private Stoppable stoppable;
    private Step step = Step.COMPUTE_NEXT_STATE;

    private CellState stateAtNexStep;

    public Cell(Int2D location, CellState state) {
        this.location = location;
        this.state = state;
        this.stateAtNexStep = state;
    }

    @Override
    public void step(SimState simState) {
        if (step == Step.CHANGE_STATE) {
            state = stateAtNexStep;
            step = Step.COMPUTE_NEXT_STATE;
        } else if (step == Step.COMPUTE_NEXT_STATE) {
            Beings beings = (Beings) simState;

            ObjectGrid2D grid = beings.getGrid();

            Bag neighbors = grid.getMooreNeighbors(location.x, location.y, 1, Grid2D.TOROIDAL, false);


            long aliveNeighborsCount = neighbors.stream().filter(o -> ((Cell) o).getState() == CellState.ALIVE).count();

            // Any live cell with fewer than two live neighbours dies
            // Any live cell with more than three live neighbours dies
            // Any live cell with two or three live neighbours lives, unchanged, to the next generation.
            // Any dead cell with exactly three live neighbours will come to life.

            if (state == CellState.DEAD && aliveNeighborsCount == 3) {
                stateAtNexStep = CellState.ALIVE;
                beings.incrementLiveCells();
            } else if (state == CellState.ALIVE && (aliveNeighborsCount < 2 || aliveNeighborsCount > 3)) {
                stateAtNexStep = CellState.DEAD;
                beings.decrementLiveCells();
            } else {
                stateAtNexStep = state;
            }

            step = Step.CHANGE_STATE;
        }
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
        this.stateAtNexStep = state;
    }

    public void setStoppable(Stoppable stoppable) {
        this.stoppable = stoppable;
    }

    private enum Step { CHANGE_STATE, COMPUTE_NEXT_STATE }
}