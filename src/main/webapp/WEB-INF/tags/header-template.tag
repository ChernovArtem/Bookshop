<!DOCTYPE html>
<%@tag description="Template Site tag" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER', 'ROLE_CONSUMER', 'ROLE_STOREKEEPER')" var="isLoginUser"/>
<security:authorize access="hasRole('ROLE_CONSUMER')" var="isConsumer"/>
<security:authorize access="hasRole('ROLE_ADMIN')" var="isAdmin"/>
<security:authorize access="hasRole('ROLE_STOREKEEPER')" var="isStorekeeper"/>
<security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_STOREKEEPER')" var="isNotConsumer"/>

<script>
    $(function fetchGenres() {
        let contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));

        $.ajax({
            url: contextPath + '/genre',
            datatype: 'json',
            type: "get",
            contentType: "application/json",
            success: function (data) {
                $(".menu_genre").append('<li class="title">Категории</li>');

                $.each(data, function (index, item) {
                    let menu_genre = '<li><a href="shop?genre=' + item[0] + '">' + item[0] + '</a></li>';
                    $(".menu_genre").append(menu_genre);
                    $(".menu_genre_mobil").append(menu_genre);
                });
            }
        });
    });

    $(getCountWishlistAndCart());

    function getCountWishlistAndCart() {
        let contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));

        $(".wishlist_count").empty();
        $(".shopcart_count").empty();

        let token = "${_csrf.token}";
        let header = "X-CSRF-TOKEN";

        $.ajax({
            url : contextPath + '/wishlistCount',
            datatype : 'json',
            type : "post",
            contentType : "application/json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success : function(data) {
                if (data === undefined) return;
                if (data[0] > 0) {
                    $(".wishlist_count").append('<span class="product_qun">' + data[0] + '</span>');
                }
                if (data[1] > 0) {
                    $(".shopcart_count").append('<span class="product_qun">' + data[1] + '</span>');
                }
            },
            error : function (request, status, error) {
                getToken();
            }
        });
    }
</script>

<!-- Header -->
<header id="wn__header" class="oth-page header__area header__absolute sticky__header">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-4 col-sm-4 col-7 col-lg-2">
                <div class="logo">
                    <a href="index">
                        <img src="resources/images/logo/logo.png" alt="">
                    </a>
                </div>
            </div>
            <div class="col-lg-8 d-none d-lg-block">
                <nav class="mainmenu__nav">
                    <ul class="meninmenu d-flex justify-content-start">
                        <li><a href="index">Главная</a></li>
                        <li class="drop"><a href="shop">Книги</a>
                            <div class="megamenu dropdown">
                                <ul class="item item01 menu_genre"></ul>
                            </div>
                        </li>
                        <li><a href="faq">Часто задаваемые вопросы</a></li>
                        <li><a href="contact">Контакты</a></li>
                    </ul>
                </nav>
            </div>
            <div class="col-md-8 col-sm-8 col-5 col-lg-2">
                <ul class="header__sidebar__right d-flex justify-content-end align-items-center">
                    <li class="shop_search"><a class="search__active" href="#"></a></li>
                    <c:if test="${not isNotConsumer}">
                        <li class="wishlist"><a href="wishlist" class="wishlist_count"></a></li>
                        <li class="shopcart"><a href="cart" class="shopcart_count"></a></li>
                    </c:if>
                    <li class="setting__bar__icon"><a class="setting__active" href="#"></a>
                        <div class="searchbar__content setting__block">
                            <div class="content-inner">
                                <div class="switcher-currency">
                                    <strong class="label switcher-label">
                                        <span>
                                            <c:if test="${not isLoginUser}">
                                                Аккаунт
                                            </c:if>
                                            <c:if test="${isLoginUser}">
                                                <security:authentication property="principal.username"/>
                                            </c:if>
                                        </span>
                                    </strong>
                                    <div class="switcher-options">
                                        <div class="switcher-currency-trigger">
                                            <div class="setting__menu">
                                                <c:if test="${isConsumer}">
                                                    <span><a href="wishlist">Избранное</a></span>
                                                    <span><a href="orders">Заказы</a></span>
                                                    <span><a href="account">Аккаунт</a></span>
                                                </c:if>
                                                <c:if test="${isAdmin}">
                                                    <span><a href="adminBooks">Книги</a></span>
                                                    <span><a href="adminUsers">Пользователи</a></span>
                                                    <span><a href="newfeedbacks">Отзывы</a></span>
                                                </c:if>
                                                <c:if test="${isStorekeeper}">
                                                    <span><a href="storekeeper">Кладовщик</a></span>
                                                </c:if>
                                                <c:if test="${not isLoginUser}">
                                                    <span><a href="login">Вход</a></span>
                                                    <span><a href="registration">Создание аккаунта</a></span>
                                                </c:if>
                                                <c:if test="${isLoginUser}">
                                                    <span>
                                                        <form id="logoutForm" method="post" action="${pageContext.request.contextPath}/logout">
                                                            <!-- защита от CSRF атак -->
                                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                                                            <a href="javascript:{}" onclick="this.closest('form').submit();return false;">Выход</a>
                                                        </form>
                                                    </span>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        <!-- Start Mobile Menu -->
        <div class="row d-none">
            <div class="col-lg-12 d-none">
                <nav class="mobilemenu__nav">
                    <ul class="meninmenu">
                        <li><a href="index">Главная</a></li>
                        <li><a href="shop">Книги</a>
                            <ul class="menu_genre_mobil"></ul>
                        </li>
                        <li><a href="faq">Часто задаваемые вопросы</a></li>
                        <li><a href="contact">Контакты</a></li>
                    </ul>
                </nav>
            </div>
        </div>
        <!-- End Mobile Menu -->
        <div class="mobile-menu d-block d-lg-none">
        </div>
        <!-- Mobile Menu -->
    </div>
</header>
<!-- //Header -->
<!-- Start Search Popup -->
<div class="box-search-content search_active block-bg close__top">
    <form id="search_mini_form" class="minisearch" action="${pageContext.request.contextPath}/shopSearch">
        <div class="field__search">
            <input type="text" name="keyword" placeholder="Искать во всем магазине...">
            <div class="action">
                <a href="javascript:{}" onclick="this.closest('form').submit();return false;">
                    <i class="zmdi zmdi-search"></i>
                </a>
            </div>
        </div>
    </form>
    <div class="close__wrap">
        <span>закрыть</span>
    </div>
</div>
<!-- End Search Popup -->
