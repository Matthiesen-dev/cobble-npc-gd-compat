package dev.matthiesen.cobble_npc_gd_compat.common.molang;

import com.bedrockk.molang.runtime.MoParams;
import com.bedrockk.molang.runtime.value.StringValue;
import com.cobblemon.mod.common.api.molang.MoLangFunctions;
import com.cobblemon.mod.common.entity.npc.NPCEntity;
import dev.matthiesen.cobble_npc_gd_compat.common.CobbleNPCGDCompat;
import dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.GDUtils;
import dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.data.ForSaleClaimData;
import dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.data.GDLocation;
import dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.data.RentalClaimData;
import dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.data.SimpleClaimData;
import dev.matthiesen.cobble_npc_gd_compat.common.molang.universal.UniversalFunctions;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.UUID;
import java.util.function.Function;

public final class NPCExtensions {
    private static GDLocation getClaim(NPCEntity npcEntity) {
        Level level = npcEntity.getCommandSenderWorld();
        BlockPos pos = npcEntity.getOnPos();
        return GDUtils.getClaim(level, pos.getX(), pos.getY() + 1, pos.getZ());
    }

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
            map.put("gd_is_wilderness", params -> UniversalFunctions.intToDouble(getClaim(npcEntity).isWilderness() ? 1 : 0));

            // q.npc.gd_available_rentals() returns array of claims in the following format
            // [ { "uuid": "string", "displayName": "string", "ownerUUID": "string", "ownerName": "string", "rentalRate": double, "isForRent": false, "isRented": false, "renter": "string", "paymentType": "string", "rentMinTime": 0, "rentMaxTime": 0 } ]
            map.put("gd_available_rentals", UniversalFunctions.getAvailableRentals(npcEntity.getCommandSenderWorld()));

            // q.npc.gd_available_forsale() returns array of claims in the following format
            // [ { "uuid": "string", "displayName": "string", "ownerUUID": "string", "ownerName": "string", "isForSale": false, "salePrice": double } ]
            map.put("gd_available_forsale", UniversalFunctions.getAvailableForSale(npcEntity.getCommandSenderWorld()));

            // q.npc.gd_claim_data() returns claim data for the npc in the following format
            // { "uuid": "string", "displayName": "string", "ownerUUID": "string", "ownerName": "string" }
            map.put("gd_claim_data", params -> {
                var claim = getClaim(npcEntity);
                SimpleClaimData claimData = SimpleClaimData.fromGDLocation(claim);
                return claimData != null ? claimData.asMolangValue() : UniversalFunctions.isNull();
            });

            // q.npc.gd_claim_uuid() returns string or 0
            map.put("gd_claim_uuid", params -> {
                var claim = getClaim(npcEntity);
                UUID claimUUID = claim.getUUID();
                return claimUUID != null ? new StringValue(claimUUID.toString()) : UniversalFunctions.isNull();
            });

            // q.npc.gd_claim_name() returns string or 0
            map.put("gd_claim_name", params -> {
                var claim = getClaim(npcEntity);
                String displayName = claim.getDisplayName();
                return displayName != null ? new StringValue(displayName) : UniversalFunctions.isNull();
            });

            // q.npc.gd_claim_owner_uuid() returns string or 0
            map.put("gd_claim_owner_uuid", params -> {
                var claim = getClaim(npcEntity);
                if (claim.getClaim() == null) return UniversalFunctions.isNull();
                var claimOwner = claim.getOwnerUUID();
                return claimOwner != null ? new StringValue(claimOwner.toString()) : UniversalFunctions.isNull();
            });

            // q.npc.gd_claim_owner_name() returns string or 0
            map.put("gd_claim_owner_name", params -> {
                var claim = getClaim(npcEntity);
                if (claim.getClaim() == null) return UniversalFunctions.isNull();
                var claimOwner = claim.getOwnerName();
                return claimOwner != null ? new StringValue(claimOwner) : UniversalFunctions.isNull();
            });

            // q.npc.gd_claim_rental_data() returns object containing claim info and rental rate in the following format
            // { "uuid": "string", "displayName": "string", "ownerUUID": "string", "ownerName": "string", "rentalRate": double, "isForRent": false, "isRented": false, "renter": "string", "paymentType": "string", "rentMinTime": 0, "rentMaxTime": 0 }
            map.put("gd_claim_rental_data", params -> {
                var claim = getClaim(npcEntity);
                var rental = RentalClaimData.fromGDLocation(claim);
                return rental != null ? rental.asMolangValue() : UniversalFunctions.isNull();
            });

            // q.npc.gd_claim_sale_data() returns object containing claim info and sale data in the following format
            // { "uuid": "string", "displayName": "string", "ownerUUID": "string", "ownerName": "string", "isForSale": false, "salePrice": double }
            map.put("gd_claim_sale_data", params -> {
                var claim = getClaim(npcEntity);
                if (claim.getClaim() == null) return UniversalFunctions.isNull();
                ForSaleClaimData claimData = ForSaleClaimData.fromGDLocation(claim);
                return claimData != null ? claimData.asMolangValue() : UniversalFunctions.isNull();
            });

            return map;
        });
    }
}
