$(document).ready(function () {
    const contextPath = document.body.dataset.contextPath;

    $("#myform").on("submit", function (event) {
        event.preventDefault();
        validate(this);
    });

    $("#us").on("keyup", function () {
        const username = $("#us").val();

        if (username === "") {
            $("#errUser").html("");
            return;
        }

        $.post(
            contextPath + "/CheckUsername",
            { us: username },
            function (data) {
                if (data === "0") {
                    $("#errUser")
                        .html("Username già in uso")
                        .css("color", "red");
                } else {
                    $("#errUser").html("");
                }
            }
        );
    });

    $("#em").on("keyup", function () {
        const email = $("#em").val();

        if (email === "") {
            $("#errEmail").html("");
            return;
        }

        $.post(
            contextPath + "/CheckEmail",
            { em: email },
            function (data) {
                if (data === "0") {
                    $("#errEmail")
                        .html("Email già in uso")
                        .css("color", "red");
                } else {
                    $("#errEmail").html("");
                }
            }
        );
    });
});

function checkNomeCognome(input) {
    return /^[A-Za-zÀ-ÿ\s]+$/.test(input.value);
}

function checkEmail(input) {
    return /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,})+$/.test(input.value);
}

function checkData(input) {
    return /^\d{4}-\d{2}-\d{2}$/.test(input.value);
}

function checkUserName(input) {
    return /^[A-Za-z0-9]+$/.test(input.value);
}

function checkPassword(input) {
    return /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$/.test(input.value);
}

function validate(form) {
    let valid = true;

    const nome = document.getElementsByName("nome")[0];
    const errNome = document.getElementById("errNome");

    if (!checkNomeCognome(nome)) {
        valid = false;
        errNome.textContent = "Nome non valido";
        errNome.style.color = "red";
    } else {
        errNome.textContent = "";
    }

    const cognome = document.getElementsByName("cognome")[0];
    const errCognome = document.getElementById("errCognome");

    if (!checkNomeCognome(cognome)) {
        valid = false;
        errCognome.textContent = "Cognome non valido";
        errCognome.style.color = "red";
    } else {
        errCognome.textContent = "";
    }

    const email = document.getElementsByName("email")[0];
    const errEmail = document.getElementById("errEmail");

	if (!checkEmail(email)) {
	    valid = false;
	    errEmail.textContent = "Email non valida";
	    errEmail.style.color = "red";
	} else {
	    errEmail.textContent = "";
	}

    const data = document.getElementsByName("nascita")[0];
    const errNascita = document.getElementById("errNascita");

    if (!checkData(data)) {
        valid = false;
        errNascita.textContent = "Data non valida";
        errNascita.style.color = "red";
    } else {
        errNascita.textContent = "";
    }

    const username = document.getElementsByName("us")[0];
    const errUser = document.getElementById("errUser");

    if (!checkUserName(username)) {
        valid = false;
        errUser.textContent = "Username non valido";
        errUser.style.color = "red";
    }		else {
		    errUser.textContent = "";
		}

    const password = document.getElementsByName("pw")[0];
    const errPass = document.getElementById("errPass");

    if (!checkPassword(password)) {
        valid = false;
        errPass.textContent =
            "La password deve contenere almeno 8 caratteri, una maiuscola, una minuscola e un numero";
        errPass.style.color = "red";
    } else {
        errPass.textContent = "";
    }

    if (valid) {
        form.submit();
    }
}