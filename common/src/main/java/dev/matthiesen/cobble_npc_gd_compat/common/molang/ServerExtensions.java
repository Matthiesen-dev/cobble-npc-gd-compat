package dev.matthiesen.cobble_npc_gd_compat.common.molang;

import com.bedrockk.molang.runtime.MoParams;
import com.cobblemon.mod.common.api.molang.MoLangFunctions;
import dev.matthiesen.cobble_npc_gd_compat.common.CobbleNPCGDCompat;
import dev.matthiesen.cobble_npc_gd_compat.common.molang.universal.UniversalFunctions;

import java.util.HashMap;
import java.util.function.Function;

public final class ServerExtensions {
    public static void register() {
        CobbleNPCGDCompat.INSTANCE.createInfoLog("Registering Cobblemon Molang Server Extensions");

        MoLangFunctions.INSTANCE.getServerFunctions().add(server -> {
            HashMap<String, Function<MoParams, Object>> map = new HashMap<>();

            // q.server.gd_economy_enabled() returns 1 for true, or 0
            map.put("gd_economy_enabled", UniversalFunctions.isEconomyEnabled());

            // q.server.gd_get_player_claims(<uuid>) returns array of claims for user in the following format
            // [ { "uuid": "string", "displayName": "string", "ownerUUID": "string", "ownerName": "string" } ]
            map.put("gd_get_player_claims", UniversalFunctions.getPlayerClaims());

            return map;
        });
    }
}
