package com.glycin.funfinal

import javazoom.jl.player.Player
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SoundPlayer {

    fun playAsync() {
        GlobalScope.launch(Dispatchers.IO){
            val inputStream = SoundPlayer::class.java.getResourceAsStream(PluginUtils.THE_PATH)
            val player = Player(inputStream)
            player.play()
            player.close()
        }
    }
}