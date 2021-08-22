package com.geeksforgeeks;

import static java.lang.System.out;

public class BuySellStock {
    public static void main(String[] args) {
        out.println(getMaxProfitUsingIterationGFG(new int[] { 1, 5, 3, 8, 12 }));
        out.println(getMaxProfitUsingIterationGFG(new int[] { 30, 20, 10 }));
        out.println(getMaxProfitUsingIterationGFG(new int[] { 10, 20, 30 }));
        out.println(getMaxProfitUsingIterationGFG(new int[] { 1, 5, 3, 1, 2, 8 }));
    }

    /***
     * My Implementation Time Complexity : Linear - Theta(n) Auxillary Space :
     * Constant - Theta(1)
     */
    static int getMaxProfit(int[] arr) {

        int sell = Integer.MIN_VALUE;
        int buy = Integer.MAX_VALUE;
        int profit = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < sell && buy != Integer.MAX_VALUE) {
                out.println("Selling @ Price : " + sell);
                profit += sell - buy;
                sell = Integer.MIN_VALUE;
                buy = Integer.MAX_VALUE;
                out.println("Profit @ Price : " + profit);
            }
            if ((buy == Integer.MAX_VALUE || arr[i] < buy) && sell == Integer.MIN_VALUE) {
                buy = arr[i];
                out.println("Buying @ Price : " + buy);
            } else if (buy != Integer.MAX_VALUE && arr[i] > sell) {
                sell = arr[i];
            }

        }

        if (buy != Integer.MAX_VALUE && sell != Integer.MIN_VALUE) {
            out.println("Selling @ Price : " + sell);
            profit += sell - buy;
            out.println("Profit @ Price : " + profit);
        }

        return profit;
    }

    private static int getMaxProfitUsingRecursiveGFG(int[] price) {
        return getMaxProfitUsingRecursiveGFG(price, 0, price.length, "");
    }

    static int getMaxProfitUsingRecursiveGFG(int[] price, int start, int end, String tabs) {
        if (end <= start) {
            return 0;
        }
        int profit = 0;
        for (int i = start; i < end; i++) {
            for (int j = i + 1; j < end; j++) {
                if (price[j] > price[i]) {
                    out.println(tabs + "i : " + i + " price : " + price[i]);
                    out.println(tabs + "j : " + j + " price : " + price[j]);
                    out.println(tabs + "price[j] - price[i] : " + (price[j] - price[i]));
                    int currProfit = price[j] - price[i]
                            + getMaxProfitUsingRecursiveGFG(price, start, i - 1, tabs + "\t")
                            + getMaxProfitUsingRecursiveGFG(price, j + 1, end, tabs + "\t");
                    out.println(tabs + "Current Profit : " + currProfit);
                    profit = Math.max(profit, currProfit);
                }
            }
        }

        return profit;
    }

    static int getMaxProfitUsingIterationGFG(int[] price) {
        int profit = 0;
        for (int i = 1; i < price.length; i++) {
            if (price[i] > price[i - 1]) {
                profit += price[i] - price[i - 1];
            }
        }

        return profit;
    }
}
