package dev.matthiesen.cobble_npc_gd_compat.common.griefdefender;

import com.griefdefender.api.claim.Claim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public record GDLocation(Level level, int x, int y, int z) {
    public @Nullable Claim getClaim() {
        UUID worldID = GDUtils.getWorldID(level);
        return GDUtils.getGriefDefenderCore().getClaimAt(worldID, x, y, z);
    }

    public boolean isWilderness() {
        Claim claim = getClaim();
        if (claim == null) return false;
        return claim.isWilderness();
    }

    public UUID getUUID() {
        Claim claim = getClaim();
        if (claim == null) return null;
        return claim.getUniqueId();
    }

    public String getDisplayName() {
        Claim claim = getClaim();
        if (claim == null) return null;
        return claim.getDisplayName();
    }

    public UUID getOwnerUUID() {
        Claim claim = getClaim();
        if (claim == null) return null;
        return claim.getOwnerUniqueId();
    }

    public String getOwnerName() {
        Claim claim = getClaim();
        if (claim == null) return null;
        return claim.getOwnerName();
    }
}
