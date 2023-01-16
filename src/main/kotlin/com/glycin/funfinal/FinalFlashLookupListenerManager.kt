package com.glycin.funfinal

import com.intellij.codeInsight.lookup.Lookup
import com.intellij.codeInsight.lookup.LookupManagerListener

class FinalFlashLookupListenerManager: LookupManagerListener {

    override fun activeLookupChanged(oldLookup: Lookup?, newLookup: Lookup?) {
        newLookup?.addLookupListener(FinalFlashLookupListener())
    }
}