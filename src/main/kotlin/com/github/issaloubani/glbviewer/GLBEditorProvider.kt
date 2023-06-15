package com.github.issaloubani.glbviewer

import com.intellij.openapi.fileEditor.FileEditor
import com.intellij.openapi.fileEditor.FileEditorPolicy
import com.intellij.openapi.fileEditor.FileEditorProvider
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

class GLBEditorProvider : FileEditorProvider, DumbAware {

    private val editorTypeId = "GLB_EDITOR"
    override fun accept(project: Project, file: VirtualFile): Boolean {
        return file.extension == "glb"
    }

    override fun createEditor(project: Project, file: VirtualFile): FileEditor {
        return GLBEditor(project, file)
    }

    override fun getEditorTypeId(): String {
        return editorTypeId
    }

    override fun getPolicy(): FileEditorPolicy {
        return FileEditorPolicy.HIDE_DEFAULT_EDITOR
    }


}