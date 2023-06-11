package com.github.issaloubani.glbviewer

import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class GLBFileType : LanguageFileType(GLBLanguage.INSTANCE) {
    companion object {
        val INSTANCE = GLBFileType()
    }

    override fun getName(): String {
        return "GLB"
    }

    override fun getDescription(): String {
        return "GLB file"
    }

    override fun getDefaultExtension(): String {
        return "glb"
    }

    override fun getIcon(): Icon {
        return AllIcons.FileTypes.Config
    }
}