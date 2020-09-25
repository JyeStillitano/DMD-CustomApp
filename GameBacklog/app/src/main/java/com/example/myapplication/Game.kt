package com.example.myapplication

enum class Platform {
    PC, Sega, Atari, Arcade,
    NES, SNES, GameCube, Nintendo64, Wii, WiiU, NintendoSwitch, GameBoy, NintendoDS,
    PlayStation1, PlayStation2, PlayStation3, PlayStation4, PlayStation5, PSP, PlayStationVita,
    XBox, XBox360, XBoxOne, XBoxOneX
}

data class Game(val name: String, val platform: Platform, val desc: String, val completion: Float) {
}