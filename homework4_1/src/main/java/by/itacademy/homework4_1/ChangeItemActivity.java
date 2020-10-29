package by.itacademy.homework4_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import static by.itacademy.homework4_1.Constants.*;

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
        name.setText(Publisher.getInstance().getItemList().get(position).getName());
        String phoneOrEmail = Publisher.getInstance().getItemList().get(position).getPhone();
        if (phoneOrEmail != null) {
            info.setText(Publisher.getInstance().getItemList().get(position).getPhone());
        } else {
            info.setText(Publisher.getInstance().getItemList().get(position).getEmail());
        }
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item newItem = new Item();
                Item oldItem = Publisher.getInstance().getItemList().get(position);
                newItem.setName(name.getText().toString());
                if (oldItem.getPhone() != null) {
                    newItem.setPhone(info.getText().toString());
                } else if (oldItem.getEmail() != null) {
                    newItem.setEmail(info.getText().toString());
                }
                Publisher.getInstance().getItemList().set(position, newItem);
                Publisher.getInstance().notifyChanged(position, CHANGE);
                finish();
            }
        });
        findViewById(R.id.remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Publisher.getInstance().getItemList().remove(position);
                Publisher.getInstance().notifyChanged(position, REMOVE);
                finish();
            }
        });
    }
}