package com.example.lockv01;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PasswordVH extends RecyclerView.ViewHolder
{
    public TextView txt_app,txt_username, txt_password,txt_option;
    public PasswordVH(@NonNull View itemView)
    {
        super(itemView);
        txt_app = itemView.findViewById(R.id.txt_app);
        txt_username = itemView.findViewById(R.id.txt_username);
        txt_password = itemView.findViewById(R.id.txt_password);
        txt_option = itemView.findViewById(R.id.txt_option);
    }
}
