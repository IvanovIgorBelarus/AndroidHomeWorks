package by.itacademy.homework2observer;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    public static MyObserver myObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("HM2","SecondActivity create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        finish();
    }


}
