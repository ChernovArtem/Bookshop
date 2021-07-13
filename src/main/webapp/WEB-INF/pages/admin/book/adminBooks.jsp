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
        <section class="section-padding--lg bg--white">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12" style="margin-bottom: 20px">
                        <div class="text-center">
                            <h3 class="store__title">Книги</h3>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="store__form col-lg-6">
                        <form method="get" action="${contextPath}/adminBooksSearch" style="display: inline;">
                            <input type="text" name="keyword" />
                            <input type="submit" value="Поиск" />
                        </form>
                    </div>
                    <div class="store__form col-lg-6 text-right">
                        <div class="form__btn">
                            <form action="${contextPath}/adminBookNew" method="get" style="display: inline;">
                                <button type="submit">Добавить книгу</button>
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
                                    <th>ISBN</th>
                                    <th>Название</th>
                                    <th>Авторы</th>
                                    <th>Тип книги</th>
                                    <th>Год публикации</th>
                                    <th>Страниц</th>
                                    <th>Стоимость</th>
                                    <th>Жанр</th>
                                    <th colspan="2">Действие</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${booksList}" var="book">
                                    <tr>
                                        <td>${book.isbn}</td>
                                        <td>${book.title}</td>
                                        <td>
                                            <c:set var="authors" value=""/>
                                            <c:forEach items="${book.authors}" var="author">
                                                <c:if test="${not empty authors}"><c:set var="authors" value="${authors}, "/></c:if>
                                                <c:set var="authors" value="${authors}${author.authorName}"/>
                                            </c:forEach>
                                            ${authors}
                                        </td>
                                        <td>${book.type}</td>
                                        <td>${book.publishingYear}</td>
                                        <td>${book.pages}</td>
                                        <td>${book.price}</td>
                                        <td>${book.genre.genre}</td>
                                        <td><a href="${contextPath}/adminBookEdit?isbn=${book.isbn}">Изменить</a></td>
                                        <td><a href="${contextPath}/adminBookDelete?isbn=${book.isbn}">Удалить</a></td>
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