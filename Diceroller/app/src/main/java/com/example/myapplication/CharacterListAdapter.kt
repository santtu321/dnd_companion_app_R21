package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


class CharacterListAdapter : ListAdapter<Characterdata, CharacterListAdapter.CharacterViewHolder>(CharactersComparator()) {


    private lateinit var cListener: OnCharacterClickListener
    interface OnCharacterClickListener{
        fun onCharacterClick(positionC: Int)
    }
    fun getItemForSheet(position: Int): Characterdata? {
        return getItem(position)

    }
    fun setOnCharacterClickListener(listener: OnCharacterClickListener){
        cListener= listener


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)

        return CharacterViewHolder(view,cListener)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val current = getItem(position)


        holder.bind(current.character + ", Class:" + current.job + ", Level:" + current.level + "Id: "+ current.id)



    }



    class CharacterViewHolder(itemView: View,listener: OnCharacterClickListener) : RecyclerView.ViewHolder(itemView) {
        private val characterItemView: TextView = itemView.findViewById(R.id.textView)

        // private val DeleteButton: ImageButton = itemView.findViewById(R.id.DeleteButton)
        init {
            itemView.setOnClickListener {
                listener.onCharacterClick(adapterPosition)
            }
        }



        fun bind(text: String?) {
            characterItemView.text = text
        }
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
