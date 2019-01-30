package com.blueshroom.harry.avoid.actions

import com.blueshroom.harry.avoid.Game
import com.blueshroom.harry.avoid.mods.Mod

/**
 * Takes a text String to return in getActionText()
 * Takes a text String to return in getDescriptionText, to display when the action is taken
 */

class ModifierAction(game: Game,
                     id : String,
                     val text : String = "MISSING ACTION TEXT: $id",
                     val descrText: String = "MISSING DESCRIPTION TEXT: $id",
                     val mods : MutableList<Mod> = mutableListOf<Mod>()) : Action(game,id)
{
    override fun getActionText() : String { return text }

    // Add a mod to the mods list
    fun addMod(mod : Mod)
    {
        mods.add(mod)
    }
}
