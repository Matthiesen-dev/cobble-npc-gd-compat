package dev.matthiesen.cobble_npc_gd_compat.common.griefdefender;

import com.cobblemon.mod.common.api.molang.ObjectValue;
import com.griefdefender.api.claim.Claim;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record RentalClaimData(String uuid, String displayName, String ownerUUID, String ownerName, double rentalRate) {
    public static RentalClaimData fromClaim(Claim claim) {
        return new RentalClaimData(
                claim.getUniqueId().toString(),
                claim.getDisplayName(),
                claim.getOwnerUniqueId().toString(),
                claim.getOwnerName(),
                claim.getEconomyData().isForRent() && claim.getEconomyData().getRentRate() > (double) -1.0F ? claim.getEconomyData().getRentRate() : 0.0
        );
    }

    public static RentalClaimData fromGDLocation(GDLocation location) {
        Claim claim = location.getClaim();
        if (claim == null) return null;
        return fromClaim(claim);
    }

    public static String makeString(RentalClaimData claimData) {
        return "{" +
                "\"uuid\": \"" + claimData.uuid() + "\", " +
                "\"displayName\": \"" + claimData.displayName() + "\", " +
                "\"ownerUUID\": \"" + claimData.ownerUUID() + "\", " +
                "\"ownerName\": \"" + claimData.ownerName() + "\"" +
                "\"rentalRate\": \"" + claimData.rentalRate() + "\"" +
                "}";
    }

    public static @NotNull String makeStringList(List<RentalClaimData> claims) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < claims.size(); i++) {
            RentalClaimData claim = claims.get(i);
            sb.append(makeString(claim));
            if (i < claims.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public ObjectValue<RentalClaimData> asMolangValue() {
        return new ObjectValue<>(this, RentalClaimData::makeString, d -> 1.0);
    }

    public static ObjectValue<List<RentalClaimData>> asMolangValueFromList(List<RentalClaimData> claims) {
        return new ObjectValue<>(claims, RentalClaimData::makeStringList, d -> 1.0);
    }
}
