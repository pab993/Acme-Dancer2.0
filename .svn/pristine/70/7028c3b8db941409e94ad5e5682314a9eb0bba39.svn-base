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

<display:table name="academies" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<spring:message code="academy.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" />

	<spring:message code="academy.surname" var="surnameHeader" />
	<display:column property="surname" title="${surnameHeader}" />

	<spring:message code="academy.phone" var="phoneHeader" />
	<display:column property="phone" title="${phoneHeader}" />

	<spring:message code="academy.email" var="emailHeader" />
	<display:column property="email" title="${emailHeader}" />

	<spring:message code="academy.postalAddress" var="postalAddressHeader" />
	<display:column property="postalAddress" title="${postalAddressHeader}" />academy.comercialName
	
	<spring:message code="academy.comercialName" var="comercialNameHeader" />
	<display:column property="comercialName" title="${comercialNameHeader}" />
	
	<display:column>
		<a href="course/listByAcademy.do?academyId=${row.id}"> <spring:message
				code="academy.courses"></spring:message></a>
	</display:column>

</display:table>
