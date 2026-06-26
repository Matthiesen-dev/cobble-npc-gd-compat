package dev.matthiesen.cobble_npc_gd_compat.common.molang;

import com.bedrockk.molang.runtime.MoParams;
import com.cobblemon.mod.common.api.molang.MoLangFunctions;
import dev.matthiesen.cobble_npc_gd_compat.common.CobbleNPCGDCompat;

import java.util.HashMap;
import java.util.function.Function;

public final class WorldExtensions {
    public static void register() {
        CobbleNPCGDCompat.INSTANCE.createInfoLog("Registering Cobblemon Molang World Extensions");

        MoLangFunctions.INSTANCE.getWorldFunctions().add(levelHolder -> {
            HashMap<String, Function<MoParams, Object>> map = new HashMap<>();

            return map;
        });
    }
}
