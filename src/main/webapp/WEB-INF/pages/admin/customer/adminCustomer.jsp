<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<page:template>
    <jsp:attribute name="title">Клиенты | Bookshop</jsp:attribute>
    <jsp:body>
        <!-- End Search Popup -->
        <!-- Start Bradcaump area -->
        <div class="ht__bradcaump__area_small bg-image--6">
        </div>
        <!-- End Bradcaump area -->
        <!-- Start Error Area -->
        <section class="section-padding--lg bg--white">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="text-center">
                            <h3 class="store__title">Клиенты</h3>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12" style="margin-bottom: 20px">
                        <h4 class="text-center" style="font-size: 13px"><a href="${contextPath}/adminUsers">Показать всех пользователей</a></h4>
                    </div>
                </div>
                <div class="row">
                    <div class="store__form col-lg-12">
                        <form method="get" action="${contextPath}/adminCustomerSearch" style="display: inline;">
                            <input type="text" name="keyword" />
                            <input type="submit" value="Поиск" />
                        </form>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12 col-12">
                        <div class="table-content wnro__table table-responsive">
                            <table id="customTable">
                                <thead>
                                <tr class="title-top">
                                    <th>Имя</th>
                                    <th>Фамилия</th>
                                    <th>Телефон</th>
                                    <th>Адрес</th>
                                    <th>Улица</th>
                                    <th>Город</th>
                                    <th>Область</th>
                                    <th>Индекс</th>
                                    <th>Страна</th>
                                    <th colspan="2">Действие</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${listCustomers}" var="customer">
                                    <tr>
                                        <td>${customer.firstname}</td>
                                        <td>${customer.lastname}</td>
                                        <td>${customer.phone}</td>
                                        <td>${customer.address}</td>
                                        <td>${customer.street}</td>
                                        <td>${customer.city}</td>
                                        <td>${customer.state}</td>
                                        <td>${customer.postal}</td>
                                        <td>${customer.country}</td>
                                        <td><a href="${contextPath}/adminCustomerEdit?id=${customer.id}">Изменить</a></td>
                                        <td><a href="${contextPath}/adminCustomerDelete?id=${customer.id}">Удалить</a></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </jsp:body>
</page:template>