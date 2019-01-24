package com.blueshroom.harry.avoid.Mods

class TextMod(private val text : String) : Mod()
{
    override fun getVal(): String { return text }
}