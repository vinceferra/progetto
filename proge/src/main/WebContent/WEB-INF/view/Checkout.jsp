<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.unisa.model.*, java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"  type="text/css">
<link rel="icon"  href="${pageContext.request.contextPath}/images/favicon.ico">
<title>Checkout</title>
</head>

<body>

    <%@ include file="./fragments/header.jsp" %>
    <%@ include file="./fragments/menu.jsp" %>

    <%
        IndirizzoSpedizioneBean spedizione =
            (IndirizzoSpedizioneBean) session.getAttribute("spedizione");

        MetodoPagamentoBean pagamento =
            (MetodoPagamentoBean) session.getAttribute("pagamento");
    %>

    <div id="main" class="clear">

        <h2>Checkout</h2>

        <form action="${pageContext.request.contextPath}/Checkout" method="post" id="myform">

            <!-- DATI SPEDIZIONE -->

            <div class="tableRow">
                <p class="heading">Dati spedizione:</p>
                <p></p>
            </div>

            <!-- Nome -->
            <div class="tableRow">
                <p>Nome:</p>
                <p>
                    <input type="text"
                           name="nome"
                           class="solo-lettere-capitale"
                           value="<%= spedizione != null ? spedizione.getNome() : "" %>"
                           pattern="^[A-ZÀ-Ý][a-zà-ÿA-ZÀ-Ý\s]*$"
                           title="Il nome deve contenere solo lettere e iniziare con la maiuscola"
                           required>
                </p>
            </div>

            <!-- Cognome -->
            <div class="tableRow">
                <p>Cognome:</p>
                <p>
                   <input type="text"
                          name="cognome"
                          class="solo-lettere-capitale"
                          value="<%= spedizione != null ? spedizione.getCognome() : "" %>"
                          pattern="^[A-ZÀ-Ý][a-zà-ÿA-ZÀ-Ý\s]*$"
                          title="Il nome deve contenere solo lettere e iniziare con la maiuscola"
                          required>
                </p>
            </div>

            <!-- Telefono -->
            <div class="tableRow">
                <p>Telefono:</p>
                <p>
                    <input type="tel"
                           name="tel"
                           value="<%= spedizione != null ? spedizione.getTelefono() : "" %>"
                           pattern="^\+\d{2}\d{10}$"
                           maxlength="13"
                           title="Inserisci +39 seguito da 10 numeri"
                           required/>
                </p>
            </div>

            <!-- Indirizzo -->
            <div class="tableRow">
                <p>Indirizzo:</p>
                <p>
                    <input type="text"
                           name="ind"
                           value="<%= spedizione != null ? spedizione.getIndirizzo() : "" %>"
                           required/>
                </p>
            </div>

            <!-- CAP -->
            <div class="tableRow">
                <p>Cap:</p>
                <p>
                    <input type="text"
                           name="cap"
                           value="<%= spedizione != null ? spedizione.getCap() : "" %>"
                           pattern="^\d{5}$"
                           maxlength="5"
                           title="Il CAP deve contenere 5 numeri"
                           class="solo-numeri"
                           required/>
                </p>
            </div>

            <!-- Provincia -->
            <div class="tableRow">
                <p>Provincia:</p>
                <p>
                    <input type="text"
                           name="prov"
                           value="<%= spedizione != null ? spedizione.getProvincia() : "" %>"
                           required/>
                </p>
            </div>

            <!-- Città -->
            <div class="tableRow">
                <p>Città:</p>
                <p>
                    <input type="text"
                           name="citta"
                           value="<%= spedizione != null ? spedizione.getCitta() : "" %>"
                           required/>
                </p>
            </div>

            <!-- DATI PAGAMENTO -->

            <div class="tableRow">
                <p class="heading">Dati pagamento:</p>
                <p></p>
            </div>

            <!-- Titolare carta -->
            <div class="tableRow">
                <p>Titolare carta:</p>
                <p>
                   <input type="text"
                          name="tit"
                          class="solo-lettere-capitale"
                          value="<%= pagamento != null ? pagamento.getTitolare() : "" %>"
                          pattern="^[A-ZÀ-Ý][a-zà-ÿA-ZÀ-Ý\s]*$"
                          title="Il nome deve contenere solo lettere e iniziare con la maiuscola"
                          required>
                </p>
            </div>

            <!-- Numero carta -->
            <div class="tableRow">
                <p>Numero:</p>
                <p>
                    <input type="text"
                           name="numC"
                           value="<%= pagamento != null ? pagamento.getNumero() : "" %>"
                           pattern="^\d{16}$"
                           maxlength="16"
                           title="Il numero della carta deve contenere 15 numeri"
                           class="solo-numeri"
                           required/>
                </p>
            </div>

            <!-- Scadenza -->
            <div class="tableRow">
                <p>Scadenza:</p>
                <p>
                    <input type="date"
                           name="scad"
                           value="<%= pagamento != null ? pagamento.getScadenza() : "" %>"
                           required/>
                </p>
            </div>

            <!-- Bottone -->

            <div class="tableRow">
                <p></p>
                <p>
                    <input type="submit" value="checkout">
                </p>
            </div>

        </form>

    </div>

    <%@ include file="./fragments/footer.jsp" %>
    <script src="${pageContext.request.contextPath}/script/checkout.js"></script>

</body>
</html>