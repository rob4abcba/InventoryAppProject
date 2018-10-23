package com.example.android.inventoryappproject;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventoryappproject.data.InventoryContract;

public class EditorActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final int EXISTING_INVENTORY_LOADER = 0;
    private static final int REQUEST_PHONE_CALL = 1;
    boolean bQuantityPlus = false;
    boolean bQuantityMinus = false;
    int newQuantityPlus;
    int newQuantityMinus;
    private Uri mCurrentInventoryUri;
    private EditText mNameEditText;
    private EditText mPriceEditText;
    private EditText mQuantityEditText;
    private EditText mSupplierEditText;
    private EditText mPhoneEditText;
    private boolean mInventoryHasChanged = false;
    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mInventoryHasChanged = true;
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        Intent intent = getIntent();
        mCurrentInventoryUri = intent.getData();


        if (mCurrentInventoryUri == null) {
            // This is a new item, so change the app bar to say "Add Product"
            setTitle(getString(R.string.editor_new_activity_title_new_product));


            Button buttonItemDelete = (Button) findViewById(R.id.delete_items);
            buttonItemDelete.setVisibility(View.INVISIBLE);

            invalidateOptionsMenu();
        } else {


            FloatingActionButton buttonItemDelete = findViewById(R.id.delete_items);
            buttonItemDelete.setVisibility(View.VISIBLE);

            setTitle(getString(R.string.add_inventory_data));


            getLoaderManager().initLoader(EXISTING_INVENTORY_LOADER, null, this);

            String quantityString = mQuantityEditText.getText().toString().trim();

            if ((Integer.valueOf(quantityString)) > 0) {
                int quantity = (Integer.valueOf(quantityString)) - 1;


                ContentValues values2 = new ContentValues();
                String newStringQuantity = Integer.toString(quantity);
                mQuantityEditText.setText(newStringQuantity, TextView.BufferType.EDITABLE);
                values2.put(.COLUMN_QUANTITY, newStringQuantity);

            } else {

                Toast.makeText(getApplicationContext(), getString(R.string.quantity_change_inventory_error),
                        Toast.LENGTH_LONG).show();
            }

        } else{


            if (!bQuantityMinus) {
                mQuantityEditText.setText("0", TextView.BufferType.EDITABLE);


                bQuantityMinus = true;

            }
            mNameEditText = (EditText) findViewById(R.id.edit_name);
            mSupplierEditText = (EditText) findViewById(R.id.edit_price);
            mPhoneEditText = (EditText) findViewById(R.id.edit_quantity);
            mPriceEditText = (EditText) findViewById(R.id.edit_suppliername);
            mQuantityEditText = (EditText) findViewById(R.id.supplierphone);

            mNameEditText.setOnTouchListener(mTouchListener);
            mPriceEditText.setOnTouchListener(mTouchListener);
            mQuantityEditText.setOnTouchListener(mTouchListener);
            mSupplierEditText.setOnTouchListener(mTouchListener);
            mPhoneEditText.setOnTouchListener(mTouchListener);


            ImageButton buttonMinus = findViewById(R.id.minusButton);
            buttonMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (mCurrentInventoryUri != null) {

                        String quantityString = mQuantityEditText.getText().toString().trim();

                        if ((Integer.valueOf(quantityString)) > 0) {
                            int quantity = (Integer.valueOf(quantityString)) - 1;


                            ContentValues values2 = new ContentValues();
                            String newStringQuantity = Integer.toString(quantity);
                            mQuantityEditText.setText(newStringQuantity, TextView.BufferType.EDITABLE);
                            values2.put(inventoryEntry.COLUMN_BOOK_QUANTITY, newStringQuantity);

                        } else {

                            Toast.makeText(getApplicationContext(), getString(R.string.quantity_change_inventory_error),
                                    Toast.LENGTH_LONG).show();
                        }

                    } else {


                        if (!bQuantityMinus) {
                            mQuantityEditText.setText("0", TextView.BufferType.EDITABLE);


                            bQuantityMinus = true;
                        }

                        String quantityString = mQuantityEditText.getText().toString().trim();
                        newQuantityMinus = Integer.valueOf(quantityString);

                        Toast.makeText(getApplicationContext(), getString(R.string.quantity_change_inventory_error),
                                Toast.LENGTH_LONG).show();

                    } else{

                        String quantityString = mQuantityEditText.getText().toString().trim();
                        newQuantityMinus = Integer.valueOf(quantityString);

                        if (newQuantityMinus == 0) {
                            Toast.makeText(getApplicationContext(), getString(R.string.quantity_change_inventory_error),
                                    Toast.LENGTH_LONG).show();
                        } else {
                            newQuantityMinus = newQuantityMinus - 1;
                            ContentValues values = new ContentValues();
                            String newStringQuantity = Integer.toString(newQuantityMinus);
                            mQuantityEditText.setText(newStringQuantity, TextView.BufferType.EDITABLE);
                            values.put(BookEntry.COLUMN_QUANTITY, newStringQuantity);
                        }
                    }

                }

            });

            Button buttonplus = (Button) findViewById(R.id.plus_button);
            buttonplus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mCurrentInventoryUri != null) {

                        String quantityString = mQuantityEditText.getText().toString().trim();
                        int quantity = (Integer.valueOf(quantityString)) + 1;

                        ContentValues values2 = new ContentValues();
                        String newStringQuantity = Integer.toString(quantity);
                        mQuantityEditText.setText(newStringQuantity, TextView.BufferType.EDITABLE);
                        values2.put(PastryEntry.COLUMN_BOOK_QUANTITY, newStringQuantity);
                    } else {
                        if (!bQuantityPlus) {
                            mQuantityEditText.setText("1", TextView.BufferType.EDITABLE);

                            bQuantityPlus = true;


                            String quantityString = mQuantityEditText.getText().toString().trim();
                            newQuantityPlus = Integer.valueOf(quantityString);
                        } else {

                            newQuantityMinus = newQuantityMinus + 1;
                            ContentValues values = new ContentValues();
                            String newStringQuantity = Integer.toString(newQuantityMinus);
                            mQuantityEditText.setText(newStringQuantity, TextView.BufferType.EDITABLE);
                            values.put(PastryEntry.COLUMN_BOOK_QUANTITY, newStringQuantity);
                        }
                    }
                }
            });

            Button buttonItemDelete = (Button) findViewById(R.id.action_delete_all_entries);
            buttonItemDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    showDeleteConfirmationDialog();

                }
            });

            ImageButton phoneButton = (ImageButton) findViewById(R.id.phoneButton);
            phoneButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (ActivityCompat.checkSelfPermission(getBaseContext(),
                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(
                                EditorActivity.this,
                                new String[]{Manifest.permission.CALL_PHONE},
                                REQUEST_PHONE_CALL);
                        return;
                    }


                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:12123332222"));
                    startActivity(callIntent);


                }
            });

            private void saveInventory () {
                String nameString = mNameEditText.getText().toString().trim();
                String supplierString = mSupplierEditText.getText().toString().trim();
                String quantityString = mQuantityEditText.getText().toString().trim();
                String phoneString = mPhoneEditText.getText().toString().trim();
                String priceString = mPriceEditText.getText().toString().trim();

                if (mCurrentInventoryUri == null && (TextUtils.isEmpty(nameString) &&
                        TextUtils.isEmpty(priceString) &&
                        TextUtils.isEmpty(quantityString) &&
                        TextUtils.isEmpty(supplierString) &&
                        TextUtils.isEmpty(phoneString))) {

                    Toast.makeText(this, getString(R.string.nochanges_save_failed),
                            Toast.LENGTH_LONG).show();
                    return;
                }

                if (mCurrentInventoryUri == null && (TextUtils.isEmpty(nameString) ||
                        TextUtils.isEmpty(priceString) ||
                        TextUtils.isEmpty(quantityString) ||
                        TextUtils.isEmpty(supplierString) ||
                        TextUtils.isEmpty(phoneString))) {
                    Toast.makeText(this, getString(R.string.null_save_failed),
                            Toast.LENGTH_LONG).show();
                    getApplicationContext();
                    Intent i = new Intent(getApplicationContext(), EditorActivity.class);
                    startActivity(i);
                    return;
                }
                if (mCurrentInventoryUri != null && (TextUtils.isEmpty(nameString) ||
                        TextUtils.isEmpty(priceString) ||
                        TextUtils.isEmpty(quantityString) ||
                        TextUtils.isEmpty(supplierString) ||
                        TextUtils.isEmpty(phoneString))) {

                    Toast.makeText(this, getString(R.string.editnull_save_failed),
                            Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(getIntent());
                    return;
                }

                int priceZero = 0;
                if (TextUtils.isEmpty(priceString) || priceString.matches("") || priceString.equals(".")) {
                    priceString = Integer.toString(priceZero);
                }

                int quantityZero = 0;
                if (TextUtils.isEmpty(quantityString) || quantityString.matches("")) {
                    quantityString = Integer.toString(quantityZero);
                }


                if (!TextUtils.isEmpty(phoneString) && (phoneString.length() != 11)) {
                    Toast.makeText(this, getString(R.string.phone_save_failed),
                            Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(getIntent());
                } else {

                    ContentValues values = new ContentValues();
                    values.put(InventroyEntry.COLUMN_BOOK_NAME, nameString);
                    values.put(BookEntry.COLUMN_BOOK_SUPPLIER, supplierString);
                    values.put(BookEntry.COLUMN_BOOK_PHONE, phoneString);
                    values.put(BookEntry.COLUMN_BOOK_QUANTITY, quantityString);
                    values.put(BookEntry.COLUMN_BOOK_PRICE, priceString);


                    if (mCurrentInventoryUri == null) {


                        Uri newUri = getContentResolver().insert(BookEntry.CONTENT_URI, values);

                        if (newUri == null) {

                            Toast.makeText(this, getString(R.string.editor_insert_inventory_failed),
                                    Toast.LENGTH_LONG).show();
                        } else {

                            Toast.makeText(this, getString(R.string.editor_insert_inventory_successful),
                                    Toast.LENGTH_LONG).show();
                        }

                    } else {


                        int rowsAffected = getContentResolver().update(mCurrentInventoryUri, values, null, null);

                        if (rowsAffected == 0) {
                            Toast.makeText(this, getString(R.string.editor_update_inventory_failed),
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(this, getString(R.string.editor_update_inventory_successful),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }


            @Override
            public boolean onCreateOptionsMenu (Menu menu){
                getMenuInflater().inflate(R.menu.menu_editor, menu);
                return true;
            }


            @Override
            public boolean onOptionsItemSelected (MenuItem item){

                switch (item.getItemId()) {

                    case R.id.action_save:
                        saveInventory();
                        finish();
                        return true;


                    case android.R.id.action_delete:

                        if (!mInventoryHasChanged) {
                            NavUtils.navigateUpFromSameTask(EditorActivity.this);
                            return true;
                        }


                }
                return super.onOptionsItemSelected(item);
            }


            @Override
            public void onBackPressed () {

                if (!mInventoryHasChanged) {
                    super.onBackPressed();
                    return;
                }


            }

            @Override
            public Loader<Cursor> onCreateLoader ( int i, Bundle bundle){
                String[] projection = {
                        BookEntry._ID,
                        BookEntry.COLUMN_BOOK_NAME,
                        BookEntry.COLUMN_BOOK_PRICE,
                        BookEntry.COLUMN_BOOK_QUANTITY,
                        BookEntry.COLUMN_BOOK_SUPPLIER,
                        BookEntry.COLUMN_BOOK_PHONE};


                return new CursorLoader(this,   // Parent activity context
                        mCurrentInventoryUri,         // Query the content URI for the current item
                        projection,             // Columns to include in the resulting Cursor
                        null,                   // No selection clause
                        null,                   // No selection arguments
                        null);                  // Default sort order
            }

            @Override
            public void onLoadFinished (Loader < Cursor > loader, Cursor cursor){

                if (cursor == null || cursor.getCount() < 1) {
                    return;
                }
                if (cursor.moveToFirst()) {

                    int nameColumnIndex = cursor.getColumnIndex(InventoryContract.COLUMN_PRODUCT_NAME);
                    int priceColumnIndex = cursor.getColumnIndex(InventoryContract.COLUMN_BOOK_PRICE);
                    int quantityColumnIndex = cursor.getColumnIndex(InventoryContract.COLUMN_BOOK_QUANTITY);
                    int supplierColumnIndex = cursor.getColumnIndex(InventoryContract.COLUMN_BOOK_SUPPLIER);
                    int phoneColumnIndex = cursor.getColumnIndex(InventoryContract.COLUMN_BOOK_PHONE);


                    String name = cursor.getString(nameColumnIndex);
                    String price = cursor.getString(priceColumnIndex);
                    String quantity = cursor.getString(quantityColumnIndex);
                    String supplier = cursor.getString(supplierColumnIndex);
                    String phone = cursor.getString(phoneColumnIndex);

                    mNameEditText.setText(name);
                    mPriceEditText.setText(price);
                    mQuantityEditText.setText(quantity);
                    mSupplierEditText.setText(supplier);
                    mPhoneEditText.setText(phone);
                }
            }

            @Override
            public void onLoaderReset (Loader < Cursor > loader) {
                mNameEditText.setText("");
                mPriceEditText.setText("");
                mQuantityEditText.setText("");
                mSupplierEditText.setText("");
                mPhoneEditText.setText("");
            }
            private void showUnsavedChangesDialog (
                    DialogInterface.OnClickListener discardButtonClickListener){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.unsaved_changes_dialog_msg);
                builder.setPositiveButton(R.string.discard, discardButtonClickListener);
                builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                    }
                });


                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

            private void showDeleteConfirmationDialog () {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.delete_dialog_msg);
                builder.setPositiveButton(R.string.delete, new DialogInterface()
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deleteBook();
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        if (dialog != null) {
                            dialog.dismiss();
                        }
                    }
                });

                // Here the AlertDialog is created and displayed
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

            private void deleteBook () {
                if (mCurrentInventoryUri != null) {
                    int rowsDeleted = getContentResolver().delete(mCurrentInventoryUri, null, null);

                    if (rowsDeleted == 0) {

                        Toast.makeText(this, getString(R.string.editor_delete_inventory_failed),
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, getString(R.string.editor_insert_inventory_successful),
                                Toast.LENGTH_LONG).show();
                    }

                }
                finish();
            }
        }

    }
}