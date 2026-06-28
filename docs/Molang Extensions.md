# Molang Extensions

This document lists the Molang extension functions added by `cobble-npc-gd-compat`.

## Table of Contents

- [General Notes](#general-notes)
- [Universal Functions](#universal-functions)
- [NPC Functions](#npc-functions)
- [Player Functions](#player-functions)
- [Server Functions](#server-functions)
- [World Functions](#world-functions)
- [Example Usage](#example-usage)

## General Notes

- Boolean-style functions return `1` for true and `0` for false.
- Some functions return strings, integers, or arrays of objects depending on the lookup.
- Claim lookup functions generally return `0` when nothing is found.

## Universal Functions

These functions are available in multiple Molang contexts.

### `gd_economy_enabled()`

- Available on: `q.npc`, `q.player`, `q.server`, `q.world`
- Returns: `1` if claim economy is enabled, otherwise `0`

### `gd_get_player_claims(<uuid>)`

- Available on: `q.npc`, `q.server`, `q.world`
- Argument: player UUID
- Returns: an array of claims for the player
- Claim object format:
  - `uuid`
  - `displayName`
  - `ownerUUID`
  - `ownerName`

## NPC Functions

These functions are available on `q.npc`.

### `gd_is_wilderness()`

- Returns: `1` if the NPC is standing in wilderness, otherwise `0`

### `gd_claim_data()`

- Returns: claim data for the NPC's current location, or `0` if none exists
- Claim object format:
  - `uuid`
  - `displayName`
  - `ownerUUID`
  - `ownerName`

### `gd_claim_uuid()`

- Returns: the claim UUID as a string, or `0` if none exists

### `gd_claim_name()`

- Returns: the claim name as a string, or `0` if none exists

### `gd_claim_owner_uuid()`

- Returns: the owner UUID as a string, or `0` if none exists

### `gd_claim_owner_name()`

- Returns: the owner name as a string, or `0` if none exists

### `gd_available_rentals()`

- Returns: an array of available rentals
- Rental object format:
  - `uuid`
  - `displayName`
  - `ownerUUID`
  - `ownerName`
  - `rentalRate`

## Player Functions

These functions are available on `q.player`.

### `gd_claims()`

- Returns: an array of claims for the player
- Claim object format:
  - `uuid`
  - `displayName`
  - `ownerUUID`
  - `ownerName`

### `gd_current_claim()`

- Returns: the UUID of the claim the player is currently in, or `0`

### `gd_accrued_claim_blocks()`

- Returns: accrued claim blocks as an integer

### `gd_blocks_accrued_per_hour()`

- Returns: claim blocks earned per hour as an integer

### `gd_max_accrued_claim_blocks()`

- Returns: the maximum accrued claim blocks as an integer

### `gd_max_bonus_claim_blocks()`

- Returns: the maximum bonus claim blocks as an integer

### `gd_max_claim_level()`

- Returns: the maximum claim level as an integer

### `gd_min_claim_level()`

- Returns: the minimum claim level as an integer

### `gd_bonus_claim_blocks()`

- Returns: bonus claim blocks as an integer

### `gd_initial_claim_blocks()`

- Returns: initial claim blocks as an integer

### `gd_remaining_claim_blocks()`

- Returns: remaining claim blocks as an integer

### `gd_max_claimable_blocks()`

- Returns: the maximum claimable blocks as an integer

### `gd_rental_limit()`

- Returns: the rental limit as an integer

### `gd_available_rentals()`

- Returns: an array of available rentals
- Rental object format:
  - `uuid`
  - `displayName`
  - `ownerUUID`
  - `ownerName`
  - `rentalRate`

## Server Functions

These functions are available on `q.server`.

### `gd_get_player_claims(<uuid>)`

- Argument: player UUID
- Returns: an array of claims for the player

## World Functions

These functions are available on `q.world`.

### `gd_is_wilderness(x, y, z)`

- Arguments: world coordinates
- Returns: `1` if the location is wilderness, otherwise `0`

### `gd_get_claim_uuid(x, y, z)`

- Arguments: world coordinates
- Returns: the claim UUID as a string, or `0` if none exists

### `gd_get_claim_name(x, y, z)`

- Arguments: world coordinates
- Returns: the claim name as a string, or `0` if none exists

### `gd_get_claim_owner_uuid(x, y, z)`

- Arguments: world coordinates
- Returns: the owner UUID as a string, or `0` if none exists

### `gd_get_claim_owner_name(x, y, z)`

- Arguments: world coordinates
- Returns: the owner name as a string, or `0` if none exists

### `gd_available_rentals()`

- Returns: an array of available rentals
- Rental object format:
  - `uuid`
  - `displayName`
  - `ownerUUID`
  - `ownerName`
  - `rentalRate`

## Example Usage

```molang
q.player.gd_remaining_claim_blocks()
q.world.gd_is_wilderness(0, 64, 0)
q.npc.gd_claim_data()
q.npc.gd_claim_owner_name()
```

