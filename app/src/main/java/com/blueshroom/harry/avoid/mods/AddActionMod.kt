package com.blueshroom.harry.avoid.mods

import com.blueshroom.harry.avoid.actions.Action

class AddActionMod(private val action : Action) : Mod()
{
    override fun getVal(): Action {
            return action
    }
}