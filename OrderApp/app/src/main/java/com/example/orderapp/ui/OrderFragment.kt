package com.example.orderapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.orderapp.util.loadImage
import com.example.orderapp.util.show
import com.example.orderapp.util.toast
import com.example.orderapp.R
import com.example.orderapp.databinding.FragmentOrderBinding


class OrderFragment : Fragment() {

    private lateinit var mBinding: FragmentOrderBinding
    private val args: com.example.orderapp.ui.OrderFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews()
        setListeners()
    }

    private fun setListeners() {
        mBinding.btnCalculate.setOnClickListener {
            if (isOrdersOptionSelected()) {
                val orderCost = calculateOrderCost()
                setOrderCost(orderCost)
            }
        }
    }



    private fun isOrdersOptionSelected(): Boolean {
        val NO_SELECTED_RADIO_BUTTON = -1

        if (mBinding.firstOrderOptions.checkedRadioButtonId == NO_SELECTED_RADIO_BUTTON) {
            toast("Lütfen birinci menüden bir opsiyon seçiniz")
            return false
        }

        if (mBinding.secondOrderOptions.checkedRadioButtonId == NO_SELECTED_RADIO_BUTTON) {
            toast("Lütfen ikinci menüden bir opsiyon seçiniz")
            return false
        }

        return true
    }


    private fun calculateOrderCost(): Int {
        var orderCost = 0
        orderCost += getFirstOrderCost()
        orderCost += getSecondOrderCost()
        return orderCost
    }

    private fun getFirstOrderCost(): Int {
        return when (mBinding.firstOrderOptions.checkedRadioButtonId) {
            R.id.first_small -> 10
            R.id.first_medium -> 20
            R.id.first_big -> 30
            else -> -999999
        }
    }

    private fun getSecondOrderCost(): Int {
        return when (mBinding.secondOrderOptions.checkedRadioButtonId) {
            R.id.second_small -> 7
            R.id.second_medium -> 13
            R.id.second_big -> 33
            else -> -999999
        }
    }

    private fun setOrderCost(orderCost: Int) {
        mBinding.tvCost.text = "Ödemeniz gereken ücret $orderCost ₺"
        mBinding.tvCost.show()
    }

    private fun initializeViews() {
        setGreetingMessage()
        setEmailAddress()
        setProfileImage()
    }


    private fun setGreetingMessage() {
        val incomeUserNameSurname = args.nameSurname
        val upperCasedUserNameSurname = incomeUserNameSurname.uppercase()
        val greetingMessage = "Hoşgeldin $upperCasedUserNameSurname"
        mBinding.tvWelcome.text = greetingMessage
    }

    private fun setEmailAddress() {
        mBinding.tvEmail.text = args.email
    }


    private fun setProfileImage() {
        val profileUrl = "https://picsum.photos/200"
        mBinding.imgProfile.loadImage(profileUrl)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentOrderBinding.inflate(layoutInflater)
        return mBinding.root
    }

}