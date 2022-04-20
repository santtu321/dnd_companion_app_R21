package com.example.myapplication

import android.annotation.SuppressLint
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


class SpellAdapter(private val spellList: ArrayList<SpellReportModel>): RecyclerView.Adapter<SpellAdapter.SpellAdapterViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpellAdapterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.itemview_rule,
            parent, false)

        return SpellAdapterViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SpellAdapterViewHolder, position: Int) {
        val currentSpell = spellList[position]

        holder.spellName.text = "Name: \n${currentSpell.name}"
        holder.spellDesc.text = "Description: \n${currentSpell.desc}"
        holder.spellRange.text = "Range: \n${currentSpell.range}"
        holder.spellDuration.text = "Duration: \n${currentSpell.duration}"
        holder.spellDamageType.text = "Damage type: \n${currentSpell.damageType}"
        holder.spellDamageAtSlotLevelThree.text = "Damage at level \n 3: ${currentSpell.damageAtSlotLevelThree}"
        holder.spellDamageAtSlotLevelFour.text = " 4: ${currentSpell.damageAtSlotLevelFour}"
        holder.spellDamageAtSlotLevelFive.text = " 5: ${currentSpell.damageAtSlotLevelFive}"
        holder.spellDamageAtSlotLevelSix.text = " 6: ${currentSpell.damageAtSlotLevelSix}"
        holder.spellDamageAtSlotLevelSeven.text = " 7: ${currentSpell.damageAtSlotLevelSeven}"
        holder.spellDamageAtSlotLevelEight.text = " 8: ${currentSpell.damageAtSlotLevelEight}"
        holder.spellDamageAtSlotLevelNine.text = " 9: ${currentSpell.damageAtSlotLevelNine}"
        holder.spellCastingTime.text = "Casting time: \n${currentSpell.castingTime}"
        holder.spellSchool.text = "Spell school: \n${currentSpell.school}"
    }

    override fun getItemCount(): Int{
        return spellList.size
    }

    class SpellAdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val spellName: TextView = itemView.findViewById(R.id.tv_name)
        val spellDesc: TextView = itemView.findViewById(R.id.tv_desc)
        val spellRange: TextView = itemView.findViewById(R.id.tv_range)
        val spellDuration: TextView = itemView.findViewById(R.id.tv_duration)
        val spellDamageType: TextView = itemView.findViewById(R.id.tv_damageType)
        val spellDamageAtSlotLevelThree: TextView = itemView.findViewById(R.id.tv_damageAtSlotLevelThree)
        val spellDamageAtSlotLevelFour: TextView = itemView.findViewById(R.id.tv_damageAtSlotLevelFour)
        val spellDamageAtSlotLevelFive: TextView = itemView.findViewById(R.id.tv_damageAtSlotLevelFive)
        val spellDamageAtSlotLevelSix: TextView = itemView.findViewById(R.id.tv_damageAtSlotLevelSix)
        val spellDamageAtSlotLevelSeven: TextView = itemView.findViewById(R.id.tv_damageAtSlotLevelSeven)
        val spellDamageAtSlotLevelEight: TextView = itemView.findViewById(R.id.tv_damageAtSlotLevelEight)
        val spellDamageAtSlotLevelNine: TextView = itemView.findViewById(R.id.tv_damageAtSlotLevelNine)
        val spellCastingTime: TextView = itemView.findViewById(R.id.tv_castingTime)
        val spellSchool: TextView = itemView.findViewById(R.id.tv_school)
    }
}