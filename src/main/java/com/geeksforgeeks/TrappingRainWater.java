package com.geeksforgeeks;

public class TrappingRainWater {
    public static void main(String[] args) {
        int[] arr = { 2, 0, 1, 2 };
        System.out.println(unitsTrappedUsingGFGIterative_TC_ThetaN(arr));
        arr = new int[] { 2, 0, 2 };
        System.out.println(unitsTrappedUsingGFGIterative_TC_ThetaN(arr));
        arr = new int[] { 2, 2 };
        System.out.println(unitsTrappedUsingGFGIterative_TC_ThetaN(arr));
        arr = new int[] { 3, 0, 1, 2, 5 };
        System.out.println(unitsTrappedUsingGFGIterative_TC_ThetaN(arr));
        arr = new int[] { 2, 0, 2, 2, 0, 2 };
        System.out.println(unitsTrappedUsingGFGIterative_TC_ThetaN(arr));
        arr = new int[] { 3, 5, 2, 3 };
        System.out.println(unitsTrappedUsingGFGIterative_TC_ThetaN(arr));
    }

    private static int unitsTrappedUsingIteration(int[] arr) {
        int unitsTrapped = 0;

        int wallA = arr[0];
        int wallB = arr[arr.length - 1];
        int wallIndexA = 0;
        int wallIndexB = arr.length - 1;
        while (wallIndexA <= wallIndexB) {
            if (wallIndexA == wallIndexB) {
                if (arr[wallIndexA] < wallA) {
                    unitsTrapped += Math.min(wallA, wallB) - arr[wallIndexA];
                    System.out.println(Math.min(wallA, wallB) - arr[wallIndexA] + " units trapped in " + wallIndexA);
                } else {
                    System.out.println("WallA being shifted from " + wallA + " to " + arr[wallIndexA]);
                    wallA = arr[wallIndexA];
                }
            } else {
                if (arr[wallIndexA] < wallA) {
                    unitsTrapped += Math.min(wallA, wallB) - arr[wallIndexA];
                    System.out.println(Math.min(wallA, wallB) - arr[wallIndexA] + " units trapped in " + wallIndexA);

                } else {
                    System.out.println("WallA being shifted from " + wallA + " to " + arr[wallIndexA]);
                    wallA = arr[wallIndexA];
                }

                if (arr[wallIndexB] < wallB) {
                    unitsTrapped += Math.min(wallA, wallB) - arr[wallIndexB];
                    System.out.println(Math.min(wallA, wallB) - arr[wallIndexB] + " units trapped in " + wallIndexB);
                } else {
                    System.out.println("WallB being shifted from " + wallB + " to " + arr[wallIndexB]);
                    wallB = arr[wallIndexB];
                }
            }

            ++wallIndexA;
            --wallIndexB;
        }

        return unitsTrapped;
    }

    static int unitsTrappedUsingGFGIterative_TC_ONSquared(int[] arr) {

        int unitsTrapped = 0;
        for (int i = 0; i < arr.length; i++) {
            int leftBoundary = arr[i];
            for (int j = 0; j < i; j++) {
                leftBoundary = Math.max(leftBoundary, arr[j]);
            }
            int rightBoundary = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                rightBoundary = Math.max(rightBoundary, arr[j]);
            }

            unitsTrapped += Math.min(leftBoundary, rightBoundary) - arr[i];
        }

        return unitsTrapped;
    }

    static int unitsTrappedUsingGFGIterative_TC_ThetaN(int[] arr) {

        int unitsTrapped = 0;

        int[] lmax = new int[arr.length];
        int[] rmax = new int[arr.length];

        lmax[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            lmax[i] = Math.max(lmax[i - 1], arr[i]);
        }

        rmax[arr.length - 1] = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            rmax[i] = Math.max(rmax[i + 1], arr[i]);
        }

        for (int i = 1; i <= arr.length - 2; i++) {
            unitsTrapped += (Math.min(lmax[i], rmax[i]) - arr[i]);
        }

        return unitsTrapped;
    }
}
