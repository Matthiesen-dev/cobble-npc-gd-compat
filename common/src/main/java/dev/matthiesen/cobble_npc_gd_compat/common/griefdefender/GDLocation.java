package dev.matthiesen.cobble_npc_gd_compat.common.griefdefender;

import com.griefdefender.api.claim.Claim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

import static dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.GDUtils.*;

public final class GDLocation {
    private final UUID worldID;
    private final int x;
    private final int y;
    private final int z;

    public GDLocation(Level level, int x, int y, int z) {
        this.worldID = getWorldID(level);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public @Nullable Claim getClaim() {
        return getGriefDefenderCore().getClaimAt(worldID, x, y, z);
    }
}
