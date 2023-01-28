package uz.easycongroup.smartenergy.domain.data.models.rest

import uz.easycongroup.smartenergy.data.mapper.restCode
import uz.easycongroup.smartenergy.domain.data.models.rest.LocaleConstants.CONST_DEFAULT_DISTRICT_ID
import uz.easycongroup.smartenergy.domain.data.models.rest.LocaleConstants.CONST_DEFAULT_PER_PAGE
import uz.easycongroup.smartenergy.domain.data.models.rest.LocaleConstants.CONST_DEFAULT_REGION_ID
import uz.easycongroup.smartenergy.domain.data.models.rest.LocaleConstants.CONST_DEFAULT_START_PAGE
import uz.easycongroup.smartenergy.domain.data.models.rest.RemoteConstants.CONST_ALL_REGION_SELECTED
import uz.easycongroup.smartenergy.data.datasource.rest.constants.RestQueryKeys.QUERY_ALL_REGION
import uz.easycongroup.smartenergy.data.datasource.rest.constants.RestQueryKeys.QUERY_CATEGORY_ID
import uz.easycongroup.smartenergy.data.datasource.rest.constants.RestQueryKeys.QUERY_DISTRICT_ID
import uz.easycongroup.smartenergy.data.datasource.rest.constants.RestQueryKeys.QUERY_PAGE
import uz.easycongroup.smartenergy.data.datasource.rest.constants.RestQueryKeys.QUERY_PAGE_SIZE
import uz.easycongroup.smartenergy.data.datasource.rest.constants.RestQueryKeys.QUERY_QUERY
import uz.easycongroup.smartenergy.data.datasource.rest.constants.RestQueryKeys.QUERY_REGION_ID
import uz.easycongroup.smartenergy.data.datasource.rest.constants.RestQueryKeys.QUERY_SORT
import uz.easycongroup.smartenergy.domain.data.models.sort.SortType

data class ProductListFilters(

    val initialPage: Int = CONST_DEFAULT_START_PAGE,
    val initialPerPage: Int = CONST_DEFAULT_PER_PAGE,
    val initialQuery: String? = null,

    val initialCategory: Long? = null,
    val initialSortType: SortType? = null,

    val initialRegion: Long? = null,
    val initialDistrict: Long? = null,
    val initialIsAllRegionSelected: Boolean? = null,

    val isOnlyUseNotNullParams: Boolean = false
) {

    var page: Int = initialPage
    var perPage: Int = initialPerPage
    var query: String? = initialQuery

    var category: Long? = initialCategory
    var sortType: SortType? = initialSortType

    var region: Long? = initialRegion
    var district: Long? = initialDistrict
    var isAllRegionSelected: Boolean? = initialIsAllRegionSelected

    fun isUsedFiltersOrSearch(): Boolean {
        if (page != initialPage) return true
        if (query != initialQuery) return true
        if (category != initialCategory) return true
        if (sortType != initialSortType) return true
        if (isAllRegionSelected != initialIsAllRegionSelected) return true
        if (region != initialRegion) return true
        if (district != initialDistrict) return true

        return false
    }

    fun getRequiredParams(): Map<String, String> {
        return if (isOnlyUseNotNullParams) getOnlyNotNullQueryParamsAsMap()
        else getAllQueryParamsOrDefaultsAsMap()
    }

    private fun getAllQueryParamsOrDefaultsAsMap(): Map<String, String> {
        val queryMap = mutableMapOf<String, String>()

        queryMap[QUERY_PAGE] = page.toString()
        queryMap[QUERY_PAGE_SIZE] = perPage.toString()

        queryMap[QUERY_SORT] = sortType.restCode
        queryMap[QUERY_CATEGORY_ID] = category?.toString() ?: ""
        queryMap[QUERY_QUERY] = query ?: ""

        if (isAllRegionSelected == true || region == null) {
            queryMap[QUERY_ALL_REGION] = CONST_ALL_REGION_SELECTED.toString()
        } else {
            queryMap[QUERY_REGION_ID] = region?.toString() ?: CONST_DEFAULT_REGION_ID.toString()
            queryMap[QUERY_DISTRICT_ID] = region?.toString() ?: CONST_DEFAULT_DISTRICT_ID.toString()
        }

        return queryMap
    }

    private fun getOnlyNotNullQueryParamsAsMap(): Map<String, String> {
        val queryMap = mutableMapOf<String, String>()

        queryMap[QUERY_PAGE] = page.toString()
        queryMap[QUERY_PAGE_SIZE] = perPage.toString()

        sortType?.let { queryMap[QUERY_SORT] = it.restCode }
        category?.let { queryMap[QUERY_CATEGORY_ID] = it.toString() }
        query?.let { queryMap[QUERY_QUERY] = it }

        if (isAllRegionSelected == true || region == null) {
            queryMap[QUERY_ALL_REGION] = CONST_ALL_REGION_SELECTED.toString()
        } else {
            region?.let { queryMap[QUERY_REGION_ID] = it.toString() }
            district?.let { queryMap[QUERY_DISTRICT_ID] = it.toString() }
        }

        return queryMap
    }

    fun isStartPageLoading(): Boolean =
        page == CONST_DEFAULT_START_PAGE

    fun increasePage() {
        page++
    }

    fun resetPage() {
        page = CONST_DEFAULT_START_PAGE
    }

    fun resetParams() {
        page = initialPage
        perPage = initialPerPage

        query = initialQuery
        sortType = initialSortType

        category = initialCategory
        isAllRegionSelected = initialIsAllRegionSelected
        region = initialRegion
        district = initialDistrict
    }
}