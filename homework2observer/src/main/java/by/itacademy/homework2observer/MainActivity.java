package by.itacademy.homework2observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements IObserver {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("HM2", "MainActivity create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.startSecondActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Publisher.getInstance().setData(RandomSetNumbers.createArray(100));
                Publisher.getInstance().addSubscriber(MainActivity.this);
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        Log.d("HM2", "MainActivity onResume");
        super.onResume();
        if (Publisher.getResult() != null) {
            Log.d("HM2", "" + Publisher.getResult());
        }
    }

    @Override
    public void notifyDataChanged() {
        Log.d("HM2", "MainActivity notifyDataChanged used");
    }
}
