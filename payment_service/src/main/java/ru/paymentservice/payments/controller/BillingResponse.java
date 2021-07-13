package ru.paymentservice.payments.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.paymentservice.payments.models.PaymentResult;

public class BillingResponse {

    Logger logger = LoggerFactory.getLogger(BillingResponse.class);

    public void sendResponse(PaymentResult paymentResult, String token) {

        try (CloseableHttpClient httpResponse = HttpClientBuilder.create().build())
        {
            HttpPost request = new HttpPost("http://localhost:8080/Bookshop/gateway/check");
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(paymentResult);
            StringEntity params = new StringEntity(json);
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            httpResponse.execute(request);
        } catch (Exception e) {
            logger.error("Can not send response, err. msg:", e);
        }
    }
}
