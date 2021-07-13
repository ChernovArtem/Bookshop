function sendJSON() {
    // с помощью jQuery обращаемся к элементам на странице по их именам
    let name = document.querySelector('#firstname');
    let lastname = document.querySelector('#lastname');
    var shopname = document.querySelector('#shopName');
    var orderId = document.querySelector('#orderId');
    var paymentSum = document.querySelector('#paymentSum');
    // а вот сюда мы поместим ответ от сервера
    let result = document.querySelector('.result');
    // создаём новый экземпляр запроса XHR
    let xhr = new XMLHttpRequest();
    // адрес, куда мы отправим нашу JSON-строку
    let url = "http://localhost:80/gateway/payment";
    // открываем соединение
    xhr.open("POST", url, true);
    // устанавливаем заголовок — выбираем тип контента, который отправится на сервер, в нашем случае мы явно пишем, что это JSON
    xhr.setRequestHeader("Content-Type", "application/json");
    // когда придёт ответ на наше обращение к серверу, мы его обработаем здесь
    xhr.onreadystatechange = function () {
        // если запрос принят и сервер ответил, что всё в порядке
        if (xhr.readyState === 4 && xhr.status === 200) {
            // выводим то, что ответил нам сервер — так мы убедимся, что данные он получил правильно
            result.innerHTML = this.responseText;
        }
    };
    // преобразуем наши данные JSON в строку
    let data = JSON.stringify({
        "shopName": shopname.value,
        "firstName": name.value,
        "lastName": lastname.value,
        "paymentSum": paymentSum.value,
        "orderId": orderId.value
    });
    // когда всё готово, отправляем JSON на сервер
    xhr.send(data);
}