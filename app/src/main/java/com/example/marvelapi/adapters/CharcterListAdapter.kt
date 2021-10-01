package com.example.marvelapi.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelapi.R
import com.example.marvelapi.models.ResultModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.charcter_list_item.view.*

class CharcterListAdapter(var context: Context) :

    ListAdapter<ResultModel, CharcterListAdapter.CharcterLisAdapterViewHolder>(
        CharcterLisAdapterViewHolder
    )  {






    open class CharcterLisAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {



        var charImage = itemView.charcterIV
        var charName = itemView.charcternamTv
        companion object DiffCallback : DiffUtil.ItemCallback<ResultModel>() {

            override fun areItemsTheSame(oldItem: ResultModel, newItem: ResultModel): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: ResultModel, newItem: ResultModel): Boolean {
                return oldItem.id == newItem.id
            }

        }


    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharcterLisAdapterViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.charcter_list_item, parent, false)
        return CharcterLisAdapterViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: CharcterLisAdapterViewHolder,
        position: Int
    ) {
        var resultItem = getItem(position)
        holder.charName.text = resultItem.name
        Glide.with(context).load("${resultItem.thumbnail.path}.${resultItem.thumbnail.extension}").into(holder.charImage)

        holder.itemView.setOnClickListener {
            val bundle = Bundle ()
            val json: String = Gson().toJson(resultItem)
            bundle.putString("charcterObject",json)
            it.findNavController().navigate(R.id.openDetial,bundle)


        }
    }
}