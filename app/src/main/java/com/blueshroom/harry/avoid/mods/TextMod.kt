package com.blueshroom.harry.avoid.mods

class TextMod(private val text : String) : Mod()
{
    override fun getVal(): String { return text }
}