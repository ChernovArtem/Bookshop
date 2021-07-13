$(function GetPaymentStatusFromAPI() {
    let contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
    $.ajax({
        type: 'GET',
        dataType: 'json',
        url: contextPath,
        success: function (data) {
            $.each(data, function (index, item) {
                $("#nav-all").append(divValue(item));
            });
        }

    });
});

function divValue(item) {
    let paymentStatus = item.payment_status;

    return ''
}