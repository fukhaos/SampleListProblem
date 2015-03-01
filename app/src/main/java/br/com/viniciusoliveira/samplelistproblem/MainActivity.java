package br.com.viniciusoliveira.samplelistproblem;

import android.app.SearchManager;
import android.content.Context;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    ListView listView;
    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private int numero;
    private MenuItem searchItem;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        listView.setTextFilterEnabled(true);

        items = new ArrayList<String>();

        for (numero = 1; numero < 10; numero++) {
            items.add(String.valueOf(numero));
        }

        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(itemsAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (TextUtils.isEmpty(newText)) {
                    listView.clearTextFilter();
                } else {
                    listView.setFilterText(newText);
                }

                return true;
            }
        });

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addItem(View view) {
        items.add(String.valueOf(numero++));
        itemsAdapter.notifyDataSetChanged();
    }
}
