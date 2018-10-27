package com.example.android.inventoryappproject;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.inventoryappproject.data.InventoryContract;

public class CatalogActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        readdata();
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
            Log.d("Hello" , myCursor);

        } catch (Exception e) {
            Log.d("activity", "catch");
        } finally {
            Log.d("Hello" , myCursor);
            // 8) close your cursor
            myCursor.close();
        }
    }
}