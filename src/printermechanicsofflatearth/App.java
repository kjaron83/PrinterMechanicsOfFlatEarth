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
    
    private final InputStream inputStream;
    private final OutputStream outputStream;
    private final SafeScanner scanner;
    private final PrintStream printStream;
    
    public App(@Nonnull InputStream inputStream, @Nonnull OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        
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
    
    private Mechanic[] readMechanics() {
        printStream.println(bundle.getString("numberOfMechanics"));
        int n = scanner.nextInt(1, Integer.MAX_VALUE);
        
        Mechanic[] mechanics = new Mechanic[n];
        for ( int i = 0; i < n; i++ ) {
            printStream.println('\n' + String.format(bundle.getString("nMechanic"), i + 1));            
            mechanics[i] = Mechanic.create(inputStream, outputStream);
        }
        
        return mechanics;
    }
    
    private Coordinate readJobCoordinate() {
        printStream.println('\n' + bundle.getString("jobCoordinate"));
        return scanner.nextCoordinate();
    }
    
    private void printOutCheapestMechanic(@Nonnull Mechanic[] mechanics, @Nonnull Coordinate jobCoordinate) {
        Mechanic cheapestMechanic = Mechanic.cheapest(jobCoordinate, mechanics);
        printStream.println('\n' + bundle.getString("cheapestMechanic") + '\n' + cheapestMechanic.getName());
        printStream.println(bundle.getString("calculatedFee"));
        printStream.println(cheapestMechanic.calcFees(jobCoordinate));        
    }

    @Override
    public void uncaughtException(@Nonnull Thread t, @Nonnull Throwable e) {        
        if ( e instanceof SafeScanner.ReadingInterruptedException)
            printStream.println(bundle.getString("exitMessage"));
        else
            e.printStackTrace(printStream);
        System.exit(0);
    }
    
}
