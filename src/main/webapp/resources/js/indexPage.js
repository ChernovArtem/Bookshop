$(function GetAllBooksFromAPI() {
    let contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));

    $.ajax({
        type: 'GET',
        dataType: 'json',
        url: contextPath + '/book',
        success: function (data) {
            $.each(data, function (index, item) {
                $("#nav-all").append(divValue(item));
            });
        }

    });
});

$(function GetBookByGenreDetective() {
    let contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));

    $.ajax({
        type: 'GET',
        dataType: 'json',
        url: contextPath + '/book/genres/Детектив',
        success: function (data) {
            $.each(data, function (index, item) {
                $("#nav-detectives").append(divValue(item));
            });
        }
    });
});

$(function GetBookByGenreAutomotiveLiterature() {
    let contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));

    $.ajax({
        type: 'GET',
        dataType: 'json',
        url: contextPath + '/book/genres/Автомобильная литература',
        success: function (data) {
            $.each(data, function (index, item) {
                $("#nav-automotive-literature").append(divValue(item));
            });
        }
    });
});

$(function GetBookByGenreClassicalRussianLiterature () {
    let contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));

    $.ajax({
        type: 'GET',
        dataType: 'json',
        url: contextPath + '/book/genres/Классическая русская литература',
        success: function (data) {
            $.each(data, function (index, item) {
                $("#nav-сlassical-russian-literature").append(divValue(item));
            });
        }
    });
});

$(function GetBookByGenreComputersAndInternet () {
    let contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));

    $.ajax({
        type: 'GET',
        dataType: 'json',
        url: contextPath + '/book/genres/Компьютеры и интернет',
        success: function (data) {
            $.each(data, function (index, item) {
                $("#nav-computers-and-internet").append(divValue(item));
            });
        }
    });
});

$(function fetchGenres() {
    let contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));

    $.ajax({
        url: contextPath + '/genre',
        datatype: 'json',
        type: "get",
        contentType: "application/json",
        success: function (data) {
            $(".menu_genre").append('<li class="title">Категории</li>');

            $.each(data, function (index, item) {
                let menu_genre = '<li><a href="shop?genre=' + item[0] + '">' + item[0] + '</a></li>';
                $(".menu_genre").append(menu_genre);
                $(".menu_genre_mobil").append(menu_genre);
            });
        }
    });
});

function divValue(item) {
    let bookIsbn = item.isbn;

    return '' +
        '<div class="col-lg-3 col-md-4 col-sm-6 col-12">' +
        '<div class="product product__style--3"><div class="product__thumb">' +
        '<a class="first__img" <a href="book/' + bookIsbn + '">'+ '<img src="resources/images/product/books-img/' + bookIsbn + '.jpg"' + ' alt="' + item.title + '"></a>' +
        '<div class="product__content content--center content--center">' +
        '<h4 class="book-title"><a <a href="book/' + bookIsbn + '">'+ item.title + '</a></h4>' +
        '<ul class="prize d-flex"><li class="card-price">' + item.price + '</li>' +
        '</ul><div class="action"><div class="actions_inner"><p class="isbn" hidden>' + item.isbn + '</p><ul class="add_to_links">' +
        '<li><a class="cart" href="#" onclick="return false;"><i class="bi bi-shopping-bag4"></i></a></li>' +
        '<li><a class="wishlist" href="#" onclick="return false;"><i class="bi bi-heart-beat"></i></a></li>' +
        '</ul></div></div>' +
        '</div></div></div>';
}