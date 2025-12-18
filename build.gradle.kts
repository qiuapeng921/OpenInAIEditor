plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.3.0"
    id("org.jetbrains.intellij") version "1.17.4"
}

group = "com.github.qiuapeng921.openaieditor"
version = "1.0.0"

repositories {
    mavenCentral()
}

intellij {
    version.set("2023.3.8")
    type.set("IC") // IntelliJ IDEA 社区版
    
    // 移除插件配置，使用默认的平台插件
    plugins.set(listOf())
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
    }

    patchPluginXml {
        sinceBuild.set("233")
        untilBuild.set("253.*")
        
        // 插件描述信息
        pluginDescription.set("""
            A powerful JetBrains IDE plugin that seamlessly integrates AI-powered editors into your development workflow.
            Add convenient context menu options to quickly open files and folders in external AI editors like Antigravity and Kiro.
            
            Key Features:
            • Smart context menu integration
            • Support for Antigravity and Kiro AI editors  
            • Easy configuration through settings panel
            • Cross-platform compatibility
            • Universal JetBrains IDE support
        """.trimIndent())
        
        // 变更日志
        changeNotes.set("""
            <h3>🎉 Version 2.0.0 - Major Rewrite</h3>
            <ul>
                <li>🎯 Rebranded to "Open In AIEditor"</li>
                <li>📦 Package restructure to com.github.qiuapeng921.openaieditor</li>
                <li>🧹 Simplified codebase, removed auto-detection</li>
                <li>📁 Improved hierarchical menu structure</li>
                <li>⚡ Performance improvements and better UX</li>
                <li>📖 Open source ready with proper documentation</li>
            </ul>
        """.trimIndent())
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}