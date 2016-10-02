package com.samdoward.beer.android.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.samdoward.beer.android.R
import com.samdoward.beer.android.data.Beer
import kotlinx.android.synthetic.main.beer_item.view.*

class BeerAdapter(private val beers: List<Beer>) : RecyclerView.Adapter<BeerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.beer_item, parent, false)
        return ViewHolder(view, {})
    }

    override fun getItemCount() = beers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(beers.get(position))
    }


    class ViewHolder(view: View, val itemClick: (Beer) -> Unit) : RecyclerView.ViewHolder(view) {

        fun bind(beer: Beer) {
            with(beer) {
                itemView.nameTextView.text = beer.name
            }
        }
    }
}
