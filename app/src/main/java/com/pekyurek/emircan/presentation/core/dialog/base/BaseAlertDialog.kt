package com.pekyurek.emircan.presentation.core.dialog.base

import android.content.Context
import androidx.annotation.StyleRes
import androidx.appcompat.app.AlertDialog

open class BaseAlertDialog(
    context: Context,
    @StyleRes styleResId: Int = android.R.style.Theme_Material_Light_Dialog_NoActionBar
) : AlertDialog.Builder(context, styleResId)