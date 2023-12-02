package guru.sfg.common.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AllocateOrderResponse {
    private BeerOrderDto beerOrderDto;
    private boolean allocationError;
    private boolean pendingInventory;
}
