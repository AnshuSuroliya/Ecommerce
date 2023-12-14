package com.ecommerce.eccomerce_back.controller;

import com.ecommerce.eccomerce_back.entity.Order;
import com.ecommerce.eccomerce_back.exception.OrderException;
import com.ecommerce.eccomerce_back.repository.OrderRepository;
import com.ecommerce.eccomerce_back.request.PaymentRequest;
import com.ecommerce.eccomerce_back.response.PaymentLinkResponse;
import com.ecommerce.eccomerce_back.service.OrderService;

import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PaymentController {
    @Value("${razorpay.api.key}")
    String key;
    @Value("${razorpay.api.secret}")
    String secret;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

@PostMapping("/payment")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(@RequestBody PaymentRequest paymentRequest) throws OrderException, RazorpayException {
    System.out.println(paymentRequest.getOrderId());
    Order order=orderService.findOrderById(paymentRequest.getOrderId());
    try
    {
        RazorpayClient razorpay = new RazorpayClient(key,secret);
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",order.getTotalPrice()*100);
        paymentLinkRequest.put("currency","INR");
//        paymentLinkRequest.put("accept_partial",true);
//        paymentLinkRequest.put("first_min_partial_amount",100);
       // paymentLinkRequest.put("expire_by",1691097057);
        //paymentLinkRequest.put("reference_id","TS1989");
//        paymentLinkRequest.put("description","Payment for policy no #23456");
        JSONObject customer = new JSONObject();
        customer.put("name",order.getUser().getFirstName());
        customer.put("contact",order.getUser().getContactNumber());
        customer.put("email",order.getUser().getEmail());
        paymentLinkRequest.put("customer",customer);
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",true);
//        JSONObject notes = new JSONObject();
//        notes.put("policy_name","Jeevan Bima");
//        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","http://localhost:4500/api/payment");
        paymentLinkRequest.put("callback_method","get");
        PaymentLink payment = razorpay.paymentLink.create(paymentLinkRequest);
        String Url=payment.get("short_url");
        String Id=payment.get("id");
        PaymentLinkResponse paymentLinkResponse=new PaymentLinkResponse();
        paymentLinkResponse.setPaymentUrl(Url);
        paymentLinkResponse.setPaymentId(Id);
        return new ResponseEntity<>(paymentLinkResponse,HttpStatus.CREATED);
//        RazorpayClient client=new RazorpayClient(key,secret);
//        JSONObject paymentLinkRequest=new JSONObject();
//        paymentLinkRequest.put("amount",order.getTotalPrice()*100);
//        paymentLinkRequest.put("currency","INR");
//        JSONObject buyer=new JSONObject();
//        buyer.put(order.getUser().getFirstName(),order.getUser().getEmail());
//        paymentLinkRequest.put("buyer",buyer);
//        JSONObject notification=new JSONObject();
//        notification.put("email",true);
//        paymentLinkRequest.put("notification",notification);
//        paymentLinkRequest.put("callbackUrl","http://localhost:4500/api/payment");
//        paymentLinkRequest.put("callback_method","get");
//        PaymentLink paymentLink=client.paymentLink.create(paymentLinkRequest);
//        String paymentLinkId=paymentLink.get("id");
//        String paymentLinkUrl=paymentLink.get("url");
//        PaymentLinkResponse response=new PaymentLinkResponse();
//        response.setPaymentId(paymentLinkId);
//        response.setPaymentUrl(paymentLinkUrl);
//        return new ResponseEntity<>(response, HttpStatus.CREATED);

    } catch(Exception e){
        throw new RazorpayException(e.getMessage());
    }
    }
    @GetMapping("/payments")
    public ResponseEntity<?> redirect(@RequestParam("paymentId") String paymentId,@RequestParam("orderId") Long orderId) throws OrderException, RazorpayException {
        Order order = orderService.findOrderById(orderId);
        RazorpayClient client = new RazorpayClient(key, secret);
        try {
            Payment payment = client.payments.fetch(paymentId);
            if (payment.get("status").equals("captured")) {
                order.getPaymentDetails().setPaymentId(paymentId);
                order.getPaymentDetails().setStatus("Completed");
                order.setOrderStatus("Placed");
                orderRepository.save(order);
            }
            return new ResponseEntity<>("Your Order is Placed", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new RazorpayException(e.getMessage());
        }
    }
}
