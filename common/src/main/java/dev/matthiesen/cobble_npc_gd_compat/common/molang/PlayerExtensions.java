package dev.matthiesen.cobble_npc_gd_compat.common.molang;

import com.bedrockk.molang.runtime.MoParams;
import com.cobblemon.mod.common.api.molang.MoLangFunctions;
import dev.matthiesen.cobble_npc_gd_compat.common.CobbleNPCGDCompat;
import dev.matthiesen.cobble_npc_gd_compat.common.molang.functions.PlayerFunctions;
import dev.matthiesen.cobble_npc_gd_compat.common.molang.functions.UniversalFunctions;

import java.util.HashMap;
import java.util.function.Function;

public final class PlayerExtensions {
    public static void register() {
        CobbleNPCGDCompat.INSTANCE.createInfoLog("Registering Cobblemon Molang Player Extensions");

        MoLangFunctions.INSTANCE.getPlayerFunctions().add(player -> {
            HashMap<String, Function<MoParams, Object>> map = new HashMap<>();

            // q.player.gd_economy_enabled() returns 1 for true, or 0
            map.put("gd_economy_enabled", UniversalFunctions.isEconomyEnabled());

            // q.player.gd_claims() return list of UUIDs
            map.put("gd_claims", PlayerFunctions.getPlayerClaims(player));

            // q.player.gd_current_claim() returns UUID as string or 0;
            map.put("gd_current_claim", PlayerFunctions.getCurrentClaim(player));

            // q.player.gd_accrued_claim_blocks() returns an Integer
            map.put("gd_accrued_claim_blocks", PlayerFunctions.getAccruedClaimBlocks(player));

            // q.player.gd_blocks_accrued_per_hour() returns an Integer
            map.put("gd_blocks_accrued_per_hour", PlayerFunctions.getBlocksAccruedPerHour(player));

            // q.player.gd_max_accrued_claim_blocks() returns an Integer
            map.put("gd_max_accrued_claim_blocks", PlayerFunctions.getMaxAccruedClaimBlocks(player));

            // q.player.gd_max_bonus_claim_blocks() returns an Integer
            map.put("gd_max_bonus_claim_blocks", PlayerFunctions.getMaxBonusClaimBlocks(player));

            // q.player.gd_max_claim_level() returns an Integer
            map.put("gd_max_claim_level", PlayerFunctions.getMaxClaimLevel(player));

            // q.player.gd_min_claim_level() returns an Integer
            map.put("gd_min_claim_level", PlayerFunctions.getMinClaimLevel(player));

            // q.player.gd_bonus_claim_blocks() returns an Integer
            map.put("gd_bonus_claim_blocks", PlayerFunctions.getBonusClaimBlocks(player));

            // q.player.gd_initial_claim_blocks() returns an Integer
            map.put("gd_initial_claim_blocks", PlayerFunctions.getInitialClaimBlocks(player));

            // q.player.gd_remaining_claim_blocks() returns an Integer
            map.put("gd_remaining_claim_blocks", PlayerFunctions.getRemainingClaimBlocks(player));

            // q.player.gd_max_claimable_blocks() returns an Integer
            map.put("gd_max_claimable_blocks", PlayerFunctions.getMaxClaimableBlocks(player));

            // q.player.gd_rental_limit() returns an Integer
            map.put("gd_rental_limit", PlayerFunctions.getRentalLimit(player));

            return map;
        });
    }
}
