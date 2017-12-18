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

<form:form action="customRecord/edit.do" modelAttribute="customRecord">

	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:hidden path="curriculum" />
	
	<acme:textbox code="customRecord.title" path="title" mandatory="true" />
	<br />

	<acme:textbox code="customRecord.pieceOfText" path="pieceOfText" mandatory="true" />
	<br />

	<acme:textarea code="customRecord.links" path="links" mandatory="false" />
	<br />

	<acme:submit id="submitButton" name="save" code="customRecord.submit" />
	<input type="button" name="cancel" value="<spring:message code="customRecord.cancel" />"
		onclick="javascript: window.location.replace('curriculum/display.do?curriculumId=${customRecord.curriculum.id}')" />

</form:form>