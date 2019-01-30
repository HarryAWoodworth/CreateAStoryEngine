package com.blueshroom.harry.avoid.mods

class ImageMod(private val imageId : Int) : Mod()
{
    override fun getVal(): Int { return imageId }
}