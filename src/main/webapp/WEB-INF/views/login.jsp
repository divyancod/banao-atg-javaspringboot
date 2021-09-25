<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Login</title>
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css"/>
    <link
            href="https://fonts.googleapis.com/icon?family=Material+Icons"
            rel="stylesheet"/>
</head>
<body>
<div class="container">
    <div class="row">
        <c:if test="${param.logout != null}">
            <p class="center-align" style="color:#93e34a;">Logout successful.</p>
        </c:if>
        <h4 class="center-align">Log in</h4>
        <div style="width: 60%; margin: auto auto">
            <div class="center-align">
                <img src="<c:url value="/resources/img/login-avatar.png"/>" style="width: 30%"/>
                <form:form cssClass="col s12">
                    <div class="row">
                        <div class="input-field col s12">
                            <input id="email" type="email" name="username"/>
                            <label for="email">Email ID</label>
                        </div>
                        <div class="input-field col s12">
                            <input id="password" type="password" name="password"/>
                            <label for="password">Password</label>
                        </div>
                        <c:if test="${param.error != null}">
                            <p style="color: #e05a5a;">Invalid email id or password.</p>
                        </c:if>
                        <div id="div" class="row center">
                            <button
                                    class="btn waves-effect waves-light"
                                    type="submit"
                                    style="text-transform: none">
                                Login
                            </button>
                        </div>
                    </div>
                </form:form>
            </div>
            <p>Don't have account? <a href="signup">Signup Here</a></p>
        </div>
    </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>

