package com.example.android.inventoryappproject.data;
import android.provider.BaseColumns;


public final class InventoryContract {

  // give it an empty constructor
    private InventoryContract() {}

    /**
     * constant values of the inventory database
     */
    public static final class inventoryEntry implements BaseColumns {

        //name of database
        public final static String TABLE_NAME = "inventory";

        // unique id
        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_INVENTORY_PRODUCT_NAME = "product name";

        public final static String COLUMN_INVENTORY_PRICE = "price";

        public final static String COLUMN_INVENTORY_QUANTITY = "quantity";

        public final static String COLUMN_INVENTORY_SUPPLIER_NAME = "supplier name";

        public final static String COLUMN_INVENTORY_SUPPLIER_PHONE_NUMBER = "supplier phone number";

    }

}
