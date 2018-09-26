package com.example.android.inventoryappproject.data;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.android.inventoryappproject.data.InventoryContract.inventoryEntry;


public class InventoryDbHelper extends SQLiteOpenHelper {

      // database helper
    public static final String LOG_TAG = InventoryDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "inventory.db";

     // Database version
    private static final int DATABASE_VERSION = 1;
    public  Context MYCONTEXT;

    /**
     * Construct a new instance of (@link InventoryDbHelper)
     * @param context of app
     */
      public InventoryDbHelper (Context context){super(context, DATABASE_NAME, null,DATABASE_VERSION);this.MYCONTEXT = context;}


      @Override
      public void onCreate (SQLiteDatabase sqLiteDatabase) {
        try {
          String SQL_CREATE_INVENTORY_TABLE = "CREATE TABLE " + inventoryEntry.TABLE_NAME + " ("
                  + inventoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                  + inventoryEntry.COLUMN_INVENTORY_PRODUCT_NAME + " TEXT NOT NULL, "
                  + inventoryEntry.COLUMN_INVENTORY_PRICE + " DECIMAL, "
                  + inventoryEntry.COLUMN_INVENTORY_QUANTITY + " INTEGER NOT NULL, "
                  + inventoryEntry.COLUMN_INVENTORY_SUPPLIER_NAME + " TEXT NOT NULL, "
                  + inventoryEntry.COLUMN_INVENTORY_SUPPLIER_PHONE_NUMBER + " TEXT NOT NULL );";

          sqLiteDatabase.execSQL(SQL_CREATE_INVENTORY_TABLE);
            Toast.makeText(this.MYCONTEXT, " Sucssefuly created database",Toast.LENGTH_LONG).show();
      }catch (SQLException e) {

        Toast.makeText(this.MYCONTEXT, " Error mysqli !"+e.getMessage(),Toast.LENGTH_LONG).show();
    }
      }
      // when the database need too be upgrade
      @Override
      public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){

      }
}
