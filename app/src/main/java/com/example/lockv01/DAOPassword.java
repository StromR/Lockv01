package com.example.lockv01;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOPassword
{
    private DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    public DAOPassword()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://lockv01-6dc34-default-rtdb.asia-southeast1.firebasedatabase.app");
        mAuth = FirebaseAuth.getInstance();
        databaseReference = db.getReference().child(mAuth.getCurrentUser().getUid());
    }
    public Task<Void> add(Password emp)
    {
        return databaseReference.push().setValue(emp);
    }

    public Task<Void> update(String key, HashMap<String ,Object> hashMap)
    {
        return databaseReference.child(key).updateChildren(hashMap);
    }
    public Task<Void> remove(String key)
    {
        return databaseReference.child(key).removeValue();
    }

    public Query get(String key)
    {
        if(key == null)
        {
            return databaseReference.orderByKey().limitToFirst(8);
        }
        return databaseReference.orderByKey().startAfter(key).limitToFirst(8);
    }

    public Query get()
    {
        return databaseReference;
    }
}