package com.acltabontabon.openwealth.services.customermgmt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.acltabontabon.openwealth.commons.Result;
import com.acltabontabon.openwealth.dtos.CustomerResponse;
import com.acltabontabon.openwealth.models.customermgmt.Customer;
import com.acltabontabon.openwealth.services.customermgmt.customer.CustomerReader;
import com.acltabontabon.openwealth.services.customermgmt.customer.SingleCustomerReader;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerManagementComponentFactory componentFactory;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void shouldReturnListOfCustomers() {
        // Arrange
        List<Customer> customers = List.of(Customer.builder().build());
        CustomerResponse customerResponse = CustomerResponse.builder().customers(customers).build();
        Result<CustomerResponse> expectedResponse = Result.success(customerResponse);

        CustomerReader mockCustomerReader = mock(CustomerReader.class);
        CustomerReader mockCustomerReaderWithCorrelation = mock(CustomerReader.class);

        when(componentFactory.createCustomerReader()).thenReturn(mockCustomerReader);
        when(mockCustomerReader.withCorrelationId("1234")).thenReturn(mockCustomerReaderWithCorrelation);
        when(mockCustomerReaderWithCorrelation.fetch()).thenReturn(expectedResponse);

        // Act
        Result<CustomerResponse> actualResponse = customerService.customers()
            .withCorrelationId("1234")
            .fetch();

        // Assert
        assertNotNull(actualResponse);
        assertEquals(expectedResponse.getData().getCustomers().size(), actualResponse.getData().getCustomers().size());
    }

    @Test
    void shouldReturnOneCustomer() {
        // Arrange
        Customer customer = Customer.builder().customerId("1").build();
        Result<Customer> expectedResponse = Result.success(customer);

        CustomerReader mockCustomerReader = mock(CustomerReader.class);
        CustomerReader mockCustomerReaderWithCorrelation = mock(CustomerReader.class);
        SingleCustomerReader mockSingleCustomerReader = mock(SingleCustomerReader.class);

        when(componentFactory.createCustomerReader()).thenReturn(mockCustomerReader);
        when(mockCustomerReader.withCorrelationId("1234")).thenReturn(mockCustomerReaderWithCorrelation);
        when(mockCustomerReaderWithCorrelation.withCustomerId("4321")).thenReturn(mockSingleCustomerReader);
        when(mockSingleCustomerReader.fetch()).thenReturn(expectedResponse);

        // Act
        Result<Customer> actualResponse = customerService.customers()
            .withCorrelationId("1234")
            .withCustomerId("4321")
            .fetch();

        // Assert
        assertNotNull(actualResponse);
        assertNotNull(actualResponse.getData());
        assertEquals(expectedResponse.getData().getCustomerId(), actualResponse.getData().getCustomerId());
    }
}