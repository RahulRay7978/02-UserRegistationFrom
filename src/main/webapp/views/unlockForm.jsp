<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<h1 style="color: cyan; text-align: center;">Unlock Form Page</h1>
<h1 style="color:green;text-align: center">${succMsg}</h1>
<h1 style="color:red;text-align: center">${failMsg}</h1>
<body style="background-color: #9A7D0A ">
<form:form action="/unlockBtn" modelAttribute="unlockBean">
	<table align="center">
		<tr>
			<td>User Email ID</td>
			<td><form:input path="email"/></td>
		</tr>
		<tr>
			<td>Enter temp password</td>
			<td><form:input path="pwd" /></td>
		</tr>
		<tr>
			<td>Enter new password</td>
			<td><form:input path="newPwd" /></td>
		</tr>
		<tr>
			<td>Enter Confirm password</td>
			<td><form:input path="confirmPwd" /></td>
		</tr>
		<tr>
			<td><input type="submit" value="submit"
				onclick="return validate()" /></td>&nbsp; &nbsp;
			<td><input type="reset" value="reset" /></td>
		</tr>
	</table>
</form:form>
</body>
<script type="text/javascript">
	function validate() {
		alert(122)
		var newPassword = document.getElementById("newPwd").value;
		var confirmPassword = document.getElementById("confirmPwd").value;
		if (newPassword != confirmPassword) {
			alert("Enter valid password")
			return false;
		}
		return true;
	}
</script>