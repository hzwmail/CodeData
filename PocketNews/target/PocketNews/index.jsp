<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<html>


<frameset rows="100,*">
    <frame name="head" src="${pageContext.servletContext.contextPath}/title.jsp">
    <frameset cols="30%,*">
        <frame name="left" src="${pageContext.servletContext.contextPath}/left_menu.jsp">
        <frame name="main" src="${pageContext.servletContext.contextPath}/content.jsp">
    </frameset>

</frameset>

</html>
