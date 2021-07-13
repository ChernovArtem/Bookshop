package ru.paymentservice.payments.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.paymentservice.payments.models.PaymentBilling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import ru.paymentservice.payments.models.PaymentResult;
import ru.paymentservice.payments.repositories.BillingRepository;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/gateway")
public class PaymentController {

    Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private BillingRepository paymentBilling;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/payments")
    public List<PaymentBilling> getAllPayments() {
        return paymentBilling.findAll();
    }

    @PostMapping(value = "/payment")
    public void registerPaymentBilling(@ModelAttribute PaymentBilling newPaymentBilling,
                                       HttpServletResponse httpServletResponse) throws IOException {
        PaymentResult response = new PaymentResult();
        response.setCheckNumber(newPaymentBilling.getOrderId());
        response.setPaymentStatus("success");
        response.setAmount(newPaymentBilling.getPaymentSum());
        logger.info("data written");
        paymentBilling.save(newPaymentBilling);

        StringBuilder builder = new StringBuilder();
        builder.append("http://localhost:8081/Bookshop/gateway/check?");

        builder.append("checkNumber=").append(response.getCheckNumber());
        builder.append("&");
        builder.append("amount=").append(response.getAmount());
        builder.append("&");
        builder.append("paymentStatus=").append(response.getPaymentStatus());

        httpServletResponse.sendRedirect(builder.toString());
    }
}
