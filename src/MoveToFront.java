package src;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

import java.util.ArrayList;

public class MoveToFront {
    private static final int RADIX = 256;

    /*
     * Apply move-to-front encoding,
     * reading from standard input and writing to standard output.
     */
    public static void encode() {
        ArrayList<Integer> dictionary = new ArrayList<>(RADIX);
        for (int i = 0; i < RADIX; i++) {
            dictionary.add(i);
        }

        while (!BinaryStdIn.isEmpty()) {
            char currentChar = BinaryStdIn.readChar();
            int charPosition = dictionary.indexOf((int) currentChar);
            char transformedChar = (char) charPosition;
            BinaryStdOut.write(transformedChar);

            /*
             * Moving character to the front. O(RADIX)
             */
            dictionary.remove(charPosition);
            dictionary.add(0, (int) currentChar);
        }
        BinaryStdOut.close();
    }

    /*
     * Apply move-to-front decoding,
     * reading from standard input and writing to standard output
     */
    public static void decode() {
        ArrayList<Character> extendedAscii = new ArrayList<>(RADIX);
        for (int i = 0; i < RADIX; i++) {
            extendedAscii.add((char) (RADIX - 1 - i));
        }

        while (!BinaryStdIn.isEmpty()) {
            int charCode = BinaryStdIn.readChar();
            char decodedChar = extendedAscii.remove(RADIX - 1 - charCode);
            BinaryStdOut.write(decodedChar);
            extendedAscii.add(decodedChar);
        }
        BinaryStdOut.close();
    }

    /*
     * if args[0] is "-", apply move-to-front encoding
     * if args[0] is "+", apply move-to-front decoding
     */
    public static void main(String[] args) {
        if (args[0].equals("-")) {
            MoveToFront.encode();
        }
        else if (args[0].equals("+")) {
            MoveToFront.decode();
        }
    }
}
