<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="academy/register.do" modelAttribute="academyForm">
	
	<fieldset > 
	
	<legend><b> <spring:message code="academy.accountData" /> </b></legend>
	
		<acme:textbox code="academy.username" path="username" mandatory="true"/>
		<br/>
	
		<acme:password code="academy.password" path="password" mandatory="true"/>
		<br/>
	
		<acme:password code="academy.repeatPassword" path="repeatPassword" mandatory="true"/>
	
	</fieldset>
	
	
	<fieldset > 
	
		<legend><b> <spring:message code="academy.personalData" /></b> </legend>
	
	
		<acme:textbox code="academy.name" path="name" mandatory="true"/>
		<br />
			
		<acme:textbox code="academy.surname" path="surname" mandatory="true"/>
		<br />
		
		<acme:textbox code="academy.phone" path="phone"/>
		<br />
			
		<acme:textbox code="academy.email" path="email" mandatory="true"/>
		<br />
		
		<acme:textbox code="academy.postalAddress" path="postalAddress"/>
		<br/>

		<acme:textbox code="academy.comercialName" path="comercialName"/>
		<br/>	
	</fieldset>
	
	
	<div>
	<form:checkbox id="myCheck" onclick="comprobar();" path="check"/>
		<form:label path="check">
			<spring:message code="academy.accept" />
			<a href="termAndCondition/termAndCondition.do"><spring:message code="academy.lopd"/></a>
		</form:label>
	</div>
	

	<acme:submit id="submitButton" name="save" code="academy.submit"/>

</form:form>


<script type="text/javascript">

document.getElementById("submitButton").disabled = true;
document.getElementById("myCheck").checked = false;

function comprobar() {
	
	var x = document.getElementById("myCheck").checked;
	
	if(x == true){
		document.getElementById("submitButton").disabled = false;
	}
	else{
		document.getElementById("submitButton").disabled = true;
	}
}



</script>