package dev.matthiesen.cobble_npc_gd_compat.common.griefdefender;

import com.griefdefender.api.claim.Claim;
import com.griefdefender.api.claim.ClaimManager;
import net.minecraft.world.level.Level;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public final class GDRentals {
    public static List<RentalClaimData> getRentals(Level level) {
        UUID worldID = GDUtils.getWorldID(level);
        ClaimManager claimManager = GDUtils.getClaimManager(worldID);

        Set<Claim> claimsForRent = new HashSet<>();

        for (Claim worldClaim : claimManager.getWorldClaims()) {
            if (worldClaim.isWilderness()) continue;

            if (worldClaim.getEconomyData().isForRent() && worldClaim.getEconomyData().getRentRate() > (double) -1.0F) {
                claimsForRent.add(worldClaim);
            }

            for(Claim child : worldClaim.getChildren(true)) {
                if (child.getEconomyData().isForRent() && child.getEconomyData().getRentRate() > (double) -1.0F) {
                    claimsForRent.add(child);
                }
            }
        }

        return claimsForRent
                .stream()
                .map(RentalClaimData::fromClaim)
                .toList();
    }
}
