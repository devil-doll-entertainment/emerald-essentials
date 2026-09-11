# Contributing to Emerald Essentials

Contributions are welcome — bugs, fixes, features, or documentation.
This document covers how to work with the project as a contributor.

---

## Before You Start

- Search [existing issues](https://github.com/devil-doll-entertainment/emerald-essentials/issues) before opening a new one.
- For significant changes, open an issue first to discuss the direction before writing code.
- Read the [Code of Conduct](CODE_OF_CONDUCT.md). It applies to all interactions in this project.

---

## Reporting a Bug

Open a [GitHub Issue](https://github.com/devil-doll-entertainment/emerald-essentials/issues/new/choose) using the bug report template.

Include:
- What you expected to happen
- What actually happened
- Steps to reproduce
- Environment details (OS, Minecraft version, loader, Java version)

---

## Proposing a Feature

Open a [GitHub Issue](https://github.com/devil-doll-entertainment/emerald-essentials/issues/new/choose) using the feature request template, or submit a PR directly if the change is small and self-contained.

For larger features, an issue discussion first avoids wasted effort on both sides.

---

## Workflow

1. Fork the repository and create a branch from `master`.
2. Name your branch descriptively — `fix/dagger-durability`, `feat/amethyst-synergy`.
3. Make your changes.
4. Run the quality gate with `just check`.
5. Open a pull request against `master` with a clear description of what changed and why.

---

## Pull Request Checklist

Before submitting:

- [ ] The project builds without errors (`just build`)
- [ ] Quality gate passes completely (`just check`)
- [ ] Changes are described in [CHANGELOG.md](CHANGELOG.md) under `[Unreleased]`
- [ ] The PR description explains what changed and why
- [ ] New behavior is covered by tests where applicable

---

## Commit Style

This project uses [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/). Every commit message must follow the format:

```
<type>: <description>

[optional body]
[optional footer]
```

Accepted types:

| Type       | Use for                                          |
|------------|--------------------------------------------------|
| `feat`     | New functionality                                |
| `fix`      | Bug fixes                                        |
| `docs`     | Documentation only                               |
| `style`    | Formatting, whitespace — no logic changes        |
| `refactor` | Code restructure without behavior change         |
| `test`     | Adding or updating tests                         |
| `chore`    | Build process, tooling, dependencies             |
| `perf`     | Performance improvements                         |

Examples:

```
feat: add backstab critical bonus for emerald dagger
fix: resolve particle rendering for illusional flower
docs: update installation steps for fabric loader
chore: bump dependencies to latest stable
```

Commits that don't follow this format will be flagged during review.

---

## Questions

If something in the codebase is unclear, open an issue with the `question` label before assuming it's a bug.

---

*Emerald Essentials is a Devil Doll Entertainment project. Part of the [Sxnnyside Project](https://sxnnysideproject.com).*
