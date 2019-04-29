/*
 * App.java
 * Create Date: Apr 24, 2019
 * Initial-Author: Janos Aron Kiss
 */
package printermechanicsofflatearth;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ResourceBundle;
import javax.annotation.Nonnull;

/**
 * This class interacts with the user via an input stream and an output stream.
 * @author Janos Aron Kiss
 */
public class App implements UncaughtExceptionHandler {

    private static final ResourceBundle bundle = ResourceBundle.getBundle("printermechanicsofflatearth/texts");
    
    private final SafeScanner scanner;
    private final PrintStream printStream;
    
    public App(@Nonnull InputStream inputStream, @Nonnull OutputStream outputStream) {
        this.scanner = new SafeScanner(inputStream, outputStream);
        this.printStream = new PrintStream(outputStream);        
    }
    
    /**
     * Creates a new instance of the {@link App} and uses the STDIN and STDOUT streams.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        App app = new App(System.in, System.out);
        Thread.setDefaultUncaughtExceptionHandler(app);
        app.execute();
    }
    
    /**
     * Reads the parameters of the calculation, while prints questions to the output stream.
     * In the end, prints out the details of the cheapest mechanic to the output stream.
     */
    private void execute() {
        Mechanic[] mechanics = readMechanics();
        Coordinate jobCoordinate = readJobCoordinate();
        printOutCheapestMechanic(mechanics, jobCoordinate);
    }
    
    /**
     * First, reads the number of the mechanics, then reads the details of each ones.
     * @return The array of the mechanics.
     * @throws ReadingInterruptedException if the user would rather exit.
     */
    @Nonnull
    private Mechanic[] readMechanics() {
        printStream.println(bundle.getString("numberOfMechanics"));
        int n = scanner.nextInt(1, Integer.MAX_VALUE);
        
        Mechanic[] mechanics = new Mechanic[n];
        for ( int i = 0; i < n; i++ ) {
            printStream.println('\n' + String.format(bundle.getString("nMechanic"), i + 1));            
            mechanics[i] = createMechanic();
        }
        
        return mechanics;
    }
    
    /**
     * Reads the details of a mechanic from the input stream, while prints questions to the output stream.
     * In the end, creates a new mechanic.
     * @return The created mechanic.
     * @throws ReadingInterruptedException if the user would rather exit.
     */
    @Nonnull
    private Mechanic createMechanic() {
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
     * Reads the coordinate of the job from the input stream, while prints questions to the output stream.
     * @return The coordinate of the job.
     * @throws ReadingInterruptedException if the user would rather exit.
     */
    @Nonnull
    private Coordinate readJobCoordinate() {
        printStream.println('\n' + bundle.getString("jobCoordinate"));
        return scanner.nextCoordinate();
    }
    
    /**
     * Prints out name of the cheapest mechanic and the calculated fee of the job.
     * @param mechanics Non null, non empty array of mechanics which does not contains Null either.
     * @param jobCoordinate The coordinate of the job.
     * @throws IllegalStateException If mechanics array is empty.
     */
    private void printOutCheapestMechanic(@Nonnull Mechanic[] mechanics, @Nonnull Coordinate jobCoordinate) {
        Mechanic cheapestMechanic = Mechanic.cheapest(jobCoordinate, mechanics);
        printStream.println('\n' + bundle.getString("cheapestMechanic") + '\n' + cheapestMechanic.getName());
        printStream.println(bundle.getString("calculatedFee"));
        printStream.println(cheapestMechanic.calcFees(jobCoordinate));        
    }

    /**
     * If the exception is a {@link SafeScanner.ReadingInterruptedException}, prints out an exit message to the output stream.
     * Otherwise prints out the exception and its backtrace to the output stream.
     * @param t the thread
     * @param e the exception
     */
    @Override
    public void uncaughtException(@Nonnull Thread t, @Nonnull Throwable e) {        
        if ( e instanceof SafeScanner.ReadingInterruptedException)
            printStream.println(bundle.getString("exitMessage"));
        else
            e.printStackTrace(printStream);
        System.exit(0);
    }
    
}
