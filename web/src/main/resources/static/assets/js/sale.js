let sale = {
    amount: null,
    vendor: null,
    phoneNumber: null,
    responseCode: null,
    responseMessage: null,
    approvalCode: null
};

$(document).ready(function () {

    $("#phoneNumber").change(function () {
        sale.phoneNumber = $(this).val();
    });

    $("#vendor").change(function () {
        sale.vendor = $(this).val();
    });

    $("button[id^=\"amount-\"]").click(function () {
        sale.amount = parseInt(($(this).val()));
    });

    $("#submitButton").click(function () {

        if (validateForm() && confirm(
            `Teléfono: ${sale.phoneNumber}\nMonto: ${sale.amount}\nProveedor: ${sale.vendor}`)) {
            $.post("/sale", sale, successSale).fail(failedSale);
        }
    });

    $("#resetButton").click(function () {
        sale = {
            amount: null,
            vendor: null,
            phoneNumber: null,
            responseCode: null,
            responseMessage: null,
            approvalCode: null
        };
        $("#phoneNumber").val("");
        $("#vendor").val("");
    });
});

function validateForm() {
    let message = '';

    if (!sale.phoneNumber) {
        message += "Seleccione teléfono\n";
    }
    if (!sale.vendor) {
        message += "Seleccione el proveedor\n";
    }
    if (!sale.amount) {
        message += "Seleccione el monto\n";
    }

    if (message) {
        alert(message);
    }

    return !message;
}

function successSale(response) {
    alert(`APROBADA: ${response.approvalCode}`);
}

function failedSale(failure) {
    let response = failure.responseJSON;

    alert(`DENEGADA: ${response.responseCode} - ${response.responseMessage}`)
}