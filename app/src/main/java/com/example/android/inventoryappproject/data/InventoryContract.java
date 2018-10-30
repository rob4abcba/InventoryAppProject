package com.example.android.inventoryappproject.data;
import android.net.Uri;
import android.provider.BaseColumns;

import java.net.URI;


public class InventoryContract {
    public static final  String AUTHORITY = "com.example.android.inventoryappproject";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+AUTHORITY);
    public static final String PATH_WALMART = "inventory";


    /**
     * constant values of the inventory database
     */
    public static final class InventoryEntry implements BaseColumns {

        //name of database
        public final static String TABLE_NAME = "inventory";

        //public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_WALMART).build();
         public final static Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI,PATH_WALMART);
        // unique id
        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_INVENTORY_PRODUCT_NAME = "product_name";

        public final static String COLUMN_INVENTORY_PRICE = "price";

        public final static String COLUMN_INVENTORY_QUANTITY = "quantity";

        public final static String COLUMN_INVENTORY_SUPPLIER_NAME = "supplier_name";

        public final static String COLUMN_INVENTORY_SUPPLIER_PHONE_NUMBER = "supplier_phone_number";

    }
}
