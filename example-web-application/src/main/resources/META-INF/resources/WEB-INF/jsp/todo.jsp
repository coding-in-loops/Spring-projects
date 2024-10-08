<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Welcome page</title>
    <link href="/webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
    <link href="/webjars/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.standalone.min.css" rel="stylesheet"/>
    
</head>
<body>
<nav class="navbar navbar-expand-md navbar-light bg-light mb-3 p-1">
	<a class="navbar-brand m-1" href="https://courses.in28minutes.com">To-do</a>
	<div class="collapse navbar-collapse">
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="/">Home</a></li>
			<li class="nav-item"><a class="nav-link" href="/list-todos">Todos</a></li>
		</ul>
	</div>
	<ul class="navbar-nav">
		<li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
	</ul>	
</nav>
   <div class="container">
    <div class="mb-4">
        <h1>Welcome to the To-do Application!</h1>
    </div>
    <div class="mb-4">
        <h2>Enter To-do Details</h2>
        <form:form method="post" modelAttribute="todo">
            <fieldset class="mb-3">
                <form:label path="description" cssClass="form-label">Description</form:label>
                <form:input path="description" class="form-control" required="required"/>
                <form:errors path="description" cssClass="text-danger"/>
            </fieldset>
            <fieldset class="mb-3">
                <form:label path="targetDate" cssClass="form-label">Target Date</form:label>
                <form:input path="targetDate" type="text" id="targetDate" class="form-control" required="required"/>
                <form:errors path="targetDate" cssClass="text-danger"/>
            </fieldset>
            <input type="submit" class="btn btn-success" value="Add Todo"/>
        </form:form>
    </div>

        <script src="/webjars/jquery/3.6.0/jquery.min.js"></script>
        <script src="/webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
        <script src="/webjars/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('#targetDate').datepicker({
                    format: 'yyyy-mm-dd'
                });
            });
        </script>
    </div>
</body>
</html>
