package com.blueshroom.harry.avoid.mods

import com.blueshroom.harry.avoid.actions.FlagSetter

class FlagMod(private val setters : List<FlagSetter>) : Mod()
{
    override fun getVal(): List<FlagSetter> { return setters }
}