package com.example.practice.module.setting.cardlogin

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.room.Room
import com.example.practice.R
import com.example.practice.base.BaseActivity
import com.example.practice.databinding.ActivityCardLoginBinding
import com.example.practice.room.MyDataBase

class CardLoginActivity : BaseActivity<ActivityCardLoginBinding>(ActivityCardLoginBinding::inflate)  {
    private lateinit var cardViewModel: CardLoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        val titleBackBtn = viewBinding.titleCardLogin.titleBtn
        val confirmBtn = viewBinding.confirmButton
        val cardNumEdt = viewBinding.number
        val dateEdt = viewBinding.date
        val cardNameEdt = viewBinding.cardName
        val securityEdt = viewBinding.securityCode
        val db = Room.databaseBuilder(applicationContext,MyDataBase::class.java,"myCardLogin.db").allowMainThreadQueries().build()
        //タイトルの戻るボタンを設定
        titleBackBtn.visibility = View.VISIBLE
        titleBackBtn.setImageResource(R.mipmap.white_back)
        titleBackBtn.setOnClickListener {
            finish()
        }
        //dbからデータを取得する
        cardViewModel = CardLoginViewModel(db)
        val cardLogin =  cardViewModel.getCardLastData()
        cardNumEdt.setText(cardLogin?.cardNum)
        dateEdt.setText(cardLogin?.date)
        cardNameEdt.setText(cardLogin?.cardName)
        securityEdt.setText(cardLogin?.securityCode)
        //登録確認画面
        confirmBtn.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("card_num", cardNumEdt.text.toString())
            bundle.putString("date", dateEdt.text.toString())
            bundle.putString("card_name", cardNameEdt.text.toString())
            bundle.putString("security_code", securityEdt.text.toString())
            val intent = Intent(this, CardLoginConfirmActivity().javaClass)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}