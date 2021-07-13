<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>
    <jsp:attribute name="title">Регистрация | Bookshop</jsp:attribute>
    <jsp:body>
        <!-- Start Bradcaump area -->
        <div class="ht__bradcaump__area_small bg-image--6">
        </div>
        <!-- End Bradcaump area -->
        <!-- Start Registration Area -->
        <section class="my_account_area pt--30 pb--35 bg--white">
            <div class="container">
                <div class="row">
                    <div class="col-lg-3"></div>
                    <div class="col-lg-6 col-12 text-center">
                        <div class="my__account__wrapper">
                            <h3 class="account__title">Создание аккаунта</h3>
                            <form:form method="POST" modelAttribute="userForm" class="form-signin">
                                <div class="account__form">
                                    <div class="input__box">
                                        <label>Имя пользователя<span>* <p>должно начинаться с быквы, может содержать русские и латинсикие буквы, цифры, символы - и _</p></span></label>
                                        <%--<p class="text-left">Имя должно начинаться с буквы. Может содержать русские или латинские буквы, цифры,--%>
                                            <%--а так же символы: "-" тире и "_" нижнее подчеркивание</p>--%>
                                        <form:input type="text" path="username" class="form-control"/>
                                    </div>
                                    <div class="input__box">
                                        <label>Электронная почта<span>*</span></label>
                                        <form:input type="email" path="email" class="form-control" />
                                    </div>
                                    <div class="input__box">
                                        <label>Пароль<span>* <p>минимум 6 символов, допустимы символы: ., -, _, !, @, #, &, +, *</p></span></label>
                                        <%--<p class="text-left">В пароле могут использоваться строчные и прописные латинские буквы, цифры, а так же спецсимволы: "-_!@#&.+*"--%>
                                            <%--Минимум 6 символов</p>--%>
                                        <form:input type="password" path="password" class="form-control" />
                                    </div>
                                    <div class="input__box">
                                        <label>Подтвердите пароль<span>*</span></label>
                                        <form:input type="password" path="confirmPassword" class="form-control" />
                                    </div>
                                    <div class="form__btn">
                                        <!-- защита от CSRF атак -->
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                                        <form:button type="submit">Регистрация</form:button>
                                    </div>
                                    <span>${error}</span>
                                </div>
                            </form:form>
                        </div>
                    </div>
                    <div class="col-lg-3"></div>
                </div>
            </div>
        </section>
        <!-- End Registration Area -->
    </jsp:body>
</page:template>