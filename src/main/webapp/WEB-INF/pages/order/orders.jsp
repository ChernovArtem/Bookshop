<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>



<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<page:template>

    <jsp:attribute name="title">Все мои заказы | Bookshop</jsp:attribute>
    <jsp:body>
        <!-- Start Bradcaump area -->
        <div class="ht__bradcaump__area_small bg-image--6">
        </div>
        <!-- End Bradcaump area -->
        <!-- cart-main-area start -->
        <div class="cart-main-area section-padding--lg bg--white">
        <div class="container">
        <c:if test="${orders.size()>0}">
        <div class="row">
            <div class="col-md-12 col-sm-12 ol-lg-12">
                    <div class="table-content wnro__table table-responsive">
                        <table>
                            <thead>
                            <tr class="title-top">
                                <th class="product-name">Номер<br>заказа</th>
                                <th class="product-name">Дата<br>формирования<br>заказа</th>
                                <th class="product-name">Ожидаемая дата</th>
                                <th class="product-name">Дата доставки</th>
                                <th class="product-name">Стоимость заказа<br>(с доставкой)</th>
                                <th class="product-name">Стоимость<br>доставки</th>
                                <th class="product-name">Статус заказа</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="orders" items="${orders}">
                            <tr>
                                <td class="product-price">
                                    <form:form method="get" id="view_order_form" action="${pageContext.request.contextPath}/viewoneorder">
                                    <a href="javascript:actionForm('view_order_form')" onclick="actionForm('view_order_form')">${orders.orderNumber}</a>
                                    <input type="hidden" name="orderNumber" id="orderNumber" value="${orders.orderNumber}">
                                        <script>function actionForm(element) {
                                            document.getElementById(element).submit();
                                        }</script>
                                    </form:form></li></a></td>
                                <td class="product-price">${orders.orderDate}</td>
                                <td class="product-price">${orders.requiredDate}</td>
                                <td class="product-price">${orders.shippedDate}</td>
                                <td class="product-price">${orders.price} руб.</td>
                                <td class="product-price">${orders.delivery} руб.</td>
                                <td class="product-price">${orders.status.label}</td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
            </div>
        </div>
        </div>
            </c:if>
            <c:if test="${orders.size()==0}">

                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="bradcaump__inner text-center">
                                <h2>Заказов пока нет!</h2>
                                <nav class="bradcaump-content">
                                    <a class="breadcrumb_item" href="${pageContext.request.contextPath}/shop"><h2><span class="breadcrumb_item active">Выбрать книгу!</span></h2></a>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
    </jsp:body>
</page:template>
