package br.com.fiap.mspedidos.application.rest.controller;

import br.com.fiap.mspedidos.application.rest.controller.facade.OrderFacade;
import br.com.fiap.mspedidos.application.rest.dto.OrderDTO;
import br.com.fiap.mspedidos.domain.exception.AppException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController extends AbstractRestController {

    private final OrderFacade orderFacade;

    public OrderController(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    @PostMapping
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO) throws AppException {
        return orderFacade.createOrder(orderDTO);
    }

    @GetMapping("/{orderId}")
    public OrderDTO getOrder(@PathVariable String orderId) throws AppException {
        return orderFacade.getOrder(orderId);
    }

    @PutMapping("/{orderId}")
    public OrderDTO updateOrder(@PathVariable String orderId, @RequestBody OrderDTO orderDTO) throws AppException {
        return orderFacade.updateOrder(orderId, orderDTO);
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable String orderId) throws AppException {
        orderFacade.deleteOrder(orderId);
    }

    @PutMapping("/{orderId}/{status}")
    public OrderDTO changeOrderStatus(@PathVariable String orderId, @PathVariable String status) throws AppException {
        return orderFacade.changeOrderStatus(orderId, status);
    }

    @GetMapping("/status/{status}")
    public List<OrderDTO> getOrderByStatus(@PathVariable String status) {
        return orderFacade.getOrderByStatus(status);
    }

    @GetMapping("/customer/{customerId}")
    public List<OrderDTO> getOrderByCustomerId(@PathVariable String customerId) {
        return orderFacade.getOrderByCustomerId(customerId);
    }
}
