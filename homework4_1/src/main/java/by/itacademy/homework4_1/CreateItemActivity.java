package by.itacademy.homework4_1;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CreateItemActivity extends AppCompatActivity {
    private RadioButton phoneNumber;
    private RadioButton email;
    private EditText name;
    private EditText numberOrEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);

        phoneNumber = findViewById(R.id.add_phone);
        email = findViewById(R.id.add_email);
        name = findViewById(R.id.name);
        numberOrEmail = findViewById(R.id.numberOrEmail);
        Toolbar toolbar = findViewById(R.id.toolbar_create);
        setSupportActionBar(toolbar);
        findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item = new Item();
                if (!phoneNumber.isChecked() && !email.isChecked()) {
                    Toast.makeText(CreateItemActivity.this, "Choose category", Toast.LENGTH_SHORT).show();
                }
                if (!name.getText().toString().equals("")) {
                    item.setName(name.getText().toString());
                } else {
                    Toast.makeText(CreateItemActivity.this, "add info", Toast.LENGTH_SHORT).show();
                }
                if (phoneNumber.isChecked()) {
                    item.setPhone(numberOrEmail.getText().toString());
                } else if (email.isChecked()) {
                    item.setEmail(numberOrEmail.getText().toString());
                }
                Publisher.getInstance().getItemList().add(item);
                finish();
            }
        });
    }
}
