package com.example.bixi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bixi.R
import com.example.bixi.models.Streamer
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class StreamerAdapter(private var streamers: List<Streamer>) : RecyclerView.Adapter<StreamerAdapter.StreamerViewHolder>() {

    class StreamerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.streamerName)
        val viewersTextView: TextView = itemView.findViewById(R.id.streamerViewers)
        val tagsGroup: ChipGroup = itemView.findViewById(R.id.streamerTags)
        val imageView: ImageView = itemView.findViewById(R.id.streamerImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StreamerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_streamers, parent, false)
        return StreamerViewHolder(view)
    }

    override fun onBindViewHolder(holder: StreamerViewHolder, position: Int) {
        val streamer = streamers[position]
        holder.nameTextView.text = streamer.name
        holder.viewersTextView.text = streamer.viewers.toString()

        // Clear the existing chips in the group
        holder.tagsGroup.removeAllViews()

        // Add a chip to the group for each tag
        for (tag in streamer.tags) {
            val chip = Chip(holder.tagsGroup.context)
            chip.text = tag
            holder.tagsGroup.addView(chip)
        }

        // Allow the ChipGroup to wrap its content to a new line
        holder.tagsGroup.isSingleLine = false

        Glide.with(holder.itemView)
            .load(streamer.streamerIcon)
            .into(holder.imageView)
    }
    override fun getItemCount() = streamers.size

    fun updateData(newStreamers: List<Streamer>) {
        streamers = newStreamers
        notifyDataSetChanged()
    }
}