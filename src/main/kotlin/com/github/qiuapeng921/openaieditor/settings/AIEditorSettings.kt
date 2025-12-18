package com.github.qiuapeng921.openaieditor.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.*
import com.intellij.util.xmlb.XmlSerializerUtil

/**
 * AIEditor 插件设置类
 * 用于持久化存储插件配置信息
 */
@State(
    name = "AIEditorSettings",
    storages = [Storage("aieditor-settings.xml")]
)
@Service(Service.Level.APP)
class AIEditorSettings : PersistentStateComponent<AIEditorSettings> {
    
    // Kiro 可执行文件路径
    var kiroExecutablePath: String = ""
    
    // Antigravity 可执行文件路径
    var antigravityExecutablePath: String = ""
    
    override fun getState(): AIEditorSettings = this
    
    override fun loadState(state: AIEditorSettings) {
        XmlSerializerUtil.copyBean(state, this)
    }
    
    companion object {
        /**
         * 获取设置实例
         */
        fun getInstance(): AIEditorSettings {
            return ApplicationManager.getApplication().getService(AIEditorSettings::class.java)
        }
    }
}