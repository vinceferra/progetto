let usernameDisponibile = false;
let emailDisponibile = false;

$(document).ready(function () {
    const contextPath = document.body.dataset.contextPath;

    const nome = document.getElementsByName("nome")[0];
    const cognome = document.getElementsByName("cognome")[0];
    const email = document.getElementsByName("email")[0];
    const data = document.getElementsByName("nascita")[0];
    const username = document.getElementsByName("us")[0];
    const password = document.getElementsByName("pw")[0];
	
	$("#us").on("input", function () {
	    usernameDisponibile = false;
	    $("#errUser").text("");
	});

	$("#em").on("input", function () {
	    emailDisponibile = false;
	    $("#errEmail").text("");
	});

	nome.addEventListener("change", validaNome);
	cognome.addEventListener("change", validaCognome);
	data.addEventListener("change", validaData);
	password.addEventListener("change", validaPassword);

    $("#myform").on("submit", function (event) {
        event.preventDefault();

        const nomeValido = validaNome();
        const cognomeValido = validaCognome();
        const emailValida = validaEmail();
        const dataValida = validaData();
        const usernameValido = validaUsername();
        const passwordValida = validaPassword();

        const valid =
            nomeValido &&
            cognomeValido &&
            emailValida &&
            dataValida &&
            usernameValido &&
            passwordValida &&
            usernameDisponibile &&
            emailDisponibile;

        if (valid) {
            this.submit();
        }
    });

    $("#us").on("change", function () {
        const valore = username.value.trim();

        if (!checkUserName(username)) {
            usernameDisponibile = false;

            $("#errUser")
                .text("Username non valido")
                .css("color", "red");

            return;
        }

        $.post(
            contextPath + "/CheckUsername",
            { us: valore },
            function (data) {
                if (data === "0") {
                    usernameDisponibile = false;

                    $("#errUser")
                        .text("Username già in uso")
                        .css("color", "red");
                } else {
                    usernameDisponibile = true;
                    $("#errUser").text("");
                }
            }
        ).fail(function () {
            usernameDisponibile = false;

            $("#errUser")
                .text("Impossibile verificare lo username")
                .css("color", "red");
        });
    });

    $("#em").on("change", function () {
        const valore = email.value.trim();

        if (!checkEmail(email)) {
            emailDisponibile = false;

            $("#errEmail")
                .text("Email non valida")
                .css("color", "red");

            return;
        }

        $.post(
            contextPath + "/CheckEmail",
            { em: valore },
            function (data) {
                if (data === "0") {
                    emailDisponibile = false;

                    $("#errEmail")
                        .text("Email già in uso")
                        .css("color", "red");
                } else {
                    emailDisponibile = true;
                    $("#errEmail").text("");
                }
            }
        ).fail(function () {
            emailDisponibile = false;

            $("#errEmail")
                .text("Impossibile verificare l'email")
                .css("color", "red");
        });
    });
});

function checkNomeCognome(input) {
    return /^[A-Za-zÀ-ÿ\s]+$/.test(input.value.trim());
}

function checkEmail(input) {
    return /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,})+$/
        .test(input.value.trim());
}

function checkData(input) {
    return /^\d{4}-\d{2}-\d{2}$/.test(input.value);
}

function checkUserName(input) {
    return /^[A-Za-z0-9]+$/.test(input.value.trim());
}

function checkPassword(input) {
    return /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^A-Za-z0-9]).{8,}$/
        .test(input.value);
}

function validaNome() {
    const campo = document.getElementsByName("nome")[0];
    const errore = document.getElementById("errNome");

    if (!checkNomeCognome(campo)) {
        errore.textContent = "Nome non valido";
        errore.style.color = "red";
        return false;
    }

    errore.textContent = "";
    return true;
}

function validaCognome() {
    const campo = document.getElementsByName("cognome")[0];
    const errore = document.getElementById("errCognome");

    if (!checkNomeCognome(campo)) {
        errore.textContent = "Cognome non valido";
        errore.style.color = "red";
        return false;
    }

    errore.textContent = "";
    return true;
}

function validaEmail() {
    const campo = document.getElementsByName("email")[0];
    const errore = document.getElementById("errEmail");

    if (!checkEmail(campo)) {
        errore.textContent = "Email non valida";
        errore.style.color = "red";
        return false;
    }

    if (!emailDisponibile) {
        errore.textContent = "Email già in uso";
        errore.style.color = "red";
        return false;
    }

    errore.textContent = "";
    return true;
}

function validaData() {
    const campo = document.getElementsByName("nascita")[0];
    const errore = document.getElementById("errNascita");

    if (!checkData(campo)) {
        errore.textContent = "Data non valida";
        errore.style.color = "red";
        return false;
    }

    errore.textContent = "";
    return true;
}

function validaUsername() {
    const campo = document.getElementsByName("us")[0];
    const errore = document.getElementById("errUser");

    if (!checkUserName(campo)) {
        errore.textContent = "Username non valido";
        errore.style.color = "red";
        return false;
    }

    if (!usernameDisponibile) {
        errore.textContent = "Username già in uso";
        errore.style.color = "red";
        return false;
    }

    errore.textContent = "";
    return true;
}

function validaPassword() {
    const campo = document.getElementsByName("pw")[0];
    const errore = document.getElementById("errPass");

    if (!checkPassword(campo)) {
		errore.textContent ="Minimo 8 caratteri, una maiuscola, una minuscola, un numero e un carattere speciale";
        errore.style.color ="red";
        return false;
    }

    errore.textContent = "";
    return true;
}