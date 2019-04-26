/*
 * Mechanic.java
 * Create Date: Apr 24, 2019
 * Initial-Author: Janos Aron Kiss
 */

package printermechanicsofflatearth;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ResourceBundle;
import javax.annotation.Nonnull;

/**
 * This class represents a capable mechanic on FlatEarth. 
 * @version $Revision$ $LastChangedDate$
 * @author $Author$
 */
public class Mechanic {

    private static final ResourceBundle bundle = ResourceBundle.getBundle("printermechanicsofflatearth/texts");

    private final String name;
    private final int fixedFee;
    private final int kilometerFee;
    private final Coordinate officeCoordinate;

    public Mechanic(@Nonnull String name, int fixedFee, int kilometerFee, @Nonnull Coordinate office) {
        this.name = name;
        this.fixedFee = fixedFee;
        this.kilometerFee = kilometerFee;
        this.officeCoordinate = office;
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
    public Coordinate getOfficeCoordinate() {
        return officeCoordinate;
    }

    public int calcFees(@Nonnull Coordinate coordinate) {
        return fixedFee + officeCoordinate.distance(coordinate) * kilometerFee;
    }

    /**
     * Reads the details of the mechanic from the input stream, while prints questions to the output stream.
     * In the end, creates a new mechanic.
     * @param inputStream Stream to read from.
     * @param outputStream Stream to print to.
     * @return The created mechanic.
     */
    @Nonnull
    public static Mechanic create(@Nonnull InputStream inputStream, @Nonnull OutputStream outputStream) {
        SafeScanner scanner = new SafeScanner(inputStream, outputStream);
        PrintStream printStream = new PrintStream(outputStream);
        
        printStream.println(bundle.getString("mechanicsName"));
        String name = scanner.nextLineNoEmpty();
        printStream.println(bundle.getString("fixedFee"));
        int fixedFee = scanner.nextInt(0, Integer.MAX_VALUE);
        printStream.println(bundle.getString("kilometerFee"));
        int kilometerFee = scanner.nextInt(0, Integer.MAX_VALUE);
        printStream.println(bundle.getString("officeCoordinate"));
        Coordinate office = scanner.nextCoordinate();
        
        return new Mechanic(name, fixedFee, kilometerFee, office);
    }        
    
    /**
     * Returns the cheapest mechanic according to the passed coordinate.
     * @param coordinate Where the job located.
     * @param mechanics Nonnull array of mechanics which does not contains Null either.
     * @return The cheapest mechanic.
     */
    public static Mechanic cheapest(@Nonnull Coordinate coordinate, @Nonnull Mechanic... mechanics) {
        if ( mechanics.length == 0 )
            return null;
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
