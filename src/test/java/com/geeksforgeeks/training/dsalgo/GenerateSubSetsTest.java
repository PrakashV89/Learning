package com.geeksforgeeks.training.dsalgo;

import com.geeksforgeeks.GenerateSubSets;

import org.junit.jupiter.api.Test;

public class GenerateSubSetsTest {
    @Test
    void generateRecursive() {
        GenerateSubSets.generate("abc");
    }

    @Test
    void generateBitwise() {
        GenerateSubSets.generateUsingBitWise("abc");
    }

    @Test
    void generateUsingGFG() {
        GenerateSubSets.generateUsingGFG("abc", 1, "");
    }
}
