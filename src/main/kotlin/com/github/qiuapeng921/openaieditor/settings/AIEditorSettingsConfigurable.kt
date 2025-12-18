package com.github.qiuapeng921.openaieditor.settings

import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.ui.components.JBLabel
import com.intellij.util.ui.FormBuilder
import javax.swing.JComponent
import javax.swing.JPanel

/**
 * AIEditor 设置配置界面
 * 提供用户界面来配置 AIEditor 可执行文件路径
 */
class AIEditorSettingsConfigurable : Configurable {
    
    private var kiroPathField: TextFieldWithBrowseButton? = null
    private var antigravityPathField: TextFieldWithBrowseButton? = null
    private val settings = AIEditorSettings.getInstance()
    
    override fun getDisplayName(): String = "AIEditor"
    
    /**
     * 创建设置界面组件
     */
    override fun createComponent(): JComponent? {
        
        kiroPathField = TextFieldWithBrowseButton().apply {
            addActionListener {
                val descriptor = FileChooserDescriptor(true, false, false, false, false, false)
                    .withTitle("选择 Kiro 可执行文件")
                    .withDescription("选择 Kiro 可执行文件的路径")
                
                com.intellij.openapi.fileChooser.FileChooser.chooseFile(
                    descriptor,
                    null as Project?,
                    null
                ) { file ->
                    text = file.path
                }
            }
        }
        
        antigravityPathField = TextFieldWithBrowseButton().apply {
            addActionListener {
                val descriptor = FileChooserDescriptor(true, false, false, false, false, false)
                    .withTitle("选择 Antigravity 可执行文件")
                    .withDescription("选择 Antigravity 可执行文件的路径")
                
                com.intellij.openapi.fileChooser.FileChooser.chooseFile(
                    descriptor,
                    null as Project?,
                    null
                ) { file ->
                    text = file.path
                }
            }
        }
        
        return FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("Antigravity 可执行文件路径:"), antigravityPathField!!, 1, false)
            .addLabeledComponent(JBLabel("Kiro 可执行文件路径:"), kiroPathField!!, 1, false)
            .addComponentFillVertically(JPanel(), 0)
            .panel
    }
    
    /**
     * 检查设置是否已修改
     */
    override fun isModified(): Boolean {
        return kiroPathField?.text != settings.kiroExecutablePath ||
               antigravityPathField?.text != settings.antigravityExecutablePath
    }
    
    /**
     * 应用设置更改
     */
    override fun apply() {
        settings.kiroExecutablePath = kiroPathField?.text ?: ""
        settings.antigravityExecutablePath = antigravityPathField?.text ?: ""
    }
    
    /**
     * 重置设置到当前保存的值
     */
    override fun reset() {
        kiroPathField?.text = settings.kiroExecutablePath
        antigravityPathField?.text = settings.antigravityExecutablePath
    }
}