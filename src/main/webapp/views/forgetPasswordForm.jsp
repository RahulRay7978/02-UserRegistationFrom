<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 <h1 style="color: yellow;text-align: center;">Forget Password Page</h1>
 <h1 style="color:green;text-align: center;">${succMsg}</h1>
 <h1 style="color:red;text-align: center;">${failMsg}</h1>
 <body style="background-color: #186A3B ;">
	<form:form action="/forget" modelAttribute="fPwd">
		<table align="center">
				<tr style="width: 221px;color: cyan "><td>Email</td><td><form:input path="email"/></td></tr>
				<tr><td><input type="submit" value="Submit"></td><td><input type="reset" value="Reset"></td></tr>
		</table>
	</form:form>
 </body>