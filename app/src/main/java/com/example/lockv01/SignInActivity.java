package com.example.lockv01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class SignInActivity extends AppCompatActivity {

    private static final String TAG = "SignInActivity";
    public FirebaseAuth mAuth;
    EditText editTextEmail1, editTextPassword1;
    TextView errorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        editTextEmail1 = findViewById(R.id.editTextEmail);
        editTextPassword1 = findViewById(R.id.editTextPassword);
        errorView = findViewById(R.id.textView7);

        mAuth = FirebaseAuth.getInstance();
    }

    public void signinOnClick(View view) {

            if (editTextEmail1.getText().toString().contentEquals("")) {


                errorView.setText("Email cant be empty");


            } else if (editTextPassword1.getText().toString().contentEquals("")) {

                errorView.setText("Password cant be empty");

            } else {


                mAuth.signInWithEmailAndPassword(editTextEmail1.getText().toString(), editTextPassword1.getText().toString())
                        .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");

                                    FirebaseUser user = mAuth.getCurrentUser();

                                    if (user != null) {
                                        if (user.isEmailVerified()) {


                                            System.out.println("Email Verified : " + user.isEmailVerified());
                                            Intent HomeActivity = new Intent(SignInActivity.this, RVActivity.class);
                                            setResult(RESULT_OK, null);
                                            startActivity(HomeActivity);
                                            SignInActivity.this.finish();


                                        } else {

                                            Toast.makeText(SignInActivity.this, "Please Verify your Email ID and SignIn.",
                                                    Toast.LENGTH_SHORT).show();

                                        }
                                    }

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(SignInActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    if (task.getException() != null) {
                                        errorView.setText(task.getException().getMessage());
                                    }

                                }

                            }
                        });


            }


        };


    public void signupTextClick(View view) {
        Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    public void forgotpasswordTextClick(View view) {
        Intent forgotPasswordActivity = new Intent(SignInActivity.this, ForgotPasswordActivity.class);
        startActivity(forgotPasswordActivity);
        SignInActivity.this.finish();

    }
}