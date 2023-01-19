package com.glycin.funfinal

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.BufferedInputStream
import java.io.InputStream
import javax.sound.sampled.*

class SoundPlayer {

    fun playAsync() {
        GlobalScope.launch(Dispatchers.IO){
            playSound()
        }
    }

    private fun playSound(){
        val inputStream = SoundPlayer::class.java.getResourceAsStream(PluginUtils.THE_PATH)
        val bufferedInput = inputStream?.let { BufferedInputStream(it) } as InputStream
        val audioStream = AudioSystem.getAudioInputStream(bufferedInput)
        val format = audioStream.format
        val info = DataLine.Info(SourceDataLine::class.java, format)
        val dataLine = AudioSystem.getLine(info) as SourceDataLine
        dataLine.open(format)
        dataLine.start()

        val bufferBytes = ByteArray(4096)
        var readBytes = -1
        while (audioStream.read(bufferBytes).also { readBytes = it } != -1) {
            dataLine.write(bufferBytes, 0, readBytes)
        }

        dataLine.drain()
        dataLine.close()
        audioStream.close()
    }
}