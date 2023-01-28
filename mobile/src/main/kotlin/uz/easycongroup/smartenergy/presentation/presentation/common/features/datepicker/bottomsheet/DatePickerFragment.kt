package uz.easycongroup.smartenergy.presentation.presentation.common.features.datepicker.bottomsheet

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import uz.easycongroup.smartenergy.databinding.FragmentDatePickerBinding
import uz.easycongroup.smartenergy.presentation.global.GlobalActivity
import uz.easycongroup.smartenergy.presentation.support.cicerone.utils.getNonNullBundleArgument
import uz.easycongroup.smartenergy.presentation.support.cicerone.utils.getNullableBundleArgument
import uz.easycongroup.smartenergy.presentation.support.cicerone.utils.withArguments
import uz.easycongroup.smartenergy.presentation.support.moxy.BaseMvpBottomSheetDialogFragment
import java.util.*
import java.util.Calendar.YEAR
import java.util.Calendar.MONTH
import java.util.Calendar.DAY_OF_MONTH
import javax.inject.Inject

internal class DatePickerFragment : BaseMvpBottomSheetDialogFragment(), DatePickerView {

    @Inject
    @InjectPresenter
    lateinit var presenter: DatePickerPresenter

    @ProvidePresenter
    fun presenter(): DatePickerPresenter = presenter

    private lateinit var binding: FragmentDatePickerBinding

    private lateinit var calendar: Calendar

    private var defaultDate: Long = 0L
    private var minDate: Long = 0L
    private var maxDate: Long = 0L
    private var hideDaySpinner: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {

        (requireActivity() as GlobalActivity)
            .globalDaggerComponent
            .inject(this)

        super.onCreate(savedInstanceState)

        if (arguments != null) {

            calendar = Calendar.getInstance()

            defaultDate = getNullableBundleArgument(KEY_SELECTED_DATE) ?: 0L
            minDate = getNullableBundleArgument(KEY_MIN_DATE) ?: 0L
            maxDate = getNullableBundleArgument(KEY_MAX_DATE) ?: 0L
            hideDaySpinner = getNonNullBundleArgument(KEY_HIDE_DAY_SPINNER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentDatePickerBinding.inflate(inflater, container, false)
        .also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            if (minDate != 0L) datePicker.minDate = minDate
            if (maxDate != 0L) datePicker.maxDate = maxDate
            if (defaultDate != 0L) calendar.timeInMillis = defaultDate

            datePicker.updateDate(
                calendar.get(YEAR),
                calendar.get(MONTH),
                calendar.get(DAY_OF_MONTH)
            )

            if (hideDaySpinner) hideDaySpinner()

            btnDone.setOnClickListener {
                calendar.set(datePicker.year, datePicker.month, datePicker.dayOfMonth)

                presenter.setSelectedDate(calendar.timeInMillis)
                presenter.returnSelectedItem()
            }
        }
    }

    private fun hideDaySpinner() {
        val daySpinnerId: Int = Resources.getSystem().getIdentifier("day", "id", "android")
        if (daySpinnerId != 0) {
            val daySpinner: View? = binding.datePicker.findViewById(daySpinnerId)
            daySpinner?.visibility = View.GONE
        }
    }

    override fun onDismissDialog() {
        dismiss()
    }


    companion object {

        private const val KEY_DIALOG_TITLE = "key_dialog_title"

        private const val KEY_SELECTED_DATE = "key_selected_date"
        private const val KEY_MIN_DATE = "key_min_date"
        private const val KEY_MAX_DATE = "key_max_date"
        private const val KEY_HIDE_DAY_SPINNER = "key_hide_day_spinner"

        fun newInstance(
            dialogTitle: String? = null,
            selectedDate: Long? = null,
            minDate: Long? = null,
            maxDate: Long? = null,
            hideDaySpinner: Boolean = false
        ): DatePickerFragment =
            DatePickerFragment().withArguments {
                if (selectedDate != null) putLong(KEY_SELECTED_DATE, selectedDate)
                if (minDate != null) putLong(KEY_MIN_DATE, minDate)
                if (maxDate != null) putLong(KEY_MAX_DATE, maxDate)
                if (dialogTitle != null) putString(KEY_DIALOG_TITLE, dialogTitle)

                putBoolean(KEY_HIDE_DAY_SPINNER, hideDaySpinner)
            }
    }
}