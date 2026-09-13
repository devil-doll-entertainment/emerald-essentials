# Task Runner Abstraction Layer

default:
    @just --list

# Bootstrap and verify toolchain and dependencies
install:
    ./gradlew --version
    @git config core.hooksPath .githooks 2>/dev/null || true
    @chmod +x .githooks/pre-commit 2>/dev/null || true
    @echo "Environment and git hooks initialized."

# Start local dev workflow (Fabric client)
dev:
    ./gradlew :fabric:runClient

# Start local dev workflow (NeoForge client)
dev-neoforge:
    ./gradlew :neoforge:runClient

# Run Data Generation (Fabric)
datagen:
    ./gradlew :fabric:runDatagen

# Run Data Generation (NeoForge)
datagen-neoforge:
    ./gradlew :neoforge:runData

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

# Clean build artifacts and caches
clean:
    ./gradlew clean

# Clean generated test worlds from dev runs
clean-saves:
    rm -rf fabric/run/saves/* neoforge/run/saves/*
    @echo "Cleared dev world saves."

# Clean dev run logs, crash reports, and world saves
clean-runs: clean-saves
    rm -rf fabric/run/logs/* neoforge/run/logs/*
    rm -rf fabric/run/crash-reports/* neoforge/run/crash-reports/*
    @echo "Cleared dev logs and crash reports."

# Synchronize docs/wiki/ directly to the GitHub Wiki repository
wiki-sync:
    @echo "==> Syncing docs/wiki to GitHub Wiki..."
    @rm -rf /tmp/emerald-essentials-wiki
    @git clone https://github.com/devil-doll-entertainment/emerald-essentials.wiki.git /tmp/emerald-essentials-wiki
    @cp -r docs/wiki/* /tmp/emerald-essentials-wiki/
    @cd /tmp/emerald-essentials-wiki && git add -A && (git diff --cached --quiet && echo "No changes to sync." || (git commit -m "docs(wiki): update wiki documentation from main repo" && git push origin master))
    @rm -rf /tmp/emerald-essentials-wiki
    @echo "==> GitHub Wiki successfully synchronized!"

