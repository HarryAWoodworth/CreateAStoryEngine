package com.blueshroom.harry.avoid.mods

class SoundMod(private val soundId : Int) : Mod()
{
    override fun getVal(): Int { return soundId }
}