package com.example.ehealth

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ehealth.model.Health

class HealthListAdapter(
    private val onItemClickListener: (Health) -> Unit,
): ListAdapter<Health, HealthListAdapter.HealthViewHolder>(WORDS_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HealthViewHolder {
        return HealthViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HealthViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
        holder.itemView.setOnClickListener {
            onItemClickListener(current)
        }
    }


    class HealthViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val healthNama = itemView.findViewById<android.widget.TextView>(R.id.namaTextView)
        private val healthAlamat = itemView.findViewById<android.widget.TextView>(R.id.alamatTextView)
        private val healthNotelp = itemView.findViewById<android.widget.TextView>(R.id.notelpTextView)
        fun bind(current: Health?) {
            healthNama.text = current?.nama
            healthAlamat.text = current?.alamat
            healthNotelp.text = current?.notelp.toString()
        }

        companion object {
            fun create(parent: ViewGroup): HealthListAdapter.HealthViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_health, parent, false)
                return HealthViewHolder(view)
            }
        }

    }

    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<Health>() {
            override fun areItemsTheSame(oldItem: Health, newItem: Health): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Health, newItem: Health): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}