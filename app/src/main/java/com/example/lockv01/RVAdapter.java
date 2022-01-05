package com.example.lockv01;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.security.AccessController;
import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context context;
    ArrayList<Password> list = new ArrayList<>();
    public RVAdapter(Context ctx)
    {
        this.context = ctx;
    }
    public void setItems(ArrayList<Password> emp)
    {
        list.addAll(emp);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.password_list,parent,false);
        return new PasswordVH(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        Password e = null;
        this.onBindViewHolder(holder,position,e);
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, Password e)
    {
        PasswordVH vh = (PasswordVH) holder;
        Password emp = e==null? list.get(position):e;
        vh.txt_app.setText(emp.getapp());
        vh.txt_username.setText(emp.getusername());
        vh.txt_password.setText("***********");
        vh.txt_password.setOnClickListener(v -> {
            ClipboardManager myClipboard = (ClipboardManager) v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData myClip = ClipData.newPlainText("label", ((PasswordVH) holder).txt_password.getText().toString());
            myClipboard.setPrimaryClip(myClip);
            Toast.makeText(v.getContext(), "Copied to clipboard" , Toast.LENGTH_SHORT).show();
        });
        vh.txt_option.setOnClickListener(v->
        {
            PopupMenu popupMenu =new PopupMenu(context,vh.txt_option);
            popupMenu.inflate(R.menu.option_menu);
            popupMenu.setOnMenuItemClickListener(item->
            {
                switch (item.getItemId())
                {
                    case R.id.menu_show:
                        vh.txt_password.setText(emp.getpassword());
                        break;
                    case R.id.menu_edit:
                        Intent intent=new Intent(context,EditPassword.class);
                        intent.putExtra("EDIT", emp);
                        context.startActivity(intent);
                        break;
                    case R.id.menu_remove:
                        DAOPassword dao=new DAOPassword();
                        dao.remove(emp.getKey()).addOnSuccessListener(suc->
                        {
                            Toast.makeText(context, "Record is removed", Toast.LENGTH_SHORT).show();
                            notifyItemRemoved(position);
                            list.remove(emp);
                        }).addOnFailureListener(er->
                        {
                            Toast.makeText(context, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
                        });
                        break;
                }
                return false;
            });
            popupMenu.show();
        });
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }
}