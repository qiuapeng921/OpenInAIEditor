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
    
    // 关键配置:防止自动更新版本范围
    updateSinceUntilBuild.set(false)
    sameSinceUntilBuild.set(false)
    
    // 移除插件配置,使用默认的平台插件
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
    
    // 在打包时排除 Kotlin 标准库，减小插件体积
    buildPlugin {
        exclude("**/kotlin-stdlib*.jar")
        exclude("**/kotlin-reflect*.jar")
        exclude("**/kotlinx-*.jar")
    }

    patchPluginXml {
        sinceBuild.set("233")
        untilBuild.set("253.*")  // 支持到 2025.3.x
        
        // 插件描述信息
        pluginDescription.set("""
            A powerful JetBrains IDE plugin that seamlessly integrates 16 AI code editors into your development workflow.
            Quickly switch between JetBrains IDE and your favorite AI editors with cursor position preservation.
            
            ✨ Key Features:
            • Support for 16 AI code editors (Cursor, Windsurf, Void, Antigravity, Kiro, and more)
            • Smart enable/disable control - only show the editors you need
            • Smart context menu integration - right-click to open in any enabled editor
            • Status bar widget for quick editor switching
            • Cursor position preservation - maintains line and column when switching editors
            • Cross-platform compatibility (macOS, Windows, Linux)
            • Flexible configuration - individual enable switch and path for each editor
            • International support (English & Chinese)
            
            🤖 Supported AI Editors:
            Cursor, Windsurf, Void, Trae, Qoder, Kiro, Antigravity, CatPawAI, Melty, Aide, Zed, PearAI, Void Editor, Supermaven, Aider, Continue
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