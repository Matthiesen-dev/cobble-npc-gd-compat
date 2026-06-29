package dev.matthiesen.cobble_npc_gd_compat.common.molang;

import com.bedrockk.molang.runtime.MoParams;
import com.bedrockk.molang.runtime.value.StringValue;
import com.cobblemon.mod.common.api.molang.MoLangFunctions;
import com.griefdefender.api.data.PlayerData;
import dev.matthiesen.cobble_npc_gd_compat.common.CobbleNPCGDCompat;
import dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.GDUtils;
import dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.data.SimpleClaimData;
import dev.matthiesen.cobble_npc_gd_compat.common.molang.universal.UniversalFunctions;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public final class PlayerExtensions {
    public static void register() {
        CobbleNPCGDCompat.INSTANCE.createInfoLog("Registering Cobblemon Molang Player Extensions");

        MoLangFunctions.INSTANCE.getPlayerFunctions().add(player -> {
            HashMap<String, Function<MoParams, Object>> map = new HashMap<>();

            // q.player.gd_economy_enabled() returns 1 for true, or 0
            map.put("gd_economy_enabled", UniversalFunctions.isEconomyEnabled());

            // q.player.gd_available_rentals() returns array of claims in the following format
            // [ { "uuid": "string", "displayName": "string", "ownerUUID": "string", "ownerName": "string", "rentalRate": double } ]
            map.put("gd_available_rentals", UniversalFunctions.getAvailableRentals(player.level()));

            // q.player.gd_available_forsale() returns array of claims in the following format
            // [ { "uuid": "string", "displayName": "string", "ownerUUID": "string", "ownerName": "string", "isForSale": false, "salePrice": double } ]
            map.put("gd_available_forsale", UniversalFunctions.getAvailableForSale(player.level()));

            // q.npc.gd_get_player_claims(<uuid>) returns array of claims for user in the following format
            // [ { "uuid": "string", "displayName": "string", "ownerUUID": "string", "ownerName": "string" } ]
            map.put("gd_claims", params -> {
                List<SimpleClaimData> playerClaims = GDUtils.getPlayerClaims(player.getUUID());
                return SimpleClaimData.asMolangValueFromList(playerClaims);
            });

            // q.player.gd_current_claim() returns UUID as string or 0;
            map.put("gd_current_claim", params -> {
                PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
                var claim = playerData.getCurrentClaim();
                if (claim == null) return UniversalFunctions.isNull();
                return new StringValue(claim.getUniqueId().toString());
            });

            // q.player.gd_accrued_claim_blocks() returns an Integer
            map.put("gd_accrued_claim_blocks", params -> {
                PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
                return UniversalFunctions.intToDouble(playerData.getAccruedClaimBlocks());
            });

            // q.player.gd_blocks_accrued_per_hour() returns an Integer
            map.put("gd_blocks_accrued_per_hour", params -> {
                PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
                return UniversalFunctions.intToDouble(playerData.getBlocksAccruedPerHour());
            });

            // q.player.gd_max_accrued_claim_blocks() returns an Integer
            map.put("gd_max_accrued_claim_blocks", params -> {
                PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
                return UniversalFunctions.intToDouble(playerData.getMaxAccruedClaimBlocks());
            });

            // q.player.gd_max_bonus_claim_blocks() returns an Integer
            map.put("gd_max_bonus_claim_blocks", params -> {
                PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
                return UniversalFunctions.intToDouble(playerData.getMaxBonusClaimBlocks());
            });

            // q.player.gd_max_claim_level() returns an Integer
            map.put("gd_max_claim_level", params -> {
                PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
                return UniversalFunctions.intToDouble(playerData.getMaxClaimLevel());
            });

            // q.player.gd_min_claim_level() returns an Integer
            map.put("gd_min_claim_level", params -> {
                PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
                return UniversalFunctions.intToDouble(playerData.getMinClaimLevel());
            });

            // q.player.gd_bonus_claim_blocks() returns an Integer
            map.put("gd_bonus_claim_blocks", params -> {
                PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
                return UniversalFunctions.intToDouble(playerData.getBonusClaimBlocks());
            });

            // q.player.gd_initial_claim_blocks() returns an Integer
            map.put("gd_initial_claim_blocks", params -> {
                PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
                return UniversalFunctions.intToDouble(playerData.getInitialClaimBlocks());
            });

            // q.player.gd_remaining_claim_blocks() returns an Integer
            map.put("gd_remaining_claim_blocks", params -> {
                PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
                return UniversalFunctions.intToDouble(playerData.getRemainingClaimBlocks());
            });

            // q.player.gd_max_claimable_blocks() returns an Integer
            map.put("gd_max_claimable_blocks", params -> {
                PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
                return UniversalFunctions.intToDouble(playerData.getMaxClaimableBlocks());
            });

            // q.player.gd_rental_limit() returns an Integer
            map.put("gd_rental_limit", params -> {
                PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
                return UniversalFunctions.intToDouble(playerData.getRentalLimit());
            });

            return map;
        });
    }
}
