package com.blueshroom.harry.avoid

abstract class Action(private val game : Game, val identifier : String, val globalFlagsSet : List<FlagSetter>)
{
    abstract fun getActionText() : String

    fun getID() : String
    {
        return identifier
    }

    fun setGlobalFlags()
    {
        for(flagSetter : FlagSetter in globalFlagsSet)
        {
            game.changeFlags(flagSetter)
        }
    }
}