package by.itacademy.homework2observer;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

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
    public void notifyDataChanged() {
        IRandomSetNumbers operations = new RandomSetNumbers();
        String result = "We have RESULT:\nsum= " + operations.sum(Publisher.getData()) + "\naverage= " + operations.average(Publisher.getData()) + "\nhalfDiv= " + operations.halfDiv(Publisher.getData());
        Publisher.setResult(result);
        Log.d("HM2", "SecondActivity notifyDataChanged used");
    }
}
