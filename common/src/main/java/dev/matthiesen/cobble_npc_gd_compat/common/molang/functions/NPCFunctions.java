package dev.matthiesen.cobble_npc_gd_compat.common.molang.functions;

import com.bedrockk.molang.runtime.MoParams;
import com.cobblemon.mod.common.entity.npc.NPCEntity;
import dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.GDLocation;
import dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.GDUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import java.util.UUID;
import java.util.function.Function;

public final class NPCFunctions {
    private static GDLocation getClaim(NPCEntity npcEntity) {
        Level level = npcEntity.getCommandSenderWorld();
        BlockPos pos = npcEntity.getOnPos();
        return GDUtils.getClaim(level, pos.getX(), pos.getY(), pos.getZ());
    }

    public static Function<MoParams, Object> isWilderness(NPCEntity npcEntity) {
        return params -> getClaim(npcEntity).isWilderness() ? 1 : 0;
    }

    public static Function<MoParams, Object> standingClaimUUID(NPCEntity npcEntity) {
        return params -> {
            var claim = getClaim(npcEntity);
            UUID claimUUID = claim.getUUID();
            return claimUUID != null ? claimUUID.toString() : 0;
        };
    }

    public static Function<MoParams, Object> standingClaimName(NPCEntity npcEntity) {
        return params -> {
            var claim = getClaim(npcEntity);
            String displayName = claim.getDisplayName();
            return displayName != null ? displayName : 0;
        };
    }

    public static Function<MoParams, Object> standingClaimOwnerUUID(NPCEntity npcEntity) {
        return params -> {
            var claim = getClaim(npcEntity);
            if (claim.getClaim() == null) return 0;
            var claimOwner = claim.getOwnerUUID();
            return claimOwner != null ? claimOwner.toString() : 0;
        };
    }

    public static Function<MoParams, Object> standingClaimOwnerName(NPCEntity npcEntity) {
        return params -> {
            var claim = getClaim(npcEntity);
            if (claim.getClaim() == null) return 0;
            var claimOwner = claim.getOwnerName();
            return claimOwner != null ? claimOwner : 0;
        };
    }
}
