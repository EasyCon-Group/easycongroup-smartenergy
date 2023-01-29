package uz.easycongroup.smartenergy.presentation.presentation.features.main.features.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.HeatDataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Polar
import com.anychart.charts.Radar
import com.anychart.data.Mapping
import com.anychart.data.Set
import com.anychart.enums.*
import com.anychart.graphics.vector.SolidFill
import com.anychart.scales.Linear
import dagger.Lazy
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import uz.easycongroup.smartenergy.R
import uz.easycongroup.smartenergy.databinding.FragmentHomeBinding
import uz.easycongroup.smartenergy.presentation.global.GlobalActivity
import uz.easycongroup.smartenergy.presentation.support.backpresseddispatcher.addCallback
import uz.easycongroup.smartenergy.presentation.support.cicerone.utils.withArguments
import uz.easycongroup.smartenergy.presentation.utils.onBackPressedDispatcher
import javax.inject.Inject


internal class HomeFragment : MvpAppCompatFragment(R.layout.fragment_home), HomeView {

    @Inject
    lateinit var lazyPresenter: Lazy<HomePresenter>
    private val presenter by moxyPresenter { lazyPresenter.get() }

    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity() as GlobalActivity).globalDaggerComponent.inject(this)

        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnSubmitReport.setOnClickListener { presenter.openActSubmitScreen() }

        }
        addPolarChart()
    }

    override fun onDefinedViewVisibility(
        isManagerViewsVisible: Boolean,
        isSupervisorViewsVisible: Boolean,
        isCitizenViewsVisible: Boolean
    ) {
        with(binding) {
            llManager.isVisible = isManagerViewsVisible
            llSupervisor.isVisible = isSupervisorViewsVisible
            llCitizen.isVisible = isCitizenViewsVisible
        }
    }

    private fun addPolarChart() {
        val polar: Polar = AnyChart.polar()

        val data: MutableList<DataEntry> = ArrayList()
        data.add(PolarCustomDataEntry("YAN", 10261, 5134, 25056))
        data.add(PolarCustomDataEntry("FEV", 9987, 5134, 22456))
        data.add(PolarCustomDataEntry("MAR", 9300, 4574, 16879))
        data.add(PolarCustomDataEntry("APR", 8814, 4876, 9256))
        data.add(PolarCustomDataEntry("MAY", 12998, 5372, 3308))
        data.add(PolarCustomDataEntry("IYN", 12321, 6417, 5432))
        data.add(PolarCustomDataEntry("IYL", 10342, 9231, 13701))
        data.add(PolarCustomDataEntry("AVG", 10698, 7572, 4008))
        data.add(PolarCustomDataEntry("SEN", 11261, 6134, 18712))
        data.add(PolarCustomDataEntry("OKT", 10261, 5089, 20637))
        data.add(PolarCustomDataEntry("NOY", 10261, 5304, 22334))
        data.add(PolarCustomDataEntry("DEK", 10261, 5467, 25712))

        val set: Set = Set.instantiate()
        set.data(data)
        val series1Data: Mapping = set.mapAs("{ x: 'x', value: 'value' }")
        val series2Data: Mapping = set.mapAs("{ x: 'x', value: 'value2' }")
        val series3Data: Mapping = set.mapAs("{ x: 'x', value: 'value3' }")

        polar.column(series1Data)
        polar.column(series2Data)
        polar.column(series3Data)

        polar.title("Yillik elektr energiyasining oylar bo’yicha iste’moli")

        polar.sortPointsByX(true)
            .defaultSeriesType(PolarSeriesType.COLUMN)
            .yAxis(false)
            .xScale(ScaleTypes.ORDINAL)

        polar.title().margin().bottom(20.0)

        (polar.yScale(Linear::class.java) as Linear).stackMode(ScaleStackMode.VALUE)

        polar.tooltip()
            .valuePrefix("$")
            .displayMode(TooltipDisplayMode.UNION)

        binding.anyChartPolar.setChart(polar)
    }

    companion object {

        fun newInstance() = HomeFragment().withArguments()
    }
}

class PolarCustomDataEntry internal constructor(
    x: String?,
    value: Number?,
    value2: Number?,
    value3: Number?
) :
    ValueDataEntry(x, value) {
    init {
        setValue("value2", value2)
        setValue("value3", value3)
    }
}