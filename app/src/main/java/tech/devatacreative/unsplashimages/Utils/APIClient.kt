package tech.devatacreative.unsplashimages.Utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {
    lateinit var retrofit : Retrofit


    val BASE_URL = "https://api.unsplash.com/"
//    val clientid = "d8a272c480b258b875d82f4062d6c52e4ae7f4b4656add778d71e9b638b2f8be"
    val clientid = "ajRyXCd00VrBKOp1LM-YGPXmJYNj4ovqyXWm7tnSuIE"
    fun getClientBuilder(): Retrofit {

//        if (retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
//        }
        return retrofit
    }

}