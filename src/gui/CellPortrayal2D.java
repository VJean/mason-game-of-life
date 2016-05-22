package gui;

import model.Cell;
import model.CellState;
import sim.portrayal.DrawInfo2D;
import sim.portrayal.simple.OvalPortrayal2D;

import java.awt.*;

public class CellPortrayal2D extends OvalPortrayal2D{
    public CellPortrayal2D() {
        super();
        paint = Color.BLACK;
        filled = true;
    }

    @Override
    public void draw(Object object, Graphics2D graphics, DrawInfo2D info) {
        Cell c = (Cell) object;

        if (c.getState() == CellState.ALIVE)
            this.paint = Color.WHITE;
        else
            this.paint = Color.BLACK;

        super.draw(object, graphics, info);
    }
}
