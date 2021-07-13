<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<page:template>
    <jsp:attribute name="title">Редактирование клиента | Bookshop</jsp:attribute>
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
                        <form:form action="adminCustomerSave" method="post" modelAttribute="edit_customer">
                            <form:input path="id" hidden="true"/>

                            <div class="customer_details">
                                <h2>Редактирование данных покупателя</h2>
                                <div class="customar__field">
                                    <div class="margin_between">
                                        <div class="input_box space_between">
                                            <label>Имя</label>
                                            <form:input type="text" path="firstname"/>
                                        </div>
                                        <div class="input_box space_between">
                                            <label>Фамилия</label>
                                            <form:input type="text" path="lastname"/>
                                        </div>
                                    </div>
                                    <div class="margin_between">
                                        <div class="input_box space_between">
                                            <label>Эл. почта</label>
                                            <form:input type="email" path="email"/>
                                        </div>
                                        <div class="input_box space_between">
                                            <label>Телефон</label>
                                            <form:input type="text" path="phone"/>
                                        </div>
                                    </div>
                                    <div class="input_box">
                                        <label>Страна</label>
                                        <form:select class="select__option" path="country">
                                            <form:option value="NONE" label="Выберите страну"/>
                                            <form:option value="Россия" label="Россия"/>
                                            <form:option value="Беларусь" label="Беларусь"/>
                                            <form:option value="Казахстан" label="Казахстан"/>
                                            <form:option value="Гондурас" label="Гондурас"/>
                                            <form:option value="Экваториальная Гвинея" label="Экваториальная Гвинея"/>
                                            <form:option value="Вьетнам" label="Вьетнам"/>
                                        </form:select>
                                    </div>
                                    <div class="input_box">
                                        <label>Регион</label>
                                        <form:select class="select__option" path="state">
                                            <form:option value="NONE" label="Выберите регион"/>
                                            <form:option value="Алтайский край" label="Алтайский край"/>
                                            <form:option value="Амурская область" label="Амурская область"/>
                                            <form:option value="Архангельская область" label="Архангельская область"/>
                                            <form:option value="Астраханская область" label="Астраханская область"/>
                                            <form:option value="Белгородская область" label="Белгородская область"/>
                                            <form:option value="Брянская область" label="Брянская область"/>
                                            <form:option value="Владимирская область" label="Владимирская область"/>
                                            <form:option value="Волгоградская область" label="Волгоградская область"/>
                                            <form:option value="Вологодская область" label="Вологодская область"/>
                                            <form:option value="Воронежская область" label="Воронежская область"/>
                                            <form:option value="Еврейская автономная область" label="Еврейская автономная область"/>
                                            <form:option value="Забайкальский край" label="Забайкальский край"/>
                                            <form:option value="Ивановская область" label="Ивановская область"/>
                                            <form:option value="Иркутская область" label="Иркутская область"/>
                                            <form:option value="Кабардино-Балкарская Республика" label="Кабардино-Балкарская Республика"/>
                                            <form:option value="Калининградская область" label="Калининградская область"/>
                                            <form:option value="Калужская область" label="Калужская область"/>
                                            <form:option value="Камчатский край" label="Камчатский край"/>
                                            <form:option value="Карачаево-Черкесская Республика" label="Карачаево-Черкесская Республика"/>
                                            <form:option value="Кемеровская область" label="Кемеровская область"/>
                                            <form:option value="Кировская область" label="Кировская область"/>
                                            <form:option value="Костромская область" label="Костромская область"/>
                                            <form:option value="Краснодарский край" label="Краснодарский край"/>
                                            <form:option value="Красноярский край" label="Красноярский край"/>
                                            <form:option value="Курганская область" label="Курганская область"/>
                                            <form:option value="Курская область" label="Курская область"/>
                                            <form:option value="Ленинградская область" label="Ленинградская область"/>
                                            <form:option value="Липецкая область" label="Липецкая область"/>
                                            <form:option value="Магаданская область" label="Магаданская область"/>
                                            <form:option value="Московская область" label="Московская область"/>
                                            <form:option value="Мурманская область" label="Мурманская область"/>
                                            <form:option value="Ненецкий автономный округ" label="Ненецкий автономный округ"/>
                                            <form:option value="Нижегородская область" label="Нижегородская область"/>
                                            <form:option value="Новгородская область" label="Новгородская область"/>
                                            <form:option value="Новосибирская область" label="Новосибирская область"/>
                                            <form:option value="Омская область" label="Омская область"/>
                                            <form:option value="Оренбургская область" label="Оренбургская область"/>
                                            <form:option value="Орловская область" label="Орловская область"/>
                                            <form:option value="Пензенская область" label="Пензенская область"/>
                                            <form:option value="Пермский край" label="Пермский край"/>
                                            <form:option value="Приморский край" label="Приморский край"/>
                                            <form:option value="Псковская область" label="Псковская область"/>
                                            <form:option value="Республика Адыгея" label="Республика Адыгея"/>
                                            <form:option value="Республика Алтай" label="Республика Алтай"/>
                                            <form:option value="Республика Башкортостан" label="Республика Башкортостан"/>
                                            <form:option value="Республика Бурятия" label="Республика Бурятия"/>
                                            <form:option value="Республика Дагестан" label="Республика Дагестан"/>
                                            <form:option value="Республика Ингушетия" label="Республика Ингушетия"/>
                                            <form:option value="Республика Калмыкия" label="Республика Калмыкия"/>
                                            <form:option value="Республика Карелия" label="Республика Карелия"/>
                                            <form:option value="Республика Коми" label="Республика Коми"/>
                                            <form:option value="Республика Крым" label="Республика Крым"/>
                                            <form:option value="Республика Марий Эл" label="Республика Марий Эл"/>
                                            <form:option value="Республика Мордовия" label="Республика Мордовия"/>
                                            <form:option value="Республика Саха (Якутия)" label="Республика Саха (Якутия)"/>
                                            <form:option value="Республика Северная Осетия" label="Республика Северная Осетия"/>
                                            <form:option value="Республика Татарстан" label="Республика Татарстан"/>
                                            <form:option value="Республика Тыва" label="Республика Тыва"/>
                                            <form:option value="Республика Хакасия" label="Республика Хакасия"/>
                                            <form:option value="Ростовская область" label="Ростовская область"/>
                                            <form:option value="Рязанская область" label="Рязанская область"/>
                                            <form:option value="Самарская область" label="Самарская область"/>
                                            <form:option value="Саратовская область" label="Саратовская область"/>
                                            <form:option value="Сахалинская область" label="Сахалинская область"/>
                                            <form:option value="Свердловская область" label="Свердловская область"/>
                                            <form:option value="Смоленская область" label="Смоленская область"/>
                                            <form:option value="Ставропольский край" label="Ставропольский край"/>
                                            <form:option value="Тамбовская область" label="Тамбовская область"/>
                                            <form:option value="Тверская область" label="Тверская область"/>
                                            <form:option value="Томская область" label="Томская область"/>
                                            <form:option value="Тульская область" label="Тульская область"/>
                                            <form:option value="Тюменская область" label="Тюменская область"/>
                                            <form:option value="Удмуртская Республика" label="Удмуртская Республика"/>
                                            <form:option value="Ульяновская область" label="Ульяновская область"/>
                                            <form:option value="Хабаровский край" label="Хабаровский край"/>
                                            <form:option value="Ханты-Мансийский автономный округ" label="Ханты-Мансийский автономный округ"/>
                                            <form:option value="Челябинская область" label="Челябинская область"/>
                                            <form:option value="Чеченская Республика" label="Чеченская Республика"/>
                                            <form:option value="Чувашская Республика - Чувашия" label="Чувашская Республика - Чувашия"/>
                                            <form:option value="Чукотский автономный округ" label="Чукотский автономный округ"/>
                                            <form:option value="Ямало-Ненецкий автономный округ" label="Ямало-Ненецкий автономный округ"/>
                                            <form:option value="Ярославская область" label="Ярославская область"/>
                                        </form:select>
                                    </div>
                                    <div class="margin_between">
                                        <div class="input_box space_between">
                                            <label>Город</label>
                                            <form:input type="text" path="city"/>
                                        </div>
                                        <div class="input_box space_between">
                                            <label>Индекс</label>
                                            <form:input type="text" path="postal"/>
                                        </div>
                                    </div>
                                    <div class="input_box">
                                        <label>Адрес</label>
                                        <form:input type="text" path="street" placeholder="Улица"/>
                                    </div>
                                    <div class="input_box">
                                        <form:input type="text" path="address" placeholder="Дом, строение, квартира, подъезд, этаж"/>
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