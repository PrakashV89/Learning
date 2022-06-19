package com.flowchart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlowChartNavigation_Optimized {

    public static final int NODE_PRESENT = 1;
    public static final int VISITED = -1;
    static int m = 6;
    static int n = 5;
    static int[][] grid = new int[m][n];

    static int row = 0;
    static int col = 1;

    public static void main(String[] args) {

        grid[1][2] = 1;
        grid[3][2] = 1;
        grid[4][4] = 1;


        int[] from = new int[]{5, 2};
        int[] to = new int[]{2, 2};
        int[] bk = Arrays.copyOf(from, from.length);

        int row = 0;
        int col = 1;
        System.out.println("Start : " + Arrays.toString(from));
        System.out.println("end : " + Arrays.toString(to));
        System.out.println();

        System.out.println(Arrays.toString(bk));
        List<int[]> coords = new ArrayList<>();
        coords.add(bk);
        /*while (from[row] != to[row] || from[col] != to[col]) {
            Direction direction = null;
            if (Direction.UP.canGo(from)) {
                direction = Direction.UP;
                from[row] -= 1;
            } else if (Direction.RIGHT.canGo(from)) {
                direction = Direction.RIGHT;
                from[col] += 1;
            } else if (Direction.LEFT.canGo(from)) {
                direction = Direction.LEFT;
                from[col] -= 1;
            } else if (Direction.DOWN.canGo(from)) {
                direction = Direction.DOWN;
                from[row] += 1;
            } else {
                break;
            }


            System.out.println(direction + " : " + Arrays.toString(from));
            if (!(from[row] == to[row] && from[col] == to[col]))
                grid[from[row]][from[col]] = VISITED;
            coords.add(Arrays.copyOf(from, from.length));
        }*/

        Navigator navigator = new Navigator(from, to, grid);

        navigator.traverse();

        navigator.print();

        coords.add(Arrays.copyOf(to, to.length));

        System.out.println("Reached : " + Arrays.toString(from));

        String[][] resultGrid = new String[m][n];


        for (int i = 0; i < resultGrid.length; i++) {
            for (int j = 0; j < resultGrid[i].length; j++) {

                if (grid[i][j] == 1) {
                    resultGrid[i][j] = "C";
                } else {
                    resultGrid[i][j] = "0";
                }
            }
        }

        for (int[] coord : coords) {
            if (coord[row] == bk[row] && coord[col] == bk[col]) {
                resultGrid[coord[row]][coord[col]] = "S";
            } else if (coord[row] == to[row] && coord[col] == to[col]) {
                resultGrid[coord[row]][coord[col]] = "D";
            } else {
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



    private static final class Navigator {
        private static int[][] grid;
        int[] from;
        int[] to;
        Node head;
        Node tail;

        public Navigator(int[] from, int[] to, int[][] grid){
            this.from = Arrays.copyOf(from, from.length);
            this.to = Arrays.copyOf(to, to.length);
            this.head =new Node(from);
            this.tail = head;
            this.grid = Arrays.copyOf(grid, grid.length);
        }

        public void traverse(){
            while(tail.from[row] != to[row] || tail.from[col] != to[col]){
                tail = tail.next();
                grid[tail.from[row]][tail.from[col]] = VISITED;
            }
        }

        public void print(){
            Node tmp = head;
            while(tmp != null){
                System.out.println(tmp.direction + " : " + Arrays.toString(tmp.from));
                tmp = tmp.node;
            }
        }
        private enum Direction {
            UP {
                public boolean canGo(int[] from) {
                    final int[] fromCopy = Arrays.copyOf(from, from.length);
                    if (fromCopy[0] - 1 < 0 || fromCopy[0] - 1 >= grid.length || grid[fromCopy[0] - 1][fromCopy[1]] == NODE_PRESENT || grid[fromCopy[0] - 1][fromCopy[1]] == VISITED)
                        return false;
                    return true;
                }
            }, DOWN {
                public boolean canGo(int[] from) {
                    final int[] fromCopy = Arrays.copyOf(from, from.length);
                    if (fromCopy[0] + 1 < 0 || fromCopy[0] + 1 >= grid.length || grid[fromCopy[0] + 1][fromCopy[1]] == NODE_PRESENT || grid[fromCopy[0] + 1][fromCopy[1]] == VISITED)
                        return false;
                    return true;
                }
            }, LEFT {
                public boolean canGo(int[] from) {
                    final int[] fromCopy = Arrays.copyOf(from, from.length);
                    if (fromCopy[1] - 1 < 0 || fromCopy[1] - 1 >= grid[fromCopy[0]].length || grid[fromCopy[0]][fromCopy[1] - 1] == NODE_PRESENT || grid[fromCopy[0]][fromCopy[1] - 1] == VISITED)
                        return false;
                    return true;
                }
            }, RIGHT {
                public boolean canGo(int[] from) {
                    final int[] fromCopy = Arrays.copyOf(from, from.length);
                    if (fromCopy[1] + 1 < 0 || fromCopy[1] + 1 >=
                            grid[fromCopy[0]].length ||
                            grid[fromCopy[0]][fromCopy[1] + 1] == NODE_PRESENT ||
                            grid[fromCopy[0]][fromCopy[1] + 1] == VISITED)
                        return false;
                    return true;
                }
            };

            public boolean canGo(int[] from) {
                return false;
            }
        }
        private static final class Node {
            private int[] from;
            private Direction direction;
            private Node node;

            public Node(int[] from) {
                this.from = Arrays.copyOf(from, from.length);
            }

            public Node next() {
                int[] from = Arrays.copyOf(this.from, this.from.length);
                if (Direction.UP.canGo(from)) {
                    direction = Direction.UP;
                    from[row] -= 1;
                } else if (Direction.RIGHT.canGo(from)) {
                    direction = Direction.RIGHT;
                    from[col] += 1;
                } else if (Direction.LEFT.canGo(from)) {
                    direction = Direction.LEFT;
                    from[col] -= 1;
                } else if (Direction.DOWN.canGo(from)) {
                    direction = Direction.DOWN;
                    from[row] += 1;
                } else {
                    for (int i = 0; i < grid.length; i++) {
                        for (int j = 0; j < grid[i].length; j++) {
                            System.out.print(grid[i][j] + " ");
                        }
                        System.out.println();
                    }
                    throw new RuntimeException("Can't go anywhere");
                }

                node = new Node(from);
                System.out.println(Arrays.toString(from));
                return node;
            }

        }
    }
}
