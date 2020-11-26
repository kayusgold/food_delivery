package com.plustech.fooddelivery.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.plustech.fooddelivery.FoodDetails;
import com.plustech.fooddelivery.R;
import com.plustech.fooddelivery.model.Allmenu;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AllMenuAdapter extends RecyclerView.Adapter<AllMenuAdapter.AllMenuViewHolder> {
    private Context context;
    private List<Allmenu> allmenuList;

    public AllMenuAdapter(Context context, List<Allmenu> allmenuList) {
        this.context = context;
        this.allmenuList = allmenuList;
    }

    @NonNull
    @Override
    public AllMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.allmenu_recycler_items, parent, false);
        return new AllMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllMenuViewHolder holder, int position) {
        Log.e("Allmenu Name", allmenuList.get(position).getName());
        holder.popularName.setText(allmenuList.get(position).getName());
        holder.popularNote.setText(allmenuList.get(position).getNote());
        holder.popularDeliveryTime.setText(allmenuList.get(position).getDeliveryTime());
        holder.populalrPrice.setText(context.getString(R.string.currency)+allmenuList.get(position).getPrice());
        holder.popularRating.setText(allmenuList.get(position).getRating());
        holder.popularCharges.setText(allmenuList.get(position).getDeliveryCharges());

        Glide.with(context).load(allmenuList.get(position).getImageUrl()).into(holder.popularImage);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, FoodDetails.class);
                i.putExtra("name", allmenuList.get(position).getName());
                i.putExtra("price", allmenuList.get(position).getPrice());
                i.putExtra("rating", allmenuList.get(position).getRating());
                i.putExtra("note", allmenuList.get(position).getNote());
                i.putExtra("imageUrl", allmenuList.get(position).getImageUrl());

                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allmenuList.size();
    }

    public static class AllMenuViewHolder extends RecyclerView.ViewHolder{
        ImageView popularImage;
        TextView popularName, popularRating, popularCharges, popularDeliveryTime, populalrPrice, popularNote;

        public AllMenuViewHolder(@NonNull View itemView) {
            super(itemView);

            popularImage = itemView.findViewById(R.id.popularx_image);
            popularName = itemView.findViewById(R.id.popularx_name);
            popularRating = itemView.findViewById(R.id.popularx_rating);
            popularCharges = itemView.findViewById(R.id.popularx_delivery_charge);
            popularDeliveryTime = itemView.findViewById(R.id.popularx_delivery_time);
            populalrPrice = itemView.findViewById(R.id.popularx_price);
            popularNote = itemView.findViewById(R.id.popularx_note);
        }
    }
}
