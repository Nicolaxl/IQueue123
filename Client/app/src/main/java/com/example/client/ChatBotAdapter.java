package com.example.client;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatBotAdapter extends RecyclerView.Adapter {

   private ArrayList<ChatBotModels> chatBotModelsArrayList;
   private Context cont;

    public ChatBotAdapter(ArrayList<ChatBotModels> chatBotModelsArrayList, Context cont) {
        this.chatBotModelsArrayList = chatBotModelsArrayList;
        this.cont = cont;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch(viewType){
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.human_msg,parent,false);
                return new UserViewHolder(view);
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.robot_msg,parent,false);
                return new BotViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatBotModels chatBotModels = chatBotModelsArrayList.get(position);
        switch (chatBotModels.getSender()){
            case "user":
                ((UserViewHolder)holder).user.setText(chatBotModels.getMessage());
                break;
            case "bot":
                ((BotViewHolder)holder).robot.setText(chatBotModels.getMessage());
                break;
        }
    }

    @Override
    public int getItemViewType(int position){
        switch (chatBotModelsArrayList.get(position).getSender()){
            case "user":
                return 0;
            case "bot":
                return 1;
            default:
                return -1;
        }
    }

    @Override
    public int getItemCount() {
        return chatBotModelsArrayList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{
        TextView user;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.sender);
        }
    }

    public static class BotViewHolder extends RecyclerView.ViewHolder{
        TextView robot;
        public BotViewHolder(@NonNull View itemView) {
            super(itemView);
            robot = itemView.findViewById(R.id.recv);
        }
    }
}
