package _4kyu;

/*
Solving by wave algorithm
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Finder {
    static String[] lines;
    static int[][] maze_int_array;
    static int count;

    static boolean pathFinder(String maze) {
        lines = maze.split("\n");
        maze_int_array = new int[lines.length][lines.length];
        count = 1;

        Node start = new Node(0,0);
        List<Node> neighbours = findNeighbours(start, new ArrayList<>());
        if (lines[0].charAt(0) == '.') {
            maze_int_array[start.getY()][start.getX()] = count++;
        } else {
            return false;
        }

        while (!neighbours.isEmpty()) {
            List<Node> nextLevelNeighbours = new ArrayList<>();
            for (Node node: neighbours) {
                maze_int_array[node.getY()][node.getX()] = count;
            }
            for (Node node: neighbours) {
                if (isFinish(node)) {
                    break;
                } else {
                    nextLevelNeighbours.addAll(findNeighbours(node, nextLevelNeighbours)); //add all his neighbours to end of queue
                }
            }
            count++;
            neighbours = new ArrayList<>(nextLevelNeighbours);
        }
        return maze_int_array[lines.length - 1][lines.length - 1] > 0;
    }

    private static List<Node> findNeighbours(Node node, List<Node> nextLevelNeighbours) {
        List<Node> nodes = new ArrayList<>(); //for possible neighbours: up, down, left and right
        isNeighbours(node.getY() - 1, node.getX(), nodes, nextLevelNeighbours);
        isNeighbours(node.getY() + 1, node.getX(), nodes, nextLevelNeighbours);
        isNeighbours(node.getY(), node.getX() - 1, nodes, nextLevelNeighbours);
        isNeighbours(node.getY(), node.getX() + 1, nodes, nextLevelNeighbours);
        return nodes;
    }

    private static void isNeighbours(int y, int x, List<Node> nodes, List<Node> nextLevelNeighbours) {
        Node node = new Node(y, x);
        if (node.getX() >= 0 && node.getX() < lines.length && node.getY() >= 0 && node.getY() < lines.length &&
                maze_int_array[node.getY()][node.getX()] == 0 && lines[node.getY()].charAt(node.getX()) != 'W'
                && !nextLevelNeighbours.contains(node)) {
            nodes.add(node);
        }
    }

    private static boolean isFinish(Node node) {
        return node.getY() == lines.length - 1 && node.getX() == lines.length - 1;
    }
}

class Node {
    private int y;
    private int x;

    public Node(int y, int x) {
        this.y = y;
        this.x = x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return y == node.y && x == node.x;
    }

    @Override
    public int hashCode() {
        return Objects.hash(y, x);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}