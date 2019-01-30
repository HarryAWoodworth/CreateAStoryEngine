package com.blueshroom.harry.avoid.mods

class RemoveActionMod(private val actionID : String) : Mod()
{
    override fun getVal(): String { return actionID }
}