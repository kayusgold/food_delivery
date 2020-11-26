package com.plustech.fooddelivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import androidx.appcompat.app.AppCompatActivity;

public class FoodDetails extends AppCompatActivity {
    Button addToCartBtn;
    TextView nameView, priceView, noteView, ratingView, cartCountView;
    ImageView imageView;
    RatingBar ratingBarView;

    int cart_count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        Intent intent = getIntent();

        nameView = findViewById(R.id.name);
        nameView.setText(intent.getStringExtra("name"));

        imageView = findViewById(R.id.details_image);
        Glide.with(this).load(intent.getStringExtra("imageUrl")).into(imageView);

        ratingBarView = findViewById(R.id.ratingBar);
        ratingBarView.setRating(Float.parseFloat(intent.getStringExtra("rating")));

        ratingView = findViewById(R.id.rating);
        ratingView.setText(intent.getStringExtra("rating"));

        priceView = findViewById(R.id.price);
        priceView.setText("$"+intent.getStringExtra("price"));

        noteView = findViewById(R.id.details_note);
        noteView.setText(intent.getStringExtra("note"));

        cartCountView = findViewById(R.id.cart_count);
        cartCountView.setText(String.valueOf(cart_count));
        
        addToCartBtn = findViewById(R.id.add_to_cart_btn);
        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cart_count++;
                cartCountView.setText(String.valueOf(cart_count));
                Toast.makeText(FoodDetails.this, "Item added to Cart", Toast.LENGTH_SHORT).show();
            }
        });
    }
}