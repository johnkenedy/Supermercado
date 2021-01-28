package com.canytech.supermercado

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        text_view_login_forgot_password.setOnClickListener(this)
        btn_login_sing_in.setOnClickListener(this)
        btn_login_sing_google.setOnClickListener(this)
        text_view_login_sing_up.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {
                R.id.text_view_login_forgot_password -> {
                }

                R.id.btn_login_sing_in -> {
                    loginRegisteredUser()
                }

                R.id.btn_login_sing_google -> {
                    Toast.makeText(
                        this@LoginActivity,
                        "Functionality not implemented",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                R.id.text_view_login_sing_up -> {
                    val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    private fun validateLoginDetails(): Boolean {
        return when {
            TextUtils.isEmpty(edit_text_login_email.text.toString().trim() { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.error_msg_enter_email), true)
                false
            }

            TextUtils.isEmpty(edit_text_login_password.text.toString().trim() { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.error_msg_enter_password), true)
                false
            }

            else -> {
                true
            }
        }
    }

    private fun loginRegisteredUser() {
        if (validateLoginDetails()) {

            showProgressDialog(resources.getString(R.string.please_wait))

            val email = edit_text_login_email.text.toString().trim { it <= ' ' }
            val password = edit_text_login_password.text.toString().trim { it <= ' ' }

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->

                    hideProgressDialog()

                    if (task.isSuccessful) {
                        showErrorSnackBar("You are logged in successfully.", false)

                    } else {
                        showErrorSnackBar(task.exception!!.message.toString(), true)
                    }
                }
        }
    }
}