package com.acltabontabon.openwealth.services.custodyservices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.acltabontabon.openwealth.commons.Result;
import com.acltabontabon.openwealth.models.custodyservices.Customer;
import com.acltabontabon.openwealth.properties.OpenWealthApiProperties.CustodyServices;
import com.acltabontabon.openwealth.services.TestFixtures;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

@ExtendWith(MockitoExtension.class)
class CustodyServiceTest {

    @Mock
    private RestClient restClient;

    @Mock
    private CustodyServices apiProperties;

    @InjectMocks
    private CustodyService custodyService;

    @Test
    void shouldReturnListOfCustomers() {
        var customers = List.of(Customer.builder().build());
        var expectedResponse = Result.success(customers);

        var uriSpec = mock(RestClient.RequestHeadersUriSpec.class);
        var headersSpec = mock(RestClient.RequestHeadersSpec.class);
        var responseSpec = mock(RestClient.ResponseSpec.class);

        when(apiProperties.getCustomers())
            .thenReturn(TestFixtures.MOCK_URL);
        when(restClient.get())
            .thenAnswer(invocation -> uriSpec);
        when(uriSpec.uri(anyString()))
            .thenAnswer(invocation -> headersSpec);
        when(headersSpec.header(anyString(), anyString()))
            .thenAnswer(invocation -> headersSpec);
        when(headersSpec.retrieve())
            .thenReturn(responseSpec);
        when(responseSpec.body(new ParameterizedTypeReference<List<Customer>>() {}))
            .thenReturn(customers);

        var actualResponse = custodyService.customers().withCorrelationId("1234").fetch();

        assertNotNull(actualResponse.getData());
        assertEquals(expectedResponse.getData().size(), actualResponse.getData().size());
    }
}