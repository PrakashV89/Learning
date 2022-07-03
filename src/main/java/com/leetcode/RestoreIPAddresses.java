package com.leetcode;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RestoreIPAddresses {
    public static void main(String[] args) throws InterruptedException {

        String[] inputs = {"25525511135", "0000", "101023"};

//        Thread.sleep(10000);

        Instant now = Instant.now();
        BacktrackSolution solution1 = new BacktrackSolution();
        for (String input : inputs) {
            System.out.println(solution1.restoreIpAddresses(input));
        }

        System.out.println(Duration.between(now, Instant.now()).toMillis() + " ms");

//        Thread.sleep(10000);
//        now = Instant.now();
//        Solution solution = new Solution();
//        for (String input : inputs) {
//            System.out.println(solution.restoreIpAddresses(input));
//        }
//        System.out.println(Duration.between(now, Instant.now()).toMillis() + " ms");

    }

}

class Solution {
    int n;
    String s;
    LinkedList<String> segments = new LinkedList<String>();
    ArrayList<String> output = new ArrayList<String>();

    public boolean valid(String segment) {
    /*
    Check if the current segment is valid :
    1. less or equal to 255
    2. the first character could be '0'
    only if the segment is equal to '0'
    */
        int m = segment.length();
        if (m > 3) return false;
        return (segment.charAt(0) != '0') ? (Integer.valueOf(segment) <= 255) : (m == 1);
    }

    public void update_output(int curr_pos) {
    /*
    Append the current list of segments
    to the list of solutions
    */
        String segment = s.substring(curr_pos + 1, n);
        if (valid(segment)) {
            segments.add(segment);
            output.add(String.join(".", segments));
            segments.removeLast();
        }
    }

    public void backtrack(int prev_pos, int dots) {
    /*
    prev_pos : the position of the previously placed dot
    dots : number of dots to place
    */
        // The current dot curr_pos could be placed
        // in a range from prev_pos + 1 to prev_pos + 4.
        // The dot couldn't be placed
        // after the last character in the string.
        int max_pos = Math.min(n - 1, prev_pos + 4);
        System.out.println(prev_pos + " : " + max_pos);
        for (int curr_pos = prev_pos + 1; curr_pos < max_pos; curr_pos++) {
            String segment = s.substring(prev_pos + 1, curr_pos + 1);
            if (valid(segment)) {
                segments.add(segment);  // place dot
                if (dots - 1 == 0)      // if all 3 dots are placed
                    update_output(curr_pos);  // add the solution to output
                else backtrack(curr_pos, dots - 1);  // continue to place dots
                segments.removeLast();  // remove the last placed dot
            }
        }
    }

    public List<String> restoreIpAddresses(String s) {
        n = s.length();
        this.s = s;
        backtrack(-1, 3);
        return output;
    }
}

class Solution1 {

    private static char DOT = '.';
    private static char ZERO = '0';
    private static String ZERO_STR = ZERO + "";
    private static int LOWER_BOUND_INCL = 0;
    private static int UPPER_BOUND_INCL = 255;
    private static int MAX_BUCKET = 3;
    private static int MULTIPLIER = 10;


    private String s;
    private int strLength;
    private List<String> ipAddresses = new LinkedList<>();

    public List<String> restoreIpAddresses(String s) {
        this.s = s;
        this.strLength = s.length();

        generate(0, 0, "", "", 0, 0, 0);

        return ipAddresses;
    }

    public void generate(int start, int bucketNum, String ipAddress, String bucket, int length, int bucketLength, int bucketVal) {
        if (start >= strLength) {
            if (bucketNum == MAX_BUCKET && !bucket.isEmpty() && length == strLength) {
                ipAddresses.add(ipAddress);
            }
            return;
        }

        char char_ = s.charAt(start);
        String nextStr = bucket + char_;
        int nextBucketVal = bucketVal * MULTIPLIER + char_ - ZERO;
        String nextIpAddress = ipAddress + char_;

        if (!(bucketNum > MAX_BUCKET || (nextStr.length() > 1 && nextStr.startsWith(ZERO_STR)) || nextBucketVal > UPPER_BOUND_INCL)) {
            generate(start + 1, bucketNum, nextIpAddress, nextStr, length + 1, bucketLength + 1, nextBucketVal);
        }
        if (bucketNum < MAX_BUCKET && nextBucketVal <= UPPER_BOUND_INCL && (nextStr.length() <= 1 || !nextStr.startsWith(ZERO_STR))) {
            generate(start + 1, bucketNum + 1, nextIpAddress + DOT, "", length + 1, 0, 0);
        }
    }

}

class BacktrackSolution {
    private LinkedList<String> segments = new LinkedList<>();
    private List<String> ipAddresses = new ArrayList<>();
    private String s;
    private int sLength;

    public List<String> restoreIpAddresses(String s) {
        this.s = s;
        this.sLength = s.length();

        backtrack(-1, 3);
        return ipAddresses;
    }

    private void backtrack(int start, int dots) {
        if (start >= sLength) {
            return;
        }
        int max_pos = Math.min(sLength - 1, start + 4);
        for (int currPos = start + 1; currPos < max_pos; currPos++) {
            String segment = s.substring(start + 1, currPos + 1);
            if (isValid(segment)) {
                segments.add(segment);

                if (dots - 1 == 0) {
                    addIpAddressConditionally(currPos);
                } else {
                    backtrack(currPos, dots - 1);
                }

                segments.removeLast();
            }
        }
    }

    private void addIpAddressConditionally(int currPos) {
        String segment = s.substring(currPos + 1, sLength);

        if (isValid(segment)) {
            segments.add(segment);
            ipAddresses.add(String.join(".", segments));
            segments.removeLast();
        }
    }

    private boolean isValid(String segment) {
        int segmentLength = segment.length();
        int segmentVal = Integer.parseInt(segment);

        return (!segment.startsWith("0") || segmentLength == 1) && segmentVal >= 0 && segmentVal <= 255;
    }
}
