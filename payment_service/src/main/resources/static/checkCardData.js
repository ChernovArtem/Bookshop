function checkCardAvailability() {
    var carNumber = document.getElementById("CardNumber").value;
    var carNumberLength = carNumber.length;
    var firstName = document.getElementById("firstname").value;
    var lastname = document.getElementById("lastname").value;
    var expCard = document.getElementById("exp").value;
    var cvvCard = document.getElementById("cvv").value;

    //&& luhnCheck(carNumber) === true - проверка валидности карты
    if (carNumberLength === 16 && firstName.length > 0
        && lastname.length > 0 && expCard.length > 0 && cvvCard.length >= 3) {
        document.getElementById("btn").disabled = false;
    }
}