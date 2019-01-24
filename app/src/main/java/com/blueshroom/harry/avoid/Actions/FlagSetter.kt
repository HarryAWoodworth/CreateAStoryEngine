package com.blueshroom.harry.avoid.Actions

/**
 * For setting global flags, holds the String key
 * to access the flag and what value to set it to
 */
data class FlagSetter(val flagKey : String, val setVal : Boolean)