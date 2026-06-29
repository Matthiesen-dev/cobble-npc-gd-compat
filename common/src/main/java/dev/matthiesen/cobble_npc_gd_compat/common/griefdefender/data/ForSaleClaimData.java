package dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.data;

import com.cobblemon.mod.common.api.molang.ObjectValue;
import com.griefdefender.api.claim.Claim;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record ForSaleClaimData(
        String uuid,
        String displayName,
        String ownerUUID,
        String ownerName,
        boolean isForSale,
        double salePrice
) {
    public static ForSaleClaimData fromGDLocation(GDLocation location) {
        Claim claim = location.getClaim();
        if (claim == null) return null;
        return fromClaim(claim);
    }

    public static ForSaleClaimData fromClaim(Claim claim) {
        var economyData = claim.getEconomyData();
        boolean isForSale = economyData.isForSale();
        double salePrice = economyData.getSalePrice();

        return new ForSaleClaimData(
                claim.getUniqueId().toString(),
                claim.getDisplayName(),
                claim.getOwnerUniqueId().toString(),
                claim.getOwnerName(),
                isForSale,
                salePrice
        );
    }

    public static String makeString(ForSaleClaimData claimData) {
        return "{" +
                "\"uuid\": \"" + claimData.uuid() + "\", " +
                "\"displayName\": \"" + claimData.displayName() + "\", " +
                "\"ownerUUID\": \"" + claimData.ownerUUID() + "\", " +
                "\"ownerName\": \"" + claimData.ownerName() + "\"" +
                "\"isForSale\": " + claimData.isForSale() + ", " +
                "\"salePrice\": " + claimData.salePrice() +
                "}";
    }

    public static @NotNull String makeStringList(List<ForSaleClaimData> claims) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < claims.size(); i++) {
            ForSaleClaimData claim = claims.get(i);
            sb.append(makeString(claim));
            if (i < claims.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public ObjectValue<ForSaleClaimData> asMolangValue() {
        return new ObjectValue<>(this, ForSaleClaimData::makeString, d -> 1.0);
    }

    public static ObjectValue<List<ForSaleClaimData>> asMolangValueFromList(List<ForSaleClaimData> claims) {
        return new ObjectValue<>(claims, ForSaleClaimData::makeStringList, d -> 1.0);
    }
}
