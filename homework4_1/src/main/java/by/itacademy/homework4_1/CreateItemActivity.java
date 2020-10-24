package by.itacademy.homework4_1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CreateItemActivity extends AppCompatActivity {
    private RadioButton phoneNumber;
    private RadioButton email;
    private EditText name;
    private EditText numberOrEmail;
    private Button addButton;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);

        phoneNumber = findViewById(R.id.add_phone);
        email = findViewById(R.id.add_email);
        name = findViewById(R.id.name);
        numberOrEmail = findViewById(R.id.numberOrEmail);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
