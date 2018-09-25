package com.recipe.kchinnak.searchrecipe.firebaseUtils;

import com.google.firebase.auth.FirebaseAuth;

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
