package com.blueshroom.harry.avoid.PlayerCharacter

class Player(private val name : String,
             private val health : Int,
             private val sanity : Int,
             private val inventory : Inventory,
             private val equipment: Equipment)
{
    // Getters
    fun name() : String { return this.name }
    fun inventory() : Inventory { return this.inventory }
    fun equipment() : Equipment { return this.equipment }

    // Stats
    fun health() : Int { return this.health }
    fun sanity() : Int { return this.sanity }
    fun armor() : Int { return this.equipment.calculateArmor() }
}