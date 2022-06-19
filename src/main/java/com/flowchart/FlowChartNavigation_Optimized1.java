package com.flowchart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FlowChartNavigation_Optimized1 {

    public static final int NODE_PRESENT = 1;
    public static final int VISITED = -1;


    static int row = 0;
    static int col = 1;

    public static void main(String[] args) {
//        int x=20;
//
//        String ans = (x < 15)
//                ?
//                "Small"
//                :
//                (x < 22)
//                        ?
//                        "Medium" : "Large";
//
//        System.out.println(ans);
//        System.out.print(" " + 7 + 2 + " ");
//        System.out.print(7 + 2 + " ");

//        System.out.println(true&false);
//        int test[] = { 65, 66, 67, 68};
//
//        String s = new String(test, 1, 3);
//
//        System.out.println(s);
        int arr1[] = { 65, 66, 67, 68};

        String s = new String(arr1, 1, 3);

        System.out.println(s);

        int arr[][] = new int[3][];
        int m = 6;
        int n = 5;
        int[][] grid = new int[m][n];
        grid[1][2] = 1;
        grid[3][2] = 1;
//        grid[2][4] = 1;
        grid[4][4] = 1;
        grid[0][0] = 1;
        grid[1][0] = 1;
        grid[1][1] = 1;
        grid[2][3] = 1;


        int[] to = new int[]{0, 4};
        int[] from = new int[]{5, 0};
        int[] bk = Arrays.copyOf(from, from.length);

        int row = 0;
        int col = 1;
        System.out.println("Start : " + Arrays.toString(from));
        System.out.println("end : " + Arrays.toString(to));
        System.out.println();

        System.out.println(Arrays.toString(bk));
        List<int[]> coords = new ArrayList<>();
        coords.add(bk);
//        while (from[row] != to[row] || from[col] != to[col]) {
        int[][] tmpGrid = getGrid1(grid);
        List<int[]> result = goDirection(from, to, getGrid1(tmpGrid), Direction.DOWN, getCoors(coords));
        tmpGrid = getGrid1(grid);
        List<int[]> result_ = goDirection(from, to, getGrid1(tmpGrid), Direction.UP, getCoors(coords));
        tmpGrid = getGrid1(grid);
        result = getSize(result) > getSize(result_) ? result_ : result;
        result_ = goDirection(from, to, getGrid1(tmpGrid), Direction.LEFT, getCoors(coords));
        tmpGrid = getGrid1(grid);
        result = getSize(result) > getSize(result_) ? result_ : result;
        result_ = goDirection(from, to, getGrid1(tmpGrid), Direction.RIGHT, getCoors(coords));
        tmpGrid = getGrid1(grid);
        result = getSize(result) > getSize(result_) ? result_ : result;
        System.out.println();
        assert result != null;
        if(result == null){
            System.out.println("Cant reach!");
            result = Collections.emptyList();
        }
        else {
            for (int i = 0; i < result.size(); i++) {
                int[] coor = result.get(i);

                System.out.println(Arrays.toString(coor));
            }
        }
        System.out.println();

        System.out.println(Arrays.toString(from));
//            if (!(from[row] == to[row] && from[col] == to[col]))
//                grid[from[row]][from[col]] = VISITED;
//            coords.add(Arrays.copyOf(from, from.length));
//        }
        coords = result;
//        Navigator navigator = new Navigator(from, to, grid);
//
//        navigator.traverse();
//
//        navigator.print();

//        coords.add(Arrays.copyOf(to, to.length));
//
        System.out.println("Reached : " + Arrays.toString(from));

        String[][] resultGrid = new String[m][n];


        for (int i = 0; i < resultGrid.length; i++) {
            for (int j = 0; j < resultGrid[i].length; j++) {
                if(i == from[row] && j == from[col]){
                    resultGrid[i][j] = "S";
                }
                else if(i == to[row] && j == to[col]){
                    resultGrid[i][j] = "D";
                }
                else if (grid[i][j] == 1) {
                    resultGrid[i][j] = "C";
                } else {
                    resultGrid[i][j] = "0";
                }
            }
        }

        int num = 1;
        int[] prev = null;
        for (int i = 0; i < coords.size(); i++) {
            int[] coord = coords.get(i);
            if (coord[row] == bk[row] && coord[col] == bk[col]) {
                resultGrid[coord[row]][coord[col]] = "S";
            } else if (coord[row] == to[row] && coord[col] == to[col]) {
                resultGrid[coord[row]][coord[col]] = "D";
            } else {

                resultGrid[coord[row]][coord[col]] = get(coord, prev, getNext(coords, i));
            }
            prev = coord;
        }

        for (int i = 0; i < resultGrid.length; i++) {
            for (int j = 0; j < resultGrid[i].length; j++) {
                System.out.print(resultGrid[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int[] getNext(List<int[]> coords, int i) {
        if(i+1 < coords.size())
            return coords.get(i+1);
        return null;
    }

    private static String get(int[] coord, int[] prev, int[] next) {
        if(next == null){
            return "-";
        }
        if(prev != null){
            if(coord[row] != prev[row] && coord[row] != next[row]){
                return "|";
            }
            else if(coord[row] != prev[row]){
                return "+";
            }
            else if(coord[row] != next[row] ){
                return "+";
            }
            else{
                if(coord[col] != prev[col]){
                    return "-";
                }
            }
        }

        if(coord[row] != next[row] || prev == null){
            return "+";
        }
        else{
            if(coord[col] != next[col]){
                return "-";
            }
        }


        return "L";
    }

    private static List<int[]> goDirection(int[] from, int[] to, int[][] grid, Direction direction, List<int[]> coors) {
        if (from[row] == to[row] && from[col] == to[col]) {
//            System.out.println();
//            for (int[] coor : coors) {
//                System.out.println(Arrays.toString(coor));
//            }
//            System.out.println();
            return coors;
        }
        int[] from_ = Arrays.copyOf(from, from.length);
//        System.out.println(Arrays.toString(from_));
        final int[][] grid1 = getGrid1(grid);
        if (direction.canGo(from, to, grid1)) {
            List<int[]> coors_ = getCoors(coors);
            coors_.add(direction.getNextDir(from));
            grid1[from[row]][from[col]] = VISITED;
            List<int[]> result = null;
            List<int[]> down = goDirection(direction.getNextDir(from), to, getGrid1(grid1), Direction.DOWN, getCoors(coors_));
            List<int[]> up = goDirection(direction.getNextDir(from), to, getGrid1(grid1), Direction.UP, getCoors(coors_));
            result = getSize(down) > getSize(up) ? up : down;
            List<int[]> left = goDirection(direction.getNextDir(from), to, getGrid1(grid1), Direction.LEFT, getCoors(coors_));
            result = getSize(result) > getSize(left) ? left : result;
            List<int[]> right = goDirection(direction.getNextDir(from), to, getGrid1(grid1), Direction.RIGHT, getCoors(coors_));
            result = getSize(result) > getSize(right) ? right : result;

            return result;
        }

        return null;
    }

    private static ArrayList<int[]> getCoors(List<int[]> coors_) {
        var list = new ArrayList<int[]>();

        for (int[] ints : coors_) {
            list.add(Arrays.copyOf(ints, ints.length));
        }

        return  list;
    }

    private static int[][] getGrid1(int[][] grid) {
        final int[][] ints = Arrays.copyOf(grid, grid.length);
        for (int i = 0; i < ints.length; i++) {
            ints[i] = Arrays.copyOf(ints[i], ints[i].length);
        }
        return ints;
    }

    private static int getSize(List<int[]> down) {
        return down == null ? Integer.MAX_VALUE : down.size();
    }

    private static boolean isDestinationNodeReached(int from_row, int to_row, int from_col, int to_col) {
        return from_row == to_row && from_col == to_col;
    }

    private static boolean isNodeNotAvailableInGridRowCol(int[][] grid, int from_row, int from_col) {
        return grid[from_row][from_col] != NODE_PRESENT;
    }

    private static boolean isRowAvailableInGrid(int[][] grid, int from_row) {
        return from_row < grid.length;
    }

    private static boolean isColAvailableInGridForRow(int[][] grid, int from_row, int from_col) {
        return from_col < grid[from_row].length;
    }

    private enum Direction {
        UP {
            public boolean canGo(int[] from, int[] to, int[][] grid) {
                final int[] fromCopy = Arrays.copyOf(from, from.length);
                if (fromCopy[0] - 1 < 0 || fromCopy[0] - 1 >= grid.length || (grid[fromCopy[0] - 1][fromCopy[1]] == NODE_PRESENT && notTargetNode(fromCopy[0] - 1, fromCopy[1] , to)) || grid[fromCopy[0] - 1][fromCopy[1]] == VISITED)
                    return false;
                return true;
            }

            @Override
            public int[] getNextDir(int[] from) {
                int[] from_ = Arrays.copyOf(from, from.length);
                from_[row] -= 1;
                return from_;
            }
        }, DOWN {
            public boolean canGo(int[] from, int[] to, int[][] grid) {
                final int[] fromCopy = Arrays.copyOf(from, from.length);
                if (fromCopy[0] + 1 < 0 || fromCopy[0] + 1 >= grid.length || (grid[fromCopy[0] + 1][fromCopy[1]] == NODE_PRESENT && notTargetNode(fromCopy[0] + 1, fromCopy[1] , to)) || grid[fromCopy[0] + 1][fromCopy[1]] == VISITED)
                    return false;
                return true;
            }

            @Override
            public int[] getNextDir(int[] from) {
                int[] from_ = Arrays.copyOf(from, from.length);
                from_[row] += 1;
                return from_;
            }
        }, LEFT {
            public boolean canGo(int[] from, int[] to, int[][] grid) {
                final int[] fromCopy = Arrays.copyOf(from, from.length);
                if (fromCopy[1] - 1 < 0 || fromCopy[1] - 1 >= grid[fromCopy[0]].length || (grid[fromCopy[0]][fromCopy[1] - 1] == NODE_PRESENT && notTargetNode(fromCopy[0], fromCopy[1] - 1 , to)) || grid[fromCopy[0]][fromCopy[1] - 1] == VISITED)
                    return false;
                return true;
            }

            @Override
            public int[] getNextDir(int[] from) {
                int[] from_ = Arrays.copyOf(from, from.length);
                from_[col] -= 1;
                return from_;
            }
        }, RIGHT {
            public boolean canGo(int[] from, int[] to, int[][] grid) {
                final int[] fromCopy = Arrays.copyOf(from, from.length);
                if (fromCopy[1] + 1 < 0 || fromCopy[1] + 1 >=
                        grid[fromCopy[0]].length ||
                        (grid[fromCopy[0]][fromCopy[1] + 1] == NODE_PRESENT  && notTargetNode(fromCopy[0], fromCopy[1] + 1 , to)) ||
                        grid[fromCopy[0]][fromCopy[1] + 1] == VISITED)
                    return false;
                return true;
            }

            @Override
            public int[] getNextDir(int[] from) {
                int[] from_ = Arrays.copyOf(from, from.length);
                from_[col] += 1;
                return from_;
            }
        };

        private static boolean notTargetNode(int from_row, int from_col, int[] to) {
            return from_row != to[row] || from_col != to[col];
        }

        public boolean canGo(int[] from, int[]to, int[][] grid) {
            return false;
        }

        public int[] getNextDir(int[] from) {
            return from;
        }
    }
}
