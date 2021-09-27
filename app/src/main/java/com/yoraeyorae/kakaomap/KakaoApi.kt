package com.yoraeyorae.kakaomap

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import com.yoraeyorae.kakaomap.resdata.AddressSearchResult
import com.yoraeyorae.kakaomap.resdata.CategorySearchResult
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface KakaoApi {
    @GET("v2/local/search/category.json")
    fun getCategorySearchResult(
//        @Header("Authorization") key: String,
        @Query("category_group_code") category_group_code : String,
        @Query("x") x : String,
        @Query("y") y : String,
        @Query("radius") radius : String,   // 검색반경 m단위. 0~20000
        @Query("page") page : String,       // 검색결과 페이지 번호. default 1
        @Query("size") size : String,       // 한 페이지에 보여질 문서 개수. default 15
        @Query("sort") sort : String        // 정렬순서. distance / accuracy(default)
    ): Call<CategorySearchResult>

    @GET("v2/local/search/address.json")
    fun getAddressSearchResult(
        @Query("query") query : String
    ) : Call<AddressSearchResult>

    companion object {
        private val BASE_URL_KAKAO_API = "https://dapi.kakao.com/"
        private val KAKAO_API_KEY = "KakaoAK ef63749e42bc947fe40031aad38580de"

        fun create() : KakaoApi {
            val httpLoggingInterceptor =  HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val headerInterceptor = Interceptor{
                val request = it.request()
                    .newBuilder()
                    .addHeader("Authorization", KAKAO_API_KEY)
                    .build()
                return@Interceptor it.proceed(request)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL_KAKAO_API)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(KakaoApi::class.java)
        }

    }

}