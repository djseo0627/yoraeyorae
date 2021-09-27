package com.yoraeyorae.kakaomap.resdata

data class CategorySearchResult(
    var meta : CategorySearchMeta,
    var documents : List<CategorySearchData>
)

data class CategorySearchMeta(
    var is_end : Boolean,
    var pageable_count : Int,
    var same_name : String,
    var total_count : Int
)
data class CategorySearchData(
    var address_name : String = "",
    var category_group_code : String = "",
    var category_group_name : String = "",
    var category_name : String = "",
    var distance : String = "",
    var id : String = "",
    var phone : String = "",
    var place_name : String = "",
    var place_url : String = "",
    var road_address_name : String = "",
    var x : String = "",
    var y : String = ""
)
