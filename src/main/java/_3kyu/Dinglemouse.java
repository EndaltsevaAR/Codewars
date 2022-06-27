package _3kyu;

import java.util.ArrayList;
import java.util.List;

public class Dinglemouse {
    static List<Integer> liftMovingList; //result list of moving list
    static List<Integer> floorsToStopList;  //list of current passengers

    public static int[] theLift(final int[][] queues, final int capacity) {
        liftMovingList = new ArrayList<>(); //result list of moving list
        floorsToStopList = new ArrayList<>();  //list of current passengers
        liftMovingList.add(0); //start always from ground
        int currentFloor = 0;
        while (isHigherPassengersWait(0, queues)) {
            for (int floor = 0; floor < queues.length; floor++) {     //moving up check for each floor
                entryFloorChecking(true, floor, queues, capacity);
                if (!isHigherPassengersWait(floor + 1, queues) && !isLiftNeedToUp(floor, floorsToStopList)) {
                    currentFloor = floor;
                    break;
                }
            }
            for (int floor = currentFloor; floor > 0; floor--) {    //moving to ground
                entryFloorChecking(false, floor, queues, capacity);
            }
        }

        if (liftMovingList.get(liftMovingList.size() - 1) != 0) {
            liftMovingList.add(0); //end always at the ground
        }

        return liftMovingList.stream().mapToInt(i -> i).toArray();
    }

    private static void entryFloorChecking(boolean isUp, int floor, final int[][] queues, int capasity) {
        if (floorsToStopList.contains(floor)) {  //passenger unloading
            floorsToStopList = floorsToStopListWithoutThisFloor(floorsToStopList, floor);
            if (liftMovingList.get(liftMovingList.size() - 1) != floor) {
                liftMovingList.add(floor);
            }
        }

        for (int pass = 0; pass < queues[floor].length; pass++) {
            if ( queues[floor][pass] != -1 && ((queues[floor][pass] > floor && isUp) || (queues[floor][pass] < floor && !isUp))) {
                if (floorsToStopList.size() < capasity) {   //if lift has free sits, passenger can enter
                    floorsToStopList.add(queues[floor][pass]);
                    queues[floor][pass] = -1; //if passengers use lift, his place at the queue is reset to 0
                }
                if (liftMovingList.get(liftMovingList.size() - 1) != floor) {  //to avoid duplicates for many passengers from one floor
                    liftMovingList.add(floor);  //enter passenger or not, but lift stopping in counted
                }
            }
        }
    }

    private static boolean isLiftNeedToUp(int floor, List<Integer> liftMovingList) {  //if is there are passengers which need to go up
        for (Integer pass : liftMovingList) {
            if (pass > floor) {
                return true;
            }
        }
        return false;
    }

    private static boolean isHigherPassengersWait(int floor, int[][] queues) {  //checking there are still waiting passengers higher than this floor include
        for (int i = floor; i < queues.length; i++) {
            for (int pass = 0; pass < queues[i].length; pass++) {
                if (queues[i][pass] != -1) {
                    return true;
                }
            }
        }
        return false;
    }

    private static List<Integer> floorsToStopListWithoutThisFloor(List<Integer> floorsToStopList, int floor) {
        List<Integer> temp = new ArrayList<>();
        for (Integer integer : floorsToStopList) {
            if (integer != floor) {
                temp.add(integer);
            }
        }
        return temp;
    }
}
