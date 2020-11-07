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
    private EditText data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);

        phoneNumber = findViewById(R.id.addPhone);
        email = findViewById(R.id.addEmail);
        name = findViewById(R.id.name);
        data = findViewById(R.id.numberOrEmail);
        Toolbar toolbar = findViewById(R.id.toolbarCreate);
        setSupportActionBar(toolbar);
        findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact contact = new Contact();
                contact.setData(data.getText().toString());
                if (!phoneNumber.isChecked() && !email.isChecked()) {
                    Toast.makeText(CreateItemActivity.this, "Choose category!", Toast.LENGTH_SHORT).show();
                } else if (!name.getText().toString().equals("")) {
                    contact.setName(name.getText().toString());
                    if (phoneNumber.isChecked() && !data.getText().toString().equals("")) {
                        Publisher.getInstance().addItem(contact);
                        finish();
                    }
                    if (email.isChecked() && !data.getText().toString().equals("")) {
                        contact.setPhone(false);
                        Publisher.getInstance().addItem(contact);
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
