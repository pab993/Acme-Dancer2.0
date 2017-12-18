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

<form:form action="styleRecord/edit.do" modelAttribute="styleRecord">

	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:hidden path="curriculum" />
	
	<acme:textbox code="styleRecord.years" path="years" mandatory="true" />
	<br />


	<form:label path="reference">
		<spring:message code="styleRecord.reference" />
	</form:label>
	<form:select path = "reference">
		<form:options items="${styles}" itemValue="name" itemLabel="name" />
	</form:select>
	<form:errors cssClass = "error" path="reference" />
	<br/>
	<br/>

	<acme:submit id="submitButton" name="save" code="styleRecord.submit" />
	<input type="button" name="cancel" value="<spring:message code="styleRecord.cancel" />"
		onclick="javascript: window.location.replace('curriculum/display.do?curriculumId=${styleRecord.curriculum.id}')" />

</form:form>