$(function getSingleBook() {
    let contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
    let isbn = window.location.pathname.split("/").pop();

    $.ajax({
        type: 'GET',
        dataType: 'json',
        url: contextPath + '/book',
        data: {
            isbn: isbn
        },
        success: function (data) {
            $("#single_product").append(divSingleProduct(data))
        }
    });
});

$(function fetchGenres() {
    let contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));

    $.ajax({
        url: contextPath + '/genre',
        datatype: 'json',
        type: "get",
        contentType: "application/json",
        success: function (data) {
            $(".menu_genre").append('<li class="title">Категории</li>');

            $.each(data, function (index, item) {
                let menu_genre = '<li><a href="../shop?genre=' + item[0] + '">' + item[0] + '</a></li>';
                $(".menu_genre").append(menu_genre);
                $(".menu_genre_mobil").append(menu_genre);
            });
        }
    });
});

function divSingleProduct(item) {

    return '' +
        '<div class="col-lg-6 col-12">' +
        '<div class="wn__fotorama__wrapper">' +
        '<div class="fotorama wn__fotorama__action" data-nav="thumbs">' +
        '<a href="1.jpg"><img src="../resources/images/product/books-img/' + item.isbn + '.jpg"' + ' alt="' + item.title + '"></a>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '<div class="col-lg-6 col-12">' +
        '<div class="product__info__main">' +
        '<h1>' + item.title + '</h1>' +
        '<p>' + item.authorsStr + '</p>' +
        '<div class="product-info-stock-sku d-flex">' +
        '<p>Доступно на складе: <span>' + item.stock.available + 'шт.</span></p>' +
        '<p>ISBN: <span id="isbn">' + item.isbn + '</span></p>' +
        '</div>' +
        '<div class="price-box">' +
        '<span>' + item.price + ' руб.</span>' +
        '</div>' +
        '<div class="box-tocart d-flex">' +
        '<span>Qty</span>' +
        '<input id="qty" class="input-text qty" name="qty" min="1" value="1" title="Qty" typeBook="number">' +
        '<div class="addtocart__actions">' +
        '<button class="tocart" id="cartbtn" typeBook="submit" onclick="return false;" style="display: inline;">Добавить в корзину</button>' +
        '<div class="product-addto-links clearfix">' +
        '<p class="isbn" hidden>' + item.isbn + '</p>' +
        '<a class="wishlist" href="#" onclick="return false;"></a>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '<div class="product__overview">' +
        '<ul class="pro__attribute">' +
        '<li>• ' + item.type + '</li>' +
        '<li>• ' + item.publishingYear + ' год</li>' +
        '<li>• ' + item.pages + ' стр</li>' +
        '</ul>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>';
}