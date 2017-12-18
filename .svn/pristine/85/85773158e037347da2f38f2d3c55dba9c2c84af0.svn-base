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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<fieldset>

	<legend>
		<b> <spring:message code="curriculum.personalData" /></b>
	</legend>

	<spring:message code="curriculum.name" />
	:
	<jstl:out value="${curriculum.name}"></jstl:out>
	<br>
	<spring:message code="curriculum.email" />
	:
	<jstl:out value="${curriculum.email}"></jstl:out>
	<br>
	<spring:message code="curriculum.whatsappNumber" />
	:
	<jstl:out value="${curriculum.whatsappNumber}"></jstl:out>
	<br>
	<spring:message code="curriculum.linkToLinkedIn" />
	:
	<jstl:out value="${curriculum.linkToLinkedIn}"></jstl:out>
	<br>

</fieldset>
<br />

<display:table name="styleRecords" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<spring:message code="styleRecord.years" var="yearsHeader" />
	<display:column property="years" title="${yearsHeader}" />

	<spring:message code="styleRecord.reference" var="referenceHeader" />
	<display:column property="reference" title="${referenceHeader}" />

	<jstl:if test="${ principal.id == curriculum.dancer.id}">
		<display:column>
			<a href="styleRecord/edit.do?styleRecordId=${row.id}"> <spring:message
					code="curriculum.edit"></spring:message></a>
		</display:column>
	</jstl:if>

</display:table>

<jstl:if test="${ principal.id == curriculum.dancer.id}">
	<a href="styleRecord/create.do?curriculumId=${curriculum.id }"><spring:message
			code="styleRecord.create" /></a>
</jstl:if>

<br />

<display:table name="customRecords" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<spring:message code="customRecord.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" />

	<spring:message code="customRecord.pieceOfText" var="pieceOfTextHeader" />
	<display:column property="pieceOfText" title="${pieceOfTextHeader}" />

	<spring:message code="customRecord.links" var="linksHeader" />
	<display:column property="links" title="${linksHeader}" />

	<jstl:if test="${ principal.id == curriculum.dancer.id}">
		<display:column>
			<a href="customRecord/edit.do?customRecordId=${row.id}"> <spring:message
					code="curriculum.edit"></spring:message></a>
		</display:column>
	</jstl:if>

</display:table>

<jstl:if test="${ principal.id == curriculum.dancer.id}">
	<a href="customRecord/create.do?curriculumId=${curriculum.id }"><spring:message
			code="customRecord.create" /></a>
</jstl:if>

<br />

<display:table name="endorserRecords" id="row"
	requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code="endorserRecord.fullName" var="fullNameHeader" />
	<display:column property="fullName" title="${fullNameHeader}" />
	
	<spring:message code="endorserRecord.contact.means" var="meansHeader" />
	<display:column property="contact.means" title="${meansHeader}" />
	
	<spring:message code="endorserRecord.contact.value" var="valueHeader" />
	<display:column property="contact.value" title="${valueHeader}" />

	<jstl:if test="${ principal.id == curriculum.dancer.id}">
		<display:column>
			<a href="endorserRecord/edit.do?endorserRecordId=${row.id}"> <spring:message
					code="curriculum.edit"></spring:message></a>
		</display:column>
	</jstl:if>

</display:table>

<jstl:if test="${ principal.id == curriculum.dancer.id}">
	<a href="endorserRecord/create.do?curriculumId=${curriculum.id }"><spring:message
			code="endorserRecord.create" /></a>
</jstl:if>

<br />
<br />

<acme:cancel code="curriculum.cancel" url="curriculum/list.do" />