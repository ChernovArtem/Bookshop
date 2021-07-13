//добавление в корзину по нажатию на кнопку в "Добавить в корзину" на странице подробностей товара
$(document).on("click", "#cartbtn", function() {
    let contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));

    let token = $('input[name="_csrf"]').val();
    let header = "X-CSRF-TOKEN";

    let ul = $(this).closest("body");
    let isbn = ul.find("#isbn").text();
    let qty=ul.find("#qty").val();

    $.ajax({
        url : contextPath + '/cart',
        datatype : 'json',
        type : "post",
        data: JSON.stringify({
            cartDetailBook: isbn,
            cartDetailQuantity: qty
        }),
        contentType : "application/json",
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        },
        success : function(data) {
            getCountWishlistAndCart();
            if(data<qty){
                alert("Выбранного товара недостаточно на складе. Доступно: "+ data);
            }
        }

    });
});

//Добавление в корзину с главной страницы и страницы shop(при сортировке списком)
$(document).on("click", "a.cart", function() {
    let contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));

    let token = $('input[name="_csrf"]').val();
    let header = "X-CSRF-TOKEN";

    let ul = $(this).closest("div");
    let isbn = ul.find(".isbn").text();

    $.ajax({
        url : contextPath + '/cart',
        datatype : 'json',
        type : "post",
        data: JSON.stringify({
            cartDetailBook: isbn,
            cartDetailQuantity: 1
        }),
        contentType : "application/json",
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        },
        success : function(data) {
            getCountWishlistAndCart();
            if(data<1){
                alert("Выбранного товара недостаточно на складе. Доступно: "+ data);
            }
        }

    });
});