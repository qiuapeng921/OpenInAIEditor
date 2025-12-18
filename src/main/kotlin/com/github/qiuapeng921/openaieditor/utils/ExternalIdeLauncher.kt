package com.github.qiuapeng921.openaieditor.utils

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.process.ProcessHandlerFactory
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project
import java.io.File

/**
 * AIEditor 启动器工具类
 * 支持启动 Antigravity 和 Kiro IDE
 */
object ExternalIdeLauncher {
    
    /**
     * IDE 名称映射
     */
    private val ideNames = mapOf(
        "antigravity" to "Antigravity",
        "kiro" to "Kiro"
    )
    
    /**
     * 在外部 IDE 中打开指定路径
     * @param project 当前项目
     * @param path 要打开的文件或文件夹路径
     * @param ideKey IDE 标识符（如 "antigravity", "kiro"）
     * @param currentPath 当前配置的路径
     */
    fun openInExternalIde(
        project: Project?,
        path: String,
        ideKey: String,
        currentPath: String
    ) {
        val ideName = ideNames[ideKey.lowercase()]
        if (ideName == null) {
            showNotification(
                project,
                "不支持的 IDE",
                "不支持的 IDE 类型: $ideKey",
                NotificationType.ERROR
            )
            return
        }
        
        // 检查是否配置了路径
        if (currentPath.isEmpty()) {
            showNotification(
                project,
                "未配置 $ideName 路径",
                "请在 设置 > 工具 > AIEditor 中配置 $ideName 可执行文件路径",
                NotificationType.WARNING
            )
            return
        }
        
        // 检查可执行文件是否存在
        if (!File(currentPath).exists()) {
            showNotification(
                project,
                "$ideName 可执行文件未找到",
                "在以下路径未找到 $ideName 可执行文件: $currentPath",
                NotificationType.ERROR
            )
            return
        }
        
        try {
            // 创建命令行
            val commandLine = GeneralCommandLine()
                .withExePath(currentPath)
                .withParameters(path)
                .withWorkDirectory(File(path).parentFile)
            
            // 创建进程处理器并启动
            val processHandler: ProcessHandler = ProcessHandlerFactory.getInstance()
                .createColoredProcessHandler(commandLine)
            
            processHandler.startNotify()
            
            // 显示成功通知
            showNotification(
                project,
                "正在 $ideName 中打开",
                "正在 $ideName IDE 中打开 $path",
                NotificationType.INFORMATION
            )
            
        } catch (e: Exception) {
            // 显示错误通知
            showNotification(
                project,
                "在 $ideName 中打开失败",
                "错误: ${e.message}",
                NotificationType.ERROR
            )
        }
    }
    
    /**
     * 显示通知消息
     */
    private fun showNotification(
        project: Project?,
        title: String,
        content: String,
        type: NotificationType
    ) {
        NotificationGroupManager.getInstance()
            .getNotificationGroup("AIEditor")
            .createNotification(title, content, type)
            .notify(project)
    }
}