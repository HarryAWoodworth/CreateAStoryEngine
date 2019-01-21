package com.blueshroom.harry.avoid

/**
 * https://discuss.kotlinlang.org/t/overload-tostring-for-use-in-arrayadapter/3743/3
 * https://stackoverflow.com/questions/44200075/val-and-var-in-kotlin
 * https://www.journaldev.com/17318/kotlin-string
 * https://kotlinlang.org/docs/tutorials/android-plugin.html
 */

class Transition(val areaFrom : Area,
                 val areaTo : Area)
{
    // Optional throughArea
    private var areaThrough : ThroughArea? = null

    fun runThroughEvent()
    {
        val areaThroughTemp = areaThrough
        if(areaThroughTemp != null)
        {
            // Catch event obj or null
            val eventRet = areaThroughTemp.chanceEvent()

            // Event Occurs
            if (eventRet != null) {
                /** CODE TO CHANGE THE FOCUS TO THE EVENT **/
                // Pass in the areaTo ???
            }
        }
    }

    // Return the transition text by combining the text fields from
    // areaFrom, through, and areaTo
    fun getTransitionText() : String
    {
        val areaThroughTemp = areaThrough
        if(areaThroughTemp != null)
            return areaFrom.areaFromStr + areaThroughTemp.getThroughStr() + areaTo.areaToStr
        return areaFrom.areaFromStr + areaTo.areaToStr
    }

    // Set areaThrough
    fun setNewAreaThrough(newAreaThrough : ThroughArea)
    {
        this.areaThrough = newAreaThrough
    }
}
