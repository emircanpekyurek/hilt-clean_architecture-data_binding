package com.pekyurek.emircan.presentation.core.dialog

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.pekyurek.emircan.R
import com.pekyurek.emircan.databinding.DialogLoadingBinding
import com.pekyurek.emircan.presentation.core.dialog.base.BaseFragmentDialog

object LoadingFragmentDialog : BaseFragmentDialog<DialogLoadingBinding>() {

    override val layoutResId: Int = R.layout.dialog_loading

    val TAG = LoadingFragmentDialog::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.LoadingDialog)
    }

    fun show(fragmentManager: FragmentManager) {
        if (dialog?.isShowing != true) {
            show(fragmentManager, TAG)
        }
    }

    override fun dismiss() {
        if (dialog?.isShowing == true) {
            super.dismiss()
        }
    }
}