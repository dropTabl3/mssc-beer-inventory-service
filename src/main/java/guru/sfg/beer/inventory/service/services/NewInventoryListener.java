package guru.sfg.beer.inventory.service.services;

import guru.sfg.beer.inventory.service.config.JMSConfig;
import guru.sfg.beer.inventory.service.domain.BeerInventory;
import guru.sfg.beer.inventory.service.repositories.BeerInventoryRepository;
import guru.sfg.beer.inventory.service.web.model.BeerDto;
import guru.sfg.common.events.NewInventoryEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewInventoryListener {
    private final BeerInventoryRepository beerInventoryRepository;
    private final JmsTemplate jmsTemplate;


    @Transactional
    @JmsListener(destination = JMSConfig.NEW_INVENTORY_QUEUE)
    public void listen(NewInventoryEvent newInventoryEvent) {
        BeerDto beerDto = newInventoryEvent.getBeerDto();

        beerInventoryRepository.save(
                BeerInventory.builder()
                        .beerId(beerDto.getId())
                        .upc(beerDto.getUpc())
                        .quantityOnHand(beerDto.getQuantityOnHand())
                        .build());

    }

}
