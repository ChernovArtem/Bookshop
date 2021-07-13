

$(function () {
    fetchGenres();
    fetchBooks();
});

function fetchGenres() {
    let contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));

    $.ajax({
        url: contextPath + '/genre',
        datatype: 'json',
        type: "get",
        contentType: "application/json",
        success: function (data) {
            $('.menu_genre').empty();
            $('.menu_genre_mobil').empty();
            $('.genres_filter').empty();

            $(".menu_genre").append('<li class="title">Категории</li>');
            $(".genres_filter").append('<li><a href="shop">Все Жанры<span>(' + data[0][2] + ')</span></a></li>');

            $.each(data, function (index, item) {
                let menu_genre = '<li><a href="shop?genre=' + item[0] + '">' + item[0] + '</a></li>';
                $(".menu_genre").append(menu_genre);
                $(".menu_genre_mobil").append(menu_genre);

                $(".genres_filter").append('<li><a href="shop?genre=' + item[0] + '">' + item[0] + ' <span>(' + item[1] + ')</span></a></li>');
            });
        }
    });
}

function fetchBooks(page, size, sort) {
    let contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));

    let url = new URL(window.location.href);
    let genreParam = url.searchParams.get("genre");
    let genre = (typeof genreParam !== 'undefined') ?  genreParam : null;

    let pageNumber = (typeof page !== 'undefined') ?  page : 0;
    let sizeNumber = (typeof size !== 'undefined') ?  size : 6;
    let sortNumber = (typeof sort !== 'undefined') ?  sort : 0;

    $.ajax({
        url : contextPath + '/book',
        datatype : 'json',
        type : "get",
        data: {
            genre: genre,
            page: pageNumber,
            size: sizeNumber,
            sortType: sortNumber
        },
        contentType : "application/json",
        success : function(data) {
            $('.shop-grid-book').empty();
            $('.shop-list-book').empty();

            $('.showing_result').empty();
            let startResult = (data.number * sizeNumber) + 1;
            let finishResult = startResult + (sizeNumber - 1);
            if (finishResult > data.totalElements) {
                finishResult = data.totalElements;
            }
            $(".showing_result").append('Отображение результатов ' + startResult + '–' + finishResult + ' из ' + data.totalElements);

            $.each(data.content, function (index, item) {

                let book = '<div class="col-lg-4 col-md-4 col-sm-6 col-12">' +
                    '<div class="product"><div class="product__thumb">' +
                    '<a class="first__img" href="book/' + item.isbn + '">' +
                    '<img src="resources/images/product/books-img/' + item.isbn + '.jpg" alt="' + item.title + '"></a>';
                if(false) {
                    book = book + '<div class="new__box"><span class="new-label">Hot</span></div>';
                }
                book = book + '<ul class="prize position__right__bottom d-flex">' +
                    '<li>' + item.price + ' руб.</li>';
                if(false) {
                    book = book + '<li class="old_prize">$55.00</li>';
                }
                book = book + '</ul></div><div class="product__content">' +
                    '<h4><a href="book/' + item.isbn + '">' + item.title + '</a></h4>' +
                    '</div></div></div>';
                $(".shop-grid-book").append(book);

                book = '<div class="list__view mt--40"> <div class="thumb">' +
                    '<a class="first__img" href="book/' + item.isbn + '">' +
                    '<img src="resources/images/product/books-img/' + item.isbn + '.jpg" alt="' + item.title + '"></a>' +
                    '</div><div class="content">' +
                    '<h2><a href="book/' + item.isbn + '">' + item.title + '</a></h2>' +
                    '<ul class="prize__box"><li>' + item.price + ' руб.</li>';
                if(false) {
                    book = book + '<li class="old__prize">$220.00</li>';
                }
                book = book + '</ul>' +
                    '<p>' + item.authorsStr + '<br>' + item.type + ', ' + item.publishingYear + ' год, ' +
                    item.pages + ' стр<br>' + item.isbn + '</p>' +
                    '<ul class="cart__action d-flex"><p class="isbn" hidden>' + item.isbn + '</p>' +
                    '<li class="cart"><a class="cart" href="#" onclick="return false;">Добавить в корзину</a></li>' +
                    '<li class="wishlist"><a class="wishlist" href="#" onclick="return false;"></a></li>' +
                    '</ul></div></div>';
                $(".shop-list-book").append(book);
            });

            if ($('ul.wn__pagination li').length - 2 != data.totalPages){
                $('ul.wn__pagination').empty();
                buildPagination(data.totalPages);
            }
        }
    });
}

function buildPagination(totalPages) {

    let pageIndex = '<li><a href="#" onclick="return false;"><i class="zmdi zmdi-chevron-left"></i></a></li>';
    $("ul.wn__pagination").append(pageIndex);

    for(let i = 1; i <= totalPages; i++){
        if(i == 1){
            pageIndex = '<li class="active"><a href="#" onclick="return false;">'
                + i + "</a></li>"
        } else {
            pageIndex = '<li><a href="#" onclick="return false;">'
                + i + '</a></li>'
        }
        $("ul.wn__pagination").append(pageIndex);
    }

    pageIndex = '<li><a href="#" onclick="return false;"><i class="zmdi zmdi-chevron-right"></i></a></li>';
    $("ul.wn__pagination").append(pageIndex);
}

$(document).on("click", "ul.wn__pagination li a", function() {
    let sort = $("#sort").val();

    let child = $(this).find("i");
    if(child.hasClass('zmdi-chevron-right')){
        let activeValue = parseInt($("ul.wn__pagination li.active").text());
        let totalPages = $("ul.wn__pagination li").length - 2; // -2 beacause 1 for Previous and 1 for Next
        if(activeValue < totalPages){
            let currentActive = $("li.active");
            fetchBooks(activeValue, 6, sort);
            $("li.active").removeClass("active");
            currentActive.next().addClass("active");
        }
    } else if(child.hasClass('zmdi-chevron-left')){
        let activeValue = parseInt($("ul.wn__pagination li.active").text());
        if(activeValue > 1){
            fetchBooks(activeValue-2, 6, sort);
            let currentActive = $("li.active");
            currentActive.removeClass("active");
            currentActive.prev().addClass("active");
        }
    } else {
        let val = $(this).text();
        fetchBooks(parseInt(val) - 1, 6, sort);
        $("li.active").removeClass("active");
        $(this).parent().addClass("active");
    }
});

$("#sort").change(function(){
    let activeValue = parseInt($("ul.wn__pagination li.active").text());
    fetchBooks(activeValue - 1, 6, $(this).val());
});