# Changelog

All notable changes to the "Open In AIEditor" plugin will be documented in this file.

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