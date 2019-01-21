package com.blueshroom.harry.avoid

import android.util.Log

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
    abstract fun generateActions(/** player : Player, global_flags : Map<String, Boolean> **/) : MutableList<Action>

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