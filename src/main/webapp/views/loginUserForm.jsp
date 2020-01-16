<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<h1 style="color: yellow; text-align: center">User Login Page</h1>
<h1 style="color: red;text-align: center;">${errMsg}</h1>
<form:form action="/userLogin" modelAttribute="loginBean">
	<table   align="center">
		<tr>
			<td>User Email</td>
			<td><form:input path="email" /></td>
		</tr>
		<tr>
			<td>User Password</td>
			<td><form:input path="pwd" /></td>
		</tr>
		
		<tr>
			<td></td><td><input type="submit" value="signIn" ></td>
		</tr>
		</col>
		<tr>
			<td><a href="showForgetPwd">Forget Password</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="reg">signUp</a></td>
		</tr>
	</table>

</form:form>
</body>