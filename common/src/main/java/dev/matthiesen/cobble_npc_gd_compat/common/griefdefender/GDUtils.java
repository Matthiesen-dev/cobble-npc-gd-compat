package dev.matthiesen.cobble_npc_gd_compat.common.griefdefender;

import com.griefdefender.api.Core;
import com.griefdefender.api.GriefDefender;
import com.griefdefender.api.claim.Claim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public final class GDUtils {
    public static Core getGriefDefenderCore() {
        return GriefDefender.getCore();
    }

    public static UUID getWorldID(Level level) {
        return getGriefDefenderCore().getWorldUniqueId(level);
    }

    public static @Nullable UUID getClaimID(Level level, int x, int y, int z) {
        Claim claim = new GDLocation(level, x, y, z).getClaim();
        if (claim == null) {
            return null;
        }
        return claim.getUniqueId();
    }

    public static @Nullable GDClaimOwner getClaimOwner(UUID claimID) {
        Claim claim = getGriefDefenderCore().getClaim(claimID);
        if (claim == null) {
            return null;
        }
        UUID owner = claim.getOwnerUniqueId();
        String displayName = claim.getOwnerName();
        return new GDClaimOwner(owner, displayName);
    }
}
