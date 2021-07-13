<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<page:template>
    <jsp:attribute name="title">Обратная свзь | Bookshop</jsp:attribute>
    <jsp:body>
        <!-- Start Bradcaump area -->
        <div class="ht__bradcaump__area_small bg-image--6">
        </div>
        <!-- End Bradcaump area -->
        <!-- Start Faq Area -->

        <section class="wn__faq__area bg--white pt--80 pb--60">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="wn__accordeion__content">
                            <h2>МЕНЕДЖЕР, ПОМНИ!</h2>
                            <p>ДОВОЛЬНЫЙ КЛИЕНТ ПРИВОДИТ ДВУХ, А НЕДОВОЛЬНЫЙ УВОДИТ ДЕСЯТЕРЫХ!</p>
                        </div>
                        <!--Если показываются новые сообщения, то отображать кнопку перехода к старым-->
                        <c:if test="${feedbacks!=null}">

                        <div id="accordion" class="wn_accordion" role="tablist">
                            <div class="contact-btn">
                                <form action="${pageContext.request.contextPath}/oldfeedbacks">
                                    <input type="hidden" value="0" name="page" id="page">
                                    <button type="submit">Показать закрытые</button>
                                </form>
                            </div>
                            <br>
                        <c:forEach var="new_f" items="${feedbacks}" >
                            <div class="card">
                                <div class="acc-header" role="tab" id="${new_f.hashCode()}n">
                                    <h5>
                                        <a data-toggle="collapse" href="#${new_f.hashCode()}" role="button" aria-expanded="false" aria-controls="${new_f.hashCode()}">(${new_f.get(0).feedbackDate}) (${new_f.size()})</a>
                                    </h5>
                                </div>

                                <div id="${new_f.hashCode()}" class="collapse show" role="tabpanel" aria-labelledby="${new_f.hashCode()}n" data-parent="#accordion">

                                    <div class="card-body">
                                        <div class="table-content wnro__table table-responsive">
                                            <table>
                                                <tbody>
                                                <c:forEach var="newitem" items="${new_f}">
                                                <tr>
                                                    <td><span>${newitem.feedbackId}</span></td>
                                                    <td><span>${newitem.feedbackFName} ${newitem.feedbackLName}</span></td>
                                                    <td><span>${newitem.feedbackSubject}</span></td>
                                                    <td><form method="get" action="${pageContext.request.contextPath}/viewfeedback">
                                                        <div class="contact-btn">
                                                            <button type="submit">Подробности</button>
                                                        </div>
                                                        <input type="hidden" name="feedbackId" value="${newitem.feedbackId}"/>
                                                    </form></td>
                                                </tr>
                                                </c:forEach>

                                                </tbody>
                                            </table>
                                        </div>
                                    </div>

                                </div>

                            </div>
                        </c:forEach>
                        </div>
                        </c:if>
                        <!--Если отображаются старые сообщения, то отображать кнопку перехода к старым-->
                        <c:if test="${oldfeedbacks!=null}">
                            <form method="get" action="${pageContext.request.contextPath}/newfeedbacks">
                                <div class="contact-btn">
                                <button type="submit">Показать новые</button>
                                </div>
                            </form>
                            <br>
                            <div class="card-body">
                                <div class="table-content wnro__table table-responsive">
                                    <table>
                                        <thead>
                                        <tr class="title-top">
                                            <th class="product-name">Номер</th>
                                            <th class="product-name">Дата</th>
                                            <th class="product-name">Имя, Фамилия</th>
                                            <th class="product-name">Тема</th>
                                            <th>...</th>

                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="old" items="${oldfeedbacks.content}">
                                            <tr>
                                                <td><span>${old.feedbackId}</span></td>
                                                <td><span>${old.feedbackDate}</span></td>
                                                <td><span>${old.feedbackFName} ${old.feedbackLName}</span></td>
                                                <td><span>${old.feedbackSubject}</span></td>
                                                <td><form method="get" action="${pageContext.request.contextPath}/viewfeedback">
                                                    <div class="contact-btn">
                                                        <button type="submit">Подробности</button>
                                                    </div>
                                                    <input type="hidden" name="feedbackId" value="${old.feedbackId}"/>
                                                </form></td>
                                            </tr>
                                        </c:forEach>

                                        </tbody>
                                        <tfoot>
                                        <tr>
                                            <td>
                                        <c:if test="${oldfeedbacks.totalPages > 1}">
                                            <div class="page-navigator">
                                                <c:if test="${!oldfeedbacks.first}">
                                                    <a href="${pageContext.request.contextPath}/oldfeedbacks?page=${oldfeedbacks.previousOrFirstPageable().pageNumber}" class="nav-item">... << </a>
                                                </c:if>
                                                <a class="nav-item">  ${oldfeedbacks.number+1}  </a>
                                                <c:if test="${!oldfeedbacks.last}">
                                                    <a href="${pageContext.request.contextPath}/oldfeedbacks?page=${oldfeedbacks.nextOrLastPageable().pageNumber}" class="nav-item"> >> ...</a>
                                                </c:if>

                                            </div>
                                        </c:if>
                                            </td>
                                        </tr>
                                        </tfoot>
                                    </table>

                                </div>
                            </div>
                        </c:if>
                            </div>
                        </div>
                    </div>

        </section>

        <!-- End Faq Area -->
    </jsp:body>
</page:template>
