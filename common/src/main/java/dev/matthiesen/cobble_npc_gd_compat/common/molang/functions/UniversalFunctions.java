package dev.matthiesen.cobble_npc_gd_compat.common.molang.functions;

import com.bedrockk.molang.runtime.MoParams;
import com.bedrockk.molang.runtime.value.MoValue;
import dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.GDUtils;
import dev.matthiesen.cobble_npc_gd_compat.common.griefdefender.SimpleClaimData;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

public final class UniversalFunctions {
    public static Function<MoParams, Object> getPlayerClaims() {
        return moParams -> {
            String stringUuid = moParams.getString(0);
            UUID uuid = UUID.fromString(stringUuid);
            List<SimpleClaimData> playerClaims = GDUtils.getPlayerClaims(uuid);
            return MoValue.of(playerClaims);
        };
    }

    public static Function<MoParams, Object> isEconomyEnabled() {
        return moParams -> GDUtils.isEconomyEnabled() ? 1 : 0;
    }
}
