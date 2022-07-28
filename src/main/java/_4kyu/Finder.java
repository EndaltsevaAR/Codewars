package _4kyu;

/*
Description:
Task
You are at position [0, 0] in maze NxN and you can only move in one of the four cardinal directions (i.e. North, East, South, West). Return true if you can reach position [N-1, N-1] or false otherwise.

Empty positions are marked ..
Walls are marked W.
Start and exit positions are empty in all test cases.
 */

import java.util.*;

public class Finder {

    static Queue<Node> queueForChecking; //queue for checking nodes by levels: first there are checked first element, then all his neighbours, than all neighbours of these neighbors, so on. Element is checked and  deleted from this queue
    static int[][] checkedNodesArray; //array - a copy in size from the maze array, filled with 0 at the beginning. After checking the node, the array cell with the corresponding coordinates of the node is filled with 1. Used to check for duplication
    static char[][] mazeArray; //transform string to char[][]

    static boolean pathFinder(String maze) {
        transformStringMazeToArray(maze);
        queueForChecking = new LinkedList<>();
        checkedNodesArray = new int[mazeArray.length][mazeArray[0].length];

        Node node = new Node(0,0); //start node (0,0)
        if (isFinish(node)) return true; //if maze is just 1 cell
        List<Node> neighbours = findNeighbours(node); //first node's neighbours
        queueForChecking.addAll(neighbours);
        checkedNodesArray[node.getY()][node.getX()] = 1;

        while (!queueForChecking.isEmpty()) {      //BFS algorithm
            Node neighbour = queueForChecking.poll();  //check neighbour
            if (isFinish(neighbour)) {
                return true;
            } else {
                queueForChecking.addAll(findNeighbours(neighbour)); //add all his neighbours to end of queue
                checkedNodesArray[neighbour.getY()][neighbour.getX()] = 1; //current neighbour is checked
            }
        }
        return false;
    }

    private static void transformStringMazeToArray(String maze) {
        String[] lines = maze.split("\n");
        mazeArray = new char[lines.length][lines[0].split("").length];
        for (int y = 0; y < lines.length; y++) {
            for (int x = 0; x < lines[y].length(); x++) {
                mazeArray[y][x] = lines[y].charAt(x);
            }
        }
    }

    private static List<Node> findNeighbours(Node node) {
        List<Node> nodes = new ArrayList<>();
        Node up = new Node(node.getY() - 1, node.getX()); //for possible neighbours: up, down, left and right
        //There are must be at the borders of maze array, and must be included at the checkedNodesArray and queueForChecking just once
        if (up.getY() >= 0 && checkedNodesArray[up.getY()][up.getX()] == 0 && isRoad(up) && !queueForChecking.contains(up)) {
            nodes.add(up);
        }
        Node down = new Node(node.getY() + 1, node.getX());
        if (down.getY() < mazeArray.length && checkedNodesArray[down.getY()][down.getX()] == 0 && isRoad(down) && !queueForChecking.contains(down) ) {
            nodes.add(down);
        }
        Node left = new Node(node.getY(), node.getX() - 1);

        if (left.getX() >= 0 && checkedNodesArray[left.getY()][left.getX()] == 0 && isRoad(left) && !queueForChecking.contains(left)) {
            nodes.add(left);
        }
        Node right = new Node(node.getY(), node.getX() + 1);
        if (right.getX() < mazeArray[0].length && checkedNodesArray[right.getY()][right.getX()] == 0 && isRoad(right) && !queueForChecking.contains(right)) {
            nodes.add(right);
        }
        return nodes;
    }

    private static boolean isFinish(Node neighbour) {
        return neighbour.getY() == mazeArray.length - 1 && neighbour.getX() == mazeArray[0].length - 1;
    }

    private static boolean isRoad(Node neighbour) {
        return mazeArray[neighbour.getY()][neighbour.getX()] == '.'; //'W' - is wall, '.' - is road
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
