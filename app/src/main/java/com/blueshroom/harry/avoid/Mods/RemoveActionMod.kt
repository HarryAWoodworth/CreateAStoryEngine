package com.blueshroom.harry.avoid.Mods

class RemoveActionMod(private val actionID : String) : Mod()
{
    override fun getVal(): String { return actionID }
}