package com.project.AccountService.dto;

import jakarta.validation.constraints.NotNull;

public class TransactionDTO {
    @NotNull(message = "Sender ID cannot be null")
    private Long senderId;
    @NotNull(message = "Receiver ID cannot be null")
    private Long receiverId;
    @NotNull(message = "Amount cannot be null")
    private Double amount;

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
