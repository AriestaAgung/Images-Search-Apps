package tech.devatacreative.unsplashimages.Utils

import retrofit2.Call
import retrofit2.http.*
import tech.devatacreative.unsplashimages.Model.Results
import tech.devatacreative.unsplashimages.Model.ResultsItem

//import tech.devatacreative.unsplashimages.Model.UnsplashResult

interface UnsplashInterface {


    companion object{
        var page : Int? = null
        val query: String? = null
    }

    @GET("search/photos/")
    fun getHomeURL(@QueryMap  options: HashMap<String, String>): Call<Results>


}