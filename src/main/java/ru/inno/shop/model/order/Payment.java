package ru.inno.shop.model.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payments")
public class Payment {

    @Id
    @Column(name = "income_payment", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long incomePayment;

    @Basic
    @Column(name = "order_number")
    private String checkNumber;

    @Basic
    @Column(name = "amount")
    private double amount;

    @Basic
    @Column(name = "payments_status")
    private String paymentStatus;
}
