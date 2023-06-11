package com.github.issaloubani.glbviewer

import com.intellij.ide.AppLifecycleListener
import org.apache.commons.io.FileUtils
import java.io.File
import java.net.ServerSocket


class GLBListener : AppLifecycleListener {

    companion object {
        var flutterAppPort = -1
        var springbootServerPort = -1
    }

    override fun appFrameCreated(commandLineArgs: MutableList<String>) {
        super.appFrameCreated(commandLineArgs)
        hostFlutterApp()
        hostSpringbootServer()
    }

    // get an available port
    private fun findAvailablePort(): Int {
        val s = ServerSocket(0)
        val port = s.localPort
        println("Available port $port")
        return port
    }

    private fun hostFlutterApp() {
        val webappRunner = "webapp-runner-9.0.75.0.jar";
        val flutterWebApp = "webapp.zip";

        val jarFile = javaClass.getResourceAsStream("/$webappRunner")
        val flutterWebAppZip = javaClass.getResourceAsStream("/$flutterWebApp")
        // run the jar file
        val file: File = File.createTempFile("webapp-runner", ".jar")
        val flutterWebAppDir: File = File.createTempFile("webapp", ".zip")
        FileUtils.copyInputStreamToFile(flutterWebAppZip, flutterWebAppDir)
        FileUtils.copyInputStreamToFile(jarFile, file)

        try {

            flutterAppPort = findAvailablePort()

            val processBuilder = ProcessBuilder(
                "java", "-jar", file.absolutePath, "--port", "$flutterAppPort", flutterWebAppDir.path
            )

            val process = processBuilder.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun hostSpringbootServer() {
        val webappRunner = "webapp-runner-9.0.75.0.jar";
        val springbootServer = "webserver.war";

        val jarFile = javaClass.getResourceAsStream("/$webappRunner")
        val springbootServerWar = javaClass.getResourceAsStream("/$springbootServer")
        // run the jar file
        val file: File = File.createTempFile("webapp-runner", ".jar")
        val springbootWarDir: File = File.createTempFile("webapp", ".zip")
        FileUtils.copyInputStreamToFile(springbootServerWar, springbootWarDir)
        FileUtils.copyInputStreamToFile(jarFile, file)

        try {

            springbootServerPort = findAvailablePort()

            val processBuilder = ProcessBuilder(
                "java", "-jar", file.absolutePath, "--port", "$springbootServerPort", springbootWarDir.path
            )

            val process = processBuilder.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}