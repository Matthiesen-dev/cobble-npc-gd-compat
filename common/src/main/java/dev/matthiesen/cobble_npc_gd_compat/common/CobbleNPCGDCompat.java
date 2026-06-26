package dev.matthiesen.cobble_npc_gd_compat.common;

import dev.matthiesen.cobble_npc_gd_compat.common.molang.NPCExtensions;
import dev.matthiesen.cobble_npc_gd_compat.common.molang.PlayerExtensions;
import dev.matthiesen.cobble_npc_gd_compat.common.molang.ServerExtensions;
import dev.matthiesen.cobble_npc_gd_compat.common.molang.WorldExtensions;
import dev.matthiesen.common.matthiesen_lib_api.abstracts.AbstractCommonMod;
import dev.matthiesen.libs.faststats.Token;
import org.jetbrains.annotations.Nullable;

public class CobbleNPCGDCompat extends AbstractCommonMod {
    public static final String MOD_ID = "cobble_npc_gd_compat";
    public static final String MOD_NAME = "Cobblemon NPC GriefDefender Compatability";
    public static @Token final String METRICS_TOKEN = "ca6c7119064f9cfeeb83829dccab9ec6";

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

        NPCExtensions.register();
        PlayerExtensions.register();
        ServerExtensions.register();
        WorldExtensions.register();
        createInfoLog("Initialized");
    }

    @Override
    public Runnable reload() {
        return () -> {};
    }
}
