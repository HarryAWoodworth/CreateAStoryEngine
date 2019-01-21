package com.blueshroom.harry.avoid

class RemoveActionMod(private val actionID : String) : Mod()
{
    override fun getVal(): String { return actionID }
}