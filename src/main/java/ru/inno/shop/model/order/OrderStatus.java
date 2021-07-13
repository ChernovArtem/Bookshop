package ru.inno.shop.model.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Артём Матюнин
 * Статус заказа
 */
@Getter
@AllArgsConstructor
public enum OrderStatus {
    NEW_ORDER("Новый"),             //Присваивается в момент создания заказа
    AWAITING_PAY("Ожидает оплаты"), //Присваивается в момент перехода к оплате
    PAID("Оплачен"),                //Присваивается после оплаты и до отправки
    SEND("Отправлен"),              //Присваивается после отправки и до доставки
    DELIVERED("Доставлен");         //Присваеивается после доставки

    public final String label;
}
