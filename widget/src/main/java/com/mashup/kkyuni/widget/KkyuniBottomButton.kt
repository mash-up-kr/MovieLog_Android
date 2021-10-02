package com.mashup.kkyuni.widget

import android.content.Context
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import androidx.appcompat.widget.AppCompatButton

class KkyuniBottomButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatButton(ContextThemeWrapper(context, R.style.KkyuniBottomButtonStyle), attrs)