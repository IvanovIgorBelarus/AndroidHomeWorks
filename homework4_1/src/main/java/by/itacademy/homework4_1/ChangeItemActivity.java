package by.itacademy.homework4_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ChangeItemActivity extends AppCompatActivity {
    private EditText name;
    private EditText info;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_item);

        final Intent intent = getIntent();
        if (intent != null) {
            position = intent.getIntExtra("position", 0);
        }
        name = findViewById(R.id.name);
        info = findViewById(R.id.info);
        Contact contact = Publisher.getInstance().getContactList().get(position);
        name.setText(contact.getName());
        String data = contact.getData();
        if (data != null) {
            info.setText(contact.getData());
        }
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact newContact = new Contact();
                newContact.setName(name.getText().toString());
                newContact.setData(info.getText().toString());
                Publisher.getInstance().setItem(position, newContact);
                finish();
            }
        });
        findViewById(R.id.remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Publisher.getInstance().removeItem(position);
                finish();
            }
        });
    }
}
