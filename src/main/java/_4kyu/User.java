package _4kyu;

import java.util.Arrays;
import java.util.List;

public class User {
    int rank = -8;
    int progress = 0;
    List<Integer> ranksList = Arrays.asList(-8, -7, -6, -5, -4, -3, -2, -1, 1, 2, 3, 4, 5, 6, 7, 8);


    void incProgress(int taskRank) {
        if (!ranksList.contains(taskRank))            //illegal argument of rang for activities
            throw new RuntimeException();
        if ( rank == 8) return;                       //if rang is max

        int d = ranksList.indexOf(taskRank) - ranksList.indexOf(rank); //difference between ranks
        if (d < -1) {
            return;
        } else if (d == -1) {
            progress++;
        } else if (d == 0) {
            progress += 3;
        } else {
            progress += d * d * 10;
        }
        while (progress >= 100 && rank < 8) {
            progress -= 100;
            rank = ranksList.get(ranksList.indexOf(rank) + 1);
        }
        if (rank == 8) progress = 0;        //if rank is max
    }
}

