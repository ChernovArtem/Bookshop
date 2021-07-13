<%--
  Created by IntelliJ IDEA.
  User: Артём
  Date: 26.05.2021
  Time: 0:12
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<page:template>

    <jsp:attribute name="title">Моя корзина | Bookshop</jsp:attribute>
    <jsp:body>
        <!-- Start Bradcaump area -->
        <div class="ht__bradcaump__area bg-image--3">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="bradcaump__inner text-center">
                            <h2 class="bradcaump-title">Корзина</h2>
                            <nav class="bradcaump-content">
                                <a class="breadcrumb_item" href="${pageContext.request.contextPath}/index">На главную</a>
                                <span class="brd-separetor">/</span>
                                <span class="breadcrumb_item active">Корзина</span>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Bradcaump area -->
        <!-- cart-main-area start -->

        <div class="cart-main-area section-padding--lg bg--white">
            <div class="container">

                <c:if test="${newCart.cartDetailsSet.size()>0}">
                    <div class="row">
                        <div class="col-md-12 col-sm-12 ol-lg-12">
                            <div class="table-content wnro__table table-responsive">
                                <table>
                                    <thead>
                                    <tr class="title-top">
                                        <th class="product-thumbnail">Изображение</th>
                                        <th class="product-name">Книга</th>
                                        <th class="product-price">Цена</th>
                                        <th class="product-quantity">Количество</th>
                                        <th class="product-subtotal">Итог</th>
                                        <th class="product-remove">Удалить</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:if test="${available!=null}">
                                     <script>
                                            alert("Выбранного товара недостаточно на складе. Доступно: ${available}");
                                     </script>
                                    </c:if>
                                    <c:forEach var="cartDetails" items="${newCart.cartDetailsSet}">
                                        <tr>
                                            <td class="product-thumbnail"><a href="${pageContext.request.contextPath}/book/${cartDetails.cartDetailBook}"><img src="resources/images/product/books-img/${cartDetails.book.isbn}.jpg" alt="${cartDetails.book.title}"></a></td>
                                            <td class="product-name"><a href="${pageContext.request.contextPath}/book/${cartDetails.cartDetailBook}">${cartDetails.book.title}</a></td>
                                            <td class="product-price"><span class="amount">₽${cartDetails.book.price}</span></td>
                                            <td class="product-quantity">
                                                <form:form method="post" modelAttribute="cartDetail" id="${cartDetails.cartDetailBook}"
                                                           action="${pageContext.request.contextPath}/count">
                                                    <input type="hidden" name="cartId" value="${cartDetails.cartId}">
                                                    <label for="${cartDetails.cartDetailQuantity}qty">
                                                        <select class="select__option"
                                                                id="${cartDetails.cartDetailBook}qty"
                                                                name="cartDetailQuantity">
                                                            <option>${cartDetails.cartDetailQuantity}</option>
                                                            <option>1</option>
                                                            <option>2</option>
                                                            <option>3</option>
                                                            <option>4</option>
                                                            <option>5</option>
                                                            <option>6</option>
                                                            <option>7</option>
                                                            <option>8</option>
                                                            <option>9</option>
                                                            <option>10</option>
                                                        </select><span><script>
                                                document.getElementById('${cartDetails.cartDetailBook}qty').onchange = function () {
                                                    document.getElementById('${cartDetails.cartDetailBook}').submit();
                                                }
                                            </script></span>
                                                    </label>
                                                    <input type="hidden" name="cartDetailBook" class="form-control"
                                                           id="book"
                                                           value="${cartDetails.cartDetailBook}">
                                                </form:form>
                                            </td>
                                            <td class="product-subtotal">₽${cartDetails.cartDetailQuantity*cartDetails.book.price}
                                            </td>
                                            <td class="product-remove">
                                                <form:form method="post" id="${cartDetails.cartDetailBook}tkwy"
                                                           action="${pageContext.request.contextPath}/takeaway">
                                                    <span class="product-remove"><a href="javascript:actionForm('${cartDetails.book.isbn}tkwy')" onclick="actionForm('${cartDetails.cartDetailBook}tkwy')">X</a></span>
                                                    <script>function actionForm(element) {
                                                        document.getElementById(element).submit();
                                                    }</script>

                                                    <input type="hidden" name="book" class="form-control"
                                                           id="book"
                                                           value="${cartDetails.cartDetailBook}">
                                                </form:form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>

                            <div class="cartbox__btn">
                                <ul class="cart__btn__list d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between">
                                    <li><a href="#" onclick="return false;"onclick="return false;"onclick="return false;">Код купона</a></li>
                                    <li><a href="#" onclick="return false;">Применить код</a></li>
                                    <li><a href="#">Обновить корзину</a></li>
                                    <li><form:form method="Post" id="check_out_form" action="${pageContext.request.contextPath}/createorder">
                                    <a href="javascript:actionForm('check_out_form')" onclick="actionForm('check_out_form')">Оформить заказ</a>
                                    </form:form></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-6 offset-lg-6">
                            <div class="cartbox__total__area">
                                <div class="cartbox-total d-flex justify-content-between">
                                    <ul class="cart__total__list">
                                        <li>Товаров в корзине на сумму</li>
                                        <li>Ваша скидка</li>
                                    </ul>
                                    <ul class="cart__total__tk">
                                        <li id="cart_tot"><span id="cart_total"><script>
                                function allSum() {
                                    let sum = 0;
                                    let values = document.getElementsByClassName('product-subtotal');
                                    for (let i = 1; i < values.length; i++) {
                                        sum += parseInt(values[i].innerText.substring(1));
                                    }
                                    return sum;
                                }

                                let p = allSum();
                                document.getElementById('cart_total').outerText = '₽' + p;
                            </script></span></li>
                                        <li><span id="sub_total">₽0</span></li>
                                    </ul>
                                </div>
                                <div class="cart__total__amount">
                                    <span>Общий итог</span>
                                    <span id="grand_total"><script>
                            function grandSum() {
                                let cartSum = document.getElementById('cart_tot').innerText;
                                let disc = document.getElementById("sub_total").outerText;
                                return parseInt(cartSum.substring(1)) - parseInt(disc.substring(1));
                            }

                            let grandTotal = grandSum();
                            document.getElementById('grand_total').innerText = '₽' + grandTotal;
                        </script></span>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${newCart.cartDetailsSet.size()==0}">

                    <div class="container">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="bradcaump__inner text-center">
                                    <h2>В корзине пока ничего нет!</h2>
                                    <nav class="bradcaump-content">
                                        <h2><a class="breadcrumb_item"
                                               href="${pageContext.request.contextPath}/shop"><span
                                                class="breadcrumb_item active">Выбрать книгу!</span></a></h2>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </jsp:body>
</page:template>
