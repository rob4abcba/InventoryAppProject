package com.example.android.inventoryappproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.android.inventoryappproject.data.InventoryContract.inventoryEntry;
import com.example.android.inventoryappproject.data.InventoryDbHelper;

public class CatalogActivity extends AppCompatActivity {

    private InventoryDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         mDbHelper = new InventoryDbHelper(this);
         }

         @Override
    protected void onStart() {
             super.onStart();
             displayDatabaseInfo();
         }

         private void displayDatabaseInfo (){

             SQLiteDatabase db = mDbHelper.getReadableDatabase();

             String[] projection = {
                     inventoryEntry._ID,
                     inventoryEntry.COLUMN_INVENTORY_PRODUCT_NAME,
                     inventoryEntry.COLUMN_INVENTORY_PRICE,
                     inventoryEntry.COLUMN_INVENTORY_QUANTITY,
                     inventoryEntry.COLUMN_INVENTORY_SUPPLIER_NAME,
                     inventoryEntry.COLUMN_INVENTORY_SUPPLIER_PHONE_NUMBER };

             Cursor cursor = db.query(
                     inventoryEntry.TABLE_NAME,
                     projection,
                     null,
                     null,
                     null,
                     null,
                     null);



           try {
               displayView.setText("The inventory table contains" + cursor.getCount() + "inventory.\n\n");
               displayView.append(inventoryEntry._ID + "-" +
                       inventoryEntry.COLUMN_INVENTORY_PRODUCT_NAME + "-" +
                       inventoryEntry.COLUMN_INVENTORY_PRICE + "-" +
                       inventoryEntry.COLUMN_INVENTORY_QUANTITY + "-" +
                       inventoryEntry.COLUMN_INVENTORY_SUPPLIER_NAME + "-" +
                       inventoryEntry.COLUMN_INVENTORY_SUPPLIER_PHONE_NUMBER + "\n");

               int idColumnIndex = cursor.getColumnIndex(inventoryEntry._ID);
               int productColumnIndex = cursor.getColumnIndex(inventoryEntry.COLUMN_INVENTORY_PRODUCT_NAME);
               int priceColumnIndex = cursor.getColumnIndex(inventoryEntry.COLUMN_INVENTORY_PRICE);
               int quantityColumnIndex = cursor.getColumnIndex(inventoryEntry.COLUMN_INVENTORY_QUANTITY);
               int supplierColumnIndex = cursor.getColumnIndex(inventoryEntry.COLUMN_INVENTORY_SUPPLIER_NAME);
               int supplierPhoneColumnIndex = cursor.getColumnIndex(inventoryEntry.COLUMN_INVENTORY_SUPPLIER_PHONE_NUMBER);

               while (cursor.moveToNext()) {

                   int currentID = cursor.getInt(idColumnIndex);
                   String currentProduct = cursor.getString(productColumnIndex);
                   String currentPrice = cursor.getString(priceColumnIndex);
                   int currentQuantity = cursor.getInt(quantityColumnIndex);
                   String currentSupplier = cursor.getString(supplierColumnIndex);
                   String currentSupplierPhone = cursor.getString(supplierPhoneColumnIndex);

                   displayView.append(("\n" + currentID + "-" +
                           currentProduct + "-" +
                           currentPrice + "-" +
                           currentQuantity + "_" +
                           currentSupplier + "_" +
                           currentSupplierPhone));
               }
           }finally {
               cursor.close();
           }
         }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

