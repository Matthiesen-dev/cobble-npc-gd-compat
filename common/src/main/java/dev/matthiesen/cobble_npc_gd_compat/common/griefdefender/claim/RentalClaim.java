package dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.claim;

import com.cobblemon.mod.common.api.molang.ObjectValue;
import com.griefdefender.api.economy.PaymentType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record RentalClaim(
        String uuid,
        String displayName,
        String ownerUUID,
        String ownerName,
        String spawnPos,
        boolean isForRent,
        boolean isRented,
        double rentalRate,
        String renter,
        String paymentType,
        int rentMinTime,
        int rentMaxTime
) {
    public static String paymentTypeToString(PaymentType paymentType) {
        return switch (paymentType) {
            case UNDEFINED -> "undefined";
            case DAILY -> "daily";
            case HOURLY -> "hourly";
            case WEEKLY -> "weekly";
            case MONTHLY -> "monthly";
        };
    }

    public static String makeString(RentalClaim claimData) {
        return "{" +
                "\"uuid\": \"" + claimData.uuid() + "\", " +
                "\"displayName\": \"" + claimData.displayName() + "\", " +
                "\"ownerUUID\": \"" + claimData.ownerUUID() + "\", " +
                "\"ownerName\": \"" + claimData.ownerName() + "\", " +
                "\"spawnPos\": \"" + claimData.spawnPos() + "\", " +
                "\"isForRent\": " + claimData.isForRent() + ", " +
                "\"isRented\": " + claimData.isRented() + ", " +
                "\"rentalRate\": \"" + claimData.rentalRate() + "\", " +
                "\"renter\": \"" + claimData.renter() + "\", " +
                "\"paymentType\": \"" + claimData.paymentType() + "\", " +
                "\"rentMinTime\": \"" + claimData.rentMinTime() + "\", " +
                "\"rentMaxTime\": \"" + claimData.rentMaxTime() + "\"" +
                "}";
    }

    public static @NotNull String makeStringList(List<RentalClaim> claims) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < claims.size(); i++) {
            RentalClaim claim = claims.get(i);
            sb.append(makeString(claim));
            if (i < claims.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public ObjectValue<RentalClaim> asMolangValue() {
        return new ObjectValue<>(this, RentalClaim::makeString, d -> 1.0);
    }

    public static ObjectValue<List<RentalClaim>> asMolangValueFromList(List<RentalClaim> claims) {
        return new ObjectValue<>(claims, RentalClaim::makeStringList, d -> 1.0);
    }
}
