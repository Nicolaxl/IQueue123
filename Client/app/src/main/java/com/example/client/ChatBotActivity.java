package com.example.client;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatBotActivity extends AppCompatActivity {

    ImageView send;
    EditText msg;
    RecyclerView chatlist;
    private final String KEY_BOT= "bot";
    private final String KEY_USER = "user";
    private ArrayList<ChatBotModels> chatBotModelsArrayList;
    private ChatBotAdapter chatBotAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_room);

        send = (ImageView) findViewById(R.id.sendButton);
        msg = (EditText) findViewById(R.id.messageArea);
        chatlist = findViewById(R.id.chatting);
        chatBotModelsArrayList = new ArrayList<>();
        chatBotAdapter = new ChatBotAdapter(chatBotModelsArrayList, this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        chatlist.setLayoutManager(manager);
        chatlist.setAdapter(chatBotAdapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(msg.getText().toString().isEmpty()){
                    Toast.makeText(ChatBotActivity.this,"Please enter your message",Toast.LENGTH_SHORT).show();
                    return;
                }
                getResponse(msg.getText().toString());
                msg.setText("");
            }
        });
    }

    private void getResponse(String message) {
        chatBotModelsArrayList.add(new ChatBotModels(message,KEY_USER));
        chatBotAdapter.notifyDataSetChanged();
        String url = "http://api.brainshop.ai/get?bid=162534&key=zUHDC9WxHJDyoZpK&uid=[uid]&msg=" + message;
        String BASE_URL = "http://api.brainshop.ai/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ChatBotAPI chatBotAPI = retrofit.create(ChatBotAPI.class);
        Call<ChatBotMsg> call = chatBotAPI.getMessage(url);
        call.enqueue(new Callback<ChatBotMsg>() {
            @Override
            public void onResponse(Call<ChatBotMsg> call, Response<ChatBotMsg> response) {
                if(response.isSuccessful()){
                    ChatBotMsg msgmodel = response.body();
                    chatBotModelsArrayList.add(new ChatBotModels(msgmodel.getCnt(),KEY_BOT));
                    chatBotAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ChatBotMsg> call, Throwable t) {
                chatBotModelsArrayList.add(new ChatBotModels("I'm confused",KEY_BOT));
                chatBotAdapter.notifyDataSetChanged();
            }
        });

    }
}
