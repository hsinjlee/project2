<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
	<title>Student</title>
	<link href="../css/bootstrap.min.css" rel="stylesheet">
	<script src="../js/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
</head>
<body>
	<%
    List<String> quizNames = (ArrayList<String>) request.getAttribute("quizNames");
    List<String> quizStatus = (ArrayList<String>) request.getAttribute("quizStatus");
    List<Integer> quizIds = (ArrayList<Integer>) request.getAttribute("quizIds");
%>
	<h2>Quiz</h2>
	<form method="GET" >
		<div class="container">
			<table class="table table-bordered">
				<%session.setAttribute("action","load");
				if(quizNames.size()==0){ %>
                <p align="center">
				<h3> No quizzes are created by the professor!! </h3>
                </p>
                <%
                } else {  %>

                <p align="center">
				<h3> You have following quizzes for the course!! <br> </h3>
                </p>
				<%
            for( int i =0;i<quizNames.size();i++){
        %>
				<tr>
					<td><%=quizNames.get(i)%> <a href="student/?id=<%=quizIds.get(i)%>">Click here
							to start the quiz</a></td>
                    <td><%=quizStatus.get(i)%></td>
				</tr>

				<%
            }}
        %>
			</table>
		</div>
	</form>
</body>
</html>
