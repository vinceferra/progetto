document.addEventListener("DOMContentLoaded", function () {

    const form = document.getElementById("passwordForm");
    const currentPassword = document.getElementById("currentPassword");
    const newPassword = document.getElementById("newPassword");
    const confirmPassword = document.getElementById("confirmPassword");

    if (!form || !currentPassword || !newPassword || !confirmPassword) {
        console.error("Elementi del form cambio password non trovati");
        return;
    }

    currentPassword.addEventListener(
        "input",
        validaPasswordAttuale
    );

    newPassword.addEventListener("input", function () {
        validaNuovaPassword();

        if (confirmPassword.value !== "") {
            validaConfermaPassword();
        }
    });

    confirmPassword.addEventListener(
        "input",
        validaConfermaPassword
    );

    form.addEventListener("submit", function (event) {
        event.preventDefault();

        const attualeValida = validaPasswordAttuale();
        const nuovaValida = validaNuovaPassword();
        const confermaValida = validaConfermaPassword();

        const formValido =
            attualeValida &&
            nuovaValida &&
            confermaValida;

        if (formValido) {
            form.submit();
        }
    });
});

function validaPasswordAttuale() {
    const campo =
        document.getElementById("currentPassword");

    const errore =
        document.getElementById("errCurrentPassword");

    if (campo.value.trim() === "") {
        mostraErrore(
            errore,
            "Inserisci la password attuale."
        );

        return false;
    }

    rimuoviErrore(errore);
    return true;
}

function validaNuovaPassword() {
    const passwordAttuale =
        document.getElementById("currentPassword");

    const nuovaPassword =
        document.getElementById("newPassword");

    const errore =
        document.getElementById("errNewPassword");

    const formatoValido =
        /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^A-Za-z0-9]).{8,}$/
            .test(nuovaPassword.value);

    if (!formatoValido) {
        mostraErrore(
            errore,
            "Minimo 8 caratteri, una maiuscola, una minuscola, un numero e un carattere speciale."
        );

        return false;
    }

    if (nuovaPassword.value === passwordAttuale.value) {
        mostraErrore(
            errore,
            "La nuova password deve essere diversa da quella attuale."
        );

        return false;
    }

    rimuoviErrore(errore);
    return true;
}

function validaConfermaPassword() {
    const nuovaPassword =
        document.getElementById("newPassword");

    const confermaPassword =
        document.getElementById("confirmPassword");

    const errore =
        document.getElementById("errConfirmPassword");

    if (confermaPassword.value.trim() === "") {
        mostraErrore(
            errore,
            "Conferma la nuova password."
        );

        return false;
    }

    if (nuovaPassword.value !== confermaPassword.value) {
        mostraErrore(
            errore,
            "Le password non coincidono."
        );

        return false;
    }

    rimuoviErrore(errore);
    return true;
}

function mostraErrore(elemento, messaggio) {
    elemento.textContent = messaggio;
    elemento.style.color = "red";
}

function rimuoviErrore(elemento) {
    elemento.textContent = "";
}