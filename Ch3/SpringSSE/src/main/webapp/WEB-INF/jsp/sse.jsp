<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
    <script src="webjars/jquery/1.9.1/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
	<c:url value="/css/main.css" var="jstlCss" />
	<link href="${jstlCss}" rel="stylesheet" />
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Spring Boot</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#about">About</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="starter-template">
			<h1>Spring Boot Web JSP Example</h1>
			<h2>Message: </h2>
			<ul id="messages"></ul>
		</div>
	</div>
	<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
<script>
    if (!!window.EventSource) {
    	var eventSource = new EventSource("/notify");
        var elements = document.getElementById("messages");
        function add(message) {
            var element = document.createElement("li");
            element.innerHTML = message;
            elements.appendChild(element);
        }
        eventSource.onmessage = function (e) {
            var message = e.data;
            add(message);
        };
        eventSource.onopen = function (e) {
            add("connection was opened");
        };
        eventSource.onerror = function (e) {
            if (e.readyState == EventSource.CONNECTING) {
                add("event: CONNECTING");
            } else if (e.readyState == EventSource.OPEN) {
                add("event: OPEN");
            } else if (e.readyState == EventSource.CLOSING) {
                add("event: CLOSING");
            } else if (e.readyState == EventSource.CLOSED) {
                add("event: CLOSED");
            }
        };
    } else {
        alert("The browser does not support Server-Sent Events");
    }
</script>
</html>