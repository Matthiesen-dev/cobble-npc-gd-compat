package dev.matthiesen.cobble_npc_gd_compat.fabric;

import dev.matthiesen.cobble_npc_gd_compat.common.CobbleNPCGDCompat;
import net.fabricmc.api.ModInitializer;

public final class CobbleNPCGDCompatFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        var instance = CobbleNPCGDCompat.INSTANCE;
        instance.createInfoLog("Loading for Fabric Mod Loader");
        instance.initialize();
    }
}
