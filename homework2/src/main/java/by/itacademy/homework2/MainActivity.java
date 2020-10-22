package by.itacademy.homework2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String CREATEARRAY = "1";
    public static final String SUM = "2";
    public static final String AVERAGE = "3";
    public static final String HALFDIV = "4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("HM2", "Create MainActivity!!!!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.startSecondActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra(CREATEARRAY, RandomSetNumbers.createArray(20));
                startActivityForResult(intent, 1000);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1000 && resultCode == Activity.RESULT_OK && data != null) {
            Log.d("HM2", "MainActivity Result" + String.format(
                    "\nsum= %s \naverage= %s \nhalfdiv= %s",
                    data.getIntExtra(SUM, 0),
                    data.getDoubleExtra(AVERAGE, 0),
                    data.getDoubleExtra(HALFDIV, 0)));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
