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

    public MessagesAdapter(ArrayList<String> messages, Context context) {
        this.messages = messages;
        this.context = context;
    }

    @NonNull
    @Override
    public MessagesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View botRowLayout = inflater.inflate(R.layout.bot_row_layout,viewGroup,false);
       // View userRowLayout = inflater.inflate(R.layout.user_chat_layout,viewGroup,false);
        return new MessagesViewHolder(botRowLayout);
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
