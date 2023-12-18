package com.enit.shipping.api;


import com.enit.shipping.api.DTOs.OrderRequest;
import com.enit.shipping.applicationService.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("shipping")
public class ShippingApi {
    private ShippingService shippingService;
    @Autowired
     public  ShippingApi(ShippingService shippingService)
     {
         this.shippingService=shippingService;
     }
     @PostMapping("/startshipping")
     @ResponseStatus(HttpStatus.CREATED)
     public void startshipping(@RequestBody OrderRequest order)
     {
         shippingService.StartShipping(order);
     }
     @PostMapping("/shipped/{id}")
     @ResponseStatus(HttpStatus.CREATED)
    public void shipped(@PathVariable("id") Long idShipping)
     {
         shippingService.shipped(idShipping);
     }



}
