package dev.matthiesen.cobble_npc_gd_compat.neoforge;

import dev.matthiesen.cobble_npc_gd_compat.common.CobbleNPCGDCompat;
import net.neoforged.fml.common.Mod;

@Mod(CobbleNPCGDCompat.MOD_ID)
public class CobbleNPCGDCompatNeoForge {
    public CobbleNPCGDCompatNeoForge() {
        var instance = CobbleNPCGDCompat.INSTANCE;
        instance.createInfoLog("Loading for NeoForge Mod Loader");
        instance.initialize();
    }
}
