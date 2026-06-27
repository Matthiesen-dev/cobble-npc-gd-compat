package dev.matthiesen.cobble_npc_gd_compat.common.griefdefender;

import com.cobblemon.mod.common.api.molang.ObjectValue;
import com.griefdefender.api.claim.Claim;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record SimpleClaimData(String uuid, String displayName, String ownerUUID, String ownerName) {
    public static SimpleClaimData fromClaim(Claim claim) {
        return new SimpleClaimData(
                claim.getUniqueId().toString(),
                claim.getDisplayName(),
                claim.getOwnerUniqueId().toString(),
                claim.getOwnerName()
        );
    }

    public static SimpleClaimData fromGDLocation(GDLocation location) {
        Claim claim = location.getClaim();
        if (claim == null) return null;
        return fromClaim(claim);
    }

    public static String makeString(SimpleClaimData claimData) {
        return "{" +
                "\"uuid\": \"" + claimData.uuid() + "\", " +
                "\"displayName\": \"" + claimData.displayName() + "\", " +
                "\"ownerUUID\": \"" + claimData.ownerUUID() + "\", " +
                "\"ownerName\": \"" + claimData.ownerName() + "\"" +
                "}";
    }

    public static @NotNull String makeStringList(List<SimpleClaimData> claims) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < claims.size(); i++) {
            SimpleClaimData claim = claims.get(i);
            sb.append(makeString(claim));
            if (i < claims.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public ObjectValue<SimpleClaimData> asMolangValue() {
        return new ObjectValue<>(this, SimpleClaimData::makeString, d -> 1.0);
    }

    public static ObjectValue<List<SimpleClaimData>> asMolangValueFromList(List<SimpleClaimData> claims) {
        return new ObjectValue<>(claims, SimpleClaimData::makeStringList, d -> 1.0);
    }
}
