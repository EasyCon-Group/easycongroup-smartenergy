package uz.easycongroup.smartenergy.core.presentation.customview.inputview

import android.content.Context
import android.text.InputType.*
import android.text.method.DigitsKeyListener
import android.util.AttributeSet
import android.view.Gravity.START
import android.view.inputmethod.EditorInfo.IME_ACTION_DONE
import android.widget.LinearLayout
import androidx.core.content.withStyledAttributes
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textfield.TextInputLayout.END_ICON_NONE
import uz.easycongroup.smartenergy.R
import uz.easycongroup.smartenergy.core.presentation.mask.InputMask
import uz.easycongroup.smartenergy.core.presentation.mask.MaskFormat
import uz.easycongroup.smartenergy.core.presentation.utils.textview.distinctText
import uz.easycongroup.smartenergy.core.presentation.utils.textview.setTextChangedListener
import uz.easycongroup.smartenergy.databinding.ViewInputFieldBinding

open class InputFieldView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    var actionDoneListener: (() -> Unit)? = null
    var actionListener: (() -> Unit)? = null
        set(value) {
            field = value
            if (value != null) {
                binding.inputTil.setEndIconOnClickListener {
                    actionListener?.invoke()
                }
            }
        }

    var labelText: String = ""
        set(value) {
            field = value
            binding.labelTv.text = value
            binding.labelTv.isVisible = value.isNotEmpty()
        }

    var hintText: String = ""
        set(value) {
            field = value
            binding.inputEt.hint = value
        }

    var distinctText: String
        get() = binding.inputEt.distinctText
        set(value) {
            binding.inputEt.distinctText = value
        }

    var text: String
        get() = binding.inputEt.text?.toString() ?: ""
        set(value) {
            binding.inputEt.setText(value)
        }

    @TextInputLayout.EndIconMode
    var endIconMode: Int
        get() = binding.inputTil.endIconMode
        set(value) {
            binding.inputTil.endIconMode = value
        }

    var errorText: String? = null
        set(value) {
            field = value
            renderTexts()
        }

    var helperText: String? = null
        set(value) {
            field = value
            renderTexts()
        }

    var inputType: Int
        get() = binding.inputEt.inputType
        set(value) {
            binding.inputEt.inputType = value
        }

    private val binding by lazy(LazyThreadSafetyMode.NONE) { ViewInputFieldBinding.bind(this) }

    init {
        orientation = VERTICAL
        inflate(context, R.layout.view_input_field, this)
        initAttrs(attrs)
        initListeners()
    }

    /**
     * Добавляет слушатель изменения текста.
     */
    fun addTextChangedListener(listener: (String) -> Unit) {
        binding.inputEt.setTextChangedListener { listener(it) }
    }

    /**
     * Устанавливает маску и слушатель изменения поле ввода.
     * Слушатель вызывается для отформатированной строки.
     */
    fun setMask(
        format: MaskFormat,
        shouldShowEmptySlots: Boolean = false,
        isSwap: Boolean = false
    ) {
        InputMask.install(
            mask = format,
            editText = binding.inputEt,
            shouldShowEmptySlots = shouldShowEmptySlots,
            isSwap = isSwap
        )
    }

    fun setAcceptedCharacters(keyListener: String) {
        binding.inputEt.keyListener = DigitsKeyListener.getInstance(keyListener)
    }

    val editText: TextInputEditText
        get() = binding.inputEt

    private fun initAttrs(attrs: AttributeSet?) {
        attrs ?: return
        context.withStyledAttributes(attrs, R.styleable.InputFieldView) {
            endIconMode = getInteger(
                R.styleable.InputFieldView_endIconMode,
                END_ICON_NONE
            )
            binding.inputTil.endIconDrawable =
                getDrawable(R.styleable.InputFieldView_endIconDrawable)
            binding.inputEt.hint =
                getString(R.styleable.InputFieldView_android_hint) ?: ""
            binding.inputEt.imeOptions =
                getInteger(R.styleable.InputFieldView_android_imeOptions, IME_ACTION_DONE)
            helperText = getString(R.styleable.InputFieldView_helperText)
            labelText = getString(R.styleable.InputFieldView_labelText) ?: ""
            binding.inputTil.boxBackgroundColor = getColor(
                R.styleable.InputFieldView_boxBackgroundColor,
                context.getColor(R.color.color_input_background_light)
            )
            binding.inputEt.gravity =
                getInteger(R.styleable.InputFieldView_android_gravity, START)


            val minLines = getInteger(R.styleable.InputFieldView_android_minLines, 1)
            val maxLines = getInteger(R.styleable.InputFieldView_android_maxLines, -1)

            val singleLineInputType = TYPE_CLASS_TEXT or TYPE_TEXT_FLAG_AUTO_COMPLETE
            val multilineInputType =
                singleLineInputType or TYPE_TEXT_FLAG_MULTI_LINE or TYPE_TEXT_FLAG_IME_MULTI_LINE

            val attrOrDefaultInputType =
                if (minLines > 1)
                    getInteger(R.styleable.InputFieldView_android_inputType, multilineInputType)
                else getInteger(R.styleable.InputFieldView_android_inputType, singleLineInputType)

            inputType = attrOrDefaultInputType or TYPE_TEXT_FLAG_NO_SUGGESTIONS

            binding.inputEt.minLines = minLines

            if (maxLines > 0)
                binding.inputEt.maxLines = maxLines
        }
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        with(binding) {
            inputEt.apply {
//                isEnabled = enabled
                isFocusable = enabled
                isClickable = enabled
                isFocusableInTouchMode = enabled
            }

            viewDisabled.isVisible = !enabled
            if (enabled) viewDisabled.setOnClickListener(null)
            else viewDisabled.setOnClickListener { actionListener?.invoke() }

        }
    }

    private fun initListeners() {
        binding.inputEt.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                IME_ACTION_DONE -> actionDoneListener?.invoke()?.let { true } ?: false
                else -> false
            }
        }
    }

    private fun renderTexts() = with(binding) {
//        val labelColorAttr =
//            if (errorText == null) { R.color.app_text_color_primary }
//            else { R.color.app_text_color_error }
//
//        labelTv.setTextColor(labelColorAttr)
        errorTv.isVisible = errorText != null
        // Это сделано для того чтобы всегда вью всегда занимал место для вывода сообщений
        if (helperText == null && errorText == null) {
            helperTv.isInvisible = true
        } else {
            helperTv.isVisible = errorText == null && helperText != null
        }
        helperTv.text = helperText
        errorTv.text = errorText
    }
}