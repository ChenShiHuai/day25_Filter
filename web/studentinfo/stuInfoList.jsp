<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/20 0020
  Time: 上午 1:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学生信息列表页面</title>
    <style>
        p{
            font-size: 16px;
            color: aqua;
        }
    </style>

    <script>
        function removeStu(){
            //提交表单，表单form对象的提交事件
            document.getElementById("form").submit();
        }
    </script>
</head>
<body>
<p align="center">
    <a href="${pageContext.request.contextPath}/stuInfoServlet?method=list">展示学生信息列表</a>
</p>

<c:if test="${not empty stuList}">
    <div>
        <a href="${pageContext.request.contextPath}/stuInfoServlet?method=toAdd">添加</a>
        <a href="javascript:void(0);" onclick="removeStu();">批量删除</a>
    </div>
<%--<form>
    爱好：
    <input type="checkbox" name="love" value="1"/>篮球
    <input type="checkbox" name="love" value="2"/>足球
</form>--%>


<form id="form" action="${pageContext.request.contextPath}/stuInfoServlet?method=remove" method="post">


    <table border="1" width="100%" align="center" cellspacing="0">
        <caption>学生信息表</caption>
        <tr>
            <td>
                <input type="checkbox" name="uid"/>
            </td>
            <td>编号</td>
            <td>姓名</td>
            <td>性别</td>
            <td>年龄</td>
            <td>院系</td>
            <td>班级</td>
            <td>入校时间</td>
            <td>电话</td>
            <td>籍贯</td>
            <td>操作</td>

        </tr>

        <%--var 表示临时变量，没循环一次都会把一个对象赋值给stu--%>
        <c:forEach items="${stuList}" var="stu" varStatus="s">
            <tr>
                <td>
                    <input type="checkbox" name="uid" value="${stu.id}"/>
                </td>
                <%--循环状态对象的循环次数--%>
                <td>${s.count}</td>
                <td>${stu.name}</td>
                <td>${stu.sex}</td>
                <td>${stu.age}</td>
                <td>${stu.ys}</td>
                <td>${stu.className}</td>
                <td>${stu.hireDate}</td>
                <td>${stu.tel}</td>
                <td>${stu.jg}</td>
                <td>
                    <%--编辑和删除的时候，完成id的传递--%>
                    <a href="${pageContext.request.contextPath}/stuInfoServlet?method=toEdit&id=${stu.id}">编辑</a>
                    |
                    <a href="${pageContext.request.contextPath}/stuInfoServlet?method=remove&uid=${stu.id}">删除</a>
                </td>
            </tr>
        </c:forEach>

    </table>
</form>

</c:if>

</body>
</html>
