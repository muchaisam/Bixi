package com.example.bixi.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bixi.R

class OnboardingAdapter(private val context: Context, private val images: IntArray, private val captions: Array<String>) :
    RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
        return OnboardingViewHolder(LayoutInflater.from(context).inflate(R.layout.onboarding_layout, parent, false))
    }

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
        holder.image.setImageResource(images[position])
        holder.caption.text = captions[position]
    }

    override fun getItemCount(): Int {
        return images.size
    }

    class OnboardingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.imageonboarding)
        val caption: TextView = itemView.findViewById(R.id.caption)
    }
}