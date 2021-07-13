$(function () {
    buildMenu();
});

function getToken() {
    let contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
    $.ajax({
        url : contextPath + '/get_token',
        datatype : 'json',
        type : "get",
        contentType : "application/json",
        success : function(data) {
            $('input[name="_csrf"]').val(data);
            getCountWishlistAndCart();
            buildMenu();
        }
    });
}

function getCountWishlistAndCart() {
    let contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));

    $(".wishlist_count").empty();
    $(".shopcart_count").empty();

    let token = $('input[name="_csrf"]').val();
    let header = "X-CSRF-TOKEN";

    $.ajax({
        url : contextPath + '/wishlistCount',
        datatype : 'json',
        type : "post",
        contentType : "application/json",
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        },
        success : function(data) {
            if (data === undefined) return;
            if (data[0] > 0) {
                $(".wishlist_count").append('<span class="product_qun">' + data[0] + '</span>');
            }
            if (data[1] > 0) {
                $(".shopcart_count").append('<span class="product_qun">' + data[1] + '</span>');
            }
        },
        error : function (request, status, error) {
            getToken();
        }
    });
}

function buildMenu() {
    let contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));

    $('.box-search-content').empty();
    let search = '<form id="search_mini_form" class="minisearch" action="' + contextPath + '/shopSearch">' +
        '<div class="field__search">' +
        '<input type="text" name="keyword" placeholder="Искать во всем магазине...">' +
        '<div class="action">' +
        '<a href="javascript:{}" onclick="this.closest(\'form\').submit();return false;">' +
        '<i class="zmdi zmdi-search"></i>' +
        '</a></div></div></form>' +
        '<div class="close__wrap">' +
        '<span>закрыть</span></div>';
    $(".box-search-content").append(search);


    let token = $('input[name="_csrf"]').val();
    let header = "X-CSRF-TOKEN";

    $.ajax({
        url : contextPath + '/current_auth_user',
        datatype : 'json',
        type : "post",
        contentType : "application/json",
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        },
        success : function(data) {

            if (data != undefined && (data.roles.some(elem => elem.name === 'ROLE_STOREKEEPER')
                || data.roles.some(elem => elem.name === 'ROLE_ADMIN'))) {
                $(".wishlist").hide();
                $(".wishlist_foot").hide();
                $(".shopcart").hide();
            }

            $('.searchbar__content').empty();

            let menu = '<div class="content-inner">' +
            '<div class="switcher-currency">' +
            '<strong class="label switcher-label"><span>';

            if (data == undefined) {
                menu = menu + 'Аккаунт';
            } else if (data.roles.some(elem => elem.name === 'ROLE_CONSUMER')
                || data.roles.some(elem => elem.name === 'ROLE_STOREKEEPER')
                || data.roles.some(elem => elem.name === 'ROLE_ADMIN')) {
                menu = menu + data.username;
            }

            menu = menu + '</span></strong>' +
                '<div class="switcher-options">' +
                '<div class="switcher-currency-trigger">' +
                '<div class="setting__menu">';

            if (data == undefined) {
                menu = menu + '<span><a href="login">Вход</a></span>' +
                    '<span><a href="registration">Создание аккаунта</a></span>';
            } else if (data.roles.some(elem => elem.name === 'ROLE_CONSUMER')) {
                menu = menu + '<span><a href="wishlist">Избранное</a></span>' +
                    '<span><a href="orders">Заказы</a></span>' +
                    '<span><a href="account">Аккаунт</a></span>';
            } else if (data.roles.some(elem => elem.name === 'ROLE_ADMIN')) {
                menu = menu + '<span><a href="adminBooks">Книги</a></span>' +
                    '<span><a href="adminUsers">Пользователи</a></span>' +
                    '<span><a href="newfeedbacks">Отзывы</a></span>';
            } else if (data.roles.some(elem => elem.name === 'ROLE_STOREKEEPER')) {
                menu = menu + '<span><a href="storekeeper">Кладовщик</a></span>';
            }


            if (data != undefined && (data.roles.some(elem => elem.name === 'ROLE_CONSUMER')
                || data.roles.some(elem => elem.name === 'ROLE_STOREKEEPER')
                || data.roles.some(elem => elem.name === 'ROLE_ADMIN'))) {
                menu = menu + '<span>' +
                    '<form id="logoutForm" method="post" action="' + contextPath + '/logout">' +
                    '<input type="hidden" name="_csrf" value="' + token + '"/>' +
                    '<a href="javascript:{}" onclick="this.closest(\'form\').submit();return false;">Выход</a>' +
                    '</form></span>';
            }

            menu = menu + '</div></div></div></div></div>';
            $(".searchbar__content").append(menu);
        },
        error : function (request, status, error) {
            getToken();
        }
    });
}

