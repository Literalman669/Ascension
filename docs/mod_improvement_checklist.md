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

## Batch 1 (in progress)

- `[~]` Remove remaining `System.out.println` debug spam in runtime code.
- `[~]` Harden `PlayerSkillData` null/index edge cases (invalid skill IDs and unsafe slot updates).
- `[~]` Replace hot-path `stream().toList()` snapshots with lower-overhead list copies in skill maps.
- `[~]` Replace unchecked `Optional.get()` access in channeling recipe lookup.
- `[ ]` Re-run focused build validation and keep branch green.

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

