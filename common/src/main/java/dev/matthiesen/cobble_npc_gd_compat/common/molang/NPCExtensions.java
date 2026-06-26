package dev.matthiesen.cobble_npc_gd_compat.common.molang;

import com.bedrockk.molang.runtime.MoParams;
import com.cobblemon.mod.common.api.molang.MoLangFunctions;
import dev.matthiesen.cobble_npc_gd_compat.common.CobbleNPCGDCompat;
import dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.GDUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.function.Function;

public final class NPCExtensions {
    public static void register() {
        CobbleNPCGDCompat.INSTANCE.createInfoLog("Registering Cobblemon Molang NPC Extensions");

        MoLangFunctions.INSTANCE.getNpcFunctions().add(npcEntity -> {
            HashMap<String, Function<MoParams, Object>> map = new HashMap<>();

            // q.npc.get_gd_claim_uuid() returns string or 0
            map.put("get_gd_claim_uuid", params -> {
                Level level = npcEntity.getCommandSenderWorld();
                BlockPos pos = npcEntity.getOnPos();
                var claimID = GDUtils.getClaimID(level, pos.getX(), pos.getY(), pos.getZ());
                if (claimID == null) return 0;
                return claimID.toString();
            });

            // q.npc.get_gd_claim_owner_uuid() returns string or 0
            map.put("get_gd_claim_owner_uuid", params -> {
                Level level = npcEntity.getCommandSenderWorld();
                BlockPos pos = npcEntity.getOnPos();
                var claimID = GDUtils.getClaimID(level, pos.getX(), pos.getY(), pos.getZ());
                if (claimID == null) return 0;
                var claim = GDUtils.getClaimOwner(claimID);
                if (claim == null) return 0;
                return claim.ownerUniqueID();
            });

            // q.npc.get_gd_claim_owner_name() returns string or 0
            map.put("get_gd_claim_owner_name", params -> {
                Level level = npcEntity.getCommandSenderWorld();
                BlockPos pos = npcEntity.getOnPos();
                var claimID = GDUtils.getClaimID(level, pos.getX(), pos.getY(), pos.getZ());
                if (claimID == null) return 0;
                var claim = GDUtils.getClaimOwner(claimID);
                if (claim == null) return 0;
                return claim.displayName();
            });

            return map;
        });
    }
}
