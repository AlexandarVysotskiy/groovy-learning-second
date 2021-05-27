<html>
<head>
    <title><g:layoutTitle default="An example decorator"/></title>
    <g:layoutHead/>
</head>

<body onload="${pageProperty(name: 'body.onload')}">

<div id="header" class="footer-header-box">
    <p>My learning grails application</p>

    <g:if test="${session.user}">
        <p>Your logged as ${session.user.name}</p>

        <g:form controller="login" action="logout">
            <g:submitButton name="Logout"/>
        </g:form>
    </g:if>
</div>

<div class="content-box">
    <g:layoutBody/>
</div>

<div id="footer" class="footer-header-box">
    <p>Copyright © 2021 A.Vysotskiy - <a href="http://localhost:8090/grails-com.learning">Grails learning</a></p>
</div>

</body>
</html>