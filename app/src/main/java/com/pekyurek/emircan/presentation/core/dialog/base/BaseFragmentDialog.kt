package com.pekyurek.emircan.presentation.core.dialog.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragmentDialog<Binding : ViewBinding> : DialogFragment() {

    abstract val layoutResId: Int

    lateinit var binding: Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = false
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), layoutResId, null, false)
        return binding.root
    }

}