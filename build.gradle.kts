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
            <h2>Open In AIEditor - AI 代码编辑器快速切换插件</h2>
            
            <p>一个强大的 JetBrains IDE 插件，让你可以无缝切换到 16 个主流 AI 代码编辑器，保持光标位置和编辑上下文。</p>
            
            <h3>✨ 核心特性</h3>
            <ul>
                <li><strong>支持 16 个 AI 代码编辑器</strong>：Cursor, Windsurf, Void, Trae, Qoder, Kiro, Antigravity, CatPawAI, Melty, Aide, Zed, PearAI, Void Editor, Supermaven, Aider, Continue</li>
                <li><strong>智能启用控制</strong>：每个编辑器独立的启用开关，只显示你需要的编辑器</li>
                <li><strong>右键菜单集成</strong>：在文件或文件夹上右键即可快速打开</li>
                <li><strong>状态栏组件</strong>：快速切换默认编辑器</li>
                <li><strong>光标位置保持</strong>：自动定位到相同的行和列</li>
                <li><strong>跨平台支持</strong>：完整支持 macOS, Windows, Linux</li>
                <li><strong>灵活配置</strong>：每个编辑器独立的路径配置</li>
                <li><strong>国际化支持</strong>：中英文界面</li>
            </ul>
            
            <h3>🚀 使用方法</h3>
            <ol>
                <li>打开 <code>Settings → Tools → AIEditor</code></li>
                <li>勾选要使用的 AI 编辑器</li>
                <li>配置编辑器路径（macOS 通常自动检测）</li>
                <li>右键点击文件 → <code>Open In AIEditor</code> → 选择编辑器</li>
            </ol>
            
            <h3>⚡ 性能优化</h3>
            <ul>
                <li>插件体积仅 <strong>50 KB</strong>，下载和安装极快</li>
                <li>使用 IDE 提供的 Kotlin 标准库，避免版本冲突</li>
            </ul>
            
            <h3>🤖 支持的 AI 编辑器</h3>
            <p>Cursor, Windsurf, Void, Trae, Qoder, Kiro, Antigravity, CatPawAI, Melty, Aide, Zed, PearAI, Void Editor, Supermaven, Aider, Continue</p>
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