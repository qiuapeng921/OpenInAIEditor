@echo off
echo 正在提交更改...
git add .
git commit -m "修复 GitHub Actions 构建问题

- 使用 gradle/gradle-build-action@v2 自动处理 gradlew 权限
- 修复 Kotlin 编译器选项语法（kotlinOptions -> compilerOptions）
- 重新下载 gradle-wrapper.jar 文件
- 简化工作流程配置"

echo.
echo 正在推送到 GitHub...
git push origin master

echo.
echo 完成！
pause
