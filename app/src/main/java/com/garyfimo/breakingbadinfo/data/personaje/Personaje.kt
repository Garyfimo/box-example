package com.garyfimo.breakingbadinfo.data.personaje


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    primaryKeys = ["id"], indices = [Index("id", unique = false)]
)
data class Personaje(
    @SerializedName("char_id")
    val id: Int,
    @SerializedName("birthday")
    val cumpleanios: String,
    @SerializedName("img")
    val imagenPerfil: String,
    @SerializedName("name")
    val nombre: String,
    @SerializedName("nickname")
    val apodo: String,
    @SerializedName("portrayed")
    val actor: String,
    @SerializedName("status")
    val estado: String
)