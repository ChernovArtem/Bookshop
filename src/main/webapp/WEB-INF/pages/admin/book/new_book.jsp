<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<page:template>
    <jsp:attribute name="title">Новая книга | Bookshop</jsp:attribute>
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
                        <form:form action="adminBookSave" method="post" modelAttribute="newBook" enctype="multipart/form-data">

                            <div class="customer_details">
                                <h2>Новая книга</h2>
                                <div class="customar__field">
                                    <div class="input_box">
                                        <label>Картинка</label>
                                        <img id="blah" height="320" style="display: none;"/>
                                        <br>
                                        <input id="file-input" type="file" name="image" style="display: none;" accept=".jpg" onchange="loadFile(event)" />
                                        <button id="uploadButton" type="button">Загрузить картинку</button>

                                        <script>
                                            $(document).on("click", "#uploadButton", function() {
                                                $('#file-input').trigger('click');
                                            });

                                            var loadFile = function(event) {
                                                $('#blah').show();

                                                var blah = document.getElementById('blah');
                                                blah.src = URL.createObjectURL(event.target.files[0]);
                                                blah.onload = function() {
                                                    URL.revokeObjectURL(blah.src) // free memory
                                                }
                                            };
                                        </script>
                                    </div>
                                    <div class="input_box">
                                        <label>ISBN</label>
                                        <form:input type="text" path="isbn"/>
                                    </div>
                                    <div class="input_box">
                                        <label>Название</label>
                                        <form:input type="text" path="title"/>
                                    </div>
                                    <div class="input_box">
                                        <label>Авторы</label>
                                        <form:input type="text" path="authorsStr"/>
                                    </div>
                                    <div class="input_box">
                                        <label>Жанр</label>
                                        <form:input type="text" path="genre.genre"/>
                                    </div>

                                    <div class="margin_between">
                                        <div class="input_box space_between">
                                            <label>Тип книги</label>
                                            <form:input type="text" path="type"/>
                                        </div>
                                        <div class="input_box space_between">
                                            <label>Год публикации</label>
                                            <form:input type="number" path="publishingYear"/>
                                        </div>
                                    </div>
                                    <div class="margin_between">
                                        <div class="input_box space_between">
                                            <label>Кол-во страниц</label>
                                            <form:input type="number" path="pages"/>
                                        </div>
                                        <div class="input_box space_between">
                                            <label>Стоимость</label>
                                            <form:input type="number" path="price"/>
                                        </div>
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