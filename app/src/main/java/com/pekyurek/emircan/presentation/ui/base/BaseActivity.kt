package com.pekyurek.emircan.presentation.ui.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewbinding.ViewBinding
import com.pekyurek.emircan.presentation.core.dialog.ExceptionDialog
import com.pekyurek.emircan.presentation.core.dialog.LoadingFragmentDialog

abstract class BaseActivity<Binding : ViewBinding, VM : BaseViewModel> : AppCompatActivity() {

    abstract val viewModel: VM
    abstract val layoutResId: Int

    protected lateinit var binding: Binding

    abstract fun onInit(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        getArgs()
        initViews()
        setObservers()
        onInit(savedInstanceState)
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, layoutResId)
    }

    open fun getArgs() {}

    open fun initViews() {}

    @CallSuper
    open fun setObservers() {
        viewModel.loading.observe(this) { showLoading ->
            if (showLoading) LoadingFragmentDialog.show(supportFragmentManager) else LoadingFragmentDialog.dismiss()
        }

        viewModel.showErrorPopup.observe(this) { exception ->
            ExceptionDialog(this, exception).show()
        }
    }
}