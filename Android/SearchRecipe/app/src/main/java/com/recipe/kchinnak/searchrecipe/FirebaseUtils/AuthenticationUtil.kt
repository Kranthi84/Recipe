package com.recipe.kchinnak.searchrecipe.firebaseUtils

import com.google.firebase.auth.FirebaseAuth

class AuthenticationUtil private constructor() {

    private object Holder {
        val ourInstance = AuthenticationUtil()
    }

    companion object {
        val instance: AuthenticationUtil by lazy { Holder.ourInstance }
    }

    var mFirebaseAuth: FirebaseAuth? = null

    init {
        mFirebaseAuth = FirebaseAuth.getInstance()
    }
}