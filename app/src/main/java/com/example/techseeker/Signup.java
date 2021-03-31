package com.example.techseeker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    private EditText editFullName, editEmail, editPhone, editLocation, editPassword;
    private ProgressBar progressBar;
    private TextView signIn;
    private Button btnSignUp;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editFullName = findViewById(R.id.edit_full_name);
        editEmail = findViewById(R.id.edit_email);
        editPhone = findViewById(R.id.edit_phone);
        editLocation = findViewById(R.id.edit_location);
        editPassword = findViewById(R.id.edit_password);
        signIn = (TextView) findViewById(R.id.withAccount);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        btnSignUp = findViewById(R.id.btn_signup);

        mAuth = FirebaseAuth.getInstance();
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignIn();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            //handle the already login user
        }
    }

    public void openSignIn(){
        Intent intent = new Intent(this, Signin.class);
        startActivity(intent);
    }

    public void openHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    private void registerUser() {
        final String fullName =editFullName.getText().toString().trim();
        final String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        final String phone = editPhone.getText().toString().trim();
        final String location = editLocation.getText().toString().trim();

        if (fullName.isEmpty()) {
            editFullName.setError(getString(R.string.input_error_name));
            editFullName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editEmail.setError(getString(R.string.input_error_email));
            editEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmail.setError(getString(R.string.input_error_email_invalid));
            editEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editPassword.setError(getString(R.string.input_error_password));
            editPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editPassword.setError(getString(R.string.input_error_password_length));
            editPassword.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            editPhone.setError(getString(R.string.input_error_phone));
            editPhone.requestFocus();
            return;
        }

        if (phone.length() != 9) {
            editPhone.setError(getString(R.string.input_error_phone_invalid));
            editPhone.requestFocus();
            return;
        }


        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            User user = new User(
                                    fullName,
                                    email,
                                    phone,
                                    location
                            );

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Signup.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                                        openHome();


                                    } else {
                                        //display a failure message
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(Signup.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                }
        });
    }

}