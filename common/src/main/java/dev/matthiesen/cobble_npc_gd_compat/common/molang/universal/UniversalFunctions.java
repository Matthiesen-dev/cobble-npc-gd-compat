package dev.matthiesen.cobble_npc_gd_compat.common.molang.universal;

import com.bedrockk.molang.runtime.MoParams;
import com.bedrockk.molang.runtime.value.DoubleValue;
import dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.GDCollectors;
import dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.GDUtils;
import dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.claim.ForSaleClaim;
import dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.claim.RentalClaim;
import dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.claim.SimpleClaim;
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
        return params -> {
            String stringUuid = params.getString(0);
            UUID uuid = UUID.fromString(stringUuid);
            List<SimpleClaim> playerClaims = GDUtils.getPlayerClaims(uuid);
            return SimpleClaim.asMolangValueFromList(playerClaims);
        };
    }

    public static Function<MoParams, Object> isEconomyEnabled() {
        return params -> intToDouble(GDUtils.isEconomyEnabled() ? 1 : 0);
    }

    public static Function<MoParams, Object> getAvailableRentals(Level level) {
        return params -> {
            List<RentalClaim> rentals = GDCollectors.getRentals(level);
            return RentalClaim.asMolangValueFromList(rentals);
        };
    }

    public static Function<MoParams, Object> getAvailableForSale(Level level) {
        return params -> {
            List<ForSaleClaim> forSale = GDCollectors.getForSale(level);
            return ForSaleClaim.asMolangValueFromList(forSale);
        };
    }
}
