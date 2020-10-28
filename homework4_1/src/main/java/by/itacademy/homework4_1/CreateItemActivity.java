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

        phoneNumber = findViewById(R.id.addPhone);
        email = findViewById(R.id.addEmail);
        name = findViewById(R.id.name);
        numberOrEmail = findViewById(R.id.numberOrEmail);
        Toolbar toolbar = findViewById(R.id.toolbarCreate);
        setSupportActionBar(toolbar);
        findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item = new Item();
                if (!phoneNumber.isChecked() && !email.isChecked()) {
                    Toast.makeText(CreateItemActivity.this, "Choose category!", Toast.LENGTH_SHORT).show();
                } else if (!name.getText().toString().equals("")) {
                    item.setName(name.getText().toString());
                    if (phoneNumber.isChecked() && !numberOrEmail.getText().toString().equals("")) {
                        item.setPhone(numberOrEmail.getText().toString());
                        Publisher.getInstance().getItemList().add(item);
                        finish();
                    }
                    if (email.isChecked() && !numberOrEmail.getText().toString().equals("")) {
                        item.setEmail(numberOrEmail.getText().toString());
                        Publisher.getInstance().getItemList().add(item);
                        finish();
                    }
                } else {
                    Toast.makeText(CreateItemActivity.this, "Add info!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        findViewById(R.id.backFromCreate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
