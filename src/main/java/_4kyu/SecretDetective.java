package _4kyu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SecretDetective {
    public String recoverSecret(char[][] triplets) {
        List<Character> resultList = new ArrayList<>(); //prepare list for result adding
        for (Character character : triplets[0]) {
            resultList.add(character);
        }
        List<List<Character>> tripletsList = new ArrayList<>();        //transform array to list without first line
        for (int i = 1; i < triplets.length; i++) {
            List<Character> tripletsLine = new ArrayList<>();
            for (Character character : triplets[i]) {
                tripletsLine.add(character);
            }
            tripletsList.add(tripletsLine);
        }
        while (!tripletsList.isEmpty()) {
            List<List<Character>> tempList = new ArrayList<>();
            for (List<Character> line : tripletsList) {
                boolean isActive = false;
                if (line == null || line.isEmpty() || line.size() == 1) {
                    break;
                }
                for (Character letter : line) {
                    if (letter.equals(resultList.get(resultList.size() - 1)) && line.indexOf(letter) < line.size() - 1) {
                        for (int i = line.indexOf(letter) + 1; i < line.size(); i++) {
                            resultList.add(line.get(i));
                        }
                        isActive = true;
                        break;
                    } else if (resultList.get(0).equals(letter) && line.indexOf(letter) != 0) {
                        for (int i = line.indexOf(letter) - 1; i >= 0; i--) {
                            resultList.add(0, line.get(i));
                        }
                        isActive = true;
                        break;
                    }
                }
                if (isActive || resultList.containsAll(line)) {
                } else if (line.size() == 2) {
                    tempList.add(line);
                } else if (resultList.contains(line.get(0)) && resultList.contains(line.get(2)) &&
                        resultList.indexOf(line.get(0)) + 1 == resultList.indexOf(line.get(2))) {
                    resultList.add(resultList.indexOf(line.get(2)), line.get(1));
                } else {
                    tempList.add(line);
                }
            }
            tripletsList = new ArrayList<>(tempList);
        }
        return resultList.stream().map(String::valueOf).collect(Collectors.joining());
    }
}
