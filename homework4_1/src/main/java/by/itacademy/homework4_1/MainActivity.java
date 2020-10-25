package by.itacademy.homework4_1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private ItemAdapter adapter;
    interface ListItemActionListener{
        void onItemClicked(int number);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        adapter = new ItemAdapter(Publisher.getInstance().getItemList(), new ListItemActionListener() {
            @Override
            public void onItemClicked(int number) {
                Intent intent = new Intent(MainActivity.this,ChangeItemActivity.class);
                intent.putExtra("position",number);
                startActivity(intent);
            }
        });
        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recycler.setAdapter(adapter);

        findViewById(R.id.add_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreateItemActivity.class));
            }
        });
    }
  }
