package src;

public class CircularSuffixArray {

    // circular suffix array of s
    public CircularSuffixArray(String s) {

    }

    // length of s
    public int length() {
        return 0;
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        return 0;
    }

    private class CircularSuffix implements Comparable<CircularSuffix> {
        String str;
        int suffixStart;

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
