package com.blueshroom.harry.avoid.Mods

import com.blueshroom.harry.avoid.Actions.Action

class AddActionMod(private val action : Action) : Mod()
{
    override fun getVal(): Action {
            return action
    }
}