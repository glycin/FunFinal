package com.glycin.funfinal

import com.intellij.codeInsight.editorActions.TypedHandlerDelegate
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiFile

class FinalFlashTypedHandlerDelegate: TypedHandlerDelegate() {

    override fun charTyped(c: Char, project: Project, editor: Editor, file: PsiFile): Result {
        val offset = editor.caretModel.offset
        val linestart = editor.caretModel.logicalPosition.line
        val lineStartOffset = editor.document.getLineStartOffset(linestart)
        val minOffset = if(lineStartOffset > (offset - (PluginUtils.THE_WORD.length + 1))) lineStartOffset else (offset - (PluginUtils.THE_WORD.length + 1))
        val lastWord = editor.document.getText(TextRange(minOffset, offset))

        if(lastWord.contains(PluginUtils.THE_WORD)){
            SoundPlayer().playAsync()
        }
        return Result.CONTINUE
    }
}