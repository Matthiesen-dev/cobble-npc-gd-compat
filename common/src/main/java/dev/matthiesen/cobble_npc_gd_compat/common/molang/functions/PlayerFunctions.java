package dev.matthiesen.cobble_npc_gd_compat.common.molang.functions;

import com.bedrockk.molang.runtime.MoParams;
import com.bedrockk.molang.runtime.value.StringValue;
import com.griefdefender.api.data.PlayerData;
import dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.GDUtils;
import dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.SimpleClaimData;
import net.minecraft.world.entity.player.Player;

import java.util.List;
import java.util.function.Function;

public final class PlayerFunctions {
    public static Function<MoParams, Object> getPlayerClaims(Player player) {
        return params -> {
            List<SimpleClaimData> playerClaims = GDUtils.getPlayerClaims(player.getUUID());
            return SimpleClaimData.asMolangValueFromList(playerClaims);
        };
    }

    public static Function<MoParams, Object> getCurrentClaim(Player player) {
        return moParams -> {
            PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
            var claim = playerData.getCurrentClaim();
            if (claim == null) return UniversalFunctions.isNull();
            return new StringValue(claim.getUniqueId().toString());
        };
    }

    public static Function<MoParams, Object> getAccruedClaimBlocks(Player player) {
        return moParams -> {
            PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
            return UniversalFunctions.intToDouble(playerData.getAccruedClaimBlocks());
        };
    }

    public static Function<MoParams, Object> getBlocksAccruedPerHour(Player player) {
        return moParams -> {
            PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
            return UniversalFunctions.intToDouble(playerData.getBlocksAccruedPerHour());
        };
    }

    public static Function<MoParams, Object> getMaxAccruedClaimBlocks(Player player) {
        return moParams -> {
            PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
            return UniversalFunctions.intToDouble(playerData.getMaxAccruedClaimBlocks());
        };
    }

    public static Function<MoParams, Object> getMaxBonusClaimBlocks(Player player) {
        return moParams -> {
            PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
            return UniversalFunctions.intToDouble(playerData.getMaxBonusClaimBlocks());
        };
    }

    public static Function<MoParams, Object> getMaxClaimLevel(Player player) {
        return moParams -> {
            PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
            return UniversalFunctions.intToDouble(playerData.getMaxClaimLevel());
        };
    }

    public static Function<MoParams, Object> getMinClaimLevel(Player player) {
        return moParams -> {
            PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
            return UniversalFunctions.intToDouble(playerData.getMinClaimLevel());
        };
    }

    public static Function<MoParams, Object> getBonusClaimBlocks(Player player) {
        return moParams -> {
            PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
            return UniversalFunctions.intToDouble(playerData.getBonusClaimBlocks());
        };
    }

    public static Function<MoParams, Object> getInitialClaimBlocks(Player player) {
        return moParams -> {
            PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
            return UniversalFunctions.intToDouble(playerData.getInitialClaimBlocks());
        };
    }

    public static Function<MoParams, Object> getRemainingClaimBlocks(Player player) {
        return moParams -> {
            PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
            return UniversalFunctions.intToDouble(playerData.getRemainingClaimBlocks());
        };
    }

    public static Function<MoParams, Object> getMaxClaimableBlocks(Player player) {
        return moParams -> {
            PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
            return UniversalFunctions.intToDouble(playerData.getMaxClaimableBlocks());
        };
    }

    public static Function<MoParams, Object> getRentalLimit(Player player) {
        return moParams -> {
            PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
            return UniversalFunctions.intToDouble(playerData.getRentalLimit());
        };
    }
}
