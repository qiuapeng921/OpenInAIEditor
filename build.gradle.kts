plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.3.0"
    id("org.jetbrains.intellij") version "1.17.4"
}

group = "com.github.qiuapeng921.openaieditor"
version = "1.0.1"

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
        untilBuild.set("")  // 不设置上限，支持所有未来版本
        
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
        
        // 从 CHANGELOG.md 读取变更日志
        val changelogFile = file("CHANGELOG.md")
        if (changelogFile.exists()) {
            val changelog = changelogFile.readText()
            // 提取最新版本的变更内容（从第一个 ## 到下一个 ## 或文件结束）
            val latestChanges = changelog
                .substringAfter("## [")
                .substringBefore("\n## [")
                .let { section ->
                    val version = section.substringBefore("]")
                    val content = section.substringAfter("\n").trim()
                    // 转换 Markdown 为简单 HTML
                    val htmlContent = content
                        .replace(Regex("### (.+)")) { "<h4>${it.groupValues[1]}</h4>" }
                        .replace(Regex("- (.+)")) { "<li>${it.groupValues[1]}</li>" }
                        .let { "<h3>🎉 Version $version</h3><ul>$it</ul>" }
                    htmlContent
                }
            changeNotes.set(latestChanges)
        }
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