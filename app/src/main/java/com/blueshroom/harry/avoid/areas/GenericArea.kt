package com.blueshroom.harry.avoid.areas

import com.blueshroom.harry.avoid.Game
import com.blueshroom.harry.avoid.actions.Action
import com.blueshroom.harry.avoid.R
import com.blueshroom.harry.avoid.player.Player

class GenericArea(val id : String,
                  newName : String = "DEFAULT",
                  drawId : Int = R.drawable.default_location_image,
                  fromStr : String = "MISSING FROM STRING: $id",
                  toStr : String = "MISSING TO STRING: $id",
                  newDescription : String = "MISSING DESCRIPTION: $id") : Area(id,drawId,fromStr,toStr)
{
    override var name: String = newName
    override var actions: MutableList<Action> = mutableListOf()
    override var description: String = newDescription

    // Generate the action list and return is as an un-mutable List
    override fun generateActions(game: Game): MutableList<Action>
    {
        /**
         * Add/Remove actions based on Player and Game global flags
         */
        val actionsTemp = this.actions

        for(a : Action in actionsTemp)
        {
            // Filter actions that have met their requirements
            /*if(!(a.meetsRequirements(game)))
            {
                actionsTemp.remove(a)
            }*/
        }

        return actionsTemp
    }

}