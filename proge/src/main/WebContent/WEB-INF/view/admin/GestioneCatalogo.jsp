<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*, it.unisa.model.ProdottoBean, it.unisa.model.UserBean" %>
<%
    UserBean User = (UserBean) session.getAttribute("currentSessionUser");

    if (User== null || !User.isAmministratore()) {
    	response.sendRedirect(request.getContextPath() + "/Login");
        return;
    }

    ArrayList<?> products = (ArrayList<?>) session.getAttribute("products");

    if (products == null) {
    	response.sendRedirect(request.getContextPath() + "/catalogo?page=admin/GestioneCatalogo.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/css/style.css"
          rel="stylesheet" type="text/css">
    <title>Gestione catalogo</title>
</head>

<body>

<%@ include file="../fragments/header.jsp" %>
<%@ include file="../fragments/menu.jsp" %>

<div id="main" class="clear">

    <h2>GESTIONE CATALOGO</h2>

    <%
        if (!products.isEmpty()) {
    %>
        <table class="ordini">
            <tr>
                <th>Nome</th>
                <th>Categoria</th>
                <th>Quantità</th>
                <th>Stato</th>
                <th style="text-align:center;">Azione</th>                
            </tr>

            <%
                for (Object obj : products) {
                    ProdottoBean bean = (ProdottoBean) obj;
            %>
                <tr>
                    <td><%= bean.getNome() %></td>
                    <td><%= bean.getCategoria() %></td>
                    <td><%= bean.getQuantita() %></td>
                    <td> 
                        <%= bean.isInVendita() ? "Disponibile" : "Non in vendita" %>
                    </td>
                    
                    <td style="text-align:center;">
                       <a href="${pageContext.request.contextPath}/catalogo?page=admin/ModificaProdotto.jsp&prodotto=<%= bean.getIdProdotto() %>">
                          <button type="button"><b>Modifica</b></button>
                       </a>

                      <form action="${pageContext.request.contextPath}/catalogo" method="post" style="display:inline;">
                           <input type="hidden" name="action" value="Elimina">
                           <input type="hidden" name="id" value="<%= bean.getIdProdotto() %>">
                           <input type="hidden" name="page" value="admin/GestioneCatalogo.jsp">
                           
                           <button type="submit"><b>Rimuovi</b></button>
                      </form>
                     </td>
                                        
                </tr>
            <%
                }
            %>
        </table>
    <%
        } else {
    %>
        <h2>No products available</h2>
    <%
        }
    %>

    <br><br>

    <div class="center">
        <a href="${pageContext.request.contextPath}/catalogo?page=admin/AddProdotto.jsp">
            <button type="button"><b>Aggiungi prodotto</b></button>
        </a>
    </div>

</div>

<%@ include file="../fragments/footer.jsp" %>

</body>
</html>