package dev.matthiesen.cobble_npc_gd_compat.common.griefdefender;

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

    public static @NotNull String makeString(List<SimpleClaimData> claims) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < claims.size(); i++) {
            SimpleClaimData claim = claims.get(i);
            sb.append("{");
            sb.append("\"uuid\": \"").append(claim.uuid()).append("\", ");
            sb.append("\"displayName\": \"").append(claim.displayName()).append("\", ");
            sb.append("\"ownerUUID\": \"").append(claim.ownerUUID()).append("\", ");
            sb.append("\"ownerName\": \"").append(claim.ownerName()).append("\"");
            sb.append("}");
            if (i < claims.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
