package com.blueshroom.harry.avoid.Mods

class SoundMod(private val soundId : Int) : Mod()
{
    override fun getVal(): Int { return soundId }
}