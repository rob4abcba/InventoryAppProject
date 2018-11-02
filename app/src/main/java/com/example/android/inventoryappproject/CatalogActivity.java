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
import android.support.v4.app.LoaderManager;
import android.support.v4.app.NavUtils;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
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


        public class CatalogActivity extends AppCompatActivity implements
                LoaderManager.LoaderCallbacks<Cursor> {


            private static final int EXISTING_INVENTORY_LOADER = 0;
            InventoryCursorAdapter mCursorAdapter;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
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


                inventoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                        Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);

                        Uri mCurrentInventoryUri = ContentUris.withAppendedId(InventoryContract.InventoryEntry.CONTENT_URI, id);

                        startActivity(intent);
                    }
                });

                getSupportLoaderManager().initLoader(EXISTING_INVENTORY_LOADER, null, this);
            }


            public void insertdata() {
                ContentValues values = new ContentValues();
                values.put(InventoryContract.InventoryEntry.COLUMN_INVENTORY_PRODUCT_NAME, "Games");
                values.put(InventoryContract.InventoryEntry.COLUMN_INVENTORY_PRICE, 50);
                values.put(InventoryContract.InventoryEntry.COLUMN_INVENTORY_SUPPLIER_NAME, "PetsSmart");
                values.put(InventoryContract.InventoryEntry.COLUMN_INVENTORY_SUPPLIER_PHONE_NUMBER, "205-605-4040");
                Uri uri = getContentResolver().insert(InventoryContract.InventoryEntry.CONTENT_URI, values);


            }

            private void deleteAllInventory() {
                getContentResolver().delete(InventoryContract.InventoryEntry.CONTENT_URI, null, null);
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
                        deleteAllInventory();
                        return true;
                }
                return super.onOptionsItemSelected(item);
            }



            @Override
            public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {


                // Here a projection is defined. The projection specifies which columns
                // from the database you will use after this query.
                String[] projection = {
                        InventoryContract.InventoryEntry._ID,
                        InventoryContract.InventoryEntry.COLUMN_INVENTORY_PRODUCT_NAME,
                        InventoryContract.InventoryEntry.COLUMN_INVENTORY_SUPPLIER_NAME,
                        InventoryContract.InventoryEntry.COLUMN_INVENTORY_SUPPLIER_PHONE_NUMBER,
                        InventoryContract.InventoryEntry.COLUMN_INVENTORY_QUANTITY,
                        InventoryContract.InventoryEntry.COLUMN_INVENTORY_PRICE};


                return new CursorLoader(this,   // Parent activity context
                        InventoryContract.InventoryEntry.CONTENT_URI,   // Provider content URI to query
                        projection,             // Columns to include in the resulting Cursor
                        null,                   // No selection clause
                        null,                   // No selection arguments
                        null);                  // Default sort order
            }

            @Override
            public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
                mCursorAdapter.swapCursor(data);

            }

            @Override
            public void onLoaderReset(Loader<Cursor> loader) {
                mCursorAdapter.swapCursor(null);

            }

            public void decreaseCount (int columnId, int quantity) {


                if (quantity < 1) {
                    Toast.makeText(this, getString(R.string.inventory_none_left_toast_msg),
                            Toast.LENGTH_SHORT).show();
                } else {
                    quantity = quantity - 1;
                    Toast.makeText(this, getString(R.string.inventory_one_deleted_toast_msg),
                            Toast.LENGTH_SHORT).show();

                    ContentValues values = new ContentValues();
                    values.put(InventoryContract.InventoryEntry.COLUMN_INVENTORY_QUANTITY, quantity);

                    Uri updateUri = ContentUris.withAppendedId(InventoryContract.InventoryEntry.CONTENT_URI, columnId);

                    getContentResolver().update(updateUri, values, null, null);

                }
            }

        }

