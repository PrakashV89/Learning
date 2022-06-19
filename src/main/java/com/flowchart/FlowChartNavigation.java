package com.flowchart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlowChartNavigation {

    public static final int NODE_PRESENT = 1;

    public static void main(String[] args) {
        int m = 6;
        int n = 5;
        int[][] grid = new int[m][n];
        grid[1][2] = 1;
        grid[3][2] = 1;
        grid[4][4] = 1;


        int[] from = new int[]{5, 2};
        int[] to = new int[]{2, 2};
        int[] bk = Arrays.copyOf(from, from.length);

        int row = 0;
        int col = 1;
        List<int[]> coords = new ArrayList<>();
        coords.add(bk);
        while(from[row] != to[row] || from[col] != to[col]){
            if(from[row] < to[row]){
                if(from[col] == to[col]){
                    if(isRowAvailableInGrid(grid, from[row]+1) && isNodeNotAvailableInGridRowCol(grid, from[row] + 1, from[col])
                            ||
                            isDestinationNodeReached(from[row]+1, to[row], from[col], to[col])){
                        from[row] += 1;
                    }
                }
                else if(from[col] < to[col]){
                    if(isRowAvailableInGrid(grid, from[row]+1) && isNodeNotAvailableInGridRowCol(grid, from[row]+1, from[col])){
                        from[row] += 1;
                    }
                    else if(isColAvailableInGridForRow(grid, from[col]+1, from[row]) && grid[from[row]][from[col]+1] != NODE_PRESENT){
                        from[col] += 1;
                    }
                }
//                System.out.println(Arrays.toString(from));
            }
            else if(from[row] == to[row]){
                if(from[col] < to[col]){
                    if(isColAvailableInGridForRow(grid, from[col]+1, from[row]) && (
                            isNodeNotAvailableInGridRowCol(grid, from[row], from[col]+1)
                            ||
                            isDestinationNodeReached(from[row] , to[row], from[col]+1, to[col]))){
                        from[col] += 1;
                    }
                    else if(
                            from[row]-1 > 0 &&
                            from[row]-1 < grid.length &&
                            from[col]+1 < grid[from[row]].length &&
                            isNodeNotAvailableInGridRowCol(grid, from[row]-1, from[col] + 1)
                            ){
                            from[row] -= 1;
                            from[col] += 1;
                    }
                    else if(isRowAvailableInGrid(grid, from[row]+1) && (
                            isNodeNotAvailableInGridRowCol(grid, from[row]+1, from[col]))){
                        from[row] += 1;
                    }
                }
//                System.out.println(Arrays.toString(from));
            }
            else if(from[row] > to[row]){
                if(from[col] == to[col]){
                    if(isRowAvailableInGrid(grid, from[row]-1) && (isNodeNotAvailableInGridRowCol(grid, from[row] - 1, from[col])
                            ||
                            isDestinationNodeReached(from[row]-1, to[row], from[col], to[col]))){
                        from[row] -= 1;
                    }
                    else if(from[col] - 1 > 0
                            && (isColAvailableInGridForRow(grid, from[row], from[col]-1)
                                ||
                                isDestinationNodeReached(from[row], from[col] - 1, to[row], to[col]))){
                        from[col] -= 1;
                    }
                }
                else if(from[col] < to[col]){

                    if(isColAvailableInGridForRow(grid, from[col]+1, from[row]) && grid[from[row]][from[col]+1] != NODE_PRESENT){
                        from[col] += 1;
                    }
                    else if(isRowAvailableInGrid(grid, from[row]-1) && isNodeNotAvailableInGridRowCol(grid, from[row]-1, from[col])){
                        from[row] -= 1;
                    }
                }
                else if(from[col] > to[col]){
                    if(isRowAvailableInGrid(grid, from[row]-1) && isNodeNotAvailableInGridRowCol(grid, from[row]-1, from[col])){
                        from[row] -= 1;
                    }
                    else if(isColAvailableInGridForRow(grid, from[col]-1, from[row]) && grid[from[row]][from[col]-1] != NODE_PRESENT){
                        from[col] -= 1;
                    }
                }
//                System.out.println(Arrays.toString(from));
            }
            System.out.println(Arrays.toString(from));
            grid[from[row]][from[col]] = -1;
            coords.add(Arrays.copyOf(from, from.length));
        }

        System.out.println("Reached : " + Arrays.toString(from));

        String[][] resultGrid = new String[m][n];


        for (int i = 0; i < resultGrid.length; i++) {
            for (int j = 0; j < resultGrid[i].length; j++) {

            if(grid[i][j] == 1){
                    resultGrid[i][j] = "C";
            }
            else {
                resultGrid[i][j] = "0";
            }
            }
        }

        for (int[] coord : coords) {
            if(coord[row] == bk[row] && coord[col] == bk[col]){
                resultGrid[coord[row]][coord[col]] = "S";
            }
            else if(coord[row] == to[row] && coord[col] == to[col]){
                resultGrid[coord[row]][coord[col]] = "D";
            }
            else{
                resultGrid[coord[row]][coord[col]] = ".";
            }

        }

        for (int i = 0; i < resultGrid.length; i++) {
            for (int j = 0; j < resultGrid[i].length; j++) {
                System.out.print(resultGrid[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isDestinationNodeReached(int from_row, int to_row, int from_col, int to_col){
        return from_row == to_row && from_col == to_col;
    }

    private static boolean isNodeNotAvailableInGridRowCol(int[][] grid, int from_row, int from_col) {
        return grid[from_row][from_col] != NODE_PRESENT;
    }

    private static boolean isRowAvailableInGrid(int[][] grid, int from_row) {
        return from_row < grid.length;
    }

    private static boolean isColAvailableInGridForRow(int[][] grid, int from_row, int from_col){
        return from_col < grid[from_row].length;
    }
}
