package com.github.issaloubani.glbviewer

import com.intellij.openapi.fileEditor.FileEditor
import com.intellij.openapi.fileEditor.FileEditorState
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Key
import com.intellij.openapi.vfs.VirtualFile
import java.beans.PropertyChangeListener
import javax.swing.JComponent

class GLBEditor(private val project: Project, private val file: VirtualFile) : FileEditor, DumbAware {

    private val gblViewer = GLBViewer(project, file)

    override fun <T : Any?> getUserData(key: Key<T>): T? {
        return null
    }

    override fun <T : Any?> putUserData(key: Key<T>, value: T?) {
    }

    override fun dispose() {
        // close the file
        // dispose of the editor
    }

    override fun getComponent(): JComponent {
        return gblViewer.component
    }

    override fun getPreferredFocusedComponent(): JComponent? {
        return null
    }

    override fun getName(): String {
        return "GLB Editor"
    }

    override fun setState(state: FileEditorState) {
        println("GLBEditor.setState()")
    }

    override fun isModified(): Boolean {
        return false
    }

    override fun isValid(): Boolean {
        return true
    }

    override fun addPropertyChangeListener(listener: PropertyChangeListener) {
        println("GLBEditor.addPropertyChangeListener()")
    }

    override fun removePropertyChangeListener(listener: PropertyChangeListener) {
        println("GLBEditor.removePropertyChangeListener()")
    }

    override fun getFile(): VirtualFile {
        return project.projectFile!!
    }
}