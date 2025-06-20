package com.nt;

import com.nt.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderSeerviceTest {

    @Spy
   // @InjectMocks
    private OrderService service;

    @Test
    public void testPlacerOrder(){


        Mockito.doNothing().when(service).sendConfirmationEmail(Mockito.anyString());
      //  Mockito.doNothing().when(service).validateStock(Mockito.anyString());

        service.placeOrder("102");
        //Mockito.verify(service).validateStock("102");
       // Mockito.verify(service).sendConfirmationEmail("raju@gamilc.om");

    }
}
