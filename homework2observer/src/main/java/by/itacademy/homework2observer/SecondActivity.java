package by.itacademy.homework2observer;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    public static Subscriber subscriber=new Subscriber();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("HM2", "SecondActivity create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Publisher.getInstance().notifySubscribers();
        finish();
    }


}
