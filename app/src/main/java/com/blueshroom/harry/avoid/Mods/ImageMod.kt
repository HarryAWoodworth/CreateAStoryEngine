package com.blueshroom.harry.avoid.Mods

class ImageMod(private val imageId : Int) : Mod()
{
    override fun getVal(): Int { return imageId }
}