package ru.paymentservice.payments.models;

import lombok.*;
import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Setter
@ToString
@Table(name = "payment_billing")
public class PaymentBilling {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "shop_name")
    private String shopName;

    @Basic
    @Column(name = "first_name")
    private String firstName;

    @Basic
    @Column(name = "last_name")
    private String lastName;

    @Basic
    @Column(name = "payment_sum")
    private double paymentSum;

    @Basic
    @Column(name = "order_Id")
    private int orderId;

    @Transient
    private String token;
}
