package com.example.vrstyproject.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vrstyproject.R
import com.example.vrstyproject.model.HeaderBookItem
import com.squareup.picasso.Picasso


class HeaderBookItemAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    private val ITEM = 0
    private val LOADING = 1
    private var isLoadingAdded = false
    val items = ArrayList<HeaderBookItem>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        lateinit var viewHolder: RecyclerView.ViewHolder
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            ITEM -> {
                HeaderBookVH(inflater.inflate(R.layout.vh_header_bookitem, parent, false))
            }
            LOADING -> {
                HeaderBookVH(inflater.inflate(R.layout.vh_header_bookitem, parent, false))
            }
            else -> {
                HeaderBookVH(inflater.inflate(R.layout.vh_header_bookitem, parent, false))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun getItemViewType(position: Int): Int {
        return if (position == items.size - 1 && isLoadingAdded) LOADING else ITEM
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is HeaderBookVH -> {
                val musicItem: HeaderBookItem = items[position]
                // holder.hndbk_thmb.text=schedule.thumb
                ///    holder.imag = musicItem.HeaderImage

               // val url: String = getItem(position)

                Picasso.get().load(musicItem.HeaderImage).into(holder.image)


            }
        }

    }


    fun showIfNotNull(textView: TextView, string: String?) = if (string != null) {
        textView.text = string
        textView.visibility = View.VISIBLE
    } else {
        textView.visibility = View.GONE
    }

    fun add(itemObj: HeaderBookItem) {
        items.add(itemObj)
        notifyItemInserted(items.size - 1)
    }

    fun addAll(itemsObj: ArrayList<HeaderBookItem>) {
        for (t in itemsObj) {
            add(t)
        }
    }

    fun remove(t: HeaderBookItem) {
        val position = items.indexOf(t)
        if (position > -1) {
            items.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun clear() {
        while (itemCount > 0) {
            remove(getItem(0))
        }
    }

    fun getItem(position: Int): HeaderBookItem {
        return items[position]
    }

    fun isEmpty(): Boolean {
        return itemCount == 0
    }


    interface FilterSelectListener {
        fun onSelected(filterItem: HeaderBookItem)
    }
}

class HeaderBookVH(val view: View) : RecyclerView.ViewHolder(view) {
    val image: ImageView = view.findViewById(R.id.IvHeaderImage)



}