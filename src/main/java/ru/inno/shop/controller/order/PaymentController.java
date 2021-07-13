package ru.inno.shop.controller.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.inno.shop.model.order.OrderStatus;
import ru.inno.shop.model.order.Orders;
import ru.inno.shop.model.order.Payment;
import ru.inno.shop.repository.order.PaymentsDao;
import ru.inno.shop.service.order.OrderService;
import ru.inno.shop.service.stock.StockService;
import java.util.List;

@Controller
@RequestMapping(value = "/gateway")
public class PaymentController {

    @Autowired
    private PaymentsDao paymentsRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private StockService stockService;

    @GetMapping(value = "/check")
    public String newPaymentsResponse(@RequestParam(name = "checkNumber") String checkNumber,
                                    @RequestParam(name = "amount") double amount,
                                    @RequestParam(name = "paymentStatus") String paymentStatus){

        Payment payment = new Payment(null, checkNumber, amount, paymentStatus);

        if(payment.getPaymentStatus().equals("success")){
            payment = paymentsRepository.save(payment);

            String order_number = payment.getCheckNumber();
            Orders order = orderService.getOrderById(Long.valueOf(order_number));
            if (order == null) {
                return "redirect:/orders";
            }

            order.setStatus(OrderStatus.PAID);
            orderService.updateOrder(order);

            order.getOrderDetailsSet().forEach(details ->
                    stockService.shipmentOfGoods(details.getBook().getIsbn()));
        }
        return "redirect:/orders";
    }

    @GetMapping(value = "/payments")
    public List<Payment> getAllPayments() {
        return paymentsRepository.findAll();
    }
}
