package by.itacademy.homework4_1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static by.itacademy.homework4_1.Constants.*;

public class MainActivity extends AppCompatActivity implements IObserver {
    private static ItemAdapter adapter;

    interface ListItemActionListener {
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
                Intent intent = new Intent(MainActivity.this, ChangeItemActivity.class);
                intent.putExtra("position", number);
                startActivity(intent);
            }
        });
        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recycler.setAdapter(adapter);

        Publisher.getInstance().addSubscriber(this);

        findViewById(R.id.addItem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreateItemActivity.class));
            }
        });
        SearchView searchView = findViewById(R.id.searchContact);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("HM4", "newText= " + newText);
                if (newText.equals("")) {
                    Log.d("HM4", "setOldAdapter");
                    adapter.setItems(Publisher.getInstance().getItemList());
                } else {
                    Log.d("HM4", "setNewAdapter");
                    adapter.setItems(Filter.filter(newText));
                    adapter.notifyDataSetChanged();
                }

                return true;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Publisher.getInstance().removeSubscriber(this);
    }

    @Override
    public void notifyDataChange(int position, int operation) {
        Log.d("HM4", "notifyDataChange Main ");
        switch (operation) {
            case REMOVE: {
                adapter.notifyItemRemoved(position);
                break;
            }
            case CHANGE: {
                adapter.notifyItemChanged(position);
                break;
            }
        }
    }

}
