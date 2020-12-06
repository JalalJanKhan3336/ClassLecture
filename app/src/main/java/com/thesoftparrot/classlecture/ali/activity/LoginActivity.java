package com.thesoftparrot.classlecture.ali.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.thesoftparrot.classlecture.ali.model.Friend;
import com.thesoftparrot.classlecture.databinding.ActivityLoginBinding;
import com.thesoftparrot.classlecture.shaharyar.AuthActivity;
import com.thesoftparrot.classlecture.test.MainActivity;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        click();
    }

    private void click() {
        mBinding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mBinding.emailEt.getText().toString().trim();
                String password = mBinding.passwordEt.getText().toString().trim();

                if(isCredentialOK(email, password)){
                    authUser(email, password);
                }

            }
        });
    }

    private boolean isCredentialOK(String email, String password) {
        if(email == null || TextUtils.isEmpty(email)){
            mBinding.emailEt.setError("Email is missing!");
            return false;
        }

        if(password == null || TextUtils.isEmpty(password)){
            mBinding.passwordEt.setError("Password is missing!");
            return false;
        }

        mBinding.emailEt.setError(null);
        mBinding.passwordEt.setError(null);

        return true;
    }

    private void authUser(final String email, String password) {
        FirebaseAuth
                .getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Snackbar.make(mBinding.getRoot(), "Login successful!", Snackbar.LENGTH_LONG).show();
                        FirebaseAuth auth = FirebaseAuth.getInstance();
                        FirebaseUser currentUser = auth.getCurrentUser();
                        if(currentUser != null){
                            String userId = currentUser.getUid();

                            Friend friend = new Friend(25, userId, email, null);
                            addToFriendList(userId, friend);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(mBinding.getRoot(), "Login failed!", Snackbar.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        subscribeTopic();
    }

    private void subscribeTopic() {
        FirebaseMessaging fcm = FirebaseMessaging.getInstance();

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser != null) {
            String uid = firebaseUser.getUid();
            Log.d("TAG", "subscribeTopic_Uid: "+uid);

            fcm.subscribeToTopic("/topics/"+uid);
            fcm.subscribeToTopic("/topics/adventure/2dgame");
        }
    }

    private void addToFriendList(String userId, Friend friendInfo){
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        rootRef
                .child("Friends")
                .child(userId)
                .setValue(friendInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(LoginActivity.this, "Added to Friends", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, NoteActivity.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(mBinding.getRoot(), "Something went wrong...", Snackbar.LENGTH_LONG).show();
                    }
                });
    }

}