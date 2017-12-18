<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="endorserRecord/edit.do" modelAttribute="endorserRecord">

	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:hidden path="curriculum" />
	
	<acme:textbox code="endorserRecord.fullName" path="fullName" mandatory="true" />
	<br />
	
	<div>
		<form:select path="contact.means" itemLabel="means" code="endorserRecord.means">
			<option value="email">email</option>
			<option value="phone number"><spring:message code="contact.means.phoneNumber"/></option>
			<option value="postal address"><spring:message code="contact.means.postalAddress"/></option>
			<option value="PO Box">PO Box</option>
		</form:select>
	</div>
	<br/>
	
	<acme:textbox code="endorserRecord.value" path="contact.value" mandatory="true" />
	<br />
	
	<acme:submit id="submitButton" name="save" code="endorserRecord.submit" />
	<input type="button" name="cancel" value="<spring:message code="endorserRecord.cancel" />"
		onclick="javascript: window.location.replace('curriculum/display.do?curriculumId=${endorserRecord.curriculum.id}')" />

</form:form>