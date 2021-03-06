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

<form:form action="course/edit.do" modelAttribute="course">

	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:hidden path="applies" />
	<form:hidden path="academy" />

	<div>
    <form:select path="stage" itemLabel="stage" code="choose">
      <option value="DELIVERING">
        <spring:message code="course.stage.delivering" />
      </option>
      <option value="ORGANISING">
        <spring:message code="course.stage.organising" />
      </option>
    </form:select>
  </div>
  <br />

	<acme:textbox code="course.title" path="title" />
	<br />

	<div>
    <form:select path="level" itemLabel="level" code="choose">
      <option value="BEGINNER"><spring:message code="course.level.beginner"/></option>
      <option value="INTERMEDIATE"><spring:message code="course.level.intermediate"/></option>
      <option value="ADVANCED"><spring:message code="course.level.advanced"/></option>
      <option value="PROFESSIONAL"><spring:message code="course.level.professional"/></option>
    </form:select>
  </div>
	<br />

	<acme:textbox code="course.startDate" path="startDate" />
	<br />

	<acme:textbox code="course.endDate" path="endDate" />
	<br />

	<div>
    <form:select path="day" itemLabel="day" code="choose">
      <option value="MONDAY"><spring:message code="course.day.monday"/></option>
      <option value="THUESDAY"><spring:message code="course.day.tuesday"/></option>
      <option value="WEDNESDAY"><spring:message code="course.day.wednesday"/></option>
      <option value="THURSDAY"><spring:message code="course.day.thursday"/></option>
      <option value="FRIDAY"><spring:message code="course.day.friday"/></option>
    </form:select>
  </div>
	<br />

	<div>
		<form:select path="hour" itemLabel="hour" code="choose">
			<option value="09:00">09:00</option>
			<option value="10:00">10:00</option>
			<option value="11:00">11:00</option>
			<option value="12:00">12:00</option>
			<option value="13:00">13:00</option>
			<option value="16:00">16:00</option>
			<option value="17:00">17:00</option>
			<option value="18:00">18:00</option>
			<option value="19:00">19:00</option>
			<option value="20:00">20:00</option>
		</form:select>
	</div>
	<br />

	<div>
		<acme:select items="${styles}" itemLabel="name" code="choose"
			path="style" />
	</div>
	<br />

	<jstl:choose>
		<jstl:when test="${course.id == 0}">
			<acme:submit name="save" code="course.submit" />
			<acme:cancel code="course.cancel" url="course/myList.do" />
		</jstl:when>
		<jstl:otherwise>
			<acme:submit name="save" code="course.submit" />
			<acme:submit name="delete" code="course.delete" />
			<acme:cancel code="course.cancel" url="course/myList.do" />
		</jstl:otherwise>
	</jstl:choose>



</form:form>