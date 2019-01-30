package com.blueshroom.harry.avoid.actions

import com.blueshroom.harry.avoid.Game

abstract class Action(private val game : Game, val identifier : String)
{
    abstract fun getActionText() : String

    fun getID() : String
    {
        return identifier
    }

}