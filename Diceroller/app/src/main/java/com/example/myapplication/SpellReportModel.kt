package com.example.myapplication


import com.google.gson.annotations.SerializedName

data class SpellReportModel(
    @SerializedName("count") var count: Int? = null,
    @SerializedName("results") var spellReportList: ArrayList<SpellReport> = arrayListOf()
)


data class SpellReport(
    @SerializedName("url") var url: String? = null,
    @SerializedName("index") var index: String? = null,
    @SerializedName("name") var name: String? = null,
/*
    @SerializedName("_id")
    var id: String,
    @SerializedName("index")
    var index: String,
    @SerializedName("name")
    var name: String,

 */
)
/*    var desc: ArrayList<Desc>,
    var higher_level: String,
    var range: String,
    var components: String,
    var material: String,
    var ritual: Boolean,
    var duration: String,
    var concentration: Boolean,
    var casting_time: String,
    var level: Int,
    var damage: damage,
    var dc: Dc,
    var area_of_effect: Area_of_effect,
    var school: School,
    var classes: ArrayList<Classes>,
    var subclasses: Subclasses,
    var url: String,
)
/*
data class Components(
    @SerializedName(value="0")
    var firstline: String?,
    @SerializedName(value="1")
    var secondline: String?,
    @SerializedName(value="2")
    var thirdline: String?,
)

data class Higher_level(
    @SerializedName(value="0")
    var firstline: String?,
)
*/
data class Desc(
    @SerializedName(value="0")
    var firstline: String?,
    @SerializedName(value="1")
    var secondline: String?,
)

data class Subclasses(
    @SerializedName(value="0")
    var firstSubClass: FirstSubClass,
    @SerializedName(value="1")
    var secondSubClass: SecondSubClass,
)

data class FirstSubClass(
    var index: String,
    var name: String,
    var url: String,
)

data class SecondSubClass(
    var index: String,
    var name: String,
    var url: String,
)

data class Classes(
    @SerializedName(value="0")
    var firstClass: FirstClass,
    @SerializedName(value="1")
    var secondClass: SecondClass,
)

data class FirstClass(
    var index: String,
    var name: String,
    var url: String,
)

data class SecondClass(
    var index: String,
    var name: String,
    var url: String,
)

data class School(
    var index: String,
    var name: String,
    var url: String,
)

data class Area_of_effect(
    var type: String,
    var size: Int,
)

data class Dc(
    var dc_type: dc_type,
    var dc_success: String,
)

data class dc_type(
    var index: String,
    var name: String,
    var url: String,
)

data class damage (
    var damage_at_slot_level: damage_at_slot_level,
    var damage_type: damage_type
        )

data class damage_type(
    var index: String,
    var name: String,
    var url: String,
)

data class damage_at_slot_level(
    @SerializedName(value="2")
    var two: String?,
    @SerializedName(value="3")
    var three: String?,
    @SerializedName(value="4")
    var four: String?,
    @SerializedName(value="5")
    var five: String?,
    @SerializedName(value="6")
    var six: String?,
    @SerializedName(value="7")
    var seven: String?,
    @SerializedName(value="8")
    var eight: String?,
    @SerializedName(value="9")
    var nine: String?,

)

*/