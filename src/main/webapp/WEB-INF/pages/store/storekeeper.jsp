<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<page:template>
    <jsp:attribute name="title">Кладовщик | Bookshop</jsp:attribute>
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
                            <h3 class="store__title">Кладовщик</h3>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="store__form col-lg-6">
                        <div class="form__btn">
                            <form action="export-csv-file" method="get" style="display: inline;">
                                <!-- защита от CSRF атак -->
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                                <button type="submit">Экспортировать в csv</button>
                            </form>
                            <form action="upload-csv-file" method="post" enctype="multipart/form-data" style="display: inline;">
                                <!-- защита от CSRF атак -->
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                                <input id="file-input" type="file" name="file" style="display: none;" accept=".csv" />
                                <button id="uploadButton" type="button">Загрузить csv</button>

                                <script>
                                    $(document).on("click", "#uploadButton", function() {
                                        $('#file-input').trigger('click');
                                    });

                                    $(document).on("change", "#file-input", function() {
                                        $(this).closest("form").submit();
                                    });
                                </script>
                            </form>
                        </div>
                    </div>
                    <div class="store__form col-lg-6 text-right">
                        <div class="size">
                            <strong>Показать:   </strong>
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
                                        <th>Название книги</th>
                                        <th>Было продано</th>
                                        <th>На складе</th>
                                        <th>В резерве</th>
                                        <th>В продаже</th>
                                        <th>Действия</th>
                                    </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>

                            <ul class="wn__pagination" style="margin:20px 0"></ul>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <script>
            $(function () {
                var token = $('input[name="${_csrf.parameterName}"]').val();
                var header = "X-CSRF-TOKEN";
                $(document).ajaxSend(function(e, xhr, options) {
                    xhr.setRequestHeader(header, token);
                });

                fetchCustomers();
                buildSizePagination();

                function fetchCustomers(page, size) {

                    let pageNumber = (typeof page !== 'undefined') ?  page : 0;
                    let sizeNumber = (typeof size !== 'undefined') ?  size : 5;

                    $.ajax({
                        url : '${contextPath}/stock',
                        datatype : 'json',
                        type : "get",
                        data: {
                            page: pageNumber,
                            size: sizeNumber
                        },
                        contentType : "application/json",
                        success : function(data) {
                            $('#customTable tbody').empty();

                            $.each(data.content, function (index, item) {
                                $('#customTable tbody').append("<tr>" +
                                    "<td class='isbn'>" + item.isbn + "</td>" +
                                    "<td>" + item.nameBook + "</td>" +
                                    "<td><label class='sold_read'>" + item.sold + "</label>" +
                                    "<input class='sold_edit' style='display: none;' value='" + item.sold + "' type='number' /></td>" +
                                    "<td><label class='total_read'>" + item.total + "</label>" +
                                    "<input class='total_edit' style='display: none;' value='" + item.total + "' type='number' /></td>" +
                                    "<td><label class='reserve_read'>" + item.reserve + "</label>" +
                                    "<input class='reserve_edit' style='display: none;' value='" + item.reserve + "' type='number' /></td>" +
                                    "<td><label class='available_read'>" + item.available + "</label>" +
                                    "<input class='available_edit' style='display: none;' value='" + item.available + "' type='number' /></td>" +
                                    "<td class='store__form'><div class='form__btn_row'><input type='hidden' name='${_csrf.parameterName}' value='${_csrf.token}'/>" +
                                    "<button type='button' class='editButton'>Редактировать</button>" +
                                    "<button type='button' class='saveButton' style='display: none;'>Сохранить</button></div></td>" +
                                    "</tr>");
                            });

                            if ($('ul.wn__pagination li').length - 2 != data.totalPages){
                                $('ul.wn__pagination').empty();
                                buildPagination(data.totalPages);
                            }
                        }
                    });
                }

                function buildSizePagination() {

                    let pageIndex = '<a href="#" onclick="return false;" class="size_active">5</a> ';
                    $(".size").append(pageIndex);

                    pageIndex = '<a href="#" onclick="return false;">20</a> ';
                    $(".size").append(pageIndex);

                    pageIndex = '<a href="#" onclick="return false;">30</a>';
                    $(".size").append(pageIndex);
                }

                $(document).on("click", ".size a", function() {
                    let val = $(this).text();

                    $("a.size_active").removeClass("size_active");
                    $(this).addClass("size_active");

                    fetchCustomers(0, val);
                });

                function buildPagination(totalPages) {

                    let pageIndex = '<li><a href="#" onclick="return false;"><i class="zmdi zmdi-chevron-left"></i></a></li>';
                    $("ul.wn__pagination").append(pageIndex);

                    for(let i = 1; i <= totalPages; i++){
                        if(i == 1){
                            pageIndex = '<li class="active"><a href="#" onclick="return false;">'
                                + i + "</a></li>"
                        } else {
                            pageIndex = '<li><a chref="#" onclick="return false;">'
                                + i + '</a></li>'
                        }
                        $("ul.wn__pagination").append(pageIndex);
                    }

                    pageIndex = '<li><a href="#" onclick="return false;"><i class="zmdi zmdi-chevron-right"></i></a></li>';
                    $("ul.wn__pagination").append(pageIndex);
                }

                $(document).on("click", "ul.wn__pagination li a", function() {
                    let activeSize = parseInt($(".size a.size_active").text());

                    let child = $(this).find("i");
                    if(child.hasClass('zmdi-chevron-right')){
                        let activeValue = parseInt($("ul.wn__pagination li.active").text());
                        let totalPages = $("ul.wn__pagination li").length - 2; // -2 beacause 1 for Previous and 1 for Next
                        if(activeValue < totalPages){

                            let currentActive = $("li.active");
                            fetchCustomers(activeValue, activeSize);
                            $("li.active").removeClass("active");
                            currentActive.next().addClass("active");
                        }
                    } else if(child.hasClass('zmdi-chevron-left')){
                        let activeValue = parseInt($("ul.wn__pagination li.active").text());
                        if(activeValue > 1){
                            fetchCustomers(activeValue-2, activeSize);
                            let currentActive = $("li.active");
                            currentActive.removeClass("active");
                            currentActive.prev().addClass("active");
                        }
                    } else {
                        let val = $(this).text();
                        fetchCustomers(parseInt(val) - 1, activeSize);
                        $("li.active").removeClass("active");
                        $(this).parent().addClass("active");
                    }
                });

                $(document).on("click", "button.editButton", function() {
                    let $row = $(this).closest("tr");

                    $row.find(".sold_read").hide();
                    $row.find(".total_read").hide();
                    $row.find(".reserve_read").hide();
                    $row.find(".available_read").hide();
                    $(this).hide();

                    $row.find(".sold_edit").show();
                    $row.find(".total_edit").show();
                    $row.find(".reserve_edit").show();
                    $row.find(".available_edit").show();
                    $row.find(".saveButton").show();
                });

                $(document).on("click", "button.saveButton", function() {
                    let $row = $(this).closest("tr");
                    let isbn = $row.find(".isbn").text();

                    let sold = parseInt($row.find(".sold_edit").val());
                    let total = parseInt($row.find(".total_edit").val());
                    let reserve = parseInt($row.find(".reserve_edit").val());
                    let available = parseInt($row.find(".available_edit").val());

                    $.ajax({
                        url : '${contextPath}/stock',
                        datatype : 'json',
                        type : "put",
                        data: JSON.stringify({
                            isbn: isbn,
                            sold: sold,
                            total: total,
                            reserve: reserve,
                            available: available
                        }),
                        contentType : "application/json",
                        success : function(data) {
                            location.reload();
                        }
                    });
                });
            });
        </script>
    </jsp:body>
</page:template>