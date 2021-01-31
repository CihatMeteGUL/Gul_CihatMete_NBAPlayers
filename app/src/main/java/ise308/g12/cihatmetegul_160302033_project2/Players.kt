package ise308.g12.cihatmetegul_160302033_project2

class Players {
    var firstName: String? = null
    var lastName: String? = null
    var age: Int = 0
    var positionPlayed: String? = null
    var isInjured: Boolean = true

    constructor(
        firstName: String,
        lastName: String,
        age: Int,
        positionPlayed: String,
        isInjured: Boolean
        ) {
        this.firstName = firstName
        this.lastName = lastName
        this.age = age
        this.positionPlayed = positionPlayed
        this.isInjured = isInjured
    }

    fun setFirstname(x: String) {
        this.firstName = x
    }

    fun setLastname(x: String) {
        this.lastName = x
    }

    @JvmName("setAge1")
    fun setAge(x: Int) {
        this.age = x
    }

    @JvmName("setPositionPlayed1")
    fun setPositionPlayed(x: String) {
        this.positionPlayed = x
    }



}