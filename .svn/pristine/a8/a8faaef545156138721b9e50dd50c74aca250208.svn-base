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

<form:form action="style/edit.do" modelAttribute="style">

  <form:hidden path="id" />
  <form:hidden path="version" />

  <form:hidden path="courses" />

  <acme:textbox code="style.name" path="name" />
  <br />

  <acme:textbox code="style.description" path="description" />
  <br />

  <acme:textbox code="style.pictures" path="pictures" />
  <br />

  <acme:textbox code="style.videos" path="videos" />
  <br />

  <jstl:choose>
    <jstl:when test="${style.id == 0}">
      <acme:submit name="save" code="style.submit" />
      <acme:cancel code="style.cancel" url="style/list.do" />
    </jstl:when>
    <jstl:otherwise>
      <acme:submit name="save" code="style.submit" />
      <acme:submit name="delete" code="style.delete" />
      <acme:cancel code="style.cancel" url="style/list.do" />
    </jstl:otherwise>
  </jstl:choose>
</form:form>