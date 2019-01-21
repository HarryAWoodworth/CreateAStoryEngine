package com.blueshroom.harry.avoid

import android.content.Context

class Game(private val context : Context)
{
    // Game Flags
    private val flags = mutableMapOf<String,Boolean>()

    private var currentArea : Area =
            GenericArea("default_game_area_placeholder",
                    "",
                    R.drawable.error_placeholder,
                    "",
                    "",
                    "ERROR: Init area loaded incorrectly")

    init
    {
        /** Add key/value pairs from text file **/
        val inputStream = context.assets.open("flag_data.txt")
        inputStream.bufferedReader().useLines {
            lines -> lines.forEach {
                this.addFlag(it)
            }
        }
    }

    // Based on the flagSetter change flags
    fun changeFlags(flagSetter : FlagSetter)
    {
       flags.set(flagSetter.flagKey, flagSetter.setVal)
    }

    // Set a key/value pair in the flag hashmap
    // using a line from flag_data.txt
    private fun addFlag(flagLine : String)
    {
        val strSplit = flagLine.split(" ")
        this.flags.put(strSplit[0],strSplit[1].toBoolean())
    }

    // Getters and setters for current area
    fun setCurrentArea(area : Area) { this.currentArea = area }
    fun getCurrentArea() : Area { return this.currentArea }
    fun getFlag(flagId : String) : Boolean? { return this.flags.get(flagId)}
}