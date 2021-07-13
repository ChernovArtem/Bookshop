<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<page:template>
    <jsp:attribute name="title">Избранное | Bookshop</jsp:attribute>
    <jsp:body>
        <!-- End Search Popup -->
        <!-- Start Bradcaump area -->
        <div class="ht__bradcaump__area_small bg-image--6">
        </div>
        <!-- End Bradcaump area -->
        <!-- cart-main-area start -->
        <div class="wishlist-area section-padding--lg bg__white">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="wishlist-content">
                            <h2 class="text-center">Избранное</h2>

                            <c:if test="${wishlist == null || wishlist.size() == 0}">
                                <h4 class="text-center">пока пусто</h4>
                            </c:if>

                            <c:if test="${wishlist.size() > 0}">
                                <div class="wishlist-table wnro__table table-responsive">
                                    <table>
                                        <thead>
                                            <tr>
                                                <th class="product-remove"></th>
                                                <th class="product-thumbnail"></th>
                                                <th class="product-name"><span class="nobr">Название</span></th>
                                                <th class="product-price"><span class="nobr">Стоимость</span></th>
                                                <th class="product-stock-stauts"><span class="nobr">Наличие</span></th>
                                                <th class="product-add-to-cart"></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${wishlist}" var="wishlistBook">
                                                <tr>
                                                    <td class="product-remove">
                                                        <a href="${pageContext.request.contextPath}/wishlistDelete?id=${wishlistBook.id}&isbn=${wishlistBook.bookIsbn}">×</a>
                                                    </td>
                                                    <td class="product-thumbnail">
                                                        <a href="${pageContext.request.contextPath}/book/${wishlistBook.bookIsbn}">
                                                            <img src="resources/images/product/books-img/${wishlistBook.book.isbn}.jpg" height="120" alt="${wishlistBook.book.title}">
                                                        </a>
                                                    </td>
                                                    <td class="product-name">
                                                        <a href="${pageContext.request.contextPath}/book/${wishlistBook.bookIsbn}">${wishlistBook.book.title}</a>
                                                    </td>
                                                    <td class="product-price"><span class="amount">${wishlistBook.book.price} руб.</span></td>
                                                    <td class="product-stock-status">
                                                        <c:if test="${wishlistBook.book.stock.available == 0}">
                                                            <span class="wishlist-in-stock">Отсутствует</span>
                                                        </c:if>
                                                        <c:if test="${wishlistBook.book.stock.available > 0}">
                                                            <span class="wishlist-in-stock">В наличии</span>
                                                        </c:if>
                                                    </td>
                                                    <td class="product-add-to-cart">
                                                        <div>
                                                            <p class="isbn" hidden>${wishlistBook.bookIsbn}</p>'
                                                            <a class="cart" href="#" onclick="return false;"> Добавить в корзину</a>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </c:if>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- cart-main-area end -->

        <script src="resources/js/cart.js"></script>
    </jsp:body>
</page:template>