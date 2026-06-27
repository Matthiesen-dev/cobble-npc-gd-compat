package dev.matthiesen.cobble_npc_gd_compat.common.molang.functions;

import com.bedrockk.molang.runtime.MoParams;
import com.bedrockk.molang.runtime.value.MoValue;
import com.griefdefender.api.data.PlayerData;
import dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.GDUtils;
import net.minecraft.world.entity.player.Player;

import java.util.function.Function;

public final class PlayerFunctions {
    public static Function<MoParams, Object> getPlayerClaims(Player player) {
        return params -> {
            PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
            var claims = playerData.getClaims().stream().map(claim -> claim.getUniqueId().toString()).toList();
            return MoValue.of(claims);
        };
    }

    public static Function<MoParams, Object> getCurrentClaim(Player player) {
        return moParams -> {
            PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
            var claim = playerData.getCurrentClaim();
            if (claim == null) return 0;
            return claim.getUniqueId().toString();
        };
    }

    public static Function<MoParams, Object> getAccruedClaimBlocks(Player player) {
        return moParams -> {
            PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
            return playerData.getAccruedClaimBlocks();
        };
    }

    public static Function<MoParams, Object> getBlocksAccruedPerHour(Player player) {
        return moParams -> {
            PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
            return playerData.getBlocksAccruedPerHour();
        };
    }

    public static Function<MoParams, Object> getMaxAccruedClaimBlocks(Player player) {
        return moParams -> {
            PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
            return playerData.getMaxAccruedClaimBlocks();
        };
    }

    public static Function<MoParams, Object> getMaxBonusClaimBlocks(Player player) {
        return moParams -> {
            PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
            return playerData.getMaxBonusClaimBlocks();
        };
    }

    public static Function<MoParams, Object> getMaxClaimLevel(Player player) {
        return moParams -> {
            PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
            return playerData.getMaxClaimLevel();
        };
    }

    public static Function<MoParams, Object> getMinClaimLevel(Player player) {
        return moParams -> {
            PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
            return playerData.getMinClaimLevel();
        };
    }

    public static Function<MoParams, Object> getBonusClaimBlocks(Player player) {
        return moParams -> {
            PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
            return playerData.getBonusClaimBlocks();
        };
    }

    public static Function<MoParams, Object> getInitialClaimBlocks(Player player) {
        return moParams -> {
            PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
            return playerData.getInitialClaimBlocks();
        };
    }

    public static Function<MoParams, Object> getRemainingClaimBlocks(Player player) {
        return moParams -> {
            PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
            return playerData.getRemainingClaimBlocks();
        };
    }

    public static Function<MoParams, Object> getMaxClaimableBlocks(Player player) {
        return moParams -> {
            PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
            return playerData.getMaxClaimableBlocks();
        };
    }

    public static Function<MoParams, Object> getRentalLimit(Player player) {
        return moParams -> {
            PlayerData playerData = GDUtils.getPlayerData(player.level(), player.getUUID());
            return playerData.getRentalLimit();
        };
    }
}
