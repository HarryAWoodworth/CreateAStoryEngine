package com.blueshroom.harry.avoid

import android.content.Context
import android.util.Log
import com.blueshroom.harry.avoid.actions.FlagSetter
import com.blueshroom.harry.avoid.areas.Area
import com.blueshroom.harry.avoid.areas.GenericArea
import com.blueshroom.harry.avoid.player.Player

private const val FLAG_FILE_NAME = "flag_data.txt"
private const val FLAG_FILE_ID = R.raw.flag_data
private const val TAG = "void_GAME"

class Game(private val context : Context)
{
    // Game Flags
    private val flags = mutableMapOf<String,Boolean>()

    // Player
    var player = Player()

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
        val inputStream = context.openFileInput(FLAG_FILE_NAME)
        inputStream.bufferedReader().useLines {
            lines -> lines.forEach {
                this.addFlag(it)
            }
        }

        // If there was nothing local, use raw file
        if(flags.isEmpty())
        {
            val rawInputStream = context.resources.openRawResource(FLAG_FILE_ID)
            rawInputStream.bufferedReader().useLines {
                 lines -> lines.forEach {
                 this.addFlag(it)
                }
            }
        }
    }

    // Based on the flagSetter change flags
    fun changeFlags(flagSetter : FlagSetter)
    {
        flags.set(flagSetter.flagKey, flagSetter.setVal)
        Log.d(TAG,"Flag " + flagSetter.flagKey + " set to " + flagSetter.setVal)
    }

    // Set a key/value pair in the flag hashmap
    // using a line from flag_data.txt
    private fun addFlag(flagLine : String)
    {
        val strSplit = flagLine.split(" ")
        this.flags.put(strSplit[0],strSplit[1].toBoolean())
        Log.d(TAG,"Flag Added: " + strSplit[0] + " " + strSplit[1])
    }

    // Save the game data
    fun saveData()
    {
        /** FLAGS **/
        // Clear the file
        context.openFileOutput(FLAG_FILE_NAME,Context.MODE_PRIVATE).use {
            it.write("".toByteArray())
            it.close()
        }

        // Get the flags in string form
        var strToFile = ""
        for(pair in flags) { strToFile += (""+pair.key+" "+pair.value+"\n") }

        // Write to file
        context.openFileOutput(FLAG_FILE_NAME,Context.MODE_PRIVATE).use {
            it.write(strToFile.toByteArray())
            it.close()
        }

    }

    // Getters and setters for current area
    fun setCurrentArea(area : Area) { this.currentArea = area }
    fun getCurrentArea() : Area { return this.currentArea }
    fun flag(flagId : String) : Boolean? { return this.flags.get(flagId)}
}