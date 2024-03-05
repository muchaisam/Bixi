package com.example.bixi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bixi.R
import com.example.bixi.models.Category
import com.example.bixi.models.LiveStream

class LiveStreamAdapter(private var liveStreams: List<LiveStream>) : RecyclerView.Adapter<LiveStreamAdapter.LiveStreamViewHolder>() {

    class LiveStreamViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val thumbnail: ImageView = view.findViewById(R.id.thumbnail)
        val title: TextView = view.findViewById(R.id.title)
        val streamer: TextView = view.findViewById(R.id.streamer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LiveStreamViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_livestream, parent, false)
        return LiveStreamViewHolder(view)
    }

    override fun onBindViewHolder(holder: LiveStreamViewHolder, position: Int) {
        val liveStream = liveStreams[position]
        holder.title.text = liveStream.streamTitle
        holder.streamer.text = liveStream.streamerName
        // Use Glide to load the thumbnail into the ImageView
        Glide.with(holder.itemView)
            .load(liveStream.thumbnailUrl)
            .into(holder.thumbnail)
    }

    override fun getItemCount() = liveStreams.size


    fun updateData(newStreams: List<LiveStream>) {
        liveStreams = newStreams
        notifyDataSetChanged()
    }
}