package com.example.angelmartindominguez.flavourtester;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class Product extends AppCompatActivity {

    private String productNameString = null;
    private String productImagePath = null;
    private TextView name = null;
    private ImageView image = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        productNameString = getIntent().getStringExtra("NAME_OF_PRODUCT");
        productImagePath = getIntent().getStringExtra("IMAGE_OF_PRODUCT");
        name = (TextView)findViewById(R.id.productName);
        name.setText(productNameString);
        image = (ImageView)findViewById(R.id.productImage);
        File imgFile = new File(productImagePath);
        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            image.setImageBitmap(myBitmap);
        }
    }
}
