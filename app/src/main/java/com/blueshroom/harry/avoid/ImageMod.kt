package com.blueshroom.harry.avoid

class ImageMod(private val imageId : Int) : Mod()
{
    override fun getVal(): Int { return imageId }
}