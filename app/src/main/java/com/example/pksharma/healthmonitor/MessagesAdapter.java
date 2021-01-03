package com.example.pksharma.healthmonitor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesViewHolder> {

    ArrayList<String> messages;
    Context context;
    int type;

    public MessagesAdapter(ArrayList<String> messages, Context context, int type) {
        this.messages = messages;
        this.context = context;
        this.type = type;
    }

    @NonNull
    @Override
    public MessagesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View botRowLayout = inflater.inflate(R.layout.bot_row_layout,viewGroup,false);
        View userRowLayout = inflater.inflate(R.layout.user_row_layout,viewGroup,false);
        if(type==0){
            return new MessagesViewHolder(botRowLayout,0);
        }else{
            return new MessagesViewHolder(userRowLayout,1);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesViewHolder messagesViewHolder, int i) {
        messagesViewHolder.textView.setText(messages.get(i));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
