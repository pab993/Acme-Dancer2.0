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
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<security:authorize access="hasRole('ADMIN')">

	<div>
		<h2>
			<spring:message code="dashboard.minAvgDevMaxCoursesPerAcademy" />
			<br>
			<jstl:out value="${minAvgDevMaxCoursesPerAcademy}" />
		</h2>
	</div>

	<div>
		<h2>
			<spring:message code="dashboard.minAvgDevMaxApplicationsPerCourse" />
			<br>
			<jstl:out value="${minAvgDevMaxApplicationsPerCourse}" />
		</h2>
	</div>

	<div>
		<h2>
			<spring:message code="dashboard.ratioDancerWithCurricula" />
			<br>
			<jstl:out value="${ratioDancerWithCurricula}" />
		</h2>
	</div>

	<div>

		<h2>
			<spring:message code="dashboard.findStylesOrderedByNumOfCourses" />
		</h2>
		<jstl:forEach items="${findStylesOrderedByNumOfCourses}" var="item">
			<h4>
				<jstl:out value="${item.name}" />
			</h4>
		</jstl:forEach>
	</div>
	
	<div>

		<h2>
			<spring:message code="dashboard.findStylesOrderedByNumOfDancers" />
		</h2>
		<jstl:forEach items="${findStylesOrderedByNumOfDancers}" var="item">
			<h4>
				<jstl:out value="${item[0]}" />
			</h4>
		</jstl:forEach>
	</div>
</security:authorize>