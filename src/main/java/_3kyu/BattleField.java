package _3kyu;
/*
Description:
Write a method that takes a field for well-known board game "Battleship" as an argument and returns true if it has a
valid disposition of ships, false otherwise. Argument is guaranteed to be 10*10 two-dimension array. Elements in the
array are numbers, 0 if the cell is free and 1 if occupied by ship.

Battleship (also Battleships or Sea Battle) is a guessing game for two players. Each player has a 10x10 grid containing
several "ships" and objective is to destroy enemy's forces by targetting individual cells on his field. The ship
occupies one or more cells in the grid. Size and number of ships may differ from version to version. In this kata we
will use Soviet/Russian version of the game.


Before the game begins, players set up the board and place the ships accordingly to the following rules:
There must be single battleship (size of 4 cells), 2 cruisers (size 3), 3 destroyers (size 2) and 4 submarines
(size 1). Any additional ships are not allowed, as well as missing ships.
Each ship must be a straight line, except for submarines, which are just single cell.

The ship cannot overlap or be in contact with any other ship, neither by edge nor by corner.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BattleField {
    static final int MAX_SHIP_SIZE = 4;

    public static boolean fieldValidator(int[][] field) {
        Map<Integer, Integer> shipsMap = new HashMap<>();
        for (int i = MAX_SHIP_SIZE; i > 0; i--) { //ships initialization
            shipsMap.put(i, MAX_SHIP_SIZE + 1 - i);
        }
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[0].length; x++) {
                if (IsStartOfShip(y, x, field)) { //find head of each ship
                    List<Integer> shipsData = findShip(y, x, field);       //first - direction (boolean value - if there is horizontal: 1, vertical: 2, one cell:0, different: -1, second - size
                    if (shipsData.get(0) == -1 || shipsData.get(1) > MAX_SHIP_SIZE ||
                            shipsMap.get(shipsData.get(1)) == 0 || IsHasNeighbor(y, x, shipsData, field)) {
                        return false;
                    } else shipsMap.put(shipsData.get(1), shipsMap.get(shipsData.get(1)) - 1);
                }
            }
        }
        for (Map.Entry<Integer, Integer> shipPair : shipsMap.entrySet()) {  //check if there is unchecked ships yet
            if (shipPair.getValue() > 0) return false;
        }
        return true;
    }

    private static boolean IsStartOfShip(int y, int x, int[][] field) {
        return ((y == 0 || (y > 0 && field[y - 1][x] == 0)) &&  //neighbor at the top border or 0
                (x == 0 || (x > 0 && field[y][x - 1] == 0)) //neighbor at the left border or 0
                && field[y][x] == 1);   //cell itself must be 1
    }

    private static List<Integer> findShip(int y, int x, int[][] field) {
        List<Integer> shipDataList = new ArrayList<>();
        int size = 1;
        int direction = 0; // horizontal: 1, vertical: 2, one cell:0, different: -1
        while (true) {
            switch (direction) {
                case 0:
                    if (x <= field[0].length - 2 && field[y][x + 1] == 1) { //if right cell is full, there is horizontal ship
                        size++;
                        direction = 1;
                        x++;
                    } else if (y <= field.length - 2 && field[y + 1][x] == 1) { //if bottom cell is full, there is vertical ship
                        size++;
                        direction = 2;
                        y++;
                    } else { //if both of them empty, this is one cell ship
                        return addingDataToTheList(direction, size);
                    }
                    break;
                case 1:
                    if (x <= field[0].length - 2 && field[y][x + 1] == 1) {
                        size++;
                        x++;
                    } else {
                        return addingDataToTheList(direction, size);
                    }
                    break;
                case 2:
                    if (y <= field.length - 2 && field[y + 1][x] == 1) {
                        size++;
                        y++;
                    } else {
                        return addingDataToTheList(direction, size);
                    }
                    break;
            }
        }
    }

    private static List<Integer> addingDataToTheList(int direction, int size) {
        List<Integer> shipDataList = new ArrayList<>();
        shipDataList.add(direction);
        shipDataList.add(size);
        return shipDataList;
    }

    private static boolean IsHasNeighbor(int y, int x, List<Integer> shipsData, int[][] field) {
        int yMin = Math.max(0, y - 1);
        int xMin = Math.max(0, x - 1);
        int yMax, xMax;
        if (shipsData.get(0) == 1 || shipsData.get(0) == 0) {
            yMax = Math.min(field.length - 1, y + 1);
            xMax = Math.min(field[0].length - 1, x + shipsData.get(1));
        } else {
            yMax = Math.min(field.length - 1, y + shipsData.get(1));
            xMax = Math.min(field[0].length - 1, x + 1);
        }
        for (int i = yMin; i <= yMax; i++) {
            for (int j = xMin; j <= xMax; j++) {
                if (field[i][j] == 1) {
                    if ((shipsData.get(0) == 1 || shipsData.get(0) == 0) &&  //for horizontal ship
                            (i != y || j < x || j >= x + shipsData.get(1))) return true; //except ship's cells
                    if (shipsData.get(0) == 2 && //for vertical ship
                            (j != x || i < y || i >= y + shipsData.get(1))) return true; //except ship's cells
                }
            }
        }
        return false;
    }
}
