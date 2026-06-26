package dev.matthiesen.cobble_npc_gd_compat.common;

import dev.matthiesen.common.matthiesen_lib_api.MatthiesenLibApi;
import dev.matthiesen.common.matthiesen_lib_api.abstracts.AbstractCommonMod;
import dev.matthiesen.libs.faststats.Token;
import org.jetbrains.annotations.Nullable;

public class CobbleNPCGDCompat extends AbstractCommonMod {
    public static final String MOD_ID = "cobble_npc_gd_compat";
    public static final String MOD_NAME = "Cobblemon NPC GriefDefender Compatability";
    public static @Token final String METRICS_TOKEN = "";

    public static final CobbleNPCGDCompat INSTANCE = new CobbleNPCGDCompat();

    public CobbleNPCGDCompat() {
        super(MOD_ID, MOD_NAME);
    }

    @Override
    public @Nullable @Token String getMetricsToken() {
        return METRICS_TOKEN;
    }

    @Override
    public void initialize() {
        super.initialize();

        if (MatthiesenLibApi.isModLoaded("cobblemon")) {
            createInfoLog("Cobblemon is loaded, Hello there Cobblemon!");
        }

        createInfoLog("Initialized");
    }

    @Override
    public Runnable reload() {
        return () -> {
            // TODO
            createInfoLog("Reloaded");
        };
    }
}
