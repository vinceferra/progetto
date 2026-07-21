document.addEventListener("DOMContentLoaded", function () {

    const campiLettere =
        document.querySelectorAll(".solo-lettere-capitale");

    campiLettere.forEach(function (campo) {
        campo.addEventListener("input", function () {
            const valore = campo.value;

            if (valore.length === 0) {
                return;
            }

            campo.value =
                valore.charAt(0).toUpperCase() +
                valore
                    .slice(1)
                    .replace(/[^a-zA-ZÀ-ÿ\s]/g, "");
        });
    });

    const campiNumerici =
        document.querySelectorAll(".solo-numeri");

    campiNumerici.forEach(function (campo) {
        campo.addEventListener("input", function () {
            campo.value = campo.value.replace(/[^0-9]/g, "");
        });
    });
});