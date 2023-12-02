package guru.sfg.beer.inventory.service.services;


import guru.sfg.common.events.BeerOrderDto;

/**
 * Created by jt on 2019-09-09.
 */
public interface AllocationService {

    Boolean allocateOrder(BeerOrderDto beerOrderDto);
}
