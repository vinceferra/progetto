<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,it.unisa.model.*"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">

<link href="${pageContext.request.contextPath}/css/style.css"
      rel="stylesheet"
      type="text/css">

<title>Impostazioni</title>

</head>

<body>

<%
    UserBean us =
        (UserBean) request.getSession().getAttribute("currentSessionUser");

    IndirizzoSpedizioneBean spedizione =
        (IndirizzoSpedizioneBean) request.getSession().getAttribute("spedizione");

    MetodoPagamentoBean pagamento =
        (MetodoPagamentoBean) request.getSession().getAttribute("pagamento");
%>

<%@ include file="./fragments/header.jsp" %>
<%@ include file="./fragments/menu.jsp" %>

<div id="main" class="clear">

    <div>

        <h2>Impostazioni</h2>

        <h3>Dati Personali</h3>

        <ul>
            <li>Nome: <%= us.getNome() %></li>
            <li>Cognome: <%= us.getCognome() %></li>
            <li>Email: <%= us.getEmail() %></li>

            <li>
                <form action="${pageContext.request.contextPath}/account" method="get">
                      <input type="hidden" name="page" value="password.jsp">
                      <button type="submit">CAMBIA PASSWORD</button>
                </form>
            </li>
        </ul>
        
    </div>

    <!-- INDIRIZZO SPEDIZIONE -->

    <div class="account">

    <% if(us.getIndirizzo()==null && us.getCap()==null){ %>

        <h3>Indirizzo di spedizione predefinito</h3>

        <form action="${pageContext.request.contextPath}/account" method="post">

            <input type="hidden" name="action" value="addS">
            <input type="hidden" name="page" value="impostazioni.jsp">

            <!-- Nome -->
            <div class="tableRow">
                <p>Nome:</p>

                <p>
                    <input type="text"
                           name="nome"
                           pattern="^[A-ZÀ-Ý][a-zà-ÿA-ZÀ-Ý\s]*$"
                           title="Solo lettere e iniziale maiuscola"
                           class="solo-lettere-capitale"
                           required/>
                </p>
            </div>

            <!-- Cognome -->
            <div class="tableRow">
                <p>Cognome:</p>

                <p>
                    <input type="text"
                           name="cognome"
                           pattern="^[A-ZÀ-Ý][a-zà-ÿA-ZÀ-Ý\s]*$"
                           title="Solo lettere e iniziale maiuscola"
                           class="solo-lettere-capitale"
                           required/>
                </p>
            </div>

            <!-- Telefono -->
            <div class="tableRow">
                <p>Telefono:</p>

                <p>
                    <input type="tel"
                           name="tel"
                           pattern="^\+\d{2}\d{10}$"
                           maxlength="13"
                           title="Formato: +391234567890"
                           required/>
                </p>
            </div>

            <!-- Indirizzo -->
            <div class="tableRow">
                <p>Indirizzo:</p>

                <p>
                    <input type="text"
                           name="ind"
                           required/>
                </p>
            </div>

            <!-- CAP -->
            <div class="tableRow">
                <p>Cap:</p>

                <p>
                    <input type="text"
                           name="cap"
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
                           required/>
                </p>
            </div>

            <!-- Città -->
            <div class="tableRow">
                <p>Città:</p>

                <p>
                    <input type="text"
                           name="citta"
                           required/>
                </p>
            </div>

            <div class="tableRow">
                <p></p>

                <p>
                    <input type="submit" value="Aggiungi">
                </p>
            </div>

        </form>

    <% } %>

    </div>

    <!-- METODO PAGAMENTO -->

    <div class="account">

    <% if(us.getCartaDiCredito()==null){ %>

        <h3>Metodo di pagamento predefinito</h3>

        <form action="${pageContext.request.contextPath}/account" method="post">

            <input type="hidden" name="page" value="impostazioni.jsp">
            <input type="hidden" name="action" value="addP">

            <!-- Titolare -->
            <div class="tableRow">
                <p>Titolare carta:</p>

                <p>
                    <input type="text"
                           name="tit"
                           pattern="^[A-ZÀ-Ý][a-zà-ÿA-ZÀ-Ý\s]*$"
                           title="Solo lettere e iniziale maiuscola"
                           class="solo-lettere-capitale"
                           required/>
                </p>
            </div>

            <!-- Numero carta -->
            <div class="tableRow">
                <p>Numero:</p>

                <p>
                    <input type="text"
                           name="numC"
                           pattern="^\d{16}$"
                           maxlength="16"
                           title="La carta deve contenere 16 numeri"
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
                           required/>
                </p>
            </div>

            <div class="tableRow">
                <p></p>

                <p>
                    <input type="submit" value="aggiungi">
                </p>
            </div>

        </form>

    <% } %>

    </div>

</div>

<%@ include file="./fragments/footer.jsp" %>
<script src="${pageContext.request.contextPath}/script/checkout.js"></script>
</body>
</html>