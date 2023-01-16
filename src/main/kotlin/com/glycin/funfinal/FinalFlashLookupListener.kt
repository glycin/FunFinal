package com.glycin.funfinal

import com.intellij.codeInsight.lookup.LookupEvent
import com.intellij.codeInsight.lookup.LookupListener

class FinalFlashLookupListener: LookupListener {

    override fun itemSelected(event: LookupEvent) {
        if(event.item.toString() == PluginUtils.THE_WORD){
            SoundPlayer().playAsync()
        }

        super.itemSelected(event)
    }
}