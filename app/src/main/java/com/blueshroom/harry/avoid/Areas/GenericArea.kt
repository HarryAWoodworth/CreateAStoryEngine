package com.blueshroom.harry.avoid.Areas

import com.blueshroom.harry.avoid.Actions.Action
import com.blueshroom.harry.avoid.R

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
    override fun generateActions(/** player: Player, game: Game> **/): MutableList<Action>
    {
        /**
         * Add/Remove actions based on Player and Game global flags
         */

        return this.actions
    }

}