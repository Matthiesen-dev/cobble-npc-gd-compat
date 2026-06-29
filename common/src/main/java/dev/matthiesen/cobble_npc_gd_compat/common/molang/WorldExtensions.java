package dev.matthiesen.cobble_npc_gd_compat.common.molang;

import com.bedrockk.molang.runtime.MoParams;
import com.cobblemon.mod.common.api.molang.MoLangFunctions;
import dev.matthiesen.cobble_npc_gd_compat.common.CobbleNPCGDCompat;
import dev.matthiesen.cobble_npc_gd_compat.common.molang.functions.UniversalFunctions;
import dev.matthiesen.cobble_npc_gd_compat.common.molang.functions.WorldFunctions;

import java.util.HashMap;
import java.util.function.Function;

public final class WorldExtensions {
    public static void register() {
        CobbleNPCGDCompat.INSTANCE.createInfoLog("Registering Cobblemon Molang World Extensions");

        MoLangFunctions.INSTANCE.getWorldFunctions().add(levelHolder -> {
            var world = levelHolder.value();
            HashMap<String, Function<MoParams, Object>> map = new HashMap<>();

            // q.world.gd_economy_enabled() returns 1 for true, or 0
            map.put("gd_economy_enabled", UniversalFunctions.isEconomyEnabled());

            // q.world.gd_get_player_claims(<uuid>) returns array of claims for user in the following format
            // [ { "uuid": "string", "displayName": "string", "ownerUUID": "string", "ownerName": "string" } ]
            map.put("gd_get_player_claims", UniversalFunctions.getPlayerClaims());

            // q.world.gd_is_wilderness(x, y, z) returns 1 for true or 0
            map.put("gd_is_wilderness", WorldFunctions.isWilderness(world));

            // q.world.gd_get_claim_uuid(x, y, z) returns string or 0
            map.put("gd_get_claim_uuid", WorldFunctions.getClaimUUID(world));

            // q.world.gd_get_claim_name(x, y, z) returns string or 0
            map.put("gd_get_claim_name", WorldFunctions.getClaimName(world));

            // q.world.gd_get_claim_owner_uuid(x, y, z) returns string or 0
            map.put("gd_get_claim_owner_uuid", WorldFunctions.getClaimOwnerUUID(world));

            // q.world.gd_get_claim_owner_name(x, y, z) returns string or 0
            map.put("gd_get_claim_owner_name", WorldFunctions.getClaimOwnerName(world));

            // q.world.gd_available_rentals() returns array of claims in the following format
            // [ { "uuid": "string", "displayName": "string", "ownerUUID": "string", "ownerName": "string", "rentalRate": double } ]
            map.put("gd_available_rentals", WorldFunctions.getAvailableRentals(world));

            // q.world.gd_available_forsale() returns array of claims in the following format
            // [ { "uuid": "string", "displayName": "string", "ownerUUID": "string", "ownerName": "string", "isForSale": false, "salePrice": double } ]
            map.put("gd_available_forsale", WorldFunctions.getAvailableForSale(world));

            return map;
        });
    }
}
