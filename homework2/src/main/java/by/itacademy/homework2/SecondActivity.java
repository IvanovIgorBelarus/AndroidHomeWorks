package by.itacademy.homework2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import static by.itacademy.homework2.MainActivity.AVERAGE;
import static by.itacademy.homework2.MainActivity.CREATEARRAY;
import static by.itacademy.homework2.MainActivity.HALFDIV;
import static by.itacademy.homework2.MainActivity.SUM;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("HM2", "Create SecondActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final Intent intent = getIntent();
        if (intent != null) {
            ArrayList<Integer> arrayList = intent.getIntegerArrayListExtra(CREATEARRAY);
            IRandomSetNumbers operations = new RandomSetNumbers();
            Intent result = new Intent();
            result.putExtra(SUM, operations.sum(arrayList));
            result.putExtra(AVERAGE, operations.average(arrayList));
            result.putExtra(HALFDIV, operations.halfDiv(arrayList));
            setResult(Activity.RESULT_OK, result);
        }
        finish();
    }
}
