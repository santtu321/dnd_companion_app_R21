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


class SpellAdapterMonster(private val spellList: ArrayList<MonsterReportModel>): RecyclerView.Adapter<SpellAdapterMonster.SpellAdapterMonsterViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpellAdapterMonsterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.itemview_rulemonsters,
            parent, false)

        return SpellAdapterMonsterViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SpellAdapterMonsterViewHolder, position: Int) {
        val currentSpell = spellList[position]

        holder.monsterName.text = "Name: \n${currentSpell.name}"
        holder.monsterSize.text = "Size: \n${currentSpell.size}"
        holder.monsterType.text = "Type: \n${currentSpell.type}"
        holder.monsterAlignment.text = "Alignment: \n${currentSpell.alignment}"
        holder.monsterArmorClass.text = "Armor class: \n${currentSpell.armorClass}"
        holder.monsterHitPoints.text = "Hit points: \n${currentSpell.hitPoints}"
        holder.monsterHitDice.text = "Hit dice: \n${currentSpell.hitDice}"
        holder.monsterStrength.text = "Strength: ${currentSpell.strength}"
        holder.monsterDexterity.text = "Dexterity: ${currentSpell.dexterity}"
        holder.monsterConstitution.text = "Constitution: ${currentSpell.constitution}"
        holder.monsterIntelligence.text = "Intelligence: ${currentSpell.intelligence}"
        holder.monsterWisdom.text = "Wisdom: ${currentSpell.wisdom}"
        holder.monsterCharisma.text = "Charisma: ${currentSpell.charisma}"
        holder.monsterLanguages.text = "Languages: \n${currentSpell.languages}"

    }

    override fun getItemCount(): Int{
        return spellList.size
    }

    class SpellAdapterMonsterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val monsterName: TextView = itemView.findViewById(R.id.tv_name)
        val monsterSize: TextView = itemView.findViewById(R.id.tv_size)
        val monsterType: TextView = itemView.findViewById(R.id.tv_type)
        val monsterAlignment: TextView = itemView.findViewById(R.id.tv_alignment)
        val monsterArmorClass: TextView = itemView.findViewById(R.id.tv_armorClass)
        val monsterHitPoints: TextView = itemView.findViewById(R.id.tv_hitPoints)
        val monsterHitDice: TextView = itemView.findViewById(R.id.tv_hitDice)
        val monsterStrength: TextView = itemView.findViewById(R.id.tv_strength)
        val monsterDexterity: TextView = itemView.findViewById(R.id.tv_dexterity)
        val monsterConstitution: TextView = itemView.findViewById(R.id.tv_constitution)
        val monsterIntelligence: TextView = itemView.findViewById(R.id.tv_intelligence)
        val monsterWisdom: TextView = itemView.findViewById(R.id.tv_wisdom)
        val monsterCharisma: TextView = itemView.findViewById(R.id.tv_charisma)
        val monsterLanguages: TextView = itemView.findViewById(R.id.tv_language)


    }
}