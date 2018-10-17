package com.example.android.inventoryappproject;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
    private EditText mSuppliernameEditText;
    private EditText mSupplierphoneEditText;
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
            setTitle(getString(R.string.editor_activity_title_new_product));



            Button buttonItemDelete = (Button) findViewById(R.id.action_delete_all_entries);
            buttonItemDelete.setVisibility(View.INVISIBLE);

            invalidateOptionsMenu();
        } else {


            FloatingActionButton buttonItemDelete = findViewById(R.id.delete_items);
            buttonItemDelete.setVisibility(View.VISIBLE);

            setTitle(getString(R.string.editor_activity_title_edit_product));


            getLoaderManager().initLoader(EXISTING_INVENTORY_LOADER, null, this);

        }

        mNameEditText = (EditText) findViewById(R.id.book_name_edit_field);
        mSupplierEditText = (EditText) findViewById(R.id.book_supplier_edit_field);
        mPhoneEditText = (EditText) findViewById(R.id.book_phone_edit_field);
        mPriceEditText = (EditText) findViewById(R.id.book_price_edit_field);
        mQuantityEditText = (EditText) findViewById(R.id.book_quantity_edit_field);

        mNameEditText.setOnTouchListener(mTouchListener);
        mPriceEditText.setOnTouchListener(mTouchListener);
        mQuantityEditText.setOnTouchListener(mTouchListener);
        mSupplierEditText.setOnTouchListener(mTouchListener);
        mPhoneEditText.setOnTouchListener(mTouchListener);


        Button buttonMinus = (Button) findViewById(R.id.minusButton);
        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (mCurrentBookUri != null) {

                    String quantityString = mQuantityEditText.getText().toString().trim();

                    if ((Integer.valueOf(quantityString)) > 0) {
                        int quantity = (Integer.valueOf(quantityString)) - 1;


                        ContentValues values2 = new ContentValues();
                        String newStringQuantity = Integer.toString(quantity);
                        mQuantityEditText.setText(newStringQuantity, TextView.BufferType.EDITABLE);
                        values2.put(PastryEntry.COLUMN_BOOK_QUANTITY, newStringQuantity);

                    } else {

                        Toast.makeText(getApplicationContext(), getString(R.string.book_none_left_toast_msg),
                                Toast.LENGTH_LONG).show();
                    }

                }

                else {


                    if (!bQuantityMinus) {
                        mQuantityEditText.setText("0", TextView.BufferType.EDITABLE);


                        bQuantityMinus = true;

                    }
                    mNameEditText = (EditText) findViewById(R.id.edit_suppliername);
                    mSupplierEditText = (EditText) findViewById(R.id.);
                    mPhoneEditText = (EditText) findViewById(R.id.supplierphone);
                    mPriceEditText = (EditText) findViewById(R.id.);
                    mQuantityEditText = (EditText) findViewById(R.id.supp);

                    mNameEditText.setOnTouchListener(mTouchListener);
                    mPriceEditText.setOnTouchListener(mTouchListener);
                    mQuantityEditText.setOnTouchListener(mTouchListener);
                    mSupplierEditText.setOnTouchListener(mTouchListener);
                    mPhoneEditText.setOnTouchListener(mTouchListener);


                    Button buttonMinus = (Button) findViewById(R.id.minusButton);
                    buttonMinus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            if (mCurrentBookUri != null) {

                                String quantityString = mQuantityEditText.getText().toString().trim();

                                if ((Integer.valueOf(quantityString)) > 0) {
                                    int quantity = (Integer.valueOf(quantityString)) - 1;


                                    ContentValues values2 = new ContentValues();
                                    String newStringQuantity = Integer.toString(quantity);
                                    mQuantityEditText.setText(newStringQuantity, TextView.BufferType.EDITABLE);
                                    values2.put(PastryEntry.COLUMN_BOOK_QUANTITY, newStringQuantity);

                                } else {

                                    Toast.makeText(getApplicationContext(), getString(R.string.book_none_left_toast_msg),
                                            Toast.LENGTH_LONG).show();
                                }

                            }

                            else {


                                if (!bQuantityMinus) {
                                    mQuantityEditText.setText("0", TextView.BufferType.EDITABLE);


                                    bQuantityMinus = true;
                                }

                                String quantityString = mQuantityEditText.getText().toString().trim();
                                newQuantityMinus = Integer.valueOf(quantityString);

                                Toast.makeText(getApplicationContext(), getString(R.string.book_none_left_toast_msg),
                                        Toast.LENGTH_LONG).show();

                            } else {

                                String quantityString = mQuantityEditText.getText().toString().trim();
                                newQuantityMinus = Integer.valueOf(quantityString);

                                if (newQuantityMinus == 0) {
                                    Toast.makeText(getApplicationContext(), getString(R.string.book_none_left_toast_msg),
                                            Toast.LENGTH_LONG).show();
                                } else {
                                    newQuantityMinus = newQuantityMinus - 1;
                                    ContentValues values = new ContentValues();
                                    String newStringQuantity = Integer.toString(newQuantitySubtract);
                                    mQuantityEditText.setText(newStringQuantity, TextView.BufferType.EDITABLE);
                                    values.put(BookEntry.COLUMN_BOOK_QUANTITY, newStringQuantity);
                                }
                            }

                        }
                    }
                });

                Button buttonplus = (Button) findViewById(R.id.plusButton);
                buttonadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (mCurrentBookUri != null) {

                            String quantityString = mQuantityEditText.getText().toString().trim();
                            int quantity = (Integer.valueOf(quantityString)) + 1;

                            ContentValues values2 = new ContentValues();
                            String newStringQuantity = Integer.toString(quantity);
                            mQuantityEditText.setText(newStringQuantity, TextView.BufferType.EDITABLE);
                            values2.put(PastryEntry.COLUMN_BOOK_QUANTITY, newStringQuantity);
                        }

                        else {
                            if (!bQuantityPlus) {
                                mQuantityEditText.setText("1", TextView.BufferType.EDITABLE);

                                bQuantityPlus = true;


                                String quantityString = mQuantityEditText.getText().toString().trim();
                                newQuantityPlus = Integer.valueOf(quantityString);
                            } else {

                                newQuantityPlus = newQuantityPlus + 1;
                                ContentValues values = new ContentValues();
                                String newStringQuantity = Integer.toString(newQuantityPlus);
                                mQuantityEditText.setText(newStringQuantity, TextView.BufferType.EDITABLE);
                                values.put(PastryEntry.COLUMN_BOOK_QUANTITY, newStringQuantity);
                            }
                        }
                    }
                });

                Button buttonItemDelete = (Button) findViewById(R.id.delete_book_data_button);
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
                        callIntent.setData(Uri.parse("tel:12123332222" ));
                        startActivity(callIntent);


                    }
                });
            }
            }