package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_table")
data class Characterdata(
    @PrimaryKey (autoGenerate = true) val id: Int,
    @ColumnInfo(name = "character") val character: String?,
    @ColumnInfo(name= "level", defaultValue ="1") val level: Int,
    @ColumnInfo(name= "job") val job: String?,
    @ColumnInfo(name= "race", defaultValue ="1") val race: String?,
    @ColumnInfo(name= "str", defaultValue ="1") val str: Int,
    @ColumnInfo(name= "dex", defaultValue ="1") val dex: Int,
    @ColumnInfo(name= "con", defaultValue ="1") val con: Int,
    @ColumnInfo(name= "int", defaultValue ="1") val ao: Int,
    @ColumnInfo(name= "wis", defaultValue ="1") val wis: Int,
    @ColumnInfo(name= "cha", defaultValue ="1") val cha: Int,
    @ColumnInfo(name= "strpro", defaultValue ="0") val strpro: Boolean,
    @ColumnInfo(name= "dexpro", defaultValue ="0") val dexpro: Boolean,
    @ColumnInfo(name= "conpro", defaultValue ="0") val conpro: Boolean,
    @ColumnInfo(name= "intpro", defaultValue ="0") val intpro: Boolean,
    @ColumnInfo(name= "wispro", defaultValue ="0") val wispro: Boolean,
    @ColumnInfo(name= "chapro", defaultValue ="0") val chapro: Boolean


)