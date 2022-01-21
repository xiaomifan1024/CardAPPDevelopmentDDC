package com.example.practice.module

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.practice.R
import com.example.practice.base.BaseActivity
import com.example.practice.databinding.ActivityMainBinding
import com.example.practice.databinding.ActivityPushTestBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class PushTestActivity : BaseActivity<ActivityPushTestBinding>(ActivityPushTestBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.extras?.let {
            for (key in it.keySet()) {
                val value = intent.extras!!.get(key)
                Log.d(TAG, "Key: $key Value: $value")
            }
        }
        viewBinding.titleTest.title.text = "プッシュ通知"
        viewBinding.notificationsTitle.text = "Msgタイトルは："+ intent.extras?.getString("title")
        viewBinding.notificationsMsg.text = "Msg Bodyは："+intent.extras?.getString("msg")
    }
    companion object {
        private const val TAG = "PushTestActivity"
    }
}