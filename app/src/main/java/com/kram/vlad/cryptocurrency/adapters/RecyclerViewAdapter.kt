package com.kram.vlad.cryptocurrency.adapters

import android.content.res.Resources
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kram.vlad.cryptocurrency.R
import com.kram.vlad.cryptocurrency.models.ResponseItem
import kotlinx.android.synthetic.main.item.view.*

/**
 * Created by vlad on 12.12.2017.
 */
class RecyclerViewAdapter(private var result: List<ResponseItem>, var resources: Resources) : RecyclerView.Adapter<RecyclerViewAdapter.CardViewHolder>() {

    override fun getItemCount(): Int {
        return result.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) = holder.bind(result, position, resources)

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CardViewHolder {
        return CardViewHolder(LayoutInflater.from(parent?.context)
                .inflate(R.layout.item, parent, false))
    }

    class CardViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        fun bind(result: List<ResponseItem>, position: Int, resources: Resources) {
            val responseItem: ResponseItem = result.get(position)

            itemView.symbol.text = responseItem.symbol
            itemView.name.text = responseItem.name
            itemView.money.text = responseItem.priceUsd + "$"
            itemView.hours.text = responseItem.percentChange24h + "%"
            itemView.days.text = responseItem.percentChange7d + "%"

            itemView.hours.setTextColor(getTextColor(responseItem.percentChange24h!!.toFloat(), resources))
            itemView.days.setTextColor(getTextColor(responseItem.percentChange7d!!.toFloat(), resources))
        }

        private fun getTextColor(value: Float, resources: Resources): Int {
            return if (value > 0.0) {
                resources.getColor(R.color.green)
            } else {
                resources.getColor(R.color.red)
            }
        }
    }
}