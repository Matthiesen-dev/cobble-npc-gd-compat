package dev.matthiesen.cobble_npc_gd_compat.common.molang.functions;

import com.bedrockk.molang.runtime.MoParams;
import com.bedrockk.molang.runtime.value.StringValue;
import com.cobblemon.mod.common.entity.npc.NPCEntity;
import dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

public final class NPCFunctions {
    private static GDLocation getClaim(NPCEntity npcEntity) {
        Level level = npcEntity.getCommandSenderWorld();
        BlockPos pos = npcEntity.getOnPos();
        return GDUtils.getClaim(level, pos.getX(), pos.getY() + 1, pos.getZ());
    }

    public static Function<MoParams, Object> getAvailableRentals(NPCEntity npcEntity) {
        return params -> {
            Level level = npcEntity.getCommandSenderWorld();
            List<RentalClaimData> rentals = GDRentals.getRentals(level);
            return RentalClaimData.asMolangValueFromList(rentals);
        };
    }

    public static Function<MoParams, Object> getStandingRental(NPCEntity npcEntity) {
        return params -> {
            var claim = getClaim(npcEntity);
            var rental = RentalClaimData.fromGDLocation(claim);
            return rental != null ? rental.asMolangValue() : UniversalFunctions.isNull();
        };
    }

    public static Function<MoParams, Object> isWilderness(NPCEntity npcEntity) {
        return params -> UniversalFunctions.intToDouble(getClaim(npcEntity).isWilderness() ? 1 : 0);
    }

    public static Function<MoParams, Object> standingClaimUUID(NPCEntity npcEntity) {
        return params -> {
            var claim = getClaim(npcEntity);
            UUID claimUUID = claim.getUUID();
            return claimUUID != null ? new StringValue(claimUUID.toString()) : UniversalFunctions.isNull();
        };
    }

    public static Function<MoParams, Object> standingClaimData(NPCEntity npcEntity) {
        return params -> {
            var claim = getClaim(npcEntity);
            SimpleClaimData claimData = SimpleClaimData.fromGDLocation(claim);
            return claimData != null ? claimData.asMolangValue() : UniversalFunctions.isNull();
        };
    }

    public static Function<MoParams, Object> standingClaimName(NPCEntity npcEntity) {
        return params -> {
            var claim = getClaim(npcEntity);
            String displayName = claim.getDisplayName();
            return displayName != null ? new StringValue(displayName) : UniversalFunctions.isNull();
        };
    }

    public static Function<MoParams, Object> standingClaimOwnerUUID(NPCEntity npcEntity) {
        return params -> {
            var claim = getClaim(npcEntity);
            if (claim.getClaim() == null) return UniversalFunctions.isNull();
            var claimOwner = claim.getOwnerUUID();
            return claimOwner != null ? new StringValue(claimOwner.toString()) : UniversalFunctions.isNull();
        };
    }

    public static Function<MoParams, Object> standingClaimOwnerName(NPCEntity npcEntity) {
        return params -> {
            var claim = getClaim(npcEntity);
            if (claim.getClaim() == null) return UniversalFunctions.isNull();
            var claimOwner = claim.getOwnerName();
            return claimOwner != null ? new StringValue(claimOwner) : UniversalFunctions.isNull();
        };
    }
}
