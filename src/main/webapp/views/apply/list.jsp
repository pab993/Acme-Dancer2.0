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

<display:table name="applies" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<spring:message code="apply.createMoment" var="createMomentHeader" />
	<display:column property="createMoment" title="${createMomentHeader}" />

	<spring:message code="apply.status" var="statusHeader" />
	<display:column property="status" title="${statusHeader}" />

	<spring:message code="apply.course" var="courseHeader" />
	<display:column property="course.title" title="${courseHeader}" />

	<jstl:choose>
		<jstl:when test="${row.curriculum eq null}">
			<display:column>
				<spring:message code="apply.notCurriculum"></spring:message>
			</display:column>
		</jstl:when>
		<jstl:otherwise>
			<display:column>
					<a href="curriculum/display.do?curriculumId=${row.curriculum.id}"> <spring:message
							code="apply.curriculum"></spring:message></a>
				</display:column>
		</jstl:otherwise>
	</jstl:choose>

	<security:authorize access="hasRole('ACADEMY')">
		<spring:message code="apply.dancer" var="dancerHeader" />
		<display:column property="dancer.name" title="${dancerHeader}" />

		<jstl:choose>
			<jstl:when test="${row.status eq 'PENDING'}">
				<display:column>
					<a href="apply/edit.do?applyId=${row.id}"> <spring:message
							code="apply.edit"></spring:message></a>
				</display:column>
			</jstl:when>
			<jstl:otherwise>
				<display:column>
					<spring:message code="apply.notEdit"></spring:message>
				</display:column>
			</jstl:otherwise>
		</jstl:choose>
	</security:authorize>

</display:table>
