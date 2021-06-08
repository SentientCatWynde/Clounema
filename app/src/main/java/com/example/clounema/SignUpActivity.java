package com.example.clounema;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity{

    private Button signUpBtn, signInGoogle;
    //private static final int RC_SIGN_IN = 123;
    //private GoogleSignInClient mGoogleSignIn;
    private EditText mEmail, mPass, mName;
    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private DatabaseReference root;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        backBtn = findViewById(R.id.btnBack);
        mName = findViewById(R.id.etName);
        mEmail = findViewById(R.id.etEmail);
        mPass = findViewById(R.id.etPass);
        //signInGoogle = findViewById(R.id.btn_google);
        signUpBtn = findViewById(R.id.btn_sign_up1);

        // Write a message to the database
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference root = db.getReference().child("Users");

        mAuth = FirebaseAuth.getInstance();
        //createRequest();
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
        /*signInGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInGoogle();
            }
        });*/
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    /*private void createRequest(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignIn = GoogleSignIn.getClient(this, gso);
    }

    private void signInGoogle() {
        Intent signInInt = mGoogleSignIn.getSignInIntent();
        startActivityForResult(signInInt, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthGoogle(account.getIdToken());
            }catch (ApiException e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    startActivity(new Intent(SignUpActivity.this, HomeActivity.class));
                    finish();
                }else {
                    Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null){
            startActivity(new Intent(SignUpActivity.this, HomeActivity.class));
            finish();
        }
    }*/

    private void createUser() {
        String name = mName.getText().toString().trim();
        String email = mEmail.getText().toString().trim();
        String pass = mPass.getText().toString().trim();

        if(!name.isEmpty()) {
            mName.setError("Name is required!");
            mName.requestFocus();
            return;
        }
        if(!email.isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("Please provide a valid email");
            mEmail.requestFocus();
            return;
        }
        if(!pass.isEmpty() && pass.length() < 6) {
            mPass.setError("Password at least 6 characters");
            mPass.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Users users = new Users(name, email, pass);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(SignUpActivity.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                    //redirect to login activity
                                    }else {
                                        Toast.makeText(SignUpActivity.this,"Failed to register", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(SignUpActivity.this,"Failed to register", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}