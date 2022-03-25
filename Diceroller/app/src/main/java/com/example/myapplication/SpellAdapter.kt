package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class SpellAdapter(private val context: Context,
                   private val dataSource: ArrayList<SpellReportModel>) : BaseAdapter() {
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
        val cItem = getItem(position) as SpellReportModel
        val rowView = inflater.inflate(R.layout.listview_spell, parent, false)
        // Get title
        val name = rowView.findViewById(R.id.lvSpellName) as TextView
        name.text = cItem.name
        // Get description
        val description = rowView.findViewById(R.id.lvSpellAddress) as TextView
        description.text = cItem.desc

        return rowView
    }
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
}