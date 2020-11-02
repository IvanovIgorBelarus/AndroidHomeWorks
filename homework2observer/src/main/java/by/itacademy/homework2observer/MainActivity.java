package by.itacademy.homework2observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IObserver {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("HM2", "MainActivity create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Publisher.getInstance().addSubscriber(this);
        findViewById(R.id.startSecondActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Publisher.getInstance().setData(RandomSetNumbers.createArray(100));
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Publisher.getInstance().removeSubscriber(this);
        Log.d("HM2", "MainActivity ONDESTROY");
    }

    @Override
    public void notifyDataChanged(String data, ArrayList<Integer> arrayList) {
        if (data != null) {
            Log.d("HM2", "MainActivity notifyDataChanged " + data);
        }
    }
}
