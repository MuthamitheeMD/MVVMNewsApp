package com.example.mvvmnewsapp.room

import com.example.mvvmnewsapp.model.Source


class Converters {

    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name

    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }
}