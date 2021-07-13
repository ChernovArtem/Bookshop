<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<page:template>

    <jsp:attribute name="title">Мой заказ | Bookshop</jsp:attribute>
    <jsp:body>
        <!-- Start Bradcaump area -->
        <div class="ht__bradcaump__area_small bg-image--6">
        </div>
        <!-- Start Checkout Area -->
        <section class="wn__checkout__area section-padding--lg bg__white">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6 col-12">
                        <form:form action="${pageContext.request.contextPath}/checkout" method="post" modelAttribute="customer" id ="updatecustomer">
                        <div class="customer_details">
                            <h3>Данные заказа</h3>
                            <input type="hidden" name="id" id="id" value="${order.customerId.id}">
                            <input type="hidden" id="orderNumber" name="orderNumber" value="${order.orderNumber}">
                            <input type="hidden" id="price" name="price">
                            <input type="hidden" id="delivery" name="delivery">
                            <div class="customar__field">
                                <div class="margin_between">
                                    <div class="input_box space_between">
                                        <label for="firstname">Имя <span>*</span></label>
                                        <input type="text" value="${order.customerId.firstname}" name="firstname" id="firstname">
                                    </div>
                                    <div class="input_box space_between">
                                        <label for="lastname">Фамилия <span>*</span></label>
                                        <input type="text" value="${order.customerId.lastname}" id="lastname" name="lastname">
                                    </div>
                                </div>
                                <div class="input_box">
                                    <label for="country">Страна<span>*</span></label>
                                    <select class="select__option" id="country" name="country">
                                        <option>${order.customerId.country}</option>
                                        <option>Россия</option>
                                        <option>Беларусь</option>
                                        <option>Украина</option>
                                        <option>Казахстан</option>
                                        <option>Гондурас</option>
                                        <option>Экваториальная Гвинея</option>
                                        <option>Вьетнам</option>
                                    </select>
                                </div>
                                <div class="input_box">
                                    <label for="state">Регион <span>*</span></label>
                                    <select class="select__option" id="state" name="state">
                                        <option>${order.customerId.state}</option>
                                        <option>Московская область</option>
                                        <option>Республика Саха</option>
                                        <option>Хабаровский край</option>
                                        <option>Красноярский край</option>
                                        <option>Краснодарский край</option>
                                        <option>Ульяновская область</option>
                                    </select>
                                </div>
                                <div class="input_box">
                                    <label for="city">Город <span>*</span></label>
                                    <input type="text" value="${order.customerId.city}" name="city" id="city">
                                </div>
                                <div class="input_box">
                                    <label for="street">Адрес <span>*</span></label>
                                    <input type="text" name="street" id="street" placeholder="Улица/квартал/район" value="${order.customerId.street}">
                                </div>
                                <div class="input_box">
                                    <label for="address"></label>
                                    <input type="text" name="address" id="address" placeholder="Дом, квартира, комната" value="${order.customerId.address}">
                                </div>

                                <div class="input_box">
                                    <label for="postal">Почтовый индекс <span>*</span></label>
                                    <input type="text" value="${order.customerId.postal}" name="postal" id="postal">
                                </div>
                                <div class="margin_between">
                                    <div class="input_box space_between">
                                        <label for="phone">Телефон <span>*</span></label>
                                        <input type="text" value="${order.customerId.phone}" name="phone" id="phone">
                                    </div>

                                    <div class="input_box space_between">
                                        <label for="email">Адрес электронной почты <span>*</span></label>
                                        <input type="email" name="email" id="email" value="${order.customerId.user.email}">
                                    </div>
                                </div>
                            </div>
                        </div>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form:form>
                    </div>
                    <div class="col-lg-6 col-12 md-mt-40 sm-mt-40">

                        <div class="wn__order__box">
                            <h3 class="onder__title">Ваш заказ №${order.orderNumber} (${order.status.label})</h3>
                            <ul class="order__total">
                                <li>Товар</li>
                                <li>Сумма</li>
                            </ul>
                            <ul class="order_product">
                                <c:forEach var="orderDetail" items="${order.orderDetailsSet}">
                                <li>${orderDetail.book.title} × ${orderDetail.quantityOrdered}
                                    <span class="product-price">₽${orderDetail.price.floatValue() * orderDetail.quantityOrdered}</span>
                                </li>
                                </c:forEach>
                            </ul>
                            <ul class="shipping__method">
                                <li>Товаров на сумму<span id="cart_total"><script>
                                                function allSums() {
                                                    let sum = 0;
                                                    let values = document.getElementsByClassName('product-price');
                                                    for (let i = 0; i < values.length; i++) {
                                                    sum += parseInt(values[i].outerText.substring(1));
                                                    }
                                                return sum;
                                                }
                                                let p = allSums();
                                                document.getElementById('cart_total').innerText ='₽'+p;
                                        </script></span></li>
                                <li>Доставка
                                    <ul>
                                        <li>
                                            <input name="shipping_method[0]" value="legacy_flat_rate" checked="checked" type="radio" onclick="orderTotal('personally')">
                                            <label>Самовывоз: </label><span id="personally" class="text-right">₽0</span>
                                        </li>
                                        <li>
                                            <input name="shipping_method[0]" value="legacy_flat_rate" type="radio" onclick="orderTotal('postals')">
                                            <label>Почтой России: </label><span id="postals" class="text-right">₽100</span>
                                        </li>
                                        <li>
                                            <input name="shipping_method[0]" value="legacy_flat_rate" type="radio" onclick="orderTotal('courier')">
                                            <label>Курьерской службой: </label><span id="courier" class="text-right">₽1000</span>
                                        </li>
                                    </ul>
                                </li>
                            </ul>

                            <ul class="total__amount">
                                <li>Итоговая сумма заказа <span id="order_total"><script>
                                    function orderTotal(elem) {

                                        let l = parseFloat(document.getElementById('cart_total').innerText.substring(1)) + parseFloat(document.getElementById(elem).innerText.substring(1));
                                        document.getElementById('order_total').innerText ='₽'+ l;
                                        $("#price").val(l);
                                        $("#delivery").val(parseFloat(document.getElementById(elem).innerText.substring(1)));
                                    }
                                    orderTotal('personally');
                                </script></span></li>
                                <li>

                                <c:if test="${order.status=='NEW_ORDER' || order.status=='AWAITING_PAY' }">
                                    <button type="button" class="btn btn-lg btn-primary" id = "payBtn">
                                        <span>
                                            <a href="javascript:actionForm('updatecustomer')" onclick="actionForm('updatecustomer')">Оплатить</a>
                                            <script>function actionForm(element) {
                                                document.getElementById(element).submit();
                                            }</script>
                                            </span>
                                    </button>
                                </c:if>
                                <script>
                                    function checkCardAvailability() {
                                        var country = document.getElementById("country").value;
                                        var region = carNumber.length;
                                        var city = document.getElementById("state").value;
                                        var firstname = document.getElementById("firstname").value;
                                        var lastname = document.getElementById("lastname").value;
                                        var index = document.getElementById("postal").value;
                                        var phone = document.getElementById("phone").value;

                                        if (country.length > 0 && region.length > 0 && city.length > 0 && firstname.length > 0 && lastname.length > 0 && index.length && phone.length > 0){
                                            document.getElementById("payBtn").disable = false;
                                        }
                                    }
                                </script>

                                </li>
                            </ul>

                        </div>

                    </div>
                </div>
            </div>
        </section>
        <!-- End Checkout Area -->

    </jsp:body>
</page:template>
