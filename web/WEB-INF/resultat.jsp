<%--
  Created by IntelliJ IDEA.
  User: dhannane2021
  Date: 09/06/2021
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Résultat</title>
</head>
<body>
<h1>RESULTAT</h1>
<%
    String texteFinal= (String) request.getAttribute("texteFinal");
%>
<p>résultat: <%=texteFinal %></p>
<a href="chifoumi">Rejouer</a>
</body>
</html>
