package com.blueshroom.harry.avoid.areas

import android.util.Log
import com.blueshroom.harry.avoid.Game
import com.blueshroom.harry.avoid.actions.Action
import com.blueshroom.harry.avoid.R
import com.blueshroom.harry.avoid.actions.TransitionAction
import com.blueshroom.harry.avoid.player.Player

// Logging tag
private const val TAG : String = "Area Class"

abstract class Area(private val areaId : String,
                    var areaDrawId : Int = R.drawable.default_location_image,
                    val areaFromStr : String = "MISSING FROM STRING: $areaId",
                    val areaToStr : String = "MISSING TO STRING: $areaId") : Focus(areaId,areaDrawId)
{
    abstract var actions : MutableList<Action>
    abstract var name : String
    abstract var description : String

    // Returns a list of Actions to be converted to Buttons
    // Override in children so each sets their actions uniquely
    abstract fun generateActions(game : Game) : MutableList<Action>

    /** TODO TEST IT! **/
    // Add a new Action
    fun addAction(action : Action)
    {
        // Transition Check
        if(action is TransitionAction) {
            if(action.transition.areaFrom != this) { Log.e(TAG,"Transition Mismatch : $areaId") }
        }

        // Add the action
        actions.add(action)
    }

}