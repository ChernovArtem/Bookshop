$(document).on("click", "a.wishlist", function() {
    let contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));

    let token = $('input[name="_csrf"]').val();
    let header = "X-CSRF-TOKEN";

    let ul = $(this).closest("div");
    let isbn = ul.find(".isbn").text();

    $.ajax({
        url : contextPath + '/wishlist',
        datatype : 'json',
        type : "put",
        data: JSON.stringify({
            bookIsbn: isbn
        }),
        contentType : "application/json",
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        },
        success : function(data) {
            getCountWishlistAndCart();
        }
    });
});
