package src;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {
    private static final int ASCII_RADIX = 256;

    /**
     * Apply Burrows-Wheeler transform,
     * reading from standard input and writing to standard output.
     */
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

    /**
     * Apply Burrows-Wheeler inverse transform,
     * reading from standard input and writing to standard output
     */
    public static void inverseTransform() {
        while (!BinaryStdIn.isEmpty()) {
            int originalStringIndex = BinaryStdIn.readInt();
            String transformedString = BinaryStdIn.readString();

            /*
             * Partial key-indexed counting sort algorithm.
             * Here can be used other sorting algorithms but for this particular task
             * counting sort is the most effective one with complexity O(n).
             * Here "partial" algorithm consists of 3 parts and isn't fully identical to original.
             *
             * next[i] is the row in sorted order where the (j+1)st original suffix appears.
             * count[i+1] is the count of character with i-th code.
             */
            int[] next = new int[transformedString.length()];
            int[] count = new int[ASCII_RADIX + 1];
            /*
             * Counting part
             */
            for (int i = 0; i < transformedString.length(); i++) {
                count[transformedString.charAt(i) + 1]++;
            }
            /*
             * Calculating cumulative counts part.
             * Tells how the keys will be distributed.
             */
            for (int i = 1; i < count.length - 1; i++) {
                count[i + 1] += count[i];
            }
            /*
             * Final part. Consists of several steps:
             * 1) Getting sorted order position of i-th char
             * 2) Shifting in order to put the next same char to the next cell
             * 3) filling "next" with (j+1)st original suffix position
             */
            for (int i = 0; i < transformedString.length(); i++) {
                int sortedOrderPosition = count[transformedString.charAt(i)];
                count[transformedString.charAt(i)]++;
                next[sortedOrderPosition] = i;
            }

            int currentIndex = originalStringIndex;
            for (int i = 0; i < next.length - 1; i++) {
                char origStringChar = transformedString.charAt(next[currentIndex]);
                BinaryStdOut.write(origStringChar);
                currentIndex = next[currentIndex];
            }
            BinaryStdOut.write(transformedString.charAt(originalStringIndex));
        }
        BinaryStdOut.close();
    }

    /**
     * if args[0] is "-", apply Burrows-Wheeler transform
     * if args[0] is "+", apply Burrows-Wheeler inverse transform
     */
    public static void main(String[] args) {
        if (args[0].equals("-"))
            BurrowsWheeler.transform();
        else if (args[0].equals("+"))
            BurrowsWheeler.inverseTransform();
    }
}
