package by.itacademy.homework4_2;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements LikeGoogleListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LikeGoogle v = findViewById(R.id.likeGoogle);
        v.setListener(this::callback);
    }

    @Override
    public void callback(float x, float y) {
        Toast.makeText(MainActivity.this, String.format("You push x=%s, y=%s", x, y), Toast.LENGTH_SHORT).show();
    }
}
