# AGENTS.md

## Cursor Cloud specific instructions

### Overview
This is **Ascension**, a Minecraft NeoForge mod (Minecraft 1.21.1, NeoForge 21.1.215). It is a single-project Java 21 Gradle build with no external databases or services.

### Build & Run Commands
All commands use the Gradle wrapper from the project root:

| Task | Command | Notes |
|---|---|---|
| Build | `./gradlew build` | Compiles mod JAR; warnings about deprecated NeoForge APIs are expected |
| Run Server (headless) | `./gradlew runServer` | Requires `run/eula.txt` with `eula=true`; runs with `--nogui` |
| Run Client | `./gradlew runClient` | Requires a display (X11/Xvfb) and OpenGL; not feasible in headless Cloud VMs |
| Data Generation | `./gradlew runData` | Generates recipe/loot table JSON resources |
| Game Tests | `./gradlew runGameTestServer` | Runs registered game tests then exits |

### Important Caveats
- **EULA**: Before running `./gradlew runServer`, ensure `run/eula.txt` exists with content `eula=true`. The directory is created automatically by Gradle on first server run attempt.
- **No lint/static-analysis**: There is no separate lint step; `./gradlew build` runs `compileJava` which surfaces compiler warnings. There are no checkstyle, spotbugs, or other lint plugins configured.
- **No unit tests**: The project has no JUnit/TestNG tests (`test` task reports `NO-SOURCE`). The only automated testing is via NeoForge GameTest framework (`./gradlew runGameTestServer`).
- **Deprecation warnings**: The build produces ~13 deprecation warnings from NeoForge API usage (e.g., `EventBusSubscriber.Bus`). These are pre-existing and expected.
- **Recipe parse error**: The server logs a non-fatal `JsonParseException` for recipe `ascension:qi_channeling/world_axis_perm` and a Modopedia book loading error. Both are pre-existing data issues in the mod, not environment problems.
- **Client testing**: `./gradlew runClient` requires GPU/OpenGL and a display server. This is not available in headless Cloud VMs. Server-side testing via `./gradlew runServer` is the primary way to verify the mod loads correctly in CI/cloud environments.
