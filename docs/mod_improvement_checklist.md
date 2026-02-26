# Ascension Mod Improvement Checklist

This checklist tracks polish, optimization, and stability improvements in small, safe batches.

Legend:
- `[x]` done
- `[~]` in progress
- `[ ]` planned

## Batch 0 (completed)

- `[x]` Prevent map mutation during iteration in `PlayerData` tick loops.
- `[x]` Remove avoidable allocations in `SkillEventListener`.
- `[x]` Remove debug spam and simplify visibility logic in `ItemScrollContainer`.
- `[x]` Remove redundant logic in `BarrierFormation#getEnergyCost`.
- `[x]` Add server-null guards in spatial teleport callback paths.
- `[x]` Harden server/client access path in spatial ring data retrieval.
- `[x]` Validate with Gradle `compileJava`, `test`, and `check`.

## Batch 1 (completed)

- `[x]` Remove remaining `System.out.println` debug spam in runtime code.
- `[x]` Harden `PlayerSkillData` null/index edge cases (invalid skill IDs and unsafe slot updates).
- `[x]` Replace hot-path `stream().toList()` snapshots with lower-overhead list copies in skill maps.
- `[x]` Replace unchecked `Optional.get()` access in channeling recipe lookup.
- `[x]` Re-run focused build validation and keep branch green.

## Batch 2 (completed)

- `[x]` Run real gameplay smoke test and prioritize concrete runtime issues from live logs.
- `[x]` Fix runtime data/resource errors (Modopedia IDs, recipe JSON format, missing model/blockstate, invalid temp resource path, config translation keys).
- `[x]` Suppress recipe-book category warning for qi channeling by marking those recipes special.
- `[x]` Eliminate creative-tab duplicate warnings via JEI subtype interpreters for physique and formation variants.
- `[x]` Re-test in-game after restarts and confirm targeted warnings/errors are gone from `run/logs/latest.log`.

## Batch 3 (completed)

- `[x]` Audit visual UX of custom GUI screens (Skill Menu, Introspection, keybind-driven UI access points).
- `[x]` Add visible scrollbar rails/handles for Skill Menu active and passive lists.
- `[x]` Improve scroll container behavior and offset math for active/passive/spatial ring list rendering.
- `[x]` Add clear in-game feedback when opening Spatial Ring without a ring and when skill wheel has no slotted skills.
- `[x]` Rebuild and validate via manual GUI smoke test recording.

## Batch 4 (next)

- `[ ]` Start async teleport threading review in `SpatialRuptureAPI` and document safe-thread constraints.
- `[ ]` Add missing guardrails around recipe/attachment fetches in frequently used skill paths.
- `[ ]` Add first lightweight gameplay regression checks for cooldown and cast-thread transitions.

## Backlog (prioritized)

### P1 (high-value correctness/stability)
- `[ ]` Review async teleport search threading model in `SpatialRuptureAPI` and move world reads fully to server thread if needed.
- `[ ]` Add guardrails for recipe manager / attachment access in gameplay-critical systems (channeling, pill crafting, skill casting).
- `[ ]` Add a lightweight regression test surface (GameTests or deterministic integration checks) for cooldown/casting behavior.

### P2 (performance + maintainability)
- `[ ]` Cache repeated `BlockPos#getCenter()` and expensive geometry values inside barrier collision paths.
- `[ ]` Audit event listeners for repeated allocations and convert to reusable local variables where safe.
- `[ ]` Normalize defensive helper methods for item insertion checks and capability lookups to reduce duplication.
- `[ ]` Replace stringly-typed skill type checks (`"Active"`) with enum-driven checks where feasible.

### P3 (quality-of-life + developer ergonomics)
- `[ ]` Replace ad-hoc debug prints with structured mod logger usage behind debug-level logs.
- `[ ]` Add concise internal developer docs for systems that currently require code spelunking (skills, spatial rings, formations).
- `[ ]` Add optional lint/static-analysis task profile for routine quality sweeps.

