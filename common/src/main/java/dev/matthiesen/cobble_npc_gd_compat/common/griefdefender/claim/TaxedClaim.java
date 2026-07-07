package dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.claim;

import com.cobblemon.mod.common.api.molang.ObjectValue;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record TaxedClaim(
        String uuid,
        String displayName,
        String ownerUUID,
        String ownerName,
        String spawnPos,
        String taxPastDueDate,
        double taxBalance
) {
    public static String makeString(TaxedClaim claimData) {
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

    public static @NotNull String makeStringList(List<TaxedClaim> claims) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < claims.size(); i++) {
            TaxedClaim claim = claims.get(i);
            sb.append(makeString(claim));
            if (i < claims.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public ObjectValue<TaxedClaim> asMolangValue() {
        return new ObjectValue<>(this, TaxedClaim::makeString, d -> 1.0);
    }

    public static ObjectValue<List<TaxedClaim>> asMolangValueFromList(List<TaxedClaim> claims) {
        return new ObjectValue<>(claims, TaxedClaim::makeStringList, d -> 1.0);
    }
}
