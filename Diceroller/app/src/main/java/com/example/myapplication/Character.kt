package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_table")
data class Character(@PrimaryKey @ColumnInfo(name = "character") val character: String
)
//@ColumnInfo(name= "level") val level: Int