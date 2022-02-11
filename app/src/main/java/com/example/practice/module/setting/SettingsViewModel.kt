package com.example.practice.module.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practice.R
import com.example.practice.data.SettingsData
import java.util.ArrayList

class SettingsViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is settings Fragment"
    }
    val text: LiveData<String> = _text

    fun getSettingsData():List<SettingsData> {
        val arrayList = ArrayList<SettingsData>()
        var data = SettingsData(R.mipmap.credit_card,"クレジットカード登録")
        var data1 = SettingsData(R.mipmap.setting_charge,"チャージ限度額変更")
        var data2 = SettingsData(R.mipmap.user_account,"アカウント情報変更")
        var data3 = SettingsData(R.mipmap.clothes,"アプリ着せ替え")
        var data4 = SettingsData(R.mipmap.del_user,"アカウント削除")
        arrayList.add(data)
        arrayList.add(data1)
        arrayList.add(data2)
        arrayList.add(data3)
        arrayList.add(data4)
        return arrayList
    }
}