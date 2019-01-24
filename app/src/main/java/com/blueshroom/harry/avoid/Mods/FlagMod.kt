package com.blueshroom.harry.avoid.Mods

import com.blueshroom.harry.avoid.Actions.FlagSetter

class FlagMod(private val setters : List<FlagSetter>) : Mod()
{
    override fun getVal(): List<FlagSetter> { return setters }
}