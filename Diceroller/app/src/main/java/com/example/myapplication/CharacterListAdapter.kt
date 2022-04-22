
package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


class CharacterListAdapter(private val listener: MainDatabaseActivity) : ListAdapter<Characterdata,
        CharacterListAdapter.CharacterViewHolder>(CharactersComparator()) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)

        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.character + ", Class:" + current.job + ", Level:" + current.level + "Id: "+ current.id)
    }


    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val characterItemView: TextView = itemView.findViewById(R.id.textView)

        // private val DeleteButton: ImageButton = itemView.findViewById(R.id.DeleteButton)
        init {
            itemView.setOnClickListener(this)
        }
        fun bind(text: String?) {
            characterItemView.text = text
        }

        override fun onClick(v: View?) {
            val position= adapterPosition
            val yeehaa= getItem(adapterPosition)
            if (position != RecyclerView.NO_POSITION) {
                listener.onCharacterClick(yeehaa)
            }
        }
    }
    interface OnCharacterClickListener{
        fun onCharacterClick(position: Characterdata)

    }

    class CharactersComparator : DiffUtil.ItemCallback<Characterdata>() {
        override fun areItemsTheSame(oldItem: Characterdata, newItem: Characterdata): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Characterdata, newItem: Characterdata): Boolean {
            return oldItem.character == newItem.character

        }
    }

}
