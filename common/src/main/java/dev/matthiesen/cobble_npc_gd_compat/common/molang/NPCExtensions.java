package dev.matthiesen.cobble_npc_gd_compat.common.molang;

import com.bedrockk.molang.runtime.MoParams;
import com.cobblemon.mod.common.api.molang.MoLangFunctions;
import dev.matthiesen.cobble_npc_gd_compat.common.CobbleNPCGDCompat;
import dev.matthiesen.cobble_npc_gd_compat.common.molang.functions.NPCFunctions;

import java.util.HashMap;
import java.util.function.Function;

public final class NPCExtensions {
    public static void register() {
        CobbleNPCGDCompat.INSTANCE.createInfoLog("Registering Cobblemon Molang NPC Extensions");

        MoLangFunctions.INSTANCE.getNpcFunctions().add(npcEntity -> {
            HashMap<String, Function<MoParams, Object>> map = new HashMap<>();

            // q.npc.gd_is_wilderness() returns 1 for true, or 0
            map.put("gd_is_wilderness", NPCFunctions.isWilderness(npcEntity));

            // q.npc.gd_claim_uuid() returns string or 0
            map.put("gd_claim_uuid", NPCFunctions.standingClaimUUID(npcEntity));

            // q.npc.gd_claim_name() returns string or 0
            map.put("gd_claim_name", NPCFunctions.standingClaimName(npcEntity));

            // q.npc.gd_claim_owner_uuid() returns string or 0
            map.put("gd_claim_owner_uuid", NPCFunctions.standingClaimOwnerUUID(npcEntity));

            // q.npc.gd_claim_owner_name() returns string or 0
            map.put("gd_claim_owner_name", NPCFunctions.standingClaimOwnerName(npcEntity));

            return map;
        });
    }
}
