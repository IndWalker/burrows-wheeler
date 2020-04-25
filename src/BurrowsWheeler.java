package src;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {

    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output
    public static void transform() {
        while (!BinaryStdIn.isEmpty()) {
            String text = BinaryStdIn.readString();
            CircularSuffixArray suffixArray = new CircularSuffixArray(text);
            int originalStringIndex = 0;
            for (int i = 0; i < suffixArray.length(); i++) {
                if (suffixArray.index(i) == 0) {
                    originalStringIndex = i;
                    break;
                }
            }

            BinaryStdOut.write(originalStringIndex);
            for (int i = 0; i < suffixArray.length(); i++) {
                int originalSuffixIndex = suffixArray.index(i);
                if (originalSuffixIndex == 0)
                    BinaryStdOut.write(text.charAt(text.length() - 1));
                else
                    BinaryStdOut.write(text.charAt(originalSuffixIndex - 1));
            }
        }
        BinaryStdOut.close();
    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform() {

    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if (args[0].equals("-"))
            BurrowsWheeler.transform();
    }
}
