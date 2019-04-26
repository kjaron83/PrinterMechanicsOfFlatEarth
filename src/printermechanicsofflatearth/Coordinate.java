/*
 * Coordinate.java
 * Create Date: Apr 24, 2019
 * Initial-Author: Janos Aron Kiss
 */

package printermechanicsofflatearth;

import javax.annotation.Nonnull;

/**
 * This class represents a coordinate on FlatEarth. 
 * @version $Revision$ $LastChangedDate$
 * @author $Author$
 */
public class Coordinate {

    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    /**
     * Returns the distance between two coordinates.
     * We assume that people can move in the directions of the axis on FlatEarth.
     * @param coordinate Another coordinate to compare.
     */
    public int distance(@Nonnull Coordinate coordinate) {
        return Math.abs(coordinate.x - x) + Math.abs(coordinate.y - y);
    }
    
}
