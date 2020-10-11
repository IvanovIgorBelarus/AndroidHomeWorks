package by.itacademy.homework2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class RandomSetNumbers {
    public static ArrayList<Integer> createArray(int n){
        HashSet<Integer>hashSet=new HashSet<>();
        ArrayList<Integer>result=new ArrayList<>();
        for (int i=1;i<=n;i++){
            hashSet.add(new Random(1000).nextInt());
        }
        for (Integer a:hashSet) {
            result.add(a);
        }
        return result;
    }
}
