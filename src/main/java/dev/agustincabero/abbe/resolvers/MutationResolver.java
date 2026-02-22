package dev.agustincabero.abbe.resolvers;

import dev.agustincabero.abbe.services.MarketGroupService;
import dev.agustincabero.abbe.services.MarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class MutationResolver {
    private final MarketGroupService marketGroupService;
    private final MarketService marketService;

    @MutationMapping
    public boolean createMarketGroup(@Argument String name, @Argument UUID brandPublicId) {
        return marketGroupService.createMarketGroup(name, brandPublicId);
    }

    @MutationMapping
    public boolean deleteMarketGroup(@Argument UUID marketGroupPublicId) {
        return marketGroupService.deleteMarketGroup(marketGroupPublicId);
    }

    @MutationMapping
    public boolean upsertMarkets(@Argument UUID marketGroupPublicId, @Argument List<UUID> countries) {
        return marketService.upsertMarkets(marketGroupPublicId, countries);
    }
}
