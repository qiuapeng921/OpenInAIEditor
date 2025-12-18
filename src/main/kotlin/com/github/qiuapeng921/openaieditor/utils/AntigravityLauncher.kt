package com.github.qiuapeng921.openaieditor.utils

import com.intellij.openapi.project.Project
import com.github.qiuapeng921.openaieditor.settings.AIEditorSettings

/**
 * Antigravity 启动器工具类
 * 使用通用的 ExternalIdeLauncher 来启动 Antigravity IDE
 */
object AntigravityLauncher {
    
    /**
     * 在 Antigravity IDE 中打开指定路径
     * @param project 当前项目
     * @param path 要打开的文件或文件夹路径
     */
    fun openInAntigravity(project: Project?, path: String) {
        val settings = AIEditorSettings.getInstance()
        
        ExternalIdeLauncher.openInExternalIde(
            project = project,
            path = path,
            ideKey = "antigravity",
            currentPath = settings.antigravityExecutablePath
        )
    }
}