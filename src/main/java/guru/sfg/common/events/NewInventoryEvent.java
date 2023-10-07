package guru.sfg.common.events;

import guru.sfg.beer.inventory.service.web.model.BeerDto;
import lombok.NoArgsConstructor;
@NoArgsConstructor
public class NewInventoryEvent extends BeerEvent{
    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
