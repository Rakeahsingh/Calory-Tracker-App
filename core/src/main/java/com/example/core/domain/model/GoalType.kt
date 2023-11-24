package com.example.core.domain.model

sealed class GoalType(val name: String){

    object LoseWeight: GoalType("loseWeight")
    object KeepWeight: GoalType("keepWeight")
    object GainWeight: GoalType("gainWeight")


    companion object{
        fun fromString(name: String): GoalType{
            return when(name){
                "loseWeight" -> LoseWeight
                "keepWeight" -> KeepWeight
                "gainWeight" -> GainWeight
                else -> KeepWeight
            }
        }
    }
}
