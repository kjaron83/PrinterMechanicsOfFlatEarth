/*
 * SafeScanner.java
 * Create Date: Apr 25, 2019
 * Initial-Author: Janos Aron Kiss
 */

package printermechanicsofflatearth;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;

/**
 * This class provides a foolproof implementation to read data from an input stream, while it prints messages to the output stream.
 * @version $Revision$ $LastChangedDate$
 * @author $Author$
 */
public class SafeScanner {

    private static final ResourceBundle bundle = ResourceBundle.getBundle("printermechanicsofflatearth/texts");
    
    private static final String EXIT = bundle.getString("exitString").toLowerCase();
    private static final String EMPTY_INPUT_MESSAGE = String.format(bundle.getString("emptyInputMessage"), EXIT);
    private static final String INT_INPUT_MISMATCH_MESSAGE = String.format(bundle.getString("intInputMismatchMessage"), EXIT);
    private static final String INT_INPUT_IS_TOO_LOW_MESSAGE = bundle.getString("intInputIsTooLowMessage");
    private static final String INT_INPUT_IS_TOO_HIGH_MESSAGE = bundle.getString("intInputIsTooHighMessage");
    private static final String COORDINATE_INPUT_MISMATCH_MESSAGE = String.format(bundle.getString("coordinateInputMismatchMessage"), EXIT);
    
    private final Scanner scanner;
    private final PrintStream printStream;

    public SafeScanner(@Nonnull InputStream inputStream, @Nonnull OutputStream outputStream) {
        scanner = new Scanner(inputStream);
        printStream = new PrintStream(outputStream);
    }
    
    @Nonnull
    public String nextLine() {
        return scanner.nextLine().trim();
    }
    
    /**
     * Reads a new line from the input stream.
     * @throws ReadingInterruptedException if the user would rather exit.
     */
    @Nonnull
    public String nextLineNoEmpty() {
        return nextLineNoEmpty(false);
    }
    
    /**
     * Reads a new line from the input stream.
     * @param checkExit The first time we do not check whether the user wants to exit.
     * @throws ReadingInterruptedException if the user would rather exit.
     */
    @Nonnull
    private String nextLineNoEmpty(boolean checkExit) {
        String line = scanner.nextLine().trim();
        
        if ( line.isEmpty() ) {
            printStream.println(EMPTY_INPUT_MESSAGE);
            return nextLineNoEmpty(true);
        }
        else if ( checkExit && line.toLowerCase().equals(EXIT) )
            throw new ReadingInterruptedException();
        
        return line;
    }
    
    /**
     * Reads a new line from the input stream and convects it to an int value.
     * @throws ReadingInterruptedException if the user would rather exit.
     */    
    public int nextInt() {
        return nextInt(false);
    }

    /**
     * Reads a new line from the input stream and convects it to an int value.
     * @param checkExit The first time we do not check whether the user wants to exit.
     * @throws ReadingInterruptedException if the user would rather exit.
     */
    private int nextInt(boolean checkExit) {
        String line = nextLineNoEmpty();        
        
        if ( line.matches("^\\-?\\d+$") )
            return Integer.parseInt(line);            

        if ( checkExit && line.toLowerCase().equals(EXIT) )
            throw new ReadingInterruptedException();
            
        printStream.println(INT_INPUT_MISMATCH_MESSAGE);
        return nextInt(true);
    }
    
    /**
     * Reads a new line from the input stream and convects it to an int value.
     * @param min Minimum expected value.
     * @param max Maximum expected value.
     * @throws ReadingInterruptedException if the user would rather exit.
     */
    public int nextInt(int min, int max) {
        int n = nextInt();
        
        if ( n < min ) {
            printStream.println(String.format(INT_INPUT_IS_TOO_LOW_MESSAGE, min));
            return nextInt(min, max);
        }
        else if ( n > max ) {
            printStream.println(String.format(INT_INPUT_IS_TOO_HIGH_MESSAGE, max));
            return nextInt(min, max);
        }
        
        return n;
    }

    /**
     * Reads a new line from the input stream and convects it to a {@link Coordinate}.
     * @throws ReadingInterruptedException if the user would rather exit.
     */    
    @Nonnull
    public Coordinate nextCoordinate() {
        return nextCoordinate(false);
    }

    /**
     * Reads a new line from the input stream and convects it to a {@link Coordinate}.
     * @param checkExit The first time we do not check whether the user wants to exit.
     * @throws ReadingInterruptedException if the user would rather exit.
     */
    @Nonnull
    private Coordinate nextCoordinate(boolean checkExit) {
        String line = nextLineNoEmpty();                
        
        Pattern p = Pattern.compile("^(\\-?\\d+)[^\\d]+(\\-?\\d+)$");
        Matcher m = p.matcher(line);
        if ( m.find() )     
            return new Coordinate(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
        
        if ( checkExit && line.toLowerCase().equals(EXIT) )
            throw new ReadingInterruptedException();
        
        printStream.println(COORDINATE_INPUT_MISMATCH_MESSAGE);
        return nextCoordinate(true);
    }

    /**
     * This exception is thrown when the user enters the {@link #EXIT} text.
     */
    public static class ReadingInterruptedException extends RuntimeException {
    }
    
}
