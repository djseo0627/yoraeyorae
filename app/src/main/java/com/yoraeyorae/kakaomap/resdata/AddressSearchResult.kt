package com.yoraeyorae.kakaomap.resdata

data class AddressSearchResult(
    var meta : AddressSearchMeta,
    var documents : List<AddressSearchData>
)

data class AddressSearchMeta(
    var is_end : Boolean,
    var pageable_count : Int,
    var total_count : Int
)

data class AddressSearchData(
//    var address : Address,
    var address_name : String,
//    var address_type : String,
//    var road_address : RoadAddress,
    var x : String,
    var y : String
)

//data class Address(
//    var address_name : String,
//    var b_code : String,
//    var h_code : String,
//    var main_address_no : String,
//    var mountain_yn : String,
//    var region_1depth_name : String,
//    var region_2depth_name : String,
//    var region_3depth_h_name : String,
//    var region_3depth_name : String,
//    var sub_address_no : String,
//    var x : String,
//    var y : String
//)

//data class RoadAddress(
//    var address_name: String,
//    var building_name : String,
//    var main_building_no : String,
//    var region_1depth_name: String,
//    var region_2depth_name: String,
//    var region_3depth_name: String,
//    var road_name : String,
//    var sub_building_no : String,
//    var underground_yn : String,
//    var x : String,
//    var y : String,
//    var zone_no : String
//)



