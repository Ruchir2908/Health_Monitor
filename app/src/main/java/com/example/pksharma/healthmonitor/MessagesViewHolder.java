package com.example.pksharma.healthmonitor;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MessagesViewHolder extends RecyclerView.ViewHolder {

    TextView textView;

    public MessagesViewHolder(@NonNull View itemView, int type) {
        super(itemView);

        if(type==0){
            textView = itemView.findViewById(R.id.botTextView);
        }else{
            textView = itemView.findViewById(R.id.userTextView);
        }
    }
}