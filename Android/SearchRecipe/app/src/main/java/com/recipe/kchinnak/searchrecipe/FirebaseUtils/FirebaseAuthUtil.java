package com.recipe.kchinnak.searchrecipe.FirebaseUtils;

import android.app.Activity;
import android.content.Context;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;

public class FirebaseAuthUtil {


    private static final FirebaseAuthUtil ourInstance = new FirebaseAuthUtil();
    private FirebaseAuth mAuth;

    public static FirebaseAuthUtil getInstance() {
        return ourInstance;
    }

    private FirebaseAuthUtil() {
        mAuth = FirebaseAuth.getInstance();
    }


    public FirebaseAuth getAuth() {
        return mAuth;
    }

}
