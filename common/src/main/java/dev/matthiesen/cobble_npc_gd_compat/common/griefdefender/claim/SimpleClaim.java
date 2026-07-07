package dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.claim;

import com.cobblemon.mod.common.api.molang.ObjectValue;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record SimpleClaim(
        String uuid,
        String displayName,
        String ownerUUID,
        String ownerName,
        String spawnPos
) {
    public static String makeString(SimpleClaim claimData) {
        return "{" +
                "\"uuid\": \"" + claimData.uuid() + "\", " +
                "\"displayName\": \"" + claimData.displayName() + "\", " +
                "\"ownerUUID\": \"" + claimData.ownerUUID() + "\", " +
                "\"ownerName\": \"" + claimData.ownerName() + "\", " +
                "\"spawnPos\": \"" + claimData.spawnPos() + "\"" +
                "}";
    }

    public static @NotNull String makeStringList(List<SimpleClaim> claims) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < claims.size(); i++) {
            SimpleClaim claim = claims.get(i);
            sb.append(makeString(claim));
            if (i < claims.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public ObjectValue<SimpleClaim> asMolangValue() {
        return new ObjectValue<>(this, SimpleClaim::makeString, d -> 1.0);
    }

    public static ObjectValue<List<SimpleClaim>> asMolangValueFromList(List<SimpleClaim> claims) {
        return new ObjectValue<>(claims, SimpleClaim::makeStringList, d -> 1.0);
    }
}
