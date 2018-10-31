package com.example.android.inventoryappproject;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.inventoryappproject.data.InventoryContract;
import com.example.android.inventoryappproject.data.InventoryCursorAdapter;

public class CatalogActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        
        private static final int  EXISTING_INVENTORY_LOADER = 0;
        InventoryCursorAdapter mCursorAdapter;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalog_activity);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        // Find the ListView which will be populated with the pet data
        ListView inventoryListView = (ListView) findViewById(R.id.list);

        // Find and set empty view on the ListView, so that it only shows when the list has 0 items.
        View emptyView = findViewById(R.id.empty_space);
        inventoryListView.setEmptyView(emptyView);

        mCursorAdapter = new InventoryCursorAdapter(this, null);
        inventoryListView.setAdapter(mCursorAdapter);

    }
     inventoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

            Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);

            Uri mCurrentInventoryUri = ContentUris.withAppendedId(InventoryContract.InventoryEntry.CONTENT_URI, id);

            startActivity(intent);
        });

        getLoaderManager().initLoader( EXISTING_INVENTORY_LOADER, null, this);
    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){

        switch (item.getItemId()) {

            case R.id.action_insert_dummy_data:
                insertdata();
                return true;

            case R.id.action_delete_all_entries:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void insertdata() {
        ContentValues values = new ContentValues();
        values.put(InventoryContract.InventoryEntry.COLUMN_INVENTORY_PRODUCT_NAME, "Games");
        values.put(InventoryContract.InventoryEntry.COLUMN_INVENTORY_PRICE, 50);
        values.put(InventoryContract.InventoryEntry.COLUMN_INVENTORY_QUANTITY, 20);
        values.put(InventoryContract.InventoryEntry.COLUMN_INVENTORY_SUPPLIER_NAME, "PetsSmart");
        values.put(InventoryContract.InventoryEntry.COLUMN_INVENTORY_SUPPLIER_PHONE_NUMBER, "205-605-4040");
        Uri uri = getContentResolver().insert(InventoryContract.InventoryEntry.CONTENT_URI, values);

        if (uri != null) {
            Toast.makeText(this, " inserted item in database table inventory", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, " something went wrong", Toast.LENGTH_LONG).show();
        }
    }

    public void readdata() {

        // 3) set a cursor to null
        Cursor myCursor = null;

        try {
              myCursor = this.getContentResolver().query(InventoryContract.InventoryEntry.CONTENT_URI,
                    null,
                    null,
                    null,
                    BaseColumns._ID);

            if (myCursor != null) {
                myCursor.moveToFirst();
                String ProductName = myCursor.getString(myCursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_INVENTORY_PRODUCT_NAME));
                double price = myCursor.getDouble(myCursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_INVENTORY_PRICE));
                int quantity = myCursor.getInt(myCursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_INVENTORY_QUANTITY));
                String suppliername = myCursor.getString(myCursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_INVENTORY_SUPPLIER_NAME));
                String suppliernumber = myCursor.getString(myCursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_INVENTORY_SUPPLIER_PHONE_NUMBER));

                Toast.makeText(this, " ProductName = " + ProductName + "Price = " + price + "quantity = " + quantity + "suppliername = " + suppliername + "suppliernumber = " + suppliernumber, Toast.LENGTH_LONG).show();

            }


        } catch (Exception e) {
            Log.d("activity", "catch");
        } finally {

            // 8) close your cursor
            myCursor.close();
        }
    }
}