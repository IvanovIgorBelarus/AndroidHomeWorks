package by.itacademy.homework2observer;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity implements IObserver {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("HM2", "SecondActivity create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Publisher.getInstance().addSubscriber(this);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Publisher.getInstance().removeSubscriber(this);
        Log.d("HM2", "SecondActivity ONDESTROY");
    }

    @Override
    public void notifyDataChanged(String data, ArrayList<Integer> arrayList) {
        IRandomSetNumbers operations = new RandomSetNumbers();
        Publisher.setResult(String.format("We have RESULT:\nsum= %s\naverage= %s\nhalfDiv= %s",
                operations.sum(arrayList),
                operations.average(arrayList),
                operations.halfDiv(arrayList)));
        Log.d("HM2", "SecondActivity notifyDataChanged used");
    }
}
