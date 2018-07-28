package jp.yt8492.saripaartestinkotlin

import android.content.Context
import android.widget.EditText
import com.mobsandgeeks.saripaar.QuickRule

class NotBlank : QuickRule<EditText>() {
    override fun getMessage(context: Context?): String = "Please write something"

    override fun isValid(view: EditText?): Boolean {
        view?.let { editText ->
            val str: String = editText.text.toString()
            return str.isNotBlank()
        } ?: return false
    }
}