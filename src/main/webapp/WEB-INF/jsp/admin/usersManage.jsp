<%@include file="/WEB-INF/jsp/includes/standartVariables.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
    <style>
        <%@include file="/WEB-INF/css/table.css" %>
    </style>
    <title><fmt:message key="sign_in" bundle="${bundle}"/></title>
</head>
<body>
<header>
    <%@include file="/WEB-INF/jsp/includes/navbar.jsp" %>
</header>
<main>
    <div class="container my-3">
        <table class="table table-light table-sortable" id="myTable">
            <thead>
            <tr>
                <th class="columnToSort th-sort-asc" scope="col"><fmt:message key="login" bundle="${bundle}"/></th>
                <th class="columnToSort th-sort-asc" scope="col"><fmt:message key="role" bundle="${bundle}"/></th>
                <th class="columnToSort th-sort-asc" scope="col"><fmt:message key="status" bundle="${bundle}"/></th>
                <th class="col"></th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${requestScope.users}" var="users">
                <c:if test="${users.role.toString() != 'Admin'}">
                    <tr>
                        <td>
                                ${users.login}
                        </td>
                        <td>
                                ${users.role.toString()}
                        </td>
                        <td>
                                ${users.status.toString()}
                        </td>
                        <td class="d-flex justify-content-center">
                            <c:choose>
                                <c:when test="${users.status.toString() == 'Unblocked'}">
                                    <form action="${url}/updateUserStatus?status=Blocked" method="post">
                                        <input type="text" name="userId" value="${users.id}" hidden>
                                        <button type="submit" class="btn btn-danger"><fmt:message key="block"
                                                                                                   bundle="${bundle}"/></button>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <form action="${url}/updateUserStatus?status=Unblocked" method="post">
                                        <input type="text" name="userId" value="${users.id}" hidden>
                                        <button type="submit" class="btn btn-primary"><fmt:message key="unblock"
                                                                                                   bundle="${bundle}"/></button>
                                    </form>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </div>
</main>
</body>
<script>
    <%@include file="/WEB-INF/js/TableScripts.js" %>
</script>
</html>
