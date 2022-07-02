package com.example.orderapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.orderapp.util.hideKeyboard
import com.example.orderapp.util.isValidEmail
import com.example.orderapp.util.showKeyboard
import com.example.orderapp.util.toast
import com.example.orderapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var mBinding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()

    }

    private fun setListeners() {
        mBinding.btnSignup.setOnClickListener {
            if (isInputsValid()) {
                goNextScreen()
            }
        }
    }


    private fun isInputsValid(): Boolean {

        if (mBinding.etName.text.isNullOrBlank()) {
            toast("Lütfen adınızı giriniz")
            mBinding.etName.showKeyboard()
            return false
        }

        if (mBinding.etSurname.text.isNullOrBlank()) {
            toast("Lütfen soyadınızı giriniz")
            mBinding.etSurname.showKeyboard()
            return false
        }


        if (mBinding.etEmail.text.isNullOrBlank()) {
            toast("Lütfen mail adresi giriniz.")
            mBinding.etEmail.showKeyboard()
            return false
        }


        if (mBinding.etEmail.text.toString().isValidEmail().not()) {
            toast("Lütfen doğru formatta bir mail adresi giriniz.")
            mBinding.etEmail.showKeyboard()
            return false
        }


        if (mBinding.etPassword.text.isNullOrBlank()) {
            toast("Lütfen şifre giriniz.")
            mBinding.etPassword.showKeyboard()
            return false
        }


        if (mBinding.etPassword.text.toString().length < 8) {
            toast("Şifreniz 8 karakterden küçük olamaz.")
            mBinding.etPassword.showKeyboard()
            return false
        }


        if (mBinding.etEmail.text.toString().contains("@yalova.edu.tr")
                .not()
        ) {
            toast("Mail adresiniz yalova.edu.tr içermelidir.")
            mBinding.etEmail.showKeyboard()
            return false
        }

        hideKeyboard()


        if (mBinding.chkKvkk.isChecked.not()) {
            toast("KVKK metnini okuyup onaylamanız gerekmektedir.")
            return false
        }

        return true
    }



    private fun goNextScreen() {
        val userNameSurname = mBinding.etName.text.toString() + " " + mBinding.etSurname.text.toString()
        val email = mBinding.etEmail.text.toString()

        val action =
            com.example.orderapp.ui.HomeFragmentDirections.actionHomeFragmentToOrderFragment(
                userNameSurname,
                email
            )
        findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentHomeBinding.inflate(layoutInflater)
        return mBinding.root
    }

}