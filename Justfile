# Task Runner Abstraction Layer

default:
    @just --list

# Bootstrap and verify toolchain and dependencies
install:
    ./gradlew --version

# Start local dev workflow (Fabric client)
dev:
    ./gradlew :fabric:runClient

# Produce build artifacts (JARs for Fabric and NeoForge)
build:
    ./gradlew build

# Run unit test suite
test:
    ./gradlew test

# Run strict compiler type checking across all modules
typecheck:
    ./gradlew compileJava

# Run static analysis (Checkstyle and Spotless check)
lint:
    ./gradlew checkstyleMain spotlessCheck

# Apply automated formatting (Spotless / Google Java Format)
format:
    ./gradlew spotlessApply

# Full quality gate (format, lint, typecheck, test, build verification)
check: format lint typecheck test
    ./gradlew check

# Remove build artifacts and temporary caches
clean:
    ./gradlew clean
