<!DOCTYPE html>
<%@tag description="Template Site tag" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<security:authorize access="hasRole('ROLE_CONSUMER')" var="isConsumer"/>
<security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_STOREKEEPER')" var="isNotConsumer"/>

<!-- Footer Area -->
<footer id="wn__footer" class="footer__area bg__cat--8 brown--color">
    <div class="footer-static-top">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="footer__widget footer__menu">
                        <div class="ft__logo">
                            <a href="index">
                                <img src="resources/images/logo/3.png" alt="logo">
                            </a>
                            <p>Любить чтение — это обменивать часы скуки, неизбежные в жизни, на часы большого наслаждения</p>
                        </div>
                        <div class="footer__content">
                            <ul class="social__net social__net--2 d-flex justify-content-center">
                                <li><a href="#" onclick="return false;"><i class="bi bi-facebook"></i></a></li>
                                <li><a href="#" onclick="return false;"><i class="bi bi-google"></i></a></li>
                                <li><a href="#" onclick="return false;"><i class="bi bi-twitter"></i></a></li>
                                <li><a href="#" onclick="return false;"><i class="bi bi-linkedin"></i></a></li>
                                <li><a href="#" onclick="return false;"><i class="bi bi-youtube"></i></a></li>
                            </ul>
                            <ul class="mainmenu d-flex justify-content-center">
                                <li><a href="shop">Все книги</a></li>
                                <c:if test="${not isNotConsumer}">
                                    <li><a href="wishlist">Избранное</a></li>
                                </c:if>
                                <li><a href="contact">Контакты</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

    </div>
    <div class="copyright__wrapper">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-12">
                    <div class="copyright">
                        <div class="copy__right__inner text-left">
                            <p>Copyright <i class="fa fa-copyright"></i> Boighor. All Rights Reserved</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-12">
                    <div class="payment text-right">
                        <img src="resources/images/icons/payment.png" alt="" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>