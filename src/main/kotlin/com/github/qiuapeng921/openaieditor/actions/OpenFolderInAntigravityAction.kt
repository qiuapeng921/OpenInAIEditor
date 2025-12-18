package com.github.qiuapeng921.openaieditor.actions

import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.vfs.VirtualFile
import com.github.qiuapeng921.openaieditor.utils.AntigravityLauncher

/**
 * 在 Antigravity 中打开文件夹的动作类
 * 处理文件夹右键菜单中的 "Open in Antigravity" 选项
 */
class OpenFolderInAntigravityAction : AnAction() {
    
    /**
     * 指定动作更新线程类型
     */
    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }
    
    /**
     * 更新动作的可见性和可用性
     * 只有在选中文件夹时才显示此选项
     */
    override fun update(e: AnActionEvent) {
        val virtualFile = e.getData(CommonDataKeys.VIRTUAL_FILE)
        e.presentation.isEnabledAndVisible = virtualFile != null && virtualFile.isDirectory
    }
    
    /**
     * 执行动作 - 在 Antigravity 中打开选中的文件夹
     */
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project
        val virtualFile: VirtualFile? = e.getData(CommonDataKeys.VIRTUAL_FILE)
        
        virtualFile?.let { file ->
            if (file.isDirectory) {
                AntigravityLauncher.openInAntigravity(project, file.path)
            }
        }
    }
}