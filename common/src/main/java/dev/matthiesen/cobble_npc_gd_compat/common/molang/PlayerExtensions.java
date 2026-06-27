package dev.matthiesen.cobble_npc_gd_compat.common.molang;

import com.bedrockk.molang.runtime.MoParams;
import com.cobblemon.mod.common.api.molang.MoLangFunctions;
import dev.matthiesen.cobble_npc_gd_compat.common.CobbleNPCGDCompat;
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

            return map;
        });
    }
}
