package com.example.android.inventoryappproject.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.inventoryappproject.data.InventoryContract.inventoryEntry;


public class InventoryDbHelper extends SQLiteOpenHelper {

      // database helper
    public static final String LOG_TAG = InventoryDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "inventory.db";

     // Database version
    private static final int DATABASE_VERSION = 1;

    /**
     * Construct a new instance of (@link InventoryDbHelper)
     * @param context of app
     */
      public InventoryDbHelper (Context context){super(context, DATABASE_NAME, null,DATABASE_VERSION);}

      @Override
    public void onCreate (SQLiteDatabase db) {
          String SQL_CREATE_INVENTORY_TABLE = "CREATE TABLE" + inventoryEntry.TABLE_NAME + "("
              + inventoryEntry._ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
              + inventoryEntry.COLUMN_INVENTORY_PRODUCT_NAME + "TEXT NOT NULL,"
              + inventoryEntry.COLUMN_INVENTORY_PRICE + "TEXT,"
              + inventoryEntry.COLUMN_INVENTORY_QUANTITY + "INTEGER NOT NULL,"
              + inventoryEntry.COLUMN_INVENTORY_SUPPLIER_NAME + "INTEGER NOT NULL,"
              + inventoryEntry.COLUMN_INVENTORY_SUPPLIER_PHONE_NUMBER + "INTEGER NOT NULL DEFAULT 0);";

               db.execSQL(SQL_CREATE_INVENTORY_TABLE);
      }
      // when the database need too be upgrade
      @Override
      public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){

      }



}
