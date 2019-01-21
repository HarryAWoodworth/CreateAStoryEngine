package com.blueshroom.harry.avoid

class FlagMod(private val setters : List<FlagSetter>) : Mod()
{
    override fun getVal(): List<FlagSetter> { return setters }
}