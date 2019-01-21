package com.blueshroom.harry.avoid

/**
 * Takes a text String to return in getActionText()
 * Takes a text String to return in getDescriptionText, to display when the action is taken
 */

class ModifierAction(game: Game,
                     id : String,
                     val text : String = "MISSING ACTION TEXT: $id",
                     val descrText: String = "MISSING DESCRIPTION TEXT: $id",
                     val mods : MutableList<Mod> = mutableListOf<Mod>(),
                     flagsSet : List<FlagSetter> = listOf()) : Action(game,id,flagsSet)
{
    override fun getActionText() : String { return text }

    // Add a mod to the mods list
    fun addMod(mod : Mod)
    {
        mods.add(mod)
    }
}
