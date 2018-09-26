package com.example.android.inventoryappproject.data;
import android.net.Uri;
import android.provider.BaseColumns;


public class InventoryContract {
    public static final  String AUTHORITY = "com.example.android.inventoryappproject.data";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+AUTHORITY);
    public static final String PATH_WALMART = "inventory";


    /**
     * constant values of the inventory database
     */
    public static final class inventoryEntry implements BaseColumns {

        //name of database
        public final static String TABLE_NAME = "inventory";

        public static final Uri CONTENT_URI =
         BASE_CONTENT_URI.buildUpon().appendPath(PATH_WALMART).build();

        // unique id
        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_INVENTORY_PRODUCT_NAME = "product_name";

        public final static String COLUMN_INVENTORY_PRICE = "price";

        public final static String COLUMN_INVENTORY_QUANTITY = "quantity";

        public final static String COLUMN_INVENTORY_SUPPLIER_NAME = "supplier_name";

        public final static String COLUMN_INVENTORY_SUPPLIER_PHONE_NUMBER = "supplier_phone_number";

    }
}
