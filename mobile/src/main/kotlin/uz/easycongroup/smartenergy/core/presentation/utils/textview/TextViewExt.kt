package uz.easycongroup.smartenergy.core.presentation.utils.textview

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView

var EditText.distinctText
    get() = this.text.toString()
    set(value) {
        if (TextUtils.equals(value, this.text.toString())) return
        setTextDistinct(value)
    }

fun EditText.setTextDistinct(newText: CharSequence): Boolean {
    val isCursorPosGreaterThanTextLen = selectionStart > newText.length
    val isCursorAtTheEnd = selectionStart == length()
    tag = false
    val isChanged = (this as TextView).setTextDistinct(newText)
    val isFormatterWork = newText.length < text.length
    val newCursorPos = when {
        isFormatterWork -> text.length
        isCursorPosGreaterThanTextLen || isCursorAtTheEnd -> newText.length
        else -> selectionStart
    }
    if (isChanged) setSelection(newCursorPos)
    tag = true
    return isChanged
}

fun EditText.addTextChangedListener(listener: (String) -> Unit) {
    setTextChangedListener { listener(it) }
}

fun EditText.setTextChangedListener(onTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            if (tag != false) {
                removeTextChangedListener(this)
                onTextChanged.invoke(s?.toString() ?: "")
                addTextChangedListener(this)
            }
        }
    })
}

var TextView.distinctText: CharSequence
    get() = text
    set(value) {
        setTextDistinct(value)
    }

fun TextView.setTextDistinct(newText: CharSequence): Boolean {
    if (TextUtils.equals(newText, text)) return false
    text = newText
    return true
}

