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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action = "course/search.do">

	<h2><spring:message code = "course.searchText" /></h2>

	<input type = "text" name = "keyword" />
	<input type = "submit" name = "search"
		value = "<spring:message code = "course.search" />" />
		
</form:form>

<jstl:if test="${!firstTime}">
	<display:table name="courses" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<spring:message code="course.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" />

	<spring:message code="course.level" var="levelHeader" />
	<display:column property="level" title="${levelHeader}" />

	<spring:message code="course.startDate" var="startDateHeader" />
	<display:column property="startDate" title="${startDateHeader}" format="{0,date,dd/MM/yyyy HH:mm}"/>

	<spring:message code="course.endDate" var="endDateHeader" />
	<display:column property="endDate" title="${endDateHeader}" format="{0,date,dd/MM/yyyy HH:mm}"/>

	<spring:message code="course.day" var="dayHeader" />
	<display:column property="day" title="${dayHeader}" />

	<spring:message code="course.hour" var="hourHeader" />
	<display:column property="hour" title="${hourHeader}" />
	
	<display:column>
		<jstl:choose>
			<jstl:when test="${fn:length(row.tracksons) == 0}">
			<security:authorize access="hasRole('ADMIN')">
				<a href="trackson/create.do?courseId=${row.id}"> <spring:message
						code="trackson.create" />
				</a>
			</security:authorize>
			</jstl:when>
			<jstl:otherwise>
				<jstl:if test="${fn:length(row.tracksons) != 0 && row.tracksons[0].displayMoment lt currentMoment}">
					<a href="trackson/listByCourse.do?courseId=${row.id}"> <spring:message
							code="course.trackson" />
					</a>
				</jstl:if>
			</jstl:otherwise>
		</jstl:choose>
	</display:column>

</display:table>

</jstl:if>	
