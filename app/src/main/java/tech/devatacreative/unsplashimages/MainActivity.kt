package tech.devatacreative.unsplashimages

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.devatacreative.unsplashimages.Adapter.MainActivityAdapter
import tech.devatacreative.unsplashimages.Model.Results
import tech.devatacreative.unsplashimages.Model.ResultsItem
import tech.devatacreative.unsplashimages.Utils.APIClient
import tech.devatacreative.unsplashimages.Utils.UnsplashInterface
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {

    private var interfaces : UnsplashInterface = APIClient().getClientBuilder().create(UnsplashInterface::class.java)
    private var datalist : MutableList<Results> = mutableListOf()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.nav_item, menu)

        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("", false)
                searchItem.collapseActionView()

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false

            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getHomeURLImage()
        mainImageRecyclerView.layoutManager = GridLayoutManager(this, 2)


    }

    private fun getHomeURLImage() : ArrayList<String>  {
        val datas = HashMap<String, String>()
        datas["client_id"] = APIClient().clientid
        datas["query"] = "Homes"
        datas["page"] = "1"
        val call: Call<Results> = interfaces.getHomeURL(datas)
        var imageModel  = ArrayList<String>()
        var title = ArrayList<String>()

        call.enqueue(object : Callback<Results> {
            override fun onResponse(call: Call<Results>, response: Response<Results>) {
                val result = response.body()?.results
                print("Data Fetched")
                if (result != null) {
                    for (data in result){
                        imageModel.add(data.urls?.regular.toString())
                        title.add(data.user?.name.toString())

                    }
                    Log.d("ISI URL : ", imageModel.toString())
                    mainImageRecyclerView.adapter = MainActivityAdapter(imageModel, title)


                }
            }
            override fun onFailure(call: Call<Results>, t: Throwable) {
                print("Errornya : " + t.printStackTrace())
            }
        })
        return imageModel
    }




}




