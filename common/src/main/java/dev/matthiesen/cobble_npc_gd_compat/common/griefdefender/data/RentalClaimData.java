package dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.data;

import com.cobblemon.mod.common.api.molang.ObjectValue;
import com.griefdefender.api.claim.Claim;
import com.griefdefender.api.economy.PaymentType;
import dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.GDUser;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

public record RentalClaimData(
        String uuid,
        String displayName,
        String ownerUUID,
        String ownerName,
        String blockPos,
        boolean isForRent,
        boolean isRented,
        double rentalRate,
        String renter,
        String paymentType,
        int rentMinTime,
        int rentMaxTime
) {
    public static RentalClaimData fromGDLocation(GDLocation location) {
        Claim claim = location.getClaim();
        if (claim == null) return null;
        return fromClaim(claim);
    }

    public static RentalClaimData fromClaim(Claim claim) {
        var economyData = claim.getEconomyData();
        boolean isForRent = claim.getEconomyData().isForRent();
        boolean isRented = claim.getEconomyData().isRented();
        double rentalRate = 0.0;
        UUID renter = null;
        PaymentType paymentType = PaymentType.UNDEFINED;
        int rentMinTime = 0;
        int rentMaxTime = 0;

        if (isForRent) {
            rentalRate = economyData.getRentRate() > (double) -1.0F ? claim.getEconomyData().getRentRate() : 0.0;
            renter = economyData.getRenters().getFirst();
            paymentType = economyData.getPaymentType();
            rentMinTime = economyData.getRentMinTime();
            rentMaxTime = economyData.getRentMaxTime();
        }
        String blockPos = "unknown";

        var spawnPos = claim.getData().getSpawnPos();
        if (spawnPos != null) {
            blockPos = spawnPos.getX() + " " + spawnPos.getY() + " " + spawnPos.getZ();
        }

        return new RentalClaimData(
                claim.getUniqueId().toString(),
                claim.getDisplayName(),
                claim.getOwnerUniqueId().toString(),
                claim.getOwnerName(),
                blockPos,
                isForRent,
                isRented,
                rentalRate,
                renter != null ? new GDUser(renter).getFriendlyName() : "not available",
                paymentTypeToString(paymentType),
                rentMinTime,
                rentMaxTime
        );
    }

    public static String paymentTypeToString(PaymentType paymentType) {
        return switch (paymentType) {
            case UNDEFINED -> "undefined";
            case DAILY -> "daily";
            case HOURLY -> "hourly";
            case WEEKLY -> "weekly";
            case MONTHLY -> "monthly";
        };
    }

    public static String makeString(RentalClaimData claimData) {
        return "{" +
                "\"uuid\": \"" + claimData.uuid() + "\", " +
                "\"displayName\": \"" + claimData.displayName() + "\", " +
                "\"ownerUUID\": \"" + claimData.ownerUUID() + "\", " +
                "\"ownerName\": \"" + claimData.ownerName() + "\"" +
                "\"blockPos\": \"" + claimData.blockPos() + "\"" +
                "\"isForRent\": " + claimData.isForRent() +
                "\"isRented\": " + claimData.isRented() +
                "\"rentalRate\": \"" + claimData.rentalRate() + "\"" +
                "\"renter\": \"" + claimData.renter() + "\"" +
                "\"paymentType\": \"" + claimData.paymentType() + "\"" +
                "\"rentMinTime\": \"" + claimData.rentMinTime() + "\"" +
                "\"rentMaxTime\": \"" + claimData.rentMaxTime() + "\"" +
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
