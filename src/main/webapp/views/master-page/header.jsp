<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<div>
	<img src="images/logo.png" alt="Acme-Dancer2.0 Co., Inc." />
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->

		<li><a class="fNiv" href="academy/list.do"><spring:message
					code="master.page.academyList" /></a></li>
		<li><a class="fNiv" href="course/list.do"><spring:message
					code="master.page.courseList" /></a></li>
		<li><a href="style/list.do"><spring:message
					code="master.page.style.list" /></a></li>
		<li><a class="fNiv" href="course/searchForm.do"><spring:message
					code="master.page.searchCourse" /></a></li>

		<security:authorize access="hasRole('ADMIN')">
			<li><a href="administrator/dashboard.do"><spring:message
						code="master.page.administrator.dashboard" /></a></li>

			<li><a class="fNiv" href="trackson/list.do"><spring:message
						code="master.page.trackson" /></a></li>

		</security:authorize>

		<security:authorize access="hasRole('DANCER')">
			<li><a class="fNiv" href="apply/list.do"><spring:message
						code="master.page.apply" /></a></li>
		</security:authorize>

		<security:authorize access="hasRole('ACADEMY')">
			<li><a class="fNiv" href="apply/list.do"><spring:message
						code="master.page.apply" /></a></li>
			<li><a class="fNiv"><spring:message
						code="master.page.courses" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="course/myList.do"><spring:message
								code="master.page.course.myList" /></a></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message
						code="master.page.login" /></a></li>
			<li><a class="fNiv" href="dancer/register.do"><spring:message
						code="master.page.register.dancer" /></a></li>
			<li><a class="fNiv" href="academy/register.do"><spring:message
						code="master.page.register.academy" /></a></li>
		</security:authorize>

		<security:authorize access="hasRole('DANCER')">
			<li><a class="fNiv" href="curriculum/list.do"><spring:message
						code="master.page.curricula" /></a></li>
			<li><a class="fNiv"> <spring:message
						code="master.page.profile" /> (<security:authentication
						property="principal.username" />)
			</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="dancer/editProfile.do"><spring:message
								code="master.page.editProfile" /></a></li>
					<li><a href="j_spring_security_logout"><spring:message
								code="master.page.logout" /> </a></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="hasRole('ACADEMY')">
			<li><a class="fNiv"> <spring:message
						code="master.page.profile" /> (<security:authentication
						property="principal.username" />)
			</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="academy/editProfile.do"><spring:message
								code="master.page.editProfile" /></a></li>
					<li><a href="j_spring_security_logout"><spring:message
								code="master.page.logout" /> </a></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"> <spring:message
						code="master.page.profile" /> (<security:authentication
						property="principal.username" />)
			</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/editProfile.do"><spring:message
								code="master.page.editProfile" /></a></li>

					<li><a href="j_spring_security_logout"><spring:message
								code="master.page.logout" /> </a></li>
				</ul></li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

