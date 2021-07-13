<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<page:template>
    <jsp:attribute name="title">Новый администратор | Bookshop</jsp:attribute>
    <jsp:body>
        <!-- Start Bradcaump area -->
        <div class="ht__bradcaump__area_small bg-image--6">
        </div>
        <!-- End Bradcaump area -->
        <!-- Start Account Area -->
        <section class="wn__checkout__area section-padding--lg bg__white">
            <div class="container">
                <div class="row">
                    <div class="col-lg-3"></div>
                    <div class="col-lg-6 col-12">
                        <form:form action="newAdmin" method="post" modelAttribute="newAdmin">
                            <form:input path="id" hidden="true"/>

                            <div class="customer_details">
                                <h2>Новый администратор</h2>
                                <div class="customar__field">
                                    <div class="input_box">
                                        <label>Имя</label>
                                        <form:input type="text" path="username"/>
                                    </div>
                                    <div class="input_box">
                                        <label>E-mail</label>
                                        <form:input type="email" path="email"/>
                                    </div>
                                    <div class="input_box">
                                        <label>Пароль</label>
                                        <form:input type="password" path="password"/>
                                    </div>

                                    <div class="form__btn">
                                        <!-- защита от CSRF атак -->
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                                        <form:button type="submit" name="submit">Сохранить</form:button>
                                    </div>
                                </div>
                            </div>
                        </form:form>
                    </div>
                    <div class="col-lg-3"></div>
                </div>
            </div>
        </section>
        <!-- End Account Area -->
    </jsp:body>
</page:template>