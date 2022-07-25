package _4kyu;

import java.util.*;
import java.util.stream.Collectors;

public class Finder {
    static Queue<Node> queueForChecking;
    static List<Node> checkedNodesList;
    static List<List<String>> mazeList;

    static boolean pathFinder(String maze) {
        mazeList = new ArrayList<>();
        for (String line : maze.split("\n")) {
            mazeList.add(Arrays.stream(line.split("")).collect(Collectors.toList()));
        }

        queueForChecking = new LinkedList<>();
        checkedNodesList = new ArrayList<>();

        Node node = new Node(0,0);
        List<Node> neighbours = findNeighbours(node);
        queueForChecking.addAll(neighbours);

        while (!queueForChecking.isEmpty()) {
            Node neighbour = queueForChecking.poll();
            if (isFinish(neighbour)) {
                return true;
            } else {
                queueForChecking.addAll(findNeighbours(neighbour));
            }
        }
        return false;
    }

    private static List<Node> findNeighbours(Node node) {
        List<Node> nodes = new ArrayList<>();
        Node up = new Node(node.getY() - 1, node.getX());
        if (up.getY() >= 0 && !checkedNodesList.contains(up)) {
            nodes.add(up);
        }
        Node down = new Node(node.getY() + 1, node.getX());
        if (up.getY() < mazeList.size() && !checkedNodesList.contains(up)) {
            nodes.add(down);
        }
        Node left = new Node(node.getY(), node.getX() - 1);
        if (up.getX() >= 0 && !checkedNodesList.contains(up)) {
            nodes.add(left);
        }
        Node right = new Node(node.getY(), node.getX() + 1);
        if (up.getX() < mazeList.get(0).size() && !checkedNodesList.contains(up)) {
            nodes.add(right);
        }
        return nodes;
    }

    private static boolean isFinish(Node neighbour) {
        return neighbour.getY() == mazeList.size() - 1 && neighbour.getX() == mazeList.get(0).size() - 1;
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
