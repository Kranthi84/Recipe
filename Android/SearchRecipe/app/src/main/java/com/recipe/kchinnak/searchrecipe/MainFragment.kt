package com.recipe.kchinnak.searchrecipe

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.FirebaseAuth
import com.recipe.kchinnak.searchrecipe.FirebaseUtils.AuthenticationUtil
import com.recipe.kchinnak.searchrecipe.Utils.DialogUtil
import com.recipe.kchinnak.searchrecipe.fragments.SignupFragment
import kotlinx.android.synthetic.main.login_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val mAuth = AuthenticationUtil.instance.mFirebaseAuth
    private var mListener: SignedUser? = null

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        signup_button.setOnClickListener {
            view?.let { Navigation.findNavController(it).navigate(R.id.signupFragment) }
        }

        if (arguments != null) {
            var userEmail = arguments?.getString(MainActivity.USERTAG)
            username.setText(userEmail)
        }

        mAuth?.currentUser?.let { mListener?.let { it.preSignedin() } }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        login_button.setOnClickListener {

            var email = username.text.toString()
            var password = password.text.toString()

            if (email.isNullOrBlank() || password.isNullOrBlank()) {
                Toast.makeText(activity, getString(R.string.blank_email_password), Toast.LENGTH_SHORT).show()
            } else {

                mAuth?.signInWithEmailAndPassword(email, password)?.addOnCompleteListener {
                    if (it.isSuccessful) {
                        if (mAuth?.currentUser!!.isEmailVerified)
                            view?.let { Navigation.findNavController(it).navigate(R.id.drawer_layout) }
                        else {
                            var mDialog = DialogUtil(context, activity?.layoutInflater)
                            mDialog.mVerifyEmailAlertDialog.show()

                            var dialogEmailTextView = mDialog.mVerifyEmailAlertDialog.findViewById<TextView>(R.id.verify_email)
                            dialogEmailTextView.text = mAuth?.currentUser?.email


                            var signOutButton = mDialog.mVerifyEmailAlertDialog.findViewById<Button>(R.id.buttonCancelVerifyEmail)

                            signOutButton.setOnClickListener {
                                mDialog.mVerifyEmailAlertDialog.dismiss()
                            }

                            var verifyButton = mDialog.mVerifyEmailAlertDialog.findViewById<Button>(R.id.buttonVerifyEmail)
                            var mUser = mAuth?.currentUser

                            verifyButton.setOnClickListener {
                                verifyButton.isEnabled = false
                                mUser?.sendEmailVerification()?.addOnCompleteListener {
                                    if (it.isSuccessful) {

                                        verifyButton.isEnabled = true
                                        mDialog.mVerifyEmailAlertDialog.dismiss()
                                        Toast.makeText(context, getString(R.string.email_verification_sent_toast) + mUser?.email, Toast.LENGTH_LONG).show()

                                    } else {
                                        Toast.makeText(context, getString(R.string.email_verification_notsent_toast) + mUser?.email, Toast.LENGTH_LONG).show()
                                    }
                                }
                            }
                        }
                    } else {
                        Toast.makeText(context, it.exception?.localizedMessage, Toast.LENGTH_LONG).show()
                    }

                }

            }
        }
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        if (activity is MainActivity)
            mListener = activity
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is MainActivity) {
            mListener = context
        }
    }

    interface SignedUser {
        fun preSignedin()
    }

}
