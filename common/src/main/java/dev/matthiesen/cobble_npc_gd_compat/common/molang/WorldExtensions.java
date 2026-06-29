package dev.matthiesen.cobble_npc_gd_compat.common.molang;

import com.bedrockk.molang.runtime.MoParams;
import com.bedrockk.molang.runtime.value.StringValue;
import com.cobblemon.mod.common.api.molang.MoLangFunctions;
import dev.matthiesen.cobble_npc_gd_compat.common.CobbleNPCGDCompat;
import dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.GDUtils;
import dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.data.GDLocation;
import dev.matthiesen.cobble_npc_gd_compat.common.molang.universal.UniversalFunctions;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.function.Function;

public final class WorldExtensions {
    private static GDLocation getClaim(Level level, MoParams params) {
        int x = params.getInt(0);
        int y = params.getInt(1);
        int z = params.getInt(2);
        return GDUtils.getClaim(level, x, y, z);
    }

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

            // q.world.gd_available_rentals() returns array of claims in the following format
            // [ { "uuid": "string", "displayName": "string", "ownerUUID": "string", "ownerName": "string", "rentalRate": double } ]
            map.put("gd_available_rentals", UniversalFunctions.getAvailableRentals(world));

            // q.world.gd_available_forsale() returns array of claims in the following format
            // [ { "uuid": "string", "displayName": "string", "ownerUUID": "string", "ownerName": "string", "isForSale": false, "salePrice": double } ]
            map.put("gd_available_forsale", UniversalFunctions.getAvailableForSale(world));

            // q.world.gd_is_wilderness(x, y, z) returns 1 for true or 0
            map.put("gd_is_wilderness", params -> {
                var claim = getClaim(world, params);
                if (claim.getClaim() == null) return UniversalFunctions.isNull();
                return UniversalFunctions.intToDouble(claim.isWilderness() ? 1 : 0);
            });

            // q.world.gd_get_claim_uuid(x, y, z) returns string or 0
            map.put("gd_get_claim_uuid", params -> {
                var claim = getClaim(world, params);
                if (claim.getClaim() == null) return UniversalFunctions.isNull();
                var claimOwner = claim.getUUID();
                return claimOwner != null ? new StringValue(claimOwner.toString()) : UniversalFunctions.isNull();
            });

            // q.world.gd_get_claim_name(x, y, z) returns string or 0
            map.put("gd_get_claim_name", params -> {
                var claim = getClaim(world, params);
                if (claim.getClaim() == null) return UniversalFunctions.isNull();
                var claimOwner = claim.getDisplayName();
                return claimOwner != null ? new StringValue(claimOwner) : UniversalFunctions.isNull();
            });

            // q.world.gd_get_claim_owner_uuid(x, y, z) returns string or 0
            map.put("gd_get_claim_owner_uuid", params -> {
                var claim = getClaim(world, params);
                if (claim.getClaim() == null) return UniversalFunctions.isNull();
                var claimOwner = claim.getOwnerUUID();
                return claimOwner != null ? new StringValue(claimOwner.toString()) : UniversalFunctions.isNull();
            });

            // q.world.gd_get_claim_owner_name(x, y, z) returns string or 0
            map.put("gd_get_claim_owner_name", params -> {
                var claim = getClaim(world, params);
                if (claim.getClaim() == null) return UniversalFunctions.isNull();
                var claimOwner = claim.getOwnerName();
                return claimOwner != null ? new StringValue(claimOwner) : UniversalFunctions.isNull();
            });

            return map;
        });
    }
}
