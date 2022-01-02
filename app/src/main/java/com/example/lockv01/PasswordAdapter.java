package com.example.lockv01;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PasswordAdapter extends RecyclerView.Adapter<PasswordAdapter.PasswordViewHolder> {

    private ArrayList<Password> dataList;

    public PasswordAdapter(ArrayList<Password> dataList) {
        this.dataList = dataList;
    }

    @Override
    public PasswordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_view, parent, false);
        return new PasswordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PasswordViewHolder holder, int position) {
        holder.textApp.setText(dataList.get(position).getapp());
        holder.textPassword.setText(dataList.get(position).getpassword());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class PasswordViewHolder extends RecyclerView.ViewHolder{
        private TextView textApp, textId, textPassword;

        public PasswordViewHolder(View itemView) {
            super(itemView);
            textApp = (TextView) itemView.findViewById(R.id.textApp);
            textId = (TextView) itemView.findViewById(R.id.textId);
            textPassword = (TextView) itemView.findViewById(R.id.textPassword);
        }
    }
}
