<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<page:template>
    <jsp:attribute name="title">Пользователи | Bookshop</jsp:attribute>
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
                            <h3 class="store__title">Пользователи</h3>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12" style="margin-bottom: 20px">
                        <h4 class="text-center" style="font-size: 13px"><a href="${contextPath}/adminCustomer">Показать информацию по клиентам</a></h4>
                    </div>
                </div>
                <div class="row">
                    <div class="store__form col-lg-6">
                        <form method="get" action="${contextPath}/adminUsers" style="display: inline;">
                            <input type="submit" value="Сбросить" />
                        </form>
                    </div>
                    <div class="store__form col-lg-6 text-right">
                        <div class="form__btn">
                            <form action="${contextPath}/adminAdminNew" method="get" style="display: inline;">
                                <button type="submit">Добавить администратора</button>
                            </form>
                            <form action="${contextPath}/adminStorekeeperNew" method="get" style="display: inline;">
                                <button type="submit">Добавить кладовщика</button>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12 col-12">
                        <div class="table-content wnro__table table-responsive">
                            <table id="customTable">
                                <thead>
                                <tr class="title-top">
                                    <th>Имя</th>
                                    <th>Почта</th>
                                    <th>Роль</th>
                                    <th colspan="2">Действие</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${users_result}" var="user">
                                    <tr>
                                        <td>${user.username}</td>
                                        <td>${user.email}</td>
                                        <td>${user.roles.iterator().next().getName()}</td>
                                        <td><a href="${contextPath}/adminUsersEdit?id=${user.id}">Изменить</a></td>
                                        <td><a href="${contextPath}/adminUsersDelete?id=${user.id}">Удалить</a></td>
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