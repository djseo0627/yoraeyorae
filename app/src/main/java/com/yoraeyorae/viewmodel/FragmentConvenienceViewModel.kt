package com.yoraeyorae.viewmodel

import android.util.Log
import com.yoraeyorae.kakaomap.KakaoApi
import com.yoraeyorae.kakaomap.resdata.AddressSearchResult
import com.yoraeyorae.kakaomap.resdata.CategorySearchData
import com.yoraeyorae.kakaomap.resdata.CategorySearchResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentConvenienceViewModel {

    fun getConvenienceData(missionAddr: String): MutableList<CategorySearchData> {
        var retData: MutableList<CategorySearchData> = mutableListOf<CategorySearchData>()
        val api = KakaoApi.create()
        val callAddress2Coord = api.getAddressSearchResult(missionAddr)

        callAddress2Coord.enqueue(object : Callback<AddressSearchResult> {
            override fun onResponse(
                call: Call<AddressSearchResult>,
                response: Response<AddressSearchResult>
            ) {
                response.body()?.let { it1 ->
                    val callConvenienceSearch = api.getCategorySearchResult(
                        "CS2",
                        it1.documents[0].x,
                        it1.documents[0].y,
                        "1000",
                        "1",
                        "15",
                        "distance"
                    )

                    callConvenienceSearch.enqueue(object : Callback<CategorySearchResult> {
                        override fun onResponse(
                            call: Call<CategorySearchResult>,
                            response: Response<CategorySearchResult>
                        ) {
                            if (response.isSuccessful) {
                                response.body()?.let { it2 ->
                                    retData.addAll(it2.documents)
                                }
                            }
                        }

                        override fun onFailure(call: Call<CategorySearchResult>, t: Throwable) {
                            Log.d("통신실패", "${t.message}")
                        }
                    })
                }
            }

            override fun onFailure(call: Call<AddressSearchResult>, t: Throwable) {
                Log.d("통신실패", "${t.message}")
            }
        })
        return retData //여기있으면 안됨
    }
}
