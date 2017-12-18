<%--
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Listing table -->

<div>
	<h3>
		<a href="curriculum/create.do"><spring:message
				code="curriculum.create" /></a>
	</h3>
</div>

<display:table name="curricula" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<spring:message code="curriculum.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" />

	<spring:message code="curriculum.email" var="emailHeader" />
	<display:column property="email" title="${emailHeader}" />

	<spring:message code="curriculum.whatsappNumber" var="whatsappNumberHeader" />
	<display:column property="whatsappNumber" title="${whatsappNumberHeader}" />

	<spring:message code="curriculum.linkToLinkedIn"
		var="linkToLinkedInHeader" />
	<display:column property="linkToLinkedIn"
		title="${linkToLinkedInHeader}" />

	<display:column>
		<a href="curriculum/display.do?curriculumId=${row.id}"> <spring:message
				code="curriculum.display"></spring:message></a>
	</display:column>

	<display:column>
		<a href="curriculum/edit.do?curriculumId=${row.id}"> <spring:message
				code="curriculum.edit"></spring:message></a>
	</display:column>


</display:table>

