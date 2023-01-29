package uz.easycongroup.smartenergy.presentation.presentation.features.main.features.catalog

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.HeatDataEntry
import com.anychart.enums.SelectionMode
import com.anychart.graphics.vector.SolidFill
import dagger.Lazy
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import uz.easycongroup.smartenergy.R
import uz.easycongroup.smartenergy.core.presentation.event.LoadingListEvent
import uz.easycongroup.smartenergy.core.presentation.event.LoadingListEvent.*
import uz.easycongroup.smartenergy.databinding.FragmentCatalogBinding
import uz.easycongroup.smartenergy.presentation.global.GlobalActivity
import uz.easycongroup.smartenergy.presentation.presentation.common.controller.state.StateEmptyItemController
import uz.easycongroup.smartenergy.presentation.presentation.common.controller.state.StateErrorItemController
import uz.easycongroup.smartenergy.presentation.presentation.features.main.features.catalog.controller.ShimmerParentCategoryItemController
import uz.easycongroup.smartenergy.presentation.support.backpresseddispatcher.addCallback
import uz.easycongroup.smartenergy.presentation.support.cicerone.utils.withArguments
import uz.easycongroup.smartenergy.presentation.utils.onBackPressedDispatcher
import javax.inject.Inject

internal class CatalogFragment : MvpAppCompatFragment(R.layout.fragment_catalog),
    CatalogView {

    @Inject
    lateinit var lazyPresenter: Lazy<CatalogPresenter>
    private val presenter by moxyPresenter { lazyPresenter.get() }

    private val easyAdapter = EasyAdapter()
    private val errorItemController = StateErrorItemController(true) {
        presenter.getCatalogCategoryList()
    }
    private val emptyItemController = StateEmptyItemController(
        isFullScreen = true,
        drawableResId = R.drawable.ic_state_empty,
        messageResId = R.string.state_no_items
    )
    private val shimmerCategoryItemController = ShimmerParentCategoryItemController()

    private val binding: FragmentCatalogBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity() as GlobalActivity)
            .globalDaggerComponent
            .inject(this)

        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this) { presenter.back() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addHeatMapChart()
    }

    override fun onDefinedCategoryListEvent(event: LoadingListEvent<Unit>) {
        val itemList = ItemList.create()
        when (event) {
            is SuccessList -> {}
            is LoadingList -> {
                itemList.add(shimmerCategoryItemController)
                itemList.add(shimmerCategoryItemController)
                itemList.add(shimmerCategoryItemController)
                itemList.add(shimmerCategoryItemController)
                itemList.add(shimmerCategoryItemController)
                itemList.add(shimmerCategoryItemController)
                itemList.add(shimmerCategoryItemController)
                itemList.add(shimmerCategoryItemController)
                itemList.add(shimmerCategoryItemController)
                itemList.add(shimmerCategoryItemController)
            }
            is EmptyList -> itemList.add(emptyItemController)
            is ErrorList -> itemList.add(errorItemController)
        }
        easyAdapter.setItems(itemList)
    }

    fun addHeatMapChart() {

        val riskMap = AnyChart.heatMap()

        riskMap.stroke("1 #fff")
        riskMap.hovered()
            .stroke("6 #fff")
            .fill(SolidFill("#545f69", 1.0))
            .labels("{ fontColor: '#fff' }")

        riskMap.interactivity().selectionMode(SelectionMode.NONE)

        riskMap.title().enabled(true)
        riskMap.title()
            .text("Risk Matrix in Project Server")
            .padding(0.0, 0.0, 20.0, 0.0)

        riskMap.labels().enabled(true)
        riskMap.labels()
            .minFontSize(14.0)
            .format(
                "function() {\n" +
                        "      var namesList = [\"Low\", \"Medium\", \"High\", \"Extreme\"];\n" +
                        "      return namesList[this.heat];\n" +
                        "    }"
            )

        riskMap.yAxis(0).stroke(null)
        riskMap.yAxis(0).labels().padding(0.0, 15.0, 0.0, 0.0)
        riskMap.yAxis(0).ticks(false)
        riskMap.xAxis(0).stroke(null)
        riskMap.xAxis(0).ticks(false)

        riskMap.tooltip().title().useHtml(true)
        riskMap.tooltip()
            .useHtml(true)
            .titleFormat(
                "function() {\n" +
                        "      var namesList = [\"Low\", \"Medium\", \"High\", \"Extreme\"];\n" +
                        "      return '<b>' + namesList[this.heat] + '</b> Residual Risk';\n" +
                        "    }"
            )
            .format(
                ("function () {\n" +
                        "       return '<span style=\"color: #CECECE\">Likelihood: </span>' + this.x + '<br/>' +\n" +
                        "           '<span style=\"color: #CECECE\">Consequence: </span>' + this.y;\n" +
                        "   }")
            )

        val data: MutableList<DataEntry> = ArrayList()
        data.add(HeatMapCustomDataEntry("Rare", "Insignificant", 0, "#90caf9"))
        data.add(HeatMapCustomDataEntry("Rare", "Minor", 0, "#90caf9"))
        data.add(HeatMapCustomDataEntry("Rare", "Moderate", 0, "#90caf9"))
        data.add(HeatMapCustomDataEntry("Rare", "Major", 0, "#90caf9"))
        data.add(HeatMapCustomDataEntry("Rare", "Extreme", 0, "#90caf9"))
        data.add(HeatMapCustomDataEntry("Unlikely", "Insignificant", 0, "#90caf9"))
        data.add(HeatMapCustomDataEntry("Unlikely", "Minor", 0, "#90caf9"))
        data.add(HeatMapCustomDataEntry("Unlikely", "Moderate", 0, "#90caf9"))
        data.add(HeatMapCustomDataEntry("Unlikely", "Major", 1, "#ffb74d"))
        data.add(HeatMapCustomDataEntry("Unlikely", "Extreme", 1, "#ffb74d"))
        data.add(HeatMapCustomDataEntry("Possible", "Insignificant", 0, "#90caf9"))
        data.add(HeatMapCustomDataEntry("Possible", "Minor", 0, "#90caf9"))
        data.add(HeatMapCustomDataEntry("Possible", "Moderate", 1, "#ffb74d"))
        data.add(HeatMapCustomDataEntry("Possible", "Major", 1, "#ffb74d"))
        data.add(HeatMapCustomDataEntry("Possible", "Extreme", 1, "#ffb74d"))
        data.add(HeatMapCustomDataEntry("Likely", "Insignificant", 0, "#90caf9"))
        data.add(HeatMapCustomDataEntry("Likely", "Minor", 1, "#ffb74d"))
        data.add(HeatMapCustomDataEntry("Likely", "Moderate", 1, "#ffb74d"))
        data.add(HeatMapCustomDataEntry("Likely", "Major", 2, "#ef6c00"))
        data.add(HeatMapCustomDataEntry("Likely", "Extreme", 2, "#ef6c00"))
        data.add(HeatMapCustomDataEntry("Almost\\nCertain", "Insignificant", 0, "#90caf9"))
        data.add(HeatMapCustomDataEntry("Almost\\nCertain", "Minor", 1, "#ffb74d"))
        data.add(HeatMapCustomDataEntry("Almost\\nCertain", "Moderate", 1, "#ffb74d"))
        data.add(HeatMapCustomDataEntry("Almost\\nCertain", "Major", 2, "#ef6c00"))
        data.add(HeatMapCustomDataEntry("Almost\\nCertain", "Extreme", 3, "#d84315"))

        riskMap.data(data)


        binding.anyChartRadar.setChart(riskMap)
    }

    companion object {

        fun newInstance() =
            CatalogFragment().withArguments()
    }
}

class HeatMapCustomDataEntry(x: String?, y: String?, heat: Int?, fill: String?) :
    HeatDataEntry(x, y, heat) {
    init {
        setValue("fill", fill)
    }
}
