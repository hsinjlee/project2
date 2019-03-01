<%@ page import="java.util.*, Team76.Controller.*"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>

/** 
*  list-grades.jsp class is view in MVC. 
*  Author: Hsin-Jung Lee 
*  Version: 3 
*/
<!DOCTYPE html>
<html>
<head>
<title>Grade App</title>

<!-- <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"> -->
</head>

<%
	List<Grade> theGrades = (List<Grade>) request.getAttribute("GRADE_LIST");
%>

<body>


	<div id="wrapper">
		<div id="header">
			<h2>Grades</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">
			<table>

				<tr>
					<th>Student ID</th>
					<th>Quiz ID</th>
					<th>Quiz Title</th>
					<th>Student Name</th>
					<th>Grade</th>
				</tr>

				
				//<c:forEach var="tempGrade" items="${GRADE_LIST }">
					<%
						for (Grade tempGrade : theGrades) {
					%>
					<tr>
						<%-- <td>${tempGrade.studentId }</td>
						<td>${tempGrade.quizId }</td>
						<td>${tempGrade.quizTitle}</td>
						<td>${tempGrade.studentName }</td>
						<td>${tempGrade.grade }</td> --%>

						<td><%=tempGrade.getStudentId()%></td>
						<td><%=tempGrade.getQuizId()%></td>
						<td><%=tempGrade.getQuizTitle()%></td>
						<td><%=tempGrade.getStudentName()%></td>
						<td><%=tempGrade.getGrade()%></td>
					</tr>
					<%
						}
					%>
				//</c:forEach>



			</table>

		</div>

	</div>
</body>
</html>