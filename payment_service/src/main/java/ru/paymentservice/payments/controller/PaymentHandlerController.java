package ru.paymentservice.payments.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaymentHandlerController {

    Logger logger = LoggerFactory.getLogger(PaymentHandlerController.class);

    /*http://localhost:80/index?shopName=superShop&orderId=11&paymentSum=123.6*/
    @GetMapping("/index")
    public String payment(@RequestParam(name = "shopName") String shopName,
                          @RequestParam(name = "orderId") int orderId,
                          @RequestParam(name = "paymentSum") double paymentSum, Model model) {
        model.addAttribute("shopName", shopName);
        model.addAttribute("orderId", orderId);
        model.addAttribute("paymentSum", paymentSum);
        logger.info("Income payment");
        return "index";
    }
}
