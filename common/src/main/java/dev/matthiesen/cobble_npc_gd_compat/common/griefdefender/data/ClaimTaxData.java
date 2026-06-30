package dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.data;

import com.cobblemon.mod.common.api.molang.ObjectValue;
import com.griefdefender.api.claim.Claim;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record ClaimTaxData(
        String uuid,
        String displayName,
        String ownerUUID,
        String ownerName,
        String spawnPos,
        String taxPastDueDate,
        double taxBalance
) {
    public static ClaimTaxData fromGDLocation(GDLocation location) {
        Claim claim = location.getClaim();
        if (claim == null) return null;
        return fromClaim(claim);
    }

    public static ClaimTaxData fromClaim(Claim claim) {
        String blockPos = "unknown";

        var spawnPos = claim.getData().getSpawnPos();
        if (spawnPos != null) {
            blockPos = spawnPos.getX() + " " + spawnPos.getY() + " " + spawnPos.getZ();
        }

        var economyData = claim.getEconomyData();
        String taxPastDueDate = economyData.getTaxPastDueDate() != null ? economyData.getTaxPastDueDate().toString() : "unknown";
        double taxBalance = economyData.getTaxBalance();

        return new ClaimTaxData(
                claim.getUniqueId().toString(),
                claim.getDisplayName(),
                claim.getOwnerUniqueId().toString(),
                claim.getOwnerName(),
                blockPos,
                taxPastDueDate,
                taxBalance
        );
    }

    public static String makeString(ClaimTaxData claimData) {
        return "{" +
                "\"uuid\": \"" + claimData.uuid() + "\", " +
                "\"displayName\": \"" + claimData.displayName() + "\", " +
                "\"ownerUUID\": \"" + claimData.ownerUUID() + "\", " +
                "\"ownerName\": \"" + claimData.ownerName() + "\", " +
                "\"spawnPos\": \"" + claimData.spawnPos() + "\", " +
                "\"taxPastDueDate\": \"" + claimData.taxPastDueDate() + "\", " +
                "\"taxBalance\": " + claimData.taxBalance()  +
                "}";
    }

    public static @NotNull String makeStringList(List<ClaimTaxData> claims) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < claims.size(); i++) {
            ClaimTaxData claim = claims.get(i);
            sb.append(makeString(claim));
            if (i < claims.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public ObjectValue<ClaimTaxData> asMolangValue() {
        return new ObjectValue<>(this, ClaimTaxData::makeString, d -> 1.0);
    }

    public static ObjectValue<List<ClaimTaxData>> asMolangValueFromList(List<ClaimTaxData> claims) {
        return new ObjectValue<>(claims, ClaimTaxData::makeStringList, d -> 1.0);
    }
}
