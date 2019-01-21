package com.blueshroom.harry.avoid

class AddActionMod(private val action : Action) : Mod()
{
    override fun getVal(): Action {
            return action
    }
}