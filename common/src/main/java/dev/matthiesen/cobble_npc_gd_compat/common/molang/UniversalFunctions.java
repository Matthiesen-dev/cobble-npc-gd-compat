package dev.matthiesen.cobble_npc_gd_compat.common.molang;

import com.bedrockk.molang.runtime.MoParams;
import com.bedrockk.molang.runtime.value.DoubleValue;
import dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.GDCollectors;
import dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.GDUtils;
import dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.data.ForSaleClaimData;
import dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.data.RentalClaimData;
import dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.data.SimpleClaimData;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

public final class UniversalFunctions {
    public static DoubleValue intToDouble(int val) {
        return new DoubleValue((double) val);
    }

    public static DoubleValue isNull() {
        return new DoubleValue(0);
    }

    public static Function<MoParams, Object> getPlayerClaims() {
        return moParams -> {
            String stringUuid = moParams.getString(0);
            UUID uuid = UUID.fromString(stringUuid);
            List<SimpleClaimData> playerClaims = GDUtils.getPlayerClaims(uuid);
            return SimpleClaimData.asMolangValueFromList(playerClaims);
        };
    }

    public static Function<MoParams, Object> isEconomyEnabled() {
        return moParams -> intToDouble(GDUtils.isEconomyEnabled() ? 1 : 0);
    }

    public static Function<MoParams, Object> getAvailableRentals(Level level) {
        return params -> {
            List<RentalClaimData> rentals = GDCollectors.getRentals(level);
            return RentalClaimData.asMolangValueFromList(rentals);
        };
    }

    public static Function<MoParams, Object> getAvailableForSale(Level level) {
        return params -> {
            List<ForSaleClaimData> forSale = GDCollectors.getForSale(level);
            return ForSaleClaimData.asMolangValueFromList(forSale);
        };
    }
}
