package com.route.legrand.fragments.production.troubleshooting.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.route.legrand.databinding.ItemImageTroubleBinding

class DetailsAdapter(private var imagesList: List<Int>) : Adapter<DetailsAdapter.MyViewHolder>() {

    private var diffUtil: MyDiffUtil? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemImageTroubleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = imagesList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val image = imagesList[position]
        holder.bind(image)
    }

    fun updateListOfImages(newList: List<Int>) {
        diffUtil = MyDiffUtil(imagesList, newList)
        val result = DiffUtil.calculateDiff(diffUtil!!)
        imagesList = newList
        result.dispatchUpdatesTo(this)
    }

    class MyViewHolder(val binding: ItemImageTroubleBinding) : ViewHolder(binding.root) {
        fun bind(image: Int) {
            binding.imageTrouble.setImageResource(image)
        }
    }
    class MyDiffUtil(
        private val oldList: List<Int>,
        private val newList: List<Int>,
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

    }
}

