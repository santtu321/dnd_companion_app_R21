package com.example.myapplication

import com.google.gson.annotations.SerializedName

data class MonsterReportModel(
    @SerializedName("name") var name: String? = null,
    @SerializedName("size") var size: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("alignment") var alignment: String? = null,
    @SerializedName("armor_class") var armorClass: String? = null,
    @SerializedName("hit_points") var hitPoints: String? = null,
    @SerializedName("hit_dice") var hitDice: String? = null,
    @SerializedName("strength") var strength: String? = null,
    @SerializedName("dexterity") var dexterity: String? = null,
    @SerializedName("constitution") var constitution: String? = null,
    @SerializedName("intelligence") var intelligence: String? = null,
    @SerializedName("wisdom") var wisdom: String? = null,
    @SerializedName("charisma") var charisma: String? = null,
    @SerializedName("languages") var languages: String? = null,
)
