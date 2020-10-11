package by.itacademy.homework2observer;

import java.util.ArrayList;

public class Subscriber implements IObserver{
    private static String result;

    public static String getResult() {
        return result;
    }

    @Override
    public void notifyDataChanged(ArrayList<Integer> data) {
        IRandomSetNumbers operations=new RandomSetNumbers();
        result="We have RESULT:\nsum= "+operations.sum(data)+"\naverage= "+operations.average(data)+"\nhalfDiv= "+operations.halfDiv(data);
    }
}
