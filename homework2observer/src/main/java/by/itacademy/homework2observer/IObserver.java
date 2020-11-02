package by.itacademy.homework2observer;

import java.util.ArrayList;

public interface IObserver {
    void notifyDataChanged(String data, ArrayList<Integer> arrayList);
}
