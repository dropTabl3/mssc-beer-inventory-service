package guru.sfg.beer.inventory.service.services.listeners;

import guru.sfg.beer.inventory.service.config.JMSConfig;
import guru.sfg.beer.inventory.service.services.AllocationService;
import guru.sfg.common.events.AllocateOrderRequest;
import guru.sfg.common.events.AllocateOrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class AllocateOrderListener {

    private final JmsTemplate jmsTemplate;
    private final AllocationService allocationService;
    @Transactional
    @JmsListener(destination = JMSConfig.ALLOCATE_ORDER_Q)
    public void listen(AllocateOrderRequest request) {
        AllocateOrderResponse response = null;
        try {
            response = AllocateOrderResponse.builder()
                    .beerOrderDto(request.getBeerOrderDto())
                    .pendingInventory(!allocationService.allocateOrder(request.getBeerOrderDto()))
                    .build();
        } catch (Exception e) {
            response.setAllocationError(true);
        } finally {
            jmsTemplate.convertAndSend(JMSConfig.ALLOCATE_ORDER_RESULT_Q, response);
        }
    }
}
