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

<security:authorize access="hasRole('ADMIN')">
  <div>
    <h2>
      <a href="style/create.do"> <spring:message code="style.create"></spring:message></a>
    </h2>
  </div>
</security:authorize>

<display:table name="styles" id="row" requestURI="${requestURI}"
  pagesize="5" class="displaytag">

  <spring:message code="style.name" var="nameHeader" />
  <display:column property="name" title="${nameHeader}" />

  <spring:message code="style.description" var="descriptionHeader" />
  <display:column property="description" title="${descriptionHeader}" />

  <spring:message code="style.pictures" var="picturesHeader" />
  <display:column title="${picturesHeader}">
    <jstl:forEach items="${row.pictures}" var="picture">
      <img height="48" width="48" src="${picture}">
    </jstl:forEach>
  </display:column>

  <spring:message code="style.videos" var="videosHeader" />
  <display:column title="${videosHeader}">
    <jstl:forEach items="${row.videos}" var="video">
      <img height="48" width="48" src="${video}">
    </jstl:forEach>
  </display:column>

  <security:authorize access="hasRole('ADMIN')">
    <display:column>
      <a href="style/edit.do?styleId=${row.id}"> <spring:message
          code="style.edit"></spring:message></a>
    </display:column>
  </security:authorize>

  <display:column>
    <a href="course/listByStyle.do?styleId=${row.id}"> <spring:message
        code="style.listByCourse"></spring:message></a>
  </display:column>

</display:table>

