package dev.matthiesen.cobble_npc_gd_compat.common.molang.functions;

import com.bedrockk.molang.runtime.MoParams;
import dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.GDLocation;
import dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.GDUtils;
import net.minecraft.world.level.Level;

import java.util.function.Function;

public final class WorldFunctions {
    private static GDLocation getClaim(Level level, MoParams params) {
        int x = params.getInt(0);
        int y = params.getInt(1);
        int z = params.getInt(2);
        return GDUtils.getClaim(level, x, y, z);
    }

    public static Function<MoParams, Object> getClaimUUID(Level level) {
        return moParams -> {
            var claim = getClaim(level, moParams);
            if (claim.getClaim() == null) return 0;
            var claimOwner = claim.getUUID();
            return claimOwner != null ? claimOwner.toString() : 0;
        };
    }

    public static Function<MoParams, Object> getClaimName(Level level) {
        return moParams -> {
            var claim = getClaim(level, moParams);
            if (claim.getClaim() == null) return 0;
            var claimOwner = claim.getDisplayName();
            return claimOwner != null ? claimOwner : 0;
        };
    }

    public static Function<MoParams, Object> getClaimOwnerUUID(Level level) {
        return moParams -> {
            var claim = getClaim(level, moParams);
            if (claim.getClaim() == null) return 0;
            var claimOwner = claim.getOwnerUUID();
            return claimOwner != null ? claimOwner.toString() : 0;
        };
    }

    public static Function<MoParams, Object> getClaimOwnerName(Level level) {
        return moParams -> {
            var claim = getClaim(level, moParams);
            if (claim.getClaim() == null) return 0;
            var claimOwner = claim.getOwnerName();
            return claimOwner != null ? claimOwner : 0;
        };
    }
}
