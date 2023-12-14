package com.ecommerce.eccomerce_back.entity;

import jakarta.persistence.*;
import org.json.JSONObject;
import com.razorpay.*;

public class PaymentDetails {

    private String paymentMethod;
    private String status;
    private String paymentId;
    private String razorpayPaymentLinkId;
    private String razorpayPaymentReferenceLinkId;
    private String razorpayPaymentStatus;
    private String razorpayPaymentId;

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getRazorpayPaymentLinkId() {
        return razorpayPaymentLinkId;
    }

    public void setRazorpayPaymentLinkId(String razorpayPaymentLinkId) {
        this.razorpayPaymentLinkId = razorpayPaymentLinkId;
    }

    public String getRazorpayPaymentReferenceLinkId() {
        return razorpayPaymentReferenceLinkId;
    }

    public void setRazorpayPaymentReferenceLinkId(String razorpayPaymentReferenceLinkId) {
        this.razorpayPaymentReferenceLinkId = razorpayPaymentReferenceLinkId;
    }

    public String getRazorpayPaymentStatus() {
        return razorpayPaymentStatus;
    }

    public void setRazorpayPaymentStatus(String razorpayPaymentStatus) {
        this.razorpayPaymentStatus = razorpayPaymentStatus;
    }

    public String getRazorpayPaymentId() {
        return razorpayPaymentId;
    }

    public void setRazorpayPaymentId(String razorpayPaymentId) {
        this.razorpayPaymentId = razorpayPaymentId;
    }
}