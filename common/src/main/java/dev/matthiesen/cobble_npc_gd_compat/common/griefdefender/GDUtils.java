package dev.matthiesen.cobble_npc_gd_compat.common.griefdefender;

import com.griefdefender.api.Core;
import com.griefdefender.api.GriefDefender;
import net.minecraft.world.level.Level;

import java.util.UUID;

public final class GDUtils {
    public static Core getGriefDefenderCore() {
        return GriefDefender.getCore();
    }

    public static UUID getWorldID(Level level) {
        return getGriefDefenderCore().getWorldUniqueId(level);
    }

    public static GDLocation getClaim(Level level, int x, int y, int z) {
        return new GDLocation(level, x, y, z);
    }
}
