<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 24-09-21
  Time: 01:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HomePage</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Login</title>
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css"/>
    <link
            href="https://fonts.googleapis.com/icon?family=Material+Icons"
            rel="stylesheet"/>
</head>
<body>
<nav>
    <div class="nav-wrapper teal">
        <a href="#!" class="brand-logo">User HomePage</a>
        <div class="right" style="padding: 5px">
            <form:form method="post" action="/logout">
                <button class="waves-effect waves-light btn red" style="text-transform: none">Logout</button>
            </form:form>
        </div>
    </div>
</nav>
<h3>Welcome, ${user}</h3>
</body>
</html>
