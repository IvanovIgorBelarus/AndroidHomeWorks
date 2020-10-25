package by.itacademy.homework4_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChangeItemActivity extends AppCompatActivity {
    private EditText name;
    private EditText info;
    private Button remove;
    private String phoneOrEmail;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_item);

        final Intent intent = getIntent();
        if (intent != null) {
            position = intent.getIntExtra("position", 0);
        }

        remove = findViewById(R.id.remove);
        name = findViewById(R.id.name);
        info = findViewById(R.id.info);
        name.setText(Publisher.getInstance().getItemList().get(position).getName());
        phoneOrEmail = Publisher.getInstance().getItemList().get(position).getPhone();
        if (phoneOrEmail != null) {
            info.setText(Publisher.getInstance().getItemList().get(position).getPhone());
        } else {
            info.setText(Publisher.getInstance().getItemList().get(position).getEmail());
        }
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Publisher.getInstance().removeItem(position);
            }
        });
    }
}
