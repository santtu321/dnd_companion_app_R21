package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.currentCoroutineContext

/*class SpellAdapter(private val context: Context,
                   private val dataSource: SpellReport
) : BaseAdapter() {
    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val cItem = getItem(position) as SpellReport
        val rowView = inflater.inflate(R.layout.activity_rule, parent, false)
        // Get title
        val name = rowView.findViewById(R.id.tv_SpellName) as TextView
        name.text = cItem.name
        /*// Get description
        val description = rowView.findViewById(R.id.lvSpellAddress) as TextView
        description.text = cItem.desc
        // Get range
        val range = rowView.findViewById(R.id.lvSpellRange) as TextView
        range.text = cItem.range
        // Get duration
        val duration = rowView.findViewById(R.id.lvSpellDuration) as TextView
        duration.text = cItem.duration
        // Get damage
        val damage = rowView.findViewById(R.id.lvSpellDamage) as TextView
        damage.text = cItem.damage.damage_at_slot_level.two

         */



        return rowView
    }
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
}

 */

class SpellAdapter(private val spellList: List<SpellReport>): RecyclerView.Adapter<SpellAdapter.SpellAdapterViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpellAdapterViewHolder {
        val itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_rule,
            parent, false)

        return SpellAdapterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SpellAdapterViewHolder, position: Int) {
        val currentSpell = spellList[position]

        holder.spellName.text = currentSpell.name
        holder.spellDesc.text = currentSpell.url
        holder.spellRange.text = currentSpell.index
    }

    override fun getItemCount(): Int{
        return spellList.size
    }

    class SpellAdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val spellName: TextView = itemView.findViewById(R.id.tv_SpellName)
        val spellDesc: TextView = itemView.findViewById(R.id.tv_SpellDesc)
        val spellRange: TextView = itemView.findViewById(R.id.tv_SpellRange)
    }
}