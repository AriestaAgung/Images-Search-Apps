package tech.devatacreative.unsplashimages.Adapter

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.main_activity_recycler_view.view.*
import tech.devatacreative.unsplashimages.Model.Results
import tech.devatacreative.unsplashimages.R

class MainActivityAdapter(
    private val imgURL: List<String>,
    private val title: List<String>
     )
: RecyclerView.Adapter<CustomViewHolder>() {
    private lateinit var context: Context

    override fun getItemCount(): Int = imgURL.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cell = layoutInflater.inflate(R.layout.main_activity_recycler_view, parent, false)
        return CustomViewHolder(cell)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val dataImg = imgURL[position]
        val homeImage = holder.itemView.mainImage
        val titletext = holder.itemView.imgDesc
        if (dataImg != null) {
            for (datas in dataImg) {
                Picasso.get()
                    .load(dataImg)
                    .into(homeImage)
            }
        }
        if (title != null){
            for (datas in title){
                titletext.text = datas
            }
        }


    }
}


class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){

}