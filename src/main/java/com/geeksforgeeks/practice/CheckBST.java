package com.geeksforgeeks.practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CheckBST {
    public static void main(String[] args) {
        Integer[] ints = new Integer[]{4, 9, 2, 3, 5, 7};
        BinaryTree<Integer> bt = new BinaryTree<>(ints[0]);
        Queue<BinaryTree.Node<Integer>> queue = new LinkedList<>();
        if (ints.length > 0 && ints[0] != null) {
            queue.offer(bt.root);
        }
        for (int i = 1; i < ints.length && queue.size() > 0; i++) {
            BinaryTree.Node<Integer> node = queue.poll();
            if (ints[i] != null) {
                node.left = new BinaryTree.Node<>(ints[i]);
                queue.offer(node.left);
            }

            if (i > ints.length - 2) {
                break;
            }
            ++i;
            if (ints[i] != null) {
                node.right = new BinaryTree.Node<>(ints[i]);
                queue.offer(node.right);
            }
        }
        /*bt = new BinaryTree<>(ints[0]);
        currentNode = bt.root;
        currentNode.left = new BinaryTree.Node<>(ints[1]);
        currentNode.right = new BinaryTree.Node<>(ints[2]);
        currentNode.left.left = new BinaryTree.Node<>(ints[3]);
        currentNode.left.right = new BinaryTree.Node<>(ints[4]);*/

        System.out.println(checkBST(bt));
        System.out.println(largestValues(bt.root));
    }

    public static boolean checkBST(BinaryTree<Integer> binaryTree) {
        return checkBST(binaryTree.root);
    }

    private static boolean checkBST(BinaryTree.Node<Integer> root) {
        if (root == null) {
            return true;
        }

        if (null != root.left && null != root.left.data && root.left.data >= root.data) {
            return false;
        }
        if (null != root.right && null != root.right.data && root.right.data <= root.data) {
            return false;
        }
        if ((root.left != null && max(root.left) >= root.data) || (root.right != null && max(root.right) <= root.data)) {
            return false;
        }

        if (!checkBST(root.left) || !checkBST(root.right)) {
            return false;
        }
        return true;
    }

    private static ArrayList<Integer> largestValues(BinaryTree.Node<Integer> root) {
        ArrayList<Integer> list = new ArrayList<>(16);

        largestVal(root, list, 0);

        return list;
    }

    private static void largestVal(BinaryTree.Node<Integer> currNode, ArrayList<Integer> list, int level) {
        if (currNode == null) {
            return;
        }
        //System.out.println(level + " :" + currNode.data);

        if (list.size() == level) {
            list.add(currNode.data);
        } else {
            list.set(level, Math.max(list.get(level), currNode.data));
        }

        largestVal(currNode.left, list, level + 1);
        largestVal(currNode.right, list, level + 1);
    }

    private static Integer max(BinaryTree.Node<Integer> node) {
        if (node == null) {
            return Integer.MAX_VALUE;
        }

        Queue<BinaryTree.Node<Integer>> queue = new LinkedList<>();
        queue.offer(node);

        int max = node.data;
        while (queue.size() > 0) {
            final BinaryTree.Node<Integer> integerNode = queue.poll();
            max = Math.max(max, integerNode.data);
            if (integerNode.left != null) {
                queue.offer(integerNode.left);
            }
            if (integerNode.right != null) {
                queue.offer(integerNode.right);
            }

        }

        return max;
    }

    private static class BinaryTree<T> {
        Node<T> root;

        public BinaryTree(T data) {
            root = new Node<>(data);
        }

        private static class Node<T> {
            Node<T> left;
            Node<T> right;
            T data;

            public Node(T data) {
                this.data = data;
            }

            @Override
            public String toString() {
                return String.valueOf(data);
            }
        }

    }
}
