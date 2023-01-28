package uz.easycongroup.smartenergy.presentation.utils

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import uz.easycongroup.smartenergy.R

fun Context.showErrorAlertDialog(
    title: String? = null,
    message: String,
    onButtonClicked: (dialog: DialogInterface) -> Unit = { dialog -> dialog.dismiss() },
    onDismissListener: () -> Unit = { }
) {
    val dialogView: View = LayoutInflater.from(this).inflate(R.layout.dialog_error, null)

    val builder = MaterialAlertDialogBuilder(this, R.style.AppTheme_MaterialAlertDialog_Rounded)
    builder.setView(dialogView)
    val alertDialog = builder.create().apply { setOnDismissListener { onDismissListener.invoke() } }

    val dialogTitle = title ?: getString(R.string.message_title_error)
    dialogView.findViewById<TextView>(R.id.tv_dialog_title).text = dialogTitle
    dialogView.findViewById<TextView>(R.id.tv_dialog_message).text = message
    dialogView.findViewById<View>(R.id.btn_dialog_close).setOnClickListener {
        onButtonClicked.invoke(alertDialog)
    }

    alertDialog.show()
}

fun Fragment.showErrorAlertDialog(
    title: String? = null,
    message: String,
    onPositiveButtonClicked: (dialog: DialogInterface) -> Unit = { dialog -> dialog.dismiss() },
    onDismissListener: () -> Unit = { }
) = requireContext().showErrorAlertDialog(
    title,
    message,
    onPositiveButtonClicked,
    onDismissListener
)


fun Context.showWarningAlertDialog(
    title: String? = null,
    message: String,
    positiveButtonTitle: String? = null,
    negativeButtonTitle: String? = null,
    onPositiveButtonClicked: (dialog: DialogInterface) -> Unit = { dialog -> dialog.dismiss() },
    onNegativeButtonClicked: (dialog: DialogInterface) -> Unit = { dialog -> dialog.dismiss() },
    onDismissListener: () -> Unit = { }
) {
    val dialogView: View = LayoutInflater.from(this).inflate(R.layout.dialog_warning, null)

    val builder = MaterialAlertDialogBuilder(this, R.style.AppTheme_MaterialAlertDialog_Rounded)
    builder.setView(dialogView)
    val alertDialog = builder.create().apply { setOnDismissListener { onDismissListener.invoke() } }

    val dialogTitle = title ?: getString(R.string.message_title_warning)
    dialogView.findViewById<TextView>(R.id.tv_dialog_title).text = dialogTitle
    dialogView.findViewById<TextView>(R.id.tv_dialog_message).text = message

    dialogView.findViewById<Button>(R.id.btn_dialog_cancel).text =
        negativeButtonTitle ?: getString(R.string.cancel)
    dialogView.findViewById<Button>(R.id.btn_dialog_cancel).setOnClickListener {
        onNegativeButtonClicked.invoke(alertDialog)
    }

    dialogView.findViewById<Button>(R.id.btn_dialog_confirm).text =
        positiveButtonTitle ?: getString(R.string.confirm)
    dialogView.findViewById<Button>(R.id.btn_dialog_confirm).setOnClickListener {
        onPositiveButtonClicked.invoke(alertDialog)
    }

    alertDialog.show()
}

fun Fragment.showWarningAlertDialog(
    title: String? = null,
    message: String,
    positiveButtonTitle: String? = null,
    negativeButtonTitle: String? = null,
    onPositiveButtonClicked: (dialog: DialogInterface) -> Unit = { dialog -> dialog.dismiss() },
    onNegativeButtonClicked: (dialog: DialogInterface) -> Unit = { dialog -> dialog.dismiss() },
    onDismissListener: () -> Unit = { }
) = requireContext().showWarningAlertDialog(
    title = title,
    message = message,
    positiveButtonTitle = positiveButtonTitle,
    negativeButtonTitle = negativeButtonTitle,
    onPositiveButtonClicked = onPositiveButtonClicked,
    onNegativeButtonClicked = onNegativeButtonClicked,
    onDismissListener = onDismissListener
)

fun Context.showDefaultAlertDialog(
    title: String? = null,
    message: String,
    positiveButtonTitle: String? = null,
    negativeButtonTitle: String? = null,
    onPositiveButtonClicked: (dialog: DialogInterface) -> Unit = { dialog -> dialog.dismiss() },
    onNegativeButtonClicked: (dialog: DialogInterface) -> Unit = { dialog -> dialog.dismiss() },
    onDismissListener: () -> Unit = { }
) {
    val dialogView: View = LayoutInflater.from(this).inflate(R.layout.dialog_default, null)

    val builder = MaterialAlertDialogBuilder(this, R.style.AppTheme_MaterialAlertDialog_Rounded)
    builder.setView(dialogView)
    val alertDialog = builder.create().apply { setOnDismissListener { onDismissListener.invoke() } }

    val dialogTitle = title ?: getString(R.string.message_title_warning)
    dialogView.findViewById<TextView>(R.id.tv_dialog_title).text = dialogTitle
    dialogView.findViewById<TextView>(R.id.tv_dialog_message).text = message

    dialogView.findViewById<Button>(R.id.btn_dialog_cancel).text =
        negativeButtonTitle ?: getString(R.string.cancel)
    dialogView.findViewById<Button>(R.id.btn_dialog_cancel).setOnClickListener {
        onNegativeButtonClicked.invoke(alertDialog)
    }

    dialogView.findViewById<Button>(R.id.btn_dialog_confirm).text =
        positiveButtonTitle ?: getString(R.string.confirm)
    dialogView.findViewById<Button>(R.id.btn_dialog_confirm).setOnClickListener {
        onPositiveButtonClicked.invoke(alertDialog)
    }

    alertDialog.show()
}

fun Fragment.showDefaultAlertDialog(
    title: String? = null,
    message: String,
    positiveButtonTitle: String? = null,
    negativeButtonTitle: String? = null,
    onPositiveButtonClicked: (dialog: DialogInterface) -> Unit = { dialog -> dialog.dismiss() },
    onNegativeButtonClicked: (dialog: DialogInterface) -> Unit = { dialog -> dialog.dismiss() },
    onDismissListener: () -> Unit = { }
) = requireContext().showDefaultAlertDialog(
    title = title,
    message = message,
    positiveButtonTitle = positiveButtonTitle,
    negativeButtonTitle = negativeButtonTitle,
    onPositiveButtonClicked = onPositiveButtonClicked,
    onNegativeButtonClicked = onNegativeButtonClicked,
    onDismissListener = onDismissListener
)