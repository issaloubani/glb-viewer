package com.github.issaloubani.glbviewer

import com.intellij.ui.jcef.JBCefBrowser
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.modules
import com.intellij.openapi.roots.ModuleRootManager
import com.intellij.openapi.vfs.VirtualFile
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class GLBViewer(val project: Project, val glbFile: VirtualFile) : JBCefBrowser() {

    init {
        val url: String = uploadGLBFile()
        loadURL("http://localhost:${GLBListener.flutterAppPort}/webapp?glbUrl=$url")
    }

    private fun uploadGLBFile(): String {
        val client = OkHttpClient()
        val mediaType = "text/plain".toMediaType()
        println("Virtual File path : ${glbFile.path}")
        val body = MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart(
            "file", glbFile.name, File(glbFile.path).asRequestBody("application/octet-stream".toMediaType())
        ).build()
        val request = Request.Builder().url("http://localhost:${GLBListener.springbootServerPort}/glb/upload")
            .post(body).build()

        val response = client.newCall(request).execute()

        return "http://localhost:${GLBListener.springbootServerPort}/glb/file/${glbFile.name}"
    }
}