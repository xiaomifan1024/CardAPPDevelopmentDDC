package com.example.practice.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * Activity base
 */
abstract class BaseActivity<T : ViewBinding>(val inflater: (inflater: LayoutInflater) -> T) : AppCompatActivity()  {

    protected lateinit var viewBinding: T

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = inflater(layoutInflater)
        setContentView(viewBinding.root)

    }

    @CallSuper
    override fun onStart() {
        super.onStart()

    }

    @CallSuper
    override fun onStop() {
        super.onStop()

    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
    }

}