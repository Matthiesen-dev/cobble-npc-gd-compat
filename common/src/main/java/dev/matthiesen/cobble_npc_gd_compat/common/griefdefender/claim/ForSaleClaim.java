package dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.claim;

import com.cobblemon.mod.common.api.molang.ObjectValue;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record ForSaleClaim(
        String uuid,
        String displayName,
        String ownerUUID,
        String ownerName,
        String spawnPos,
        boolean isForSale,
        double salePrice
) {
    public static String makeString(ForSaleClaim claimData) {
        return "{" +
                "\"uuid\": \"" + claimData.uuid() + "\", " +
                "\"displayName\": \"" + claimData.displayName() + "\", " +
                "\"ownerUUID\": \"" + claimData.ownerUUID() + "\", " +
                "\"ownerName\": \"" + claimData.ownerName() + "\", " +
                "\"spawnPos\": \"" + claimData.spawnPos() + "\", " +
                "\"isForSale\": " + claimData.isForSale() + ", " +
                "\"salePrice\": " + claimData.salePrice() +
                "}";
    }

    public static @NotNull String makeStringList(List<ForSaleClaim> claims) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < claims.size(); i++) {
            ForSaleClaim claim = claims.get(i);
            sb.append(makeString(claim));
            if (i < claims.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public ObjectValue<ForSaleClaim> asMolangValue() {
        return new ObjectValue<>(this, ForSaleClaim::makeString, d -> 1.0);
    }

    public static ObjectValue<List<ForSaleClaim>> asMolangValueFromList(List<ForSaleClaim> claims) {
        return new ObjectValue<>(claims, ForSaleClaim::makeStringList, d -> 1.0);
    }
}
