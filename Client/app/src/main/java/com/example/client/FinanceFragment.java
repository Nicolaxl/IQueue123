package com.example.client;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinanceFragment extends Fragment {

    String apinews="1b979c38d120493c9f5a7ebdbad0e41d";
    ArrayList<NewsModel> newsModelArrayList;
    BingNewsAdapter adapter;
    String country ="tw";
    private RecyclerView recyclerViewFinance;
    private String category="business";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.finance_fragment,null);

        recyclerViewFinance = v.findViewById(R.id.latestnews);
        newsModelArrayList=new ArrayList<>();
        recyclerViewFinance.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new BingNewsAdapter(getContext(),newsModelArrayList);
        recyclerViewFinance.setAdapter(adapter);

        findNews();

        return v;
    }

    private void findNews() {

        APINews.getBingNewsAPI().getCategoriesNews(country,category,100,apinews).enqueue(new Callback<financeNews>() {
            @Override
            public void onResponse(Call<financeNews> call, Response<financeNews> response) {
                if(response.isSuccessful()){
                    newsModelArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<financeNews> call, Throwable t) {

            }
        });
    }
}
