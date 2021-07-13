<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<page:template>
    <jsp:attribute name="title">Связаться | Bookshop</jsp:attribute>
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
                            <h2 class="contact__title">Связаться с нами</h2>
                            <c:if test="${feedback.feedbackId==null}">
                            <p>Оставьте Ваше сообщение и контактные данные. Поля, помеченные символом *, обязательны для заполнения</p>
                            <form:form method="POST" modelAttribute="feedback" action="${pageContext.request.contextPath}/contact">
                                <div class="single-contact-form space-between">
                                    <label for="feedbackFNAme"></label>
                                    <input type="text" name="feedbackFName" required="required" id="feedbackFNAme" placeholder="Имя*">
                                    <label for="feedbackLName"></label>
                                    <input type="text" name="feedbackLName" required="required" id="feedbackLName" placeholder="Фамилия*">
                                </div>
                                <div class="single-contact-form space-between">
                                    <label for="feedbackEmail"></label>
                                    <input type="email" name="feedbackEmail" required="required" id="feedbackEmail" placeholder="Email*">
                                    <label for="feedbackPhone"></label>
                                    <input type="text" pattern="[0-9]{11,11}" required="required" name="feedbackPhone" id="feedbackPhone" placeholder="Телефон*">
                                </div>
                                <div class="single-contact-form">
                                    <label for="feedbackSubject"></label>
                                    <input type="text" name="feedbackSubject" required="required" id="feedbackSubject" placeholder="Тема*">
                                </div>
                                <div class="single-contact-form message">
                                    <label for="feedbackMessage"></label>
                                    <textarea name="feedbackMessage" id="feedbackMessage" required="required" placeholder="Напишите Ваше сообщение здесь.."></textarea>
                                </div>
                                <div class="contact-btn">
                                    <button type="submit">Отправить</button>
                                </div>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form:form>
                            </c:if>
                            <c:if test="${feedback.feedbackId!=null}">
                                <form method="get" action="${pageContext.request.contextPath}/index">
                                <div class="single-contact-form">
                                    <h5><label>Мы получили Ваше сообщение и обязательно ответим Вам!</label></h5>
                                </div>
                                <div class="contact-btn">
                                    <button type="submit">Ок</button>
                                </div>
                                </form>
                            </c:if>
                        </div>
                        <div class="form-output">
                            <p class="form-messege">
                        </div>
                    </div>
                    <div class="col-lg-4 col-12 md-mt-40 sm-mt-40">
                        <div class="wn__address">
                            <h2 class="contact__title">Контактные данные</h2>
                            <p>Работаем круглосуточно </p>
                            <div class="wn__addres__wreapper">

                                <div class="single__address">
                                    <i class="icon-location-pin icons"></i>
                                    <div class="content">
                                        <span>адрес:</span>
                                        <p> Университетская ул., 1, Иннополис, Респ. Татарстан, 420500</p>
                                    </div>
                                </div>

                                <div class="single__address">
                                    <i class="icon-phone icons"></i>
                                    <div class="content">
                                        <span>Телефон:</span>
                                        <p>716-298-1822</p>
                                    </div>
                                </div>

                                <div class="single__address">
                                    <i class="icon-envelope icons"></i>
                                    <div class="content">
                                        <span>Адрес электронной почты:</span>
                                        <p>university@innopolis.ru</p>
                                    </div>
                                </div>

                                <div class="single__address">
                                    <i class="icon-globe icons"></i>
                                    <div class="content">
                                        <span>Сайт:</span>
                                        <p>online-shop.ru</p>
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
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBmGmeot5jcjdaJTvfCmQPfzeoG_pABeWo"></script>
        <script>
            // When the window has finished loading create our google map below
            google.maps.event.addDomListener(window, 'load', init);

            function init() {
                // Basic options for a simple Google Map
                // For more options see: https://developers.google.com/maps/documentation/javascript/reference#MapOptions
                var mapOptions = {
                    // How zoomed in you want the map to start at (always required)
                    zoom: 12,

                    scrollwheel: false,

                    // The latitude and longitude to center the map (always required)
                    center: new google.maps.LatLng(55.7470397, 48.7326826),

                    // How you would like to style the map.
                    // This is where you would paste any style found on Snazzy Maps.
                    styles:
                        [

                            {
                                "featureType": "administrative",
                                "elementType": "labels.text.fill",
                                "stylers": [
                                    {
                                        "color": "#444444"
                                    }
                                ]
                            },
                            {
                                "featureType": "landscape",
                                "elementType": "all",
                                "stylers": [
                                    {
                                        "color": "#f2f2f2"
                                    }
                                ]
                            },
                            {
                                "featureType": "poi",
                                "elementType": "all",
                                "stylers": [
                                    {
                                        "visibility": "off"
                                    }
                                ]
                            },
                            {
                                "featureType": "road",
                                "elementType": "all",
                                "stylers": [
                                    {
                                        "saturation": -100
                                    },
                                    {
                                        "lightness": 45
                                    }
                                ]
                            },
                            {
                                "featureType": "road.highway",
                                "elementType": "all",
                                "stylers": [
                                    {
                                        "visibility": "simplified"
                                    }
                                ]
                            },
                            {
                                "featureType": "road.arterial",
                                "elementType": "labels.icon",
                                "stylers": [
                                    {
                                        "visibility": "off"
                                    }
                                ]
                            },
                            {
                                "featureType": "transit",
                                "elementType": "all",
                                "stylers": [
                                    {
                                        "visibility": "off"
                                    }
                                ]
                            },
                            {
                                "featureType": "transit.station.bus",
                                "elementType": "labels.icon",
                                "stylers": [
                                    {
                                        "saturation": "-16"
                                    }
                                ]
                            },
                            {
                                "featureType": "water",
                                "elementType": "all",
                                "stylers": [
                                    {
                                        "color": "#04b7ff"
                                    },
                                    {
                                        "visibility": "on"
                                    }
                                ]
                            }
                        ]
                };

                // Get the HTML DOM element that will contain your map
                // We are using a div with id="map" seen below in the <body>
                var mapElement = document.getElementById('googleMap');

                // Create the Google Map using our element and options defined above
                var map = new google.maps.Map(mapElement, mapOptions);

                // Let's also add a marker while we're at it
                var marker = new google.maps.Marker({
                    position: new google.maps.LatLng(55.7470397, 48.7326826),
                    map: map,
                    title: 'Dcare!',
                    icon: 'resources/images/icons/map.png',
                    animation:google.maps.Animation.BOUNCE

                });
            }
        </script>
    </jsp:body>
</page:template>