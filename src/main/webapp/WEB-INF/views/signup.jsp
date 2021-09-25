<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 24-09-21
  Time: 12:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <title>Login</title>
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css"
    />
    <link
            href="https://fonts.googleapis.com/icon?family=Material+Icons"
            rel="stylesheet"
    />
    <style>
        .myerror {
            color: red;
        }
    </style>
    <script>
        function validate() {
            let a = document.forms["signupform"]["firstname"].value;
            let b = document.forms["signupform"]["email"].value;
            let x = document.forms["signupform"]["password"].value;
            let y = document.forms["signupform"]["repassword"].value;
            var errorName = document.getElementById('errorname');
            var errorEmail = document.getElementById('erroremail');
            var errorPass = document.getElementById('errorpassword');
            var reerrorPass = document.getElementById('reerrorpassword');

            if(a==='')
            {
                errorName.innerText = '* Name can not be blank';
            }else
            {
                errorName.innerText = '';
            }

            if(b==='')
            {
                errorEmail.innerText = '* Email can not be blank';
            }else
            {
                errorEmail.innerText = '';
            }

            if (x === '') {
                errorPass.innerText = '* Password can not be blank';
                return false;
            } else {
                errorPass.innerText = '';
            }

            if (y === '') {
                reerrorPass.innerText = '* Re-Password can not be blank';
                return false;
            } else {
                reerrorPass.innerText = '';
            }

            if (x !== y) {
                reerrorPass.innerText = 'Passwords do not match';
                return false;
            } else {
                reerrorPass.innerText = '';
            }
            document.getElementById("submitbtn").disabled = true;
            return true;
        }
    </script>
</head>
<body>
<div class="container">
    <div class="row">
        <h4 class="center-align">Sign up</h4>
        <div style="width: 60%; margin: auto auto">
            <div>
                <form:form name="signupform" cssClass="col s12" modelAttribute="userModel" action="/process-user"
                           method="post" onsubmit="return validate()">
                    <div class="row">

                        <div class="input-field col s12">
                            <form:input path="firstName" id="firstname" type="text" name="firstname"/>
                            <form:label path="firstName" for="firstname">First Name</form:label>
                            <span id="errorname" class="myerror"></span>
                        </div>

                        <div class="input-field col s12">
                            <form:input path="email" id="email" type="email" name="email"/>
                            <form:label path="email" for="email">Email ID</form:label>
                            <span id="erroremail" class="myerror"></span>
                            <span class="myerror">${signuperror}</span>
                        </div>
                        <div class="input-field col s12">
                            <form:input path="password" id="password" type="password" name="password"/>
                            <form:label path="password" for="password">Password</form:label>
                            <span id="errorpassword" class="myerror"></span>
                        </div>
                        <div class="input-field col s12">
                            <input id="repassword" type="password" name="repassword"/>
                            <label for="repassword">Confirm password</label>
                            <span id="reerrorpassword" class="myerror"></span>
                        </div>
                        <div id="div" class="row center">
                            <button id="submitbtn"
                                    class="btn waves-effect waves-light"
                                    type="submit"
                                    style="text-transform: none">
                                Sign up
                            </button>
                        </div>
                    </div>
                </form:form>
            </div>
            <p>Already have account? <a href="/login">Log in Here</a></p>
        </div>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        var elems = document.querySelectorAll("select");
        var instances = M.FormSelect.init(elems);
    });
</script>
</body>
</html>
