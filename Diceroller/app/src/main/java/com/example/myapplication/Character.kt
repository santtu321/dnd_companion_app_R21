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
    @ColumnInfo(name= "cha", defaultValue ="1") val cha: Int


)