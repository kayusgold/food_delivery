package com.plustech.fooddelivery;

import android.os.Bundle;
import android.widget.Toast;

import com.plustech.fooddelivery.Adapters.AllMenuAdapter;
import com.plustech.fooddelivery.Adapters.PopularAdapter;
import com.plustech.fooddelivery.Adapters.RecommendedAdapter;
import com.plustech.fooddelivery.model.Allmenu;
import com.plustech.fooddelivery.model.FoodData;
import com.plustech.fooddelivery.model.Popular;
import com.plustech.fooddelivery.model.Recommended;
import com.plustech.fooddelivery.retrofit.ApiInterface;
import com.plustech.fooddelivery.retrofit.RetrofitClient;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiInterface apiInterface;

    RecyclerView popularRecyclerview, recommendedRecyclerview, allMenuRecyclerview;

    PopularAdapter popularAdapter;
    RecommendedAdapter recommendedAdapter;
    AllMenuAdapter allMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = RetrofitClient.getRetrofitInstace().create(ApiInterface.class);

        Call<List<FoodData>> call = apiInterface.getAllData();
        call.enqueue(new Callback<List<FoodData>>() {
            @Override
            public void onResponse(Call<List<FoodData>> call, Response<List<FoodData>> response) {
                List<FoodData> foodDataList = response.body();

                //get popular category data
                getPopularData(foodDataList.get(0).getPopular());

                //get recommended category data
                getRecommendedData(foodDataList.get(0).getRecommended());

                getAllMenuData(foodDataList.get(0).getAllmenu());
            }

            @Override
            public void onFailure(Call<List<FoodData>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getPopularData(List<Popular> popularList) {
        popularRecyclerview = findViewById(R.id.popular_recyclerview);
        popularAdapter = new PopularAdapter(this, popularList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        popularRecyclerview.setLayoutManager(layoutManager);
        popularRecyclerview.setAdapter(popularAdapter);
    }

    private void getRecommendedData(List<Recommended> recommendedList) {
        recommendedRecyclerview = findViewById(R.id.recommended_recyclerview);
        recommendedAdapter = new RecommendedAdapter(this, recommendedList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recommendedRecyclerview.setLayoutManager(layoutManager);
        recommendedRecyclerview.setAdapter(recommendedAdapter);
    }

    private void getAllMenuData(List<Allmenu> allmenuList) {
        allMenuRecyclerview = findViewById(R.id.all_menu_recycler);
        allMenuAdapter = new AllMenuAdapter(this, allmenuList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        allMenuRecyclerview.setLayoutManager(layoutManager);
        allMenuRecyclerview.setAdapter(allMenuAdapter);
    }
}