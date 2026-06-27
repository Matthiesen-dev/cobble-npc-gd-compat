package dev.matthiesen.cobble_npc_gd_compat.common.griefdefender;

import com.griefdefender.api.Core;
import com.griefdefender.api.GriefDefender;
import com.griefdefender.api.data.PlayerData;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.UUID;

public final class GDUtils {
    public static Core getGriefDefenderCore() {
        return GriefDefender.getCore();
    }

    public static boolean isEconomyEnabled() {
        return getGriefDefenderCore().isEconomyModeEnabled();
    }

    public static UUID getWorldID(Level level) {
        return getGriefDefenderCore().getWorldUniqueId(level);
    }

    public static PlayerData getPlayerData(Level level, UUID playerUUID) {
        return getGriefDefenderCore().getPlayerData(getWorldID(level), playerUUID);
    }

    public static GDLocation getClaim(Level level, int x, int y, int z) {
        return new GDLocation(level, x, y, z);
    }

    public static List<SimpleClaimData> getPlayerClaims(UUID player) {
        return getGriefDefenderCore().getAllPlayerClaims(player)
                .stream()
                .map(SimpleClaimData::fromClaim)
                .toList();
    }
}
