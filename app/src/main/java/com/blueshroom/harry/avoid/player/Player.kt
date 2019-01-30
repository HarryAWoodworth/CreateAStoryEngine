package com.blueshroom.harry.avoid.player

class Player(private val name : String = "",
             private val health : Int = 0,
             private val sanity : Int = 0,
             private val inventory : Inventory = Inventory(),
             private val equipment: Equipment = Equipment())
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