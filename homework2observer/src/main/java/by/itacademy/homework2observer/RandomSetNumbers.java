package by.itacademy.homework2observer;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class RandomSetNumbers implements IRandomSetNumbers {
    public static ArrayList<Integer> createArray(int n) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            hashSet.add(new Random().nextInt(100));
        }
        ArrayList<Integer> result = new ArrayList<>(hashSet);
        Log.d("HM2", "Create ArrayList: " + result);
        return result;
    }

    @Override
    public Integer sum(ArrayList<Integer> arrayList) {
        if (arrayList.size() != 0) {
            Integer sum = 0;
            for (Integer a : arrayList) {
                sum += a;
            }
            return sum;
        }
        return null;
    }

    @Override
    public Double average(ArrayList<Integer> arrayList) {
        if (sum(arrayList) != null) {
            return (double) sum(arrayList) / arrayList.size();
        }
        return null;
    }

    @Override
    public Double halfDiv(ArrayList<Integer> arrayList) {
        if (arrayList.size() != 0) {
            int sum = 0;
            int div = arrayList.get(arrayList.size() / 2);
            for (int i = 0; i < arrayList.size(); i++) {
                if (i < arrayList.size() / 2) {
                    sum += arrayList.get(i);
                } else if (i > arrayList.size() / 2) {
                    div -= arrayList.get(i);
                }
            }
            return div == 0 ? null : (double) sum / div;
        }
        return null;
    }
}
