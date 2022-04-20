package com.example.myapplication
import com.google.gson.annotations.SerializedName


data class SpellReportModel(

    @SerializedName("name") var name: String? = null,
    @SerializedName("desc") var desc: String? = null,
    @SerializedName("range") var range: String? = null,
    @SerializedName("desc") var duration: String? = null,
    @SerializedName("name") var damageType: String? = null,
    @SerializedName("3") var damageAtSlotLevelThree: String? = null,
    @SerializedName("4") var damageAtSlotLevelFour: String? = null,
    @SerializedName("5") var damageAtSlotLevelFive: String? = null,
    @SerializedName("6") var damageAtSlotLevelSix: String? = null,
    @SerializedName("7") var damageAtSlotLevelSeven: String? = null,
    @SerializedName("8") var damageAtSlotLevelEight: String? = null,
    @SerializedName("9") var damageAtSlotLevelNine: String? = null,
    @SerializedName("casting_time") var castingTime: String? = null,
    @SerializedName("school") var school: String? = null,
    //@SerializedName("results") var spellReportList: ArrayList<SpellReport> = arrayListOf()
)
