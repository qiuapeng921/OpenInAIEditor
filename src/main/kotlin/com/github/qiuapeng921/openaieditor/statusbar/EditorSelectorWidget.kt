package com.github.qiuapeng921.openaieditor.statusbar

import com.github.qiuapeng921.openaieditor.editors.EditorRegistry
import com.github.qiuapeng921.openaieditor.settings.AIEditorSettings
import com.github.qiuapeng921.openaieditor.utils.EditorTargetUtils
import com.github.qiuapeng921.openaieditor.utils.I18nUtils
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.intellij.openapi.ui.popup.JBPopup
import com.intellij.openapi.wm.StatusBarWidget
import com.intellij.openapi.wm.impl.status.EditorBasedWidget
import com.intellij.util.Consumer
import java.awt.Component
import java.awt.event.MouseEvent

/**
 * 状态栏编辑器选择器组件
 * 允许用户快速切换目标编辑器
 */
class EditorSelectorWidget(project: Project) : EditorBasedWidget(project), StatusBarWidget.MultipleTextValuesPresentation {
    
    companion object {
        const val ID = "AIEditorSelector"
    }
    
    override fun ID(): String = ID
    
    override fun getPresentation(): StatusBarWidget.WidgetPresentation = this
    
    override fun getTooltipText(): String {
        return I18nUtils.message("statusbar.tooltip")
    }
    
    override fun getSelectedValue(): String {
        val currentEditor = EditorTargetUtils.getTargetEditor(project)
        return I18nUtils.message("statusbar.text", currentEditor)
    }
    
    override fun getClickConsumer(): Consumer<MouseEvent>? {
        return Consumer { showPopup() }
    }
    
    /**
     * 显示编辑器选择弹窗
     */
    private fun showPopup() {
        val popup = createPopup()
        val component = myStatusBar?.component
        if (component != null) {
            popup.showInCenterOf(component)
        } else {
            popup.showInFocusCenter()
        }
    }
    
    /**
     * 创建编辑器选择弹窗
     */
    private fun createPopup(): JBPopup {
        val settings = AIEditorSettings.getInstance()
        val currentEditor = EditorTargetUtils.getTargetEditor(project)
        
        // 只显示已启用的编辑器
        val enabledEditors = EditorRegistry.getAllEditorTypes()
            .filter { editorType -> settings.isEditorEnabled(editorType) }
        
        return JBPopupFactory.getInstance().createPopupChooserBuilder(enabledEditors)
            .setTitle(I18nUtils.message("statusbar.popup.title"))
            .setItemChosenCallback { selectedEditor ->
                if (selectedEditor != currentEditor) {
                    EditorTargetUtils.setTargetEditor(project, selectedEditor)
                    // 更新状态栏显示
                    myStatusBar?.updateWidget(ID)
                }
            }
            .createPopup()
    }
}
