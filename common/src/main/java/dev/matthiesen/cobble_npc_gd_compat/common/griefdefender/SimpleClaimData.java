package dev.matthiesen.cobble_npc_gd_compat.common.griefdefender;

import com.griefdefender.api.claim.Claim;

public record SimpleClaimData(String uuid, String displayName, String ownerUUID, String ownerName) {
    public static SimpleClaimData fromClaim(Claim claim) {
        return new SimpleClaimData(
                claim.getUniqueId().toString(),
                claim.getDisplayName(),
                claim.getOwnerUniqueId().toString(),
                claim.getOwnerName()
        );
    }
}
