package src;

import java.util.Arrays;

/**
 * Circular suffix array data structure implementation.
 * Critical component of Burrows-Wheeler transform.
 */
public class CircularSuffixArray {

    private CircularSuffix[] circularSuffixes;

    /**
     * Circular suffix array of string s creation.
     */
    public CircularSuffixArray(String s) {
        if (s == null)
            throw new IllegalArgumentException("Constuctor argument must not be null");

        circularSuffixes = new CircularSuffix[s.length()];
        for (int i = 0; i < s.length(); i++) {
            circularSuffixes[i] = new CircularSuffix(s, i);
        }
        Arrays.sort(circularSuffixes);
    }

    /**
     * Length of string s.
     */
    public int length() {
        return circularSuffixes.length;
    }

    /**
     * Returns index of ith sorted suffix.
     */
    public int index(int i) {
        if (i < 0 || i > circularSuffixes.length - 1)
            throw new IllegalArgumentException("Suffix array index out of bounds");

        return circularSuffixes[i].suffixStart;
    }

    private class CircularSuffix implements Comparable<CircularSuffix> {
        private String str;
        private int suffixStart;

        public CircularSuffix(String str, int suffixStart) {
            this.str = str;
            this.suffixStart = suffixStart;
        }

        public int compareTo(CircularSuffix other) {
            String thisSuffix = this.str.substring(this.suffixStart);
            String otherSuffix = other.str.substring(other.suffixStart);
            return thisSuffix.compareTo(otherSuffix);
        }
    }
}
