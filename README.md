# Open In AIEditor

[![Build](https://github.com/qiuapeng921/OpenInAIEditor/actions/workflows/build.yml/badge.svg)](https://github.com/qiuapeng921/OpenInAIEditor/actions/workflows/build.yml)
[![Release](https://github.com/qiuapeng921/OpenInAIEditor/actions/workflows/release.yml/badge.svg)](https://github.com/qiuapeng921/OpenInAIEditor/actions/workflows/release.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A simple JetBrains IDE plugin that adds context menu options to open files and folders in AI-powered editors.

## Features

- Right-click integration for files and folders
- Support for Antigravity and Kiro AI editors
- Works with all JetBrains IDEs (IntelliJ IDEA, PhpStorm, GoLand, WebStorm, PyCharm, etc.)
- Cross-platform support (Windows, macOS, Linux)

## Installation

1. Download the plugin ZIP file
2. In your JetBrains IDE: `Settings` → `Plugins` → `Install Plugin from Disk...`
3. Select the ZIP file and restart your IDE

## Configuration

1. Go to `Settings` → `Tools` → `AIEditor`
2. Set the executable paths for Antigravity and Kiro

## Usage

Right-click on any file or folder → `Open In AIEditor` → Choose your editor

## Development

### Building from Source
```bash
git clone https://github.com/qiuapeng921/OpenInAIEditor.git
cd OpenInAIEditor
./gradlew build
```

### Running Tests
```bash
./gradlew test
```

### Running in Development IDE
```bash
./gradlew runIde
```

## Contributing

Contributions are welcome! Please see [CONTRIBUTING.md](CONTRIBUTING.md) for details.

## License

MIT License