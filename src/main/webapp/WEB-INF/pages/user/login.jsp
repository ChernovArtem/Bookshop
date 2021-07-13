<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<page:template>
    <jsp:attribute name="title">Вход | Bookshop</jsp:attribute>
    <jsp:body>
        <!-- Start Bradcaump area -->
        <div class="ht__bradcaump__area_small bg-image--6">
        </div>
        <!-- End Bradcaump area -->
        <!-- Start Login Area -->
        <section class="my_account_area pt--30 pb--35 bg--white">
            <div class="container">
                <div class="row">
                    <div class="col-lg-3"></div>
                    <div class="col-lg-6 col-12 text-center">
                        <div class="my__account__wrapper">
                            <h3 class="account__title">Вход в аккаунт</h3>
                            <form method="POST" action="j_spring_security_check">
                                <div class="account__form">
                                    <div class="input__box">
                                        <label>Имя пользователя<span>*</span></label>
                                        <input name="username" type="text">
                                    </div>
                                    <div class="input__box">
                                        <label>Пароль<span>*</span></label>
                                        <input name="password" type="password">
                                    </div>
                                    <div class="form__btn">
                                        <!-- защита от CSRF атак -->
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                                        <button type="submit">Вход</button>
                                    </div>
                                    <span>${message}</span>
                                    <span>${error}</span>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="col-lg-3"></div>
                </div>
            </div>
        </section>
        <!-- End Login Area -->
    </jsp:body>
</page:template>