package com.example.lockv01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";
    public FirebaseAuth mAuth;
    EditText editTextEmail, editTextPassword;
    TextView errorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        mAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        errorView = findViewById(R.id.textView6);

    }

        public void signupOnClick(View view) {

           if (editTextEmail .getText().toString().contentEquals("")) {


               Toast toast = Toast.makeText(this, "Email can not be empty", Toast.LENGTH_LONG);
               View view1 = toast.getView();
               TextView text = (TextView) view1.findViewById(android.R.id.message);

               //Shadow of the Of the Text Color
               text.setBackgroundColor(Color.TRANSPARENT);
               text.setTextColor(Color.parseColor("#1D3557"));
               toast.show();


            } else if (editTextPassword.getText().toString().contentEquals("")) {


               Toast toast = Toast.makeText(this, "Password can not be empty", Toast.LENGTH_LONG);
               View view1 = toast.getView();
               TextView text = (TextView) view1.findViewById(android.R.id.message);

               //Shadow of the Of the Text Color
               text.setBackgroundColor(Color.TRANSPARENT);
               text.setTextColor(Color.parseColor("#1D3557"));
               toast.show();


            }
            else {


                mAuth.createUserWithEmailAndPassword(editTextEmail.getText().toString(), editTextPassword.getText().toString()).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            try {
                                if (user != null)
                                    user.sendEmailVerification()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Log.d(TAG, "Email sent.");

                                                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                                                SignUpActivity.this);

                                                        // set title
                                                        alertDialogBuilder.setTitle("Please Verify Your EmailID");

                                                        // set dialog message
                                                        alertDialogBuilder
                                                                .setMessage("A verification Email Is Sent To Your Registered EmailID, please click on the link and Sign in again!")
                                                                .setCancelable(false)
                                                                .setPositiveButton("Sign In", new DialogInterface.OnClickListener() {
                                                                    public void onClick(DialogInterface dialog, int id) {

                                                                        Intent signInIntent = new Intent(SignUpActivity.this, SignInActivity.class);
                                                                        SignUpActivity.this.finish();
                                                                    }
                                                                });

                                                        // create alert dialog
                                                        AlertDialog alertDialog = alertDialogBuilder.create();

                                                        // show it
                                                        alertDialog.show();


                                                    }
                                                }
                                            });

                            } catch (Exception e) {
                                errorView.setText(e.getMessage());
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast toast = Toast.makeText(SignUpActivity.this, "Authentication failed.", Toast.LENGTH_SHORT);
                            View view = toast.getView();
                            TextView text = (TextView) view.findViewById(android.R.id.message);

                            //Shadow of the Of the Text Color
                            text.setBackgroundColor(Color.TRANSPARENT);
                            text.setTextColor(Color.parseColor("#1D3557"));
                            toast.show();

                            if (task.getException() != null) {
                                errorView.setText(task.getException().getMessage());
                            }

                        }

                    }
                });

            }

        }
}