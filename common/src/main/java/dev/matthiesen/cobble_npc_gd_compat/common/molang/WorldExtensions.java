package dev.matthiesen.cobble_npc_gd_compat.common.molang;

import com.bedrockk.molang.runtime.MoParams;
import com.cobblemon.mod.common.api.molang.MoLangFunctions;
import dev.matthiesen.cobble_npc_gd_compat.common.CobbleNPCGDCompat;
import dev.matthiesen.cobble_npc_gd_compat.common.molang.functions.WorldFunctions;

import java.util.HashMap;
import java.util.function.Function;

public final class WorldExtensions {
    public static void register() {
        CobbleNPCGDCompat.INSTANCE.createInfoLog("Registering Cobblemon Molang World Extensions");

        MoLangFunctions.INSTANCE.getWorldFunctions().add(levelHolder -> {
            var world = levelHolder.value();
            HashMap<String, Function<MoParams, Object>> map = new HashMap<>();

            // q.world.is_wilderness(x, y, z) returns 1 for true or 0
            map.put("is_wilderness", WorldFunctions.isWilderness(world));

            // q.world.gd_get_claim_uuid(x, y, z) returns string or 0
            map.put("gd_get_claim_uuid", WorldFunctions.getClaimUUID(world));

            // q.world.gd_get_claim_name(x, y, z) returns string or 0
            map.put("gd_get_claim_name", WorldFunctions.getClaimName(world));

            // q.world.gd_get_claim_owner_uuid(x, y, z) returns string or 0
            map.put("gd_get_claim_owner_uuid", WorldFunctions.getClaimOwnerUUID(world));

            // q.world.gd_get_claim_owner_name(x, y, z) returns string or 0
            map.put("gd_get_claim_owner_name", WorldFunctions.getClaimOwnerName(world));

            return map;
        });
    }
}
