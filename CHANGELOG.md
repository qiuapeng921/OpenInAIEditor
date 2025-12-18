# Changelog

All notable changes to the "Open In AIEditor" plugin will be documented in this file.

## [1.0.3] - 2025-12-18

### Fixed
- **修复版本兼容性配置问题** - 添加关键的 `updateSinceUntilBuild` 和 `sameSinceUntilBuild` 配置
- 明确设置 `untilBuild` 为 `253.*`,确保插件市场正确识别兼容性
- 完全解决 "Not compatible with PhpStorm 2025.3" 的问题

### Changed
- 优化构建配置,参考成功案例 EditorJumper 的配置

## [1.0.2] - 2025-12-18

### Fixed
- 移除未使用的废弃 API 调用 `FileChooserDescriptorFactory.createSingleFileDescriptor()`
- 清理未使用的导入,进一步提升代码质量

## [1.0.1] - 2025-12-18

### Fixed
- 修复使用废弃 API 的问题,替换 `addBrowseFolderListener` 为新的 `FileChooser.chooseFile` API
- 完全兼容 PhpStorm 2025.3 和 IntelliJ IDEA 2025.3

### Changed
- 更新文件选择器实现,使用最新的 IntelliJ Platform API

## [1.0.0] - 2025-12-18

### Added
- Initial plugin release
- Context menu integration for files and folders
- Support for Antigravity and Kiro AI editors
- Settings panel for configuration
- Cross-platform compatibility