package by.itacademy.homework4_1;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CreateItemActivity extends AppCompatActivity {
    private RadioButton phoneNumber;
    private RadioButton email;
    private EditText name;
    private EditText numberOrEmail;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);

        phoneNumber = findViewById(R.id.add_phone);
        email = findViewById(R.id.add_email);
        name = findViewById(R.id.name);
        numberOrEmail = findViewById(R.id.numberOrEmail);
        toolbar = findViewById(R.id.toolbar_create);
        setSupportActionBar(toolbar);
        findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!phoneNumber.isChecked() && !email.isChecked()) {
                    Toast.makeText(CreateItemActivity.this, "Choose category", Toast.LENGTH_SHORT).show();
                }
                if (phoneNumber.isChecked() && !numberOrEmail.getText().toString().equals("") && !name.getText().toString().equals("")) {
                    Publisher.getInstance().addItem(new Item.Builder()
                            .setName(name.getText().toString())
                            .setPhone(numberOrEmail.getText().toString())
                            .build());
                    finish();
                } else {
                    Toast.makeText(CreateItemActivity.this, "add info", Toast.LENGTH_SHORT).show();
                }
                if (email.isChecked() && !numberOrEmail.getText().toString().equals("") && !name.getText().toString().equals("")) {
                    Publisher.getInstance().addItem(new Item.Builder()
                            .setName(name.getText().toString())
                            .setEmail(numberOrEmail.getText().toString())
                            .build());
                    finish();
                } else {
                    Toast.makeText(CreateItemActivity.this, "add info", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
