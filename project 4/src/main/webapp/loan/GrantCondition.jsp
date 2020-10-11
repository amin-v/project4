<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.entity.LoanType" %><%--
  Created by IntelliJ IDEA.
  User: Amin
  Date: 9/28/2020
  Time: 10:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% LoanType loanType = (LoanType) request.getAttribute("loanType");
%>

<html>
<head>
    <title> home</title>
    <link rel="stylesheet" href="/style/bootstrap.css"/>
    <link rel="stylesheet" href="/style/bootstrap.min.css"/>
    <link rel="stylesheet" href="/style/bootstrap-theme.css"/>
    <link rel="stylesheet" href="/style/bootstrap-theme.min.css"/>
    <script src="/style/bootstrap.js"></script>
    <script src="/style/bootstrap.min.js.js"></script>
</head>


<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">WebSiteName</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#">Page 1</a></li>
            <li><a href="#">Page 2</a></li>
            <li><a href="#">Page 3</a></li>
        </ul>
    </div>
</nav>
<script type="text/javascript" src="create-table.js"></script>
<title>تعریف شروط اعطا تسهیلات</title>
</head>

<div class="container">
    <div class="panel panel-info">
        <div class="panel-heading text-center">
            <h2> تسهیلات <%=loanType.getLoanName()%> با نرخ سود <%=loanType.getInterestRate()%> درصد</h2>
        </div>
    </div>
    <div class="panel-heading text-right">
        <h3 style="color: saddlebrown">:لطفا شرایط اعطا را وارد نمایید</h3>
    </div>
    <br>
    <form action="/loanType/CreateGrantCondition.do" method="post">
        <table class="table-hover">
            <tr>
                <td>نام شرط اعطا:</td>
                <td><input name="grantName" id="grantName" type="text" placeholder="نام"></td>
            </tr>

            <tr>
                <td>حداقل مدت :</td>
                <td><input name="minDuration" id="minDuration" type="number" placeholder="  حداقل مدت قرارداد"></td>

            </tr>
            <tr>
                <td>حداکثر مدت :</td>
                <td><input name="maxDuration" id="maxDuration" type="number" placeholder="  حداکثر مدت قرارداد"></td>
            </tr>
            <tr>
                <td>حداقل مبلغ :</td>
                <td><input name="minAmount" id="minAmount" type="number" placeholder="  حداقل مبلغ قرارداد"></td>

            </tr>
            <tr>
                <td>حداکثر مبلغ :</td>
                <td><input name="maxAmount" id="maxAmount" type="number" placeholder="  حداکثر مبلغ قرارداد"></td>
            </tr>
        </table>

        <button class="button" onclick="showTable()">نمایش</button>

        <input type="text" name="loanName" value="<%=request.getParameter("loanName")%>">
        <input type="text" name="interestRate" value="<%=request.getParameter("interestRate")%>">
        <input type="number" name="rowNumber" id="rowNumber">

        <div class="searchTable">
            <table align="center" id="grantTableId">
            </table>
        </div>
        <input type="submit" class="button" value="ثبت اطلاعات">
    </form>


    <div class="panel-body">
        <table class="table-hover">
            <tr>
                <th><input class="form-control" type="text" readonly value="rowNumber"></th>
                <th><input class="form-control" type="text" readonly value="loanName"></th>
                <th><input class="form-control" type="text" readonly value="minDuration"></th>
                <th><input class="form-control" type="text" readonly value="maxDuration"></th>
                <th><input class="form-control" type="text" readonly value="minAmount"></th>
                <th><input class="form-control" type="text" readonly value="maxAmount"></th>
            </tr>

            <c:forEach items="${requestScope.grantConditons}" var="grantCondition">
                <form class="form-group" action="/grantCondition/find.do">
                    <tr>
                        <td><input type="number" name="id" id="id" class="form-control" value="${grantCondition.GrantConditionId}" readonly/></td>
                        <td><input type="text" name="name" class="form-control" value="${grantCondition.name}" /></td>
                        <td><input type="text" name="family" class="form-control" value="${grantCondition.minDuration}"/></td>
                        <td><input type="text" name="fatherName" class="form-control" value="${grantCondition.maxDuration}"/></td>
                        <td><input type="date" name="birthDate" class="form-control" value="${grantCondition.minAmount}"/></td>
                        <td><input type="number" name="nationalCode" class="form-control" value="${grantCondition.manAmount}"/></td>
                        <td><input type="submit" class="btn btn-info" value="Find"/></td>
                        <td><input type="button" class="btn btn-danger" value="نمایش" onclick="showTable()"/></td>
                    </tr>
                </form>
            </c:forEach>
        </table>

    </div>
</div>
</body>
</html>