package com.example.android.inventoryappproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.inventoryappproject.data.InventoryContract.inventoryEntry;
import com.example.android.inventoryappproject.data.InventoryDbHelper;

public class CatalogActivity extends AppCompatActivity {

    private InventoryDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         floatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
         fab.setOnClickListener( (view) {
                 Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                 startActivity(intent);

                 });
         mDbHelper = new inventoryDbHelper(this);
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

             TextView displayView = (TextView) findViewById(R.id.text_view_inventory);

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
               int supplierphoneColumnIndex = cursor.getColumnIndex(inventoryEntry.COLUMN_INVENTORY_SUPPLIER_PHONE_NUMBER);

               while (cursor.moveToNext()) {

                   int currentID = cursor.getInt(idColumnIndex);
                   String currentProduct = cursor.getString(productColumnIndex);
                   String currentPrice = cursor.getString(priceColumnIndex);
                   int currentQuantity = cursor.getInt(quantityColumnIndex);
                   int currentSupplier = cursor.getInt(supplierColumnIndex);
                   int currentSupplierPhone = cursor.getInt(supplierphoneColumnIndex);

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

    }

