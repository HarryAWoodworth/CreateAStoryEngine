package com.blueshroom.harry.avoid.Actions

import com.blueshroom.harry.avoid.Areas.Transition
import com.blueshroom.harry.avoid.Game

/**
 * Transition Action takes a Transition and stores it.
 * It's getActionText() returns a string "Go to " +
 * the name of the areaTo from the Transition.
 */

class TransitionAction(game: Game,
                       id : String,
                       val transition : Transition,
                       flagsSet : List<FlagSetter> = listOf()) : Action(game,id,flagsSet)
{
    override fun getActionText() : String
    {
        return "Go to " + transition.areaTo.name
    }

}
