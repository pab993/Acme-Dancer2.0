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

<form:form action="apply/createTeacher.do" modelAttribute="apply">
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	<form:hidden path="status"/>
	<form:hidden path="createMoment"/>
	
	<form:hidden path="dancer"/>
	<form:hidden path="course"/>
	
	<legend><b> <spring:message code="apply.createTeacher" /> </b></legend>
	
	<div>
		<acme:select items="${curriculums}" itemLabel="name" code="choose"
			path="curriculum" />
	</div>
	<br />
	
	<acme:submit name="save" code="apply.submit"/>
	<acme:cancel code="apply.cancel" url="course/list.do" />
		
</form:form>