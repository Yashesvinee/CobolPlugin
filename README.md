# CobolPlugin

A JetBrains IntelliJ IDEA plugin that provides essential COBOL language support, including syntax highlighting and parsing capabilities.

## Overview

CobolPlugin is a custom language support plugin for IntelliJ IDEA that implements basic COBOL language features following the IntelliJ Custom Language Support Tutorial.

## Requirements

- **IntelliJ IDEA** 2025.1.4.1 or later (IC - IntelliJ Community Edition)
- **Java** 11 or higher
- **Gradle** (included via gradle wrapper)

## Installation

### From Source

1. Clone or download the project:
```bash
git clone <repository-url>
cd CobolPlugin
```

2. Build the plugin:
```bash
./gradlew build
```

3. Run the plugin in a sandbox IDE:
```bash
./gradlew runIde
```

### From Built JAR

1. Build the plugin:
```bash
./gradlew build
```

2. The compiled plugin JAR will be located in `build/libs/CobolPlugin-1.0-SNAPSHOT.jar`

3. Install it in IntelliJ IDEA:
   - Go to **Settings** → **Plugins** → **⚙️** → **Install Plugin from Disk**
   - Select the JAR file

## Project Structure

```
CobolPlugin/
├── src/
│   ├── main/
│   │   ├── java/cobol/              # Main plugin implementation
│   │   │   ├── CobolFile.java       # File type descriptor
│   │   │   ├── CobolLanguage.java   # Language definition
│   │   │   ├── CobolLexer.java      # Lexical analyzer
│   │   │   ├── CobolParser.java     # Parser
│   │   │   └── ...                  # Other language components
│   │   ├── kotlin/cobol/            # Additional Kotlin-based components
│   │   └── resources/
│   │       ├── Cobol.flex           # Lexer definition (JFlex format)
│   │       ├── Cobol.bnf            # Grammar definition
│   │       └── META-INF/plugin.xml  # Plugin configuration
│   └── test/                        # Unit tests
├── build.gradle.kts                 # Gradle build configuration
├── gradle/                          # Gradle wrapper
└── README.md                        # This file
```

## Key Components

### Language Definition
- **CobolLanguage.java** - Defines the Cobol language
- **CobolFileType.java** - Registers `.cbl` file type

### Lexical Analysis
- **Cobol.flex** - JFlex lexer definition
- **CobolLexer.java** - Generated lexer implementation
- **CobolLexerAdapter.java** - Adapter for IntelliJ integration

### Parsing
- **Cobol.bnf** - Backus-Naur Form grammar definition
- **CobolParser.java** - Generated parser implementation
- **CobolParserDefinition.java** - Parser configuration

### Syntax Highlighting
- **CobolSyntaxHighlighter.java** - Syntax coloring rules
- **CobolSyntaxHighlighterFactory.java** - Factory for creating highlighters

## Building

### Build the Plugin

```bash
./gradlew build
```

This will:
1. Generate lexer from `Cobol.flex`
2. Generate parser from `Cobol.bnf`
3. Compile all Java/Kotlin sources
4. Create the plugin JAR in `build/libs/`

### Run in Sandbox IDE

```bash
./gradlew runIde
```

This launches a temporary IntelliJ IDEA instance with the plugin installed.

### Clean Build

```bash
./gradlew clean build
```

## Configuration

### Plugin Settings

The plugin configuration is defined in `src/main/resources/META-INF/plugin.xml`:

- **Plugin ID**: `CobolPlugin.CobolPlugin`
- **Minimum IDE Version**: 2025.1 (build 251)
- **Dependencies**: IntelliJ Platform modules

### Gradle Configuration

Key settings in `build.gradle.kts`:

```kotlin
group = "CobolPlugin"
version = "1.0-SNAPSHOT"

intellijPlatform {
    pluginConfiguration {
        ideaVersion {
            sinceBuild = "251"
        }
    }
}
```

## Development

### Adding Language Features

1. **Extend Grammar** - Modify `src/main/resources/Cobol.bnf`
2. **Extend Lexer** - Modify `src/main/resources/Cobol.flex`
3. **Regenerate** - Run `./gradlew build` to regenerate parser/lexer
4. **Implement Handlers** - Create supporting Java classes as needed

### Testing

Run the sandbox IDE to test your changes:

```bash
./gradlew runIde
```

Open a `.cbl` file to verify syntax highlighting and parsing.

## File Extension

The plugin recognizes COBOL source files with the `.cbl` extension.

Example:
```cobol
       IDENTIFICATION DIVISION.
       PROGRAM-ID. HELLO.
       PROCEDURE DIVISION.
           DISPLAY 'HELLO WORLD'.
           STOP RUN.
```

## Troubleshooting

### Plugin Not Loading
- Verify IntelliJ IDEA version is 2025.1.4.1 or later
- Check `Help` → `About` to confirm IDE version
- Reinstall the plugin from `Settings` → `Plugins`

### Syntax Highlighting Not Working
- Ensure the file extension is `.cbl`
- Verify the language is set to "Cobol" in the status bar
- Try invalidating caches: `File` → `Invalidate Caches / Restart`

### Build Errors
- Run `./gradlew clean build` for a fresh build
- Ensure Java 11+ is installed: `java -version`
- Check Gradle version compatibility

## License
This project is provided as-is for educational and development purposes.

## Resources

- [IntelliJ Plugin Development Documentation](https://plugins.jetbrains.com/docs/intellij/)
- [Custom Language Support Tutorial](https://plugins.jetbrains.com/docs/intellij/custom-language-support-tutorial.html)
- [JFlex Documentation](https://jflex.de/)
- [GrammarKit Documentation](https://github.com/JetBrains/Grammar-Kit)