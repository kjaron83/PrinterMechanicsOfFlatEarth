/*
 * Mechanic.java
 * Create Date: Apr 24, 2019
 * Initial-Author: Janos Aron Kiss
 */

package printermechanicsofflatearth;

import javax.annotation.Nonnull;

/**
 * This class represents a capable mechanic on FlatEarth. 
 * @version $Revision$ $LastChangedDate$
 * @author $Author$
 */
public class Mechanic {

    private final String name;
    private final int fixedFee;
    private final int kilometerFee;
    private final Coordinate office;

    public Mechanic(@Nonnull String name, int fixedFee, int kilometerFee, @Nonnull Coordinate office) {
        this.name = name;
        this.fixedFee = fixedFee;
        this.kilometerFee = kilometerFee;
        this.office = office;
    }
    
    @Nonnull
    public String getName() {
        return name;
    }

    public int getFixedFee() {
        return fixedFee;
    }

    public int getKilometerFee() {
        return kilometerFee;
    }

    @Nonnull
    public Coordinate getOffice() {
        return office;
    }

    public int calcFees(@Nonnull Coordinate coordinate) {
        return fixedFee + office.distance(coordinate) * kilometerFee;
    }
    
    /**
     * Returns the cheapest mechanic according to the passed coordinate.
     * @param coordinate Where the job located.
     * @param mechanics Non null, non empty array of mechanics which does not contains Null either.
     * @return The cheapest mechanic or null if mechanics array was empty.
     * @throws IllegalStateException If mechanics array is empty.
     */
    @Nonnull
    public static Mechanic cheapest(@Nonnull Coordinate coordinate, @Nonnull Mechanic... mechanics) {
        if ( mechanics.length == 0 )
            throw new IllegalStateException("Mechanics cannot be empty.");
        if ( mechanics.length == 1 )
            return mechanics[0];
        
        Mechanic cheapest = mechanics[0];
        int cheapestFees = cheapest.calcFees(coordinate);
        int fees;
        for ( int i = 1; i < mechanics.length; i++ ) {
            fees = mechanics[i].calcFees(coordinate);
            if ( fees < cheapestFees ) {
                cheapestFees = fees;
                cheapest = mechanics[i];
            }
        }
        
        return cheapest;
    }
    
}
