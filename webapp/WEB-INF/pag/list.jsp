<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="content-type" content="text/html;charset=utf-8">

</head>

<body>
<h1>流水页面</h1>
<form action="/list.do?action=list" method="post">
用户；<input type="text" name="number"> <br>
密码：<input type="password" name="password">	<br>
<input type="submit" value="查询流水">
</form>

<table>
	<tr>
		<td>卡号</td>
		<td>金额</td>
		<td>备注</td>
	</tr>
	
	<c:forEach items="${flowInfo }" var="list">
		<tr>
			<td>${list.getNumber()}</td>
			<td>${list.getMoney()}</td>
			<td>${list.getDescription()}</td>
			<!-- 这里需要注意的是两点
				1:${}里面可以不用request相关的方法直接yongServlet中的对象名.方法/变量
					直接.变量 需要注意一点就是如果直接调用类的变量就需要在相关的对象所属的类中
					有相关的get和set方法,不然不可以调用,还有这里的${}需要和Mybatis中的#{}区别
				2:这里的流水显示使用的jstl jar包这里只要会用就行
			 -->
		</tr>
	</c:forEach>
	
</table>

</body>


</html>