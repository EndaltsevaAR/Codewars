package _4kyu;

import java.util.*;

public class Finder {
    public static void main(String[] args) {
        String d = "......\n"+
                "......\n"+
                "......\n"+
                "......\n"+
                ".....W\n"+
                "....W.";
        System.out.println(pathFinder(d));
    }
    static Queue<Node> queueForChecking;
    static List<Node> checkedNodesList;
    static char[][] mazeArray;

    static boolean pathFinder(String maze) {
        String[] lines = maze.split("\n");
        mazeArray = new char[lines.length][lines[0].split("").length];
        for (int y = 0; y < lines.length; y++) {
            for (int x = 0; x < lines[y].length(); x++) {
                mazeArray[y][x] = lines[y].charAt(x);
            }
        }


        queueForChecking = new LinkedList<>();
        checkedNodesList = new ArrayList<>();

        Node node = new Node(0,0);
        if (isFinish(node)) return true;
        List<Node> neighbours = findNeighbours(node);
        queueForChecking.addAll(neighbours);
        checkedNodesList.add(node);

        int count = 0;
        while (!queueForChecking.isEmpty()) {
            Node neighbour = queueForChecking.poll();
            if (isFinish(neighbour)) {
                return true;
            } else {
                queueForChecking.addAll(findNeighbours(neighbour));
                checkedNodesList.add(neighbour);
            }
        }
        return false;
    }

    private static List<Node> findNeighbours(Node node) {
        List<Node> nodes = new ArrayList<>();
        Node up = new Node(node.getY() - 1, node.getX());

        if (up.getY() >= 0 && !checkedNodesList.contains(up) && isRoad(up) && !queueForChecking.contains(up)) {
            nodes.add(up);
        }
        Node down = new Node(node.getY() + 1, node.getX());
        if (down.getY() < mazeArray.length && !checkedNodesList.contains(down) && isRoad(down) && !queueForChecking.contains(down) ) {
            nodes.add(down);
        }
        Node left = new Node(node.getY(), node.getX() - 1);

        if (left.getX() >= 0 && !checkedNodesList.contains(left) && isRoad(left) && !queueForChecking.contains(left)) {
            nodes.add(left);
        }
        Node right = new Node(node.getY(), node.getX() + 1);
        if (right.getX() < mazeArray[0].length && !checkedNodesList.contains(right) && isRoad(right) && !queueForChecking.contains(right)) {
            nodes.add(right);
        }
        return nodes;
    }

    private static boolean isFinish(Node neighbour) {
        return neighbour.getY() == mazeArray.length - 1 && neighbour.getX() == mazeArray[0].length - 1;
    }

    private static boolean isRoad(Node neighbour) {
        return mazeArray[neighbour.getY()][neighbour.getX()] == '.';
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
