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

<form:form action="academy/editProfile.do" modelAttribute="academy">
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	<form:hidden path="courses"/>
	
	<form:hidden path="userAccount.id" />
	<form:hidden path="userAccount.version" />	
	<form:hidden path="userAccount.username" />
	<form:hidden path="userAccount.password" />
	<form:hidden path="userAccount.authorities" />
	
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


	<acme:submit id="submitButton" name="save" code="academy.editProfile"/>

</form:form>
