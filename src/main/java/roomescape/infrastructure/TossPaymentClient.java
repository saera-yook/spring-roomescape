package roomescape.infrastructure;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import roomescape.application.PaymentConfirmRequest;
import roomescape.application.reponse.PaymentConfirmResponse;
import roomescape.domain.payment.PaymentClient;

public class TossPaymentClient implements PaymentClient {
    private final RestTemplate restTemplate;

    public TossPaymentClient(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public PaymentConfirmResponse confirm(final PaymentConfirmRequest request) {
        String widgetSecretKey = "test_gsk_docs_OaPz8L5KdmQXkzRz3y47BMw6";
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode((widgetSecretKey + ":").getBytes(StandardCharsets.UTF_8));
        String authorizations = "Basic " + new String(encodedBytes);

        String url = "https://api.tosspayments.com/v1/payments/confirm";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorizations);
        headers.add("Content-Type", "application/json");

        HttpEntity<PaymentConfirmRequest> entity = new HttpEntity<PaymentConfirmRequest>(request, headers);

        return restTemplate.postForObject(url, entity, PaymentConfirmResponse.class);
    }
}
