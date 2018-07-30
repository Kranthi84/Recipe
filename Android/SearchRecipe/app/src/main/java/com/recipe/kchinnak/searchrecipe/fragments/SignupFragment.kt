package com.recipe.kchinnak.searchrecipe.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.recipe.kchinnak.searchrecipe.FirebaseUtils.AuthenticationUtil
import com.recipe.kchinnak.searchrecipe.MainActivity
import com.recipe.kchinnak.searchrecipe.R
import com.recipe.kchinnak.searchrecipe.Utils.DialogUtil


class SignupFragment : Fragment() {

    companion object {
        fun newInstance() = SignupFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.signup_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var mAuth: FirebaseAuth? = AuthenticationUtil.instance.mFirebaseAuth

        var registerButton = view.findViewById<Button>(R.id.register_button)
        registerButton.setOnClickListener {
            view?.let { createUser(mAuth) }
        }
    }

    fun createUser(mAuth: FirebaseAuth?) {

        var registerEmail = view?.findViewById<EditText>(R.id.register_email)
        var email: String? = registerEmail?.text.toString()
        var registerPassword = view?.findViewById<EditText>(R.id.register_password)
        var password: String? = registerPassword?.text.toString()

        if (email.isNullOrBlank() || password.isNullOrBlank()) {
            Toast.makeText(activity, getString(R.string.blank_email_password), Toast.LENGTH_SHORT).show()
        } else {
            mAuth?.createUserWithEmailAndPassword(email!!, password!!)!!.addOnCompleteListener(activity!!) {
                if (it.isSuccessful) {
                    var mUser: FirebaseUser? = mAuth?.currentUser
                    updateTheUser(mUser)
                } else {
                    Log.d(it.toString(), getString(R.string.user_profile_error))
                }
            }
        }
    }

    fun updateTheUser(mUser: FirebaseUser?) {

        var profile: UserProfileChangeRequest? = UserProfileChangeRequest.Builder().setDisplayName("Medha Chinnakotla").build()
        if (mUser != null) {
            mUser.updateProfile(profile!!)

            var userbundle: Bundle = Bundle()
            userbundle.putString(MainActivity.USERTAG, mUser.email)

            var mDialog = DialogUtil(context, activity?.layoutInflater)

            mDialog.mVerifyEmailAlertDialog.show()

            var dialogEmailTextView = mDialog.mVerifyEmailAlertDialog.findViewById<TextView>(R.id.verify_email)
            dialogEmailTextView.text = mUser.email

            var signOutButton = mDialog.mVerifyEmailAlertDialog.findViewById<Button>(R.id.buttonCancelVerifyEmail)

            signOutButton.setOnClickListener {
                mDialog.mVerifyEmailAlertDialog.dismiss()

                view?.let { Navigation.findNavController(it).navigate(R.id.main, userbundle) }
            }

            var verifyButton = mDialog.mVerifyEmailAlertDialog.findViewById<Button>(R.id.buttonVerifyEmail)

            verifyButton.setOnClickListener {
                verifyButton.isEnabled = false
                mUser.sendEmailVerification().addOnCompleteListener {
                    if (it.isSuccessful) {

                        verifyButton.isEnabled = true
                        mDialog.mVerifyEmailAlertDialog.dismiss()
                        Toast.makeText(context, getString(R.string.email_verification_sent_toast) + mUser.email, Toast.LENGTH_LONG).show()
                        view?.let { Navigation.findNavController(it).navigate(R.id.main, userbundle) }

                    } else {
                        Toast.makeText(context, getString(R.string.email_verification_notsent_toast) + mUser.email, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}