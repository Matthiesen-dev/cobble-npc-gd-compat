package dev.matthiesen.cobble_npc_gd_compat.common.molang;

import com.bedrockk.molang.runtime.MoParams;
import com.cobblemon.mod.common.api.molang.MoLangFunctions;
import dev.matthiesen.cobble_npc_gd_compat.common.CobbleNPCGDCompat;
import dev.matthiesen.cobble_npc_gd_compat.common.molang.functions.NPCFunctions;
import dev.matthiesen.cobble_npc_gd_compat.common.molang.functions.UniversalFunctions;

import java.util.HashMap;
import java.util.function.Function;

public final class NPCExtensions {
    public static void register() {
        CobbleNPCGDCompat.INSTANCE.createInfoLog("Registering Cobblemon Molang NPC Extensions");

        MoLangFunctions.INSTANCE.getNpcFunctions().add(npcEntity -> {
            HashMap<String, Function<MoParams, Object>> map = new HashMap<>();

            // q.npc.gd_economy_enabled() returns 1 for true, or 0
            map.put("gd_economy_enabled", UniversalFunctions.isEconomyEnabled());

            // q.npc.gd_get_player_claims(<uuid>) returns array of claims for user in the following format
            // [ { "uuid": "string", "displayName": "string", "ownerUUID": "string", "ownerName": "string" } ]
            map.put("gd_get_player_claims", UniversalFunctions.getPlayerClaims());

            // q.npc.gd_is_wilderness() returns 1 for true, or 0
            map.put("gd_is_wilderness", NPCFunctions.isWilderness(npcEntity));

            // q.npc.gd_claim_data() returns claim data for the npc in the following format
            // { "uuid": "string", "displayName": "string", "ownerUUID": "string", "ownerName": "string" }
            map.put("gd_claim_data", NPCFunctions.standingClaimData(npcEntity));

            // q.npc.gd_claim_uuid() returns string or 0
            map.put("gd_claim_uuid", NPCFunctions.standingClaimUUID(npcEntity));

            // q.npc.gd_claim_name() returns string or 0
            map.put("gd_claim_name", NPCFunctions.standingClaimName(npcEntity));

            // q.npc.gd_claim_owner_uuid() returns string or 0
            map.put("gd_claim_owner_uuid", NPCFunctions.standingClaimOwnerUUID(npcEntity));

            // q.npc.gd_claim_owner_name() returns string or 0
            map.put("gd_claim_owner_name", NPCFunctions.standingClaimOwnerName(npcEntity));

            // q.npc.gd_available_rentals() returns array of claims in the following format
            // [ { "uuid": "string", "displayName": "string", "ownerUUID": "string", "ownerName": "string", "rentalRate": double } ]
            map.put("gd_available_rentals", NPCFunctions.getAvailableRentals(npcEntity));

            // q.npc.gd_claim_rental_data() returns object containing claim info and rental rate in the following format
            // { "uuid": "string", "displayName": "string", "ownerUUID": "string", "ownerName": "string", "rentalRate": double }
            map.put("gd_claim_rental_data", NPCFunctions.getStandingRental(npcEntity));

            return map;
        });
    }
}
