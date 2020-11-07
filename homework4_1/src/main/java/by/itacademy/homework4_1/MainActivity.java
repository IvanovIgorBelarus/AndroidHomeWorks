package by.itacademy.homework4_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements IObserver {
    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        RecyclerView recycler = findViewById(R.id.recycler);
        setRecycler(recycler);
        Publisher.getInstance().addSubscriber(this);

        findViewById(R.id.addItem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreateItemActivity.class));
            }
        });
        SearchView searchView = findViewById(R.id.searchContact);
        searchViewClickListener(searchView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Publisher.getInstance().removeSubscriber(this);
    }

    @Override
    public void notifyDataChange(int position, int operation) {
        switch (operation) {
            case OperationType.REMOVE: {
                adapter.notifyItemRemoved(position);
                break;
            }
            case OperationType.CHANGE: {
                adapter.notifyItemChanged(position);
                break;
            }
        }
    }

    private void setRecycler(RecyclerView recycler) {
        adapter = new ItemAdapter(Publisher.getInstance().getContactList(), new ItemAdapter.ListItemActionListener() {
            @Override
            public void onItemClicked(int number) {
                Intent intent = new Intent(MainActivity.this, ChangeItemActivity.class);
                intent.putExtra("position", number);
                startActivity(intent);
            }
        });
        recycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recycler.setAdapter(adapter);
    }

    private void searchViewClickListener(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.equals("")) {
                    adapter.setContacts(Publisher.getInstance().getContactList());
                } else {
                    adapter.filter(newText);
                }
                return true;
            }
        });
    }

}
