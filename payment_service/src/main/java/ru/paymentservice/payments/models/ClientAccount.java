package ru.paymentservice.payments.models;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Setter
@ToString
@Table(name = "client_account")
public class ClientAccount {

    @Id
    @Column(name = "client_account_id")
    private Long clientAccountId;

    @Basic
    @Column(name ="card_number")
    private int cardNumber;

    @Basic
    @Column(name = "client_sum")
    private double clientAmount;
}
