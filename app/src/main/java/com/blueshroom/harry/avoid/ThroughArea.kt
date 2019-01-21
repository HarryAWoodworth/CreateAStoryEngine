package com.blueshroom.harry.avoid

class ThroughArea(val id : String,
                  private var drawId : Int = R.drawable.default_location_image,
                  private var throughStr : String = "MISSING THROUGH TEXT: $id",
                  private var eventThreshold : Int = 0) : Focus(id,drawId)
{
    // Using the eventThreshold, see if an
    // event happens, and if it does, return it
    fun chanceEvent() : Area?
    {
        /** Code to see if an event happens **/
        // Maybe take from a rarity loot list kinda thing?

        return null
    }

    // Setters
    fun setNewEventThreshhold(newThresh : Int) { this.eventThreshold = newThresh }
    fun setNewThroughStr(newStr : String) { this.throughStr = newStr }
    fun setNewDrawId(newDrawId : Int) { this.drawId = newDrawId }

    // Getters
    fun getThroughStr() : String { return this.throughStr }
}