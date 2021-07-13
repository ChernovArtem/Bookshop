
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<page:template>
    <jsp:attribute name="title">${feedback.feedbackId} | Bookshop</jsp:attribute>
    <jsp:body>

        <!-- Start Bradcaump area -->
        <div class="ht__bradcaump__area_small bg-image--6">
        </div>
        <!-- End Bradcaump area -->
        <!-- Start Contact Area -->
        <section class="wn_contact_area bg--white pt--80">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 col-12">
                        <div class="contact-form-wrap">
                            <h2 class="contact__title">Сообщение №${feedback.feedbackId} от ${feedback.feedbackDate} (${feedback.feedbackStatus})</h2>
                            <form:form id="feedback"  method="POST" modelAttribute="feedback" action="${pageContext.request.contextPath}/updfeedback">
                                    <input type="hidden" name="feedbackId" id="feedbackId" readonly="" value="${feedback.feedbackId}">
                                    <input type="hidden" name="feedbackFName" id="feedbackFName" value="${feedback.feedbackFName}">
                                    <input type="hidden" name="feedbackLName" id="feedbackLName" value="${feedback.feedbackLName}">
                                    <input type="hidden" name="feedbackEmail" id="feedbackEmail" value="${feedback.feedbackEmail}">
                                    <input type="hidden" name="feedbackPhone" id="feedbackPhone" value="${feedback.feedbackPhone}">
                                    <input type="hidden" name="feedbackStatus" id="feedbackStatus" value="${feedback.feedbackStatus}">
                                    <input type="hidden" name="date" id="date" readonly="" value="${feedback.feedbackDate}">
                                <div class="single-contact-form">
                                    <label for="feedbackSubject">Тема</label>
                                    <input type="text" name="feedbackSubject" id="feedbackSubject" readonly="" value="${feedback.feedbackSubject}">
                                </div>
                                <div class="single-contact-form message">
                                    <label for="feedbackMessage">Сообщение</label>
                                    <textarea name="feedbackMessage" id="feedbackMessage" readonly="">${feedback.feedbackMessage}</textarea>
                                </div>
                            <div class="single-contact-form message">
                                <label for="feedbackComment">Комментарий сотруднка</label>
                                <textarea name="feedbackComment" id="feedbackComment">${feedback.feedbackComment}</textarea>
                            </div>

                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form:form>
                            <div class="single-contact-form space-between">
                                <c:if test="${feedback.feedbackStatus=='NEW'}">
                                    <div class="contact-btn">
                                        <button type="submit" form="feedback">Обновить</button>
                                    </div>
                                </c:if>
                                <div class="contact-btn">
                                    <button type="button" onclick="javascript:history.go(-1);">Вернуться</button>
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-12 md-mt-40 sm-mt-40">
                        <div class="wn__address">
                            <h2 class="contact__title">Данные клиента</h2>

                            <div class="wn__addres__wreapper">

                                <div class="single__address">
                                    <i class="icon-user-follow"></i>
                                    <div class="content">
                                        <span>имя:</span>
                                        <p>${feedback.feedbackFName}</p>
                                    </div>
                                </div>

                                <div class="single__address">
                                    <i class="icon-user-follow"></i>
                                    <div class="content">
                                        <span>фамилия:</span>
                                        <p>${feedback.feedbackLName}</p>
                                    </div>
                                </div>

                                <div class="single__address">
                                    <i class="icon-envelope icons"></i>
                                    <div class="content">
                                        <span>Email:</span>
                                        <p>${feedback.feedbackEmail}</p>
                                    </div>
                                </div>

                                <div class="single__address">
                                    <i class="icon-phone icons"></i>
                                    <div class="content">
                                        <span>Телефон:</span>
                                        <p>${feedback.feedbackPhone}</p>
                                    </div>
                                </div>

                            </div>
                         <br>
                        </div>
                        <div class="wn__address">
                            <h2 class="contact__title">Работа с сообщением</h2>

                            <div class="wn__addres__wreapper">
                                <div class="single__address">
                                    <i class="icon-user"></i>
                                    <div class="content">
                                        <span>имя пользователя:</span>
                                        <p>${user.username}</p>
                                    </div>
                                </div>
                                <div class="single__address">
                                    <i class="icon-clock"></i>
                                    <div class="content">
                                        <span>обновлено:</span>
                                        <p>${feedback.feedbackUpdDate}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="google__map mt--80">
                <div id="googleMap"></div>
            </div>
        </section>
        <!-- End Contact Area -->

        <!-- Google Map js -->

    </jsp:body>
</page:template>