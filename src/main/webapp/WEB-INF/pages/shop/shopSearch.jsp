<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<page:template>
    <jsp:attribute name="title">Книги | Bookshop</jsp:attribute>
    <jsp:body>
        <!-- End Search Popup -->
        <!-- Start Bradcaump area -->
        <div class="ht__bradcaump__area_small bg-image--6">
        </div>
        <!-- End Bradcaump area -->
        <!-- Start Error Area -->
        <section class="page-shop-sidebar left--sidebar bg--white section-padding--lg">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="shop__list__wrapper d-flex flex-wrap flex-md-nowrap justify-content-between">
                                    <div class="shop__list nav justify-content-center" role="tablist">
                                        <a class="nav-item nav-link active" data-toggle="tab" href="#nav-grid" role="tab"><i class="fa fa-th"></i></a>
                                        <a class="nav-item nav-link" data-toggle="tab" href="#nav-list" role="tab"><i class="fa fa-list"></i></a>
                                    </div>
                                    <p class="text-center">Отображение результатов 1–${booksList.size()} из ${booksList.size()}</p>
                                </div>
                            </div>
                        </div>
                        <div class="tab__container">
                            <div class="shop-grid tab-pane fade show active" id="nav-grid" role="tabpanel">
                                <div class="row">
                                    <c:forEach items="${booksList}" var="book">
                                        <div class="col-lg-4 col-md-4 col-sm-6 col-12">
                                            <div class="product">
                                                <div class="product__thumb">
                                                    <a class="first__img" href="book/${book.isbn}">
                                                        <img src="resources/images/product/books-img/${book.isbn}.jpg" alt="${book.title}">
                                                    </a>
                                                    <ul class="prize position__right__bottom d-flex">
                                                        <li>${book.price} ' руб.</li>
                                                    </ul>
                                                </div>
                                                <div class="product__content">
                                                    <h4><a href="book/${book.isbn}">${book.title}</a></h4>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="shop-grid tab-pane fade" id="nav-list" role="tabpanel">
                                <div class="list__view__wrapper">
                                    <c:forEach items="${booksList}" var="book">
                                        <div class="list__view">
                                            <div class="thumb">
                                                <a class="first__img" href="book/${book.isbn}">
                                                <img src="resources/images/product/books-img/${book.isbn}.jpg" alt="${book.title}"></a>
                                            </div>
                                            <div class="content">
                                                <h2><a href="book/' + item.isbn + '">${book.title}</a></h2>
                                                <ul class="prize__box">
                                                    <li>${book.price} руб.</li>
                                                </ul>

                                                <p>${book.type}, ${book.publishingYear} год, ${book.pages} стр<br>${book.isbn}</p>
                                                <ul class="cart__action d-flex"><p class="isbn" hidden>${book.isbn}</p>
                                                    <li class="cart"><a class="cart" href="#" onclick="return false;">Добавить в корзину</a></li>
                                                    <li class="wishlist"><a class="wishlist" href="#" onclick="return false;"></a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </jsp:body>
</page:template>