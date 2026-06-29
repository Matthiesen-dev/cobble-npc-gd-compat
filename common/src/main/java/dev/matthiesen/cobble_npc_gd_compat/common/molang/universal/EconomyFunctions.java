package dev.matthiesen.cobble_npc_gd_compat.common.molang.universal;

import com.bedrockk.molang.runtime.MoParams;
import com.griefdefender.api.claim.Claim;
import com.griefdefender.api.claim.ClaimResult;
import com.griefdefender.api.claim.ClaimType;
import com.griefdefender.api.claim.ClaimTypes;
import dev.matthiesen.cobble_npc_gd_compat.common.CobbleNPCGDCompat;
import dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.GDUser;
import dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.data.ForSaleClaimData;
import dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.data.GDLocation;
import dev.matthiesen.cobble_npc_gd_compat.common.impactor.EcoProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.UUID;
import java.util.function.Function;

public final class EconomyFunctions {
    public static String claimNotFound(String claimUUIDString) {
        return "Claim with UUID " + claimUUIDString + " not found. Please contact an administrator.";
    }

    public static String claimNotForSale(String claimUUIDString) {
        return "Claim with UUID " + claimUUIDString + " is not for sale. Please contact an administrator.";
    }

    public static String claimMissingOwner(String claimUUIDString) {
        return "Claim with UUID " + claimUUIDString + " is missing an owner. Please contact an administrator.";
    }

    public static String cannotPurchaseOwnClaim() {
        return "You cannot purchase your own claim.";
    }

    public static String economyAccountError() {
        return "An error occurred while trying to access your economy account. Please contact an administrator.";
    }

    public static String notEnoughFundsPurchase(double amount) {
        return "You do not have enough funds to purchase this claim. You need " + amount + " more.";
    }

    public static String failedToTransfer(String string) {
        return "Failed to transfer " + string + ". Please contact an administrator.";
    }

    public static Function<MoParams, Object> purchaseClaim(Player player) {
        return params -> {
            String claimUUIDString = params.getString(0);
            UUID claimUUID = UUID.fromString(claimUUIDString);
            GDLocation claim = GDLocation.fromUUID(claimUUID);

            if (claim == null) {
                player.sendSystemMessage(
                        Component.literal(claimNotFound(claimUUIDString))
                                .withStyle(ChatFormatting.RED)
                );
                return 0;
            }

            ForSaleClaimData claimData = ForSaleClaimData.fromGDLocation(claim);

            if (claimData == null) {
                player.sendSystemMessage(
                        Component.literal(claimNotForSale(claimUUIDString))
                                .withStyle(ChatFormatting.RED)
                );
                return 0;
            }

            if (!claimData.isForSale()) {
                player.sendSystemMessage(
                        Component.literal(claimNotForSale(claimUUIDString))
                                .withStyle(ChatFormatting.RED)
                );
                return 0;
            }

            if (player.getUUID().toString().equalsIgnoreCase(claimData.ownerUUID())) {
                player.sendSystemMessage(
                        Component.literal(cannotPurchaseOwnClaim())
                                .withStyle(ChatFormatting.RED)
                );
                return 0;
            }

            EcoProvider ecoProvider = CobbleNPCGDCompat.INSTANCE.getEcoProvider();

            if (!ecoProvider.hasAccount(player)) {
                player.sendSystemMessage(
                        Component.literal(economyAccountError())
                                .withStyle(ChatFormatting.RED)
                );
                return 0;
            }

            double balance = ecoProvider.getBalance(player);

            if (balance < claimData.salePrice()) {
                player.sendSystemMessage(
                        Component.literal(notEnoughFundsPurchase(claimData.salePrice() - balance))
                                .withStyle(ChatFormatting.RED)
                );
                return 0;
            }

            if (claim.getOwnerUUID() == null) {
                player.sendSystemMessage(
                        Component.literal(claimMissingOwner(claimUUIDString))
                                .withStyle(ChatFormatting.RED)
                );
                return 0;
            }

            GDUser owner = new GDUser(claim.getOwnerUUID());

            Claim gdClaim = claim.getClaim();

            if (gdClaim == null) {
                player.sendSystemMessage(
                        Component.literal(claimNotFound(claimUUIDString))
                                .withStyle(ChatFormatting.RED)
                );
                return 0;
            }

            ClaimType originalType = gdClaim.getType();

            if (gdClaim.isAdminClaim()) {
                gdClaim.getData().setType(ClaimTypes.BASIC);
            }

            ClaimResult result = gdClaim.transferOwner(player.getUUID());

            if (!result.successful()) {
                gdClaim.getData().setType(originalType);
                player.sendSystemMessage(
                        Component.literal(failedToTransfer("claim ownership"))
                                .withStyle(ChatFormatting.RED)
                );
                return 0;
            }

            double salePrice = claimData.salePrice();

            boolean transactionSuccess = owner.getOfflinePlayer() == null || ecoProvider.depositPlayer(owner.getOfflinePlayer(), salePrice);

            if (!transactionSuccess) {
                gdClaim.getData().setType(originalType);
                gdClaim.transferOwner(UUID.fromString(claimData.ownerUUID()));
                player.sendSystemMessage(
                        Component.literal(failedToTransfer("funds"))
                                .withStyle(ChatFormatting.RED)
                );
                return 0;
            }

            ecoProvider.withdrawFunds(player, salePrice);

            Component message = Component.literal("You have successfully purchased the claim for " + salePrice + " currency units.")
                    .withStyle(ChatFormatting.GREEN);
            Component saleMessage = Component.literal("Your claim has been purchased by " + player.getName().getString() + " for " + salePrice + " currency units.")
                    .withStyle(ChatFormatting.GREEN);

            var ownerPlayer = owner.getOnlinePlayer();

            if (ownerPlayer != null) {
                ownerPlayer.sendSystemMessage(saleMessage);
            }

            gdClaim.getEconomyData().setForSale(false);
            gdClaim.getEconomyData().setSalePrice(0.0F);
            gdClaim.getData().save();

            player.sendSystemMessage(message);

            return 1;
        };
    }

    public static Function<MoParams, Object> rentClaim(Player player) {
        return params -> {
            return 1;
        };
    }
}
