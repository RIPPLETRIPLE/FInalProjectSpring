<%@include file="/WEB-INF/jsp/includes/standartVariables.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
    <title><fmt:message key="main_page" bundle="${bundle}"/></title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/main.css"%>
</style>
<header>
    <%@include file="includes/navbar.jsp" %>
</header>
<main>
    <div class="container">
        <div class="d-flex justify-content-start card-header my-3">
            <div><fmt:message key="all_products" bundle="${bundle}"/></div>
        </div>
        <form class="d-flex justify-content-between" action="${url}/mainPage"
              method="get">
            <input type="text" name="page" value="${param.getOrDefault("page", "1")}" hidden>
            <input type="text" name="sortBy" value="${param.getOrDefault("sortBy", "id")}" hidden>
            <input type="text" name="order" value="${param.getOrDefault("order", "asc")}" hidden>
            <div>
                <h4><fmt:message key="category" bundle="${bundle}"/></h4>
                <select name="category">
                    <option value="" selected><fmt:message key="choose_option" bundle="${bundle}"/></option>
                    <c:forEach items="${requestScope.categories}" var="category">
                    <option id="${category.name}" value="${category.id}">${category.name}</option>
                        </c:forEach>
                </select>
            </div>
            <div>
                <h4><fmt:message key="color" bundle="${bundle}"/></h4>
                <select name="color">
                    <option value="" selected><fmt:message key="choose_option" bundle="${bundle}"/></option>
                    <c:forEach items="${requestScope.colors}" var="color">
                    <option id="${color.color}" value="${color.id}">${color.color}</option>
                        </c:forEach>
                </select>
            </div>
            <div>
                <h4><fmt:message key="size" bundle="${bundle}"/></h4>
                <select name="size">
                    <option value="" selected><fmt:message key="choose_option" bundle="${bundle}"/></option>
                    <c:forEach items="${requestScope.sizes}" var="item">
                    <option id="${item.size}" name="filterParam" value="${item.id}">${item.size}</option>
                        </c:forEach>
                </select>
            </div>
            <div>
                <h4><fmt:message key="sex" bundle="${bundle}"/></h4>
                <select name="sex">
                    <option value="" selected><fmt:message key="choose_option" bundle="${bundle}"/></option>
                    <option id="male" name="filterParam" value="Male"><fmt:message key="male" bundle="${bundle}"/></option>
                    <option id="female" name="filterParam" value="Female"><fmt:message key="female" bundle="${bundle}"/></option>
                    <option id="unisex" name="filterParam" value="Unisex"><fmt:message key="unisex" bundle="${bundle}"/></option>
                </select>
            </div>
            <div>
                <button type="submit"
                        class="btn btn-sm btn-primary"><fmt:message key="filter"
                                                                    bundle="${bundle}"/></button>
            </div>
        </form>


        <div class="btn-group">
            <button class="btn btn-secondary btn-sm dropdown-toggle" type="button" data-bs-toggle="dropdown"
                    aria-expanded="false">
                <fmt:message key="sort_by" bundle="${bundle}"/>
            </button>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item sortCards asc"
                       href="${url}/mainPage?sortBy=name&order=asc&page=${param.getOrDefault("page", "1")}&category=${param.getOrDefault("category", "%")}&size=${param.getOrDefault("size", "%")}&color=${param.getOrDefault("color", "%")}&sex=${param.getOrDefault("sex", "%")}"><fmt:message
                        key="by_name_high" bundle="${bundle}"/></a></li>
                <li><a class="dropdown-item sortCards asc"
                       href="${url}/mainPage?sortBy=name&order=desc&page=${param.getOrDefault("page", "1")}&category=${param.getOrDefault("category", "%")}&size=${param.getOrDefault("size", "%")}&color=${param.getOrDefault("color", "%")}&sex=${param.getOrDefault("sex", "%")}"><fmt:message
                        key="by_name_low" bundle="${bundle}"/></a></li>
                <li><a class="dropdown-item sortCards asc"
                       href="${url}/mainPage?sortBy=price&order=asc&page=${param.getOrDefault("page", "1")}&category=${param.getOrDefault("category", "%")}&size=${param.getOrDefault("size", "%")}&color=${param.getOrDefault("color", "%")}&sex=${param.getOrDefault("sex", "%")}"><fmt:message
                        key="by_price_low" bundle="${bundle}"/></a></li>
                <li><a class="dropdown-item sortCards"
                       href="${url}/mainPage?sortBy=price&order=desc&page=${param.getOrDefault("page", "1")}&category=${param.getOrDefault("category", "%")}&size=${param.getOrDefault("size", "%")}&color=${param.getOrDefault("color", "%")}&sex=${param.getOrDefault("sex", "%")}"><fmt:message
                        key="by_price_high" bundle="${bundle}"/></a></li>
                <li><a class="dropdown-item sortCards asc"
                       href="${url}/mainPage?sortBy=id&order=asc&page=${param.getOrDefault("page", "1")}&category=${param.getOrDefault("category", "%")}&size=${param.getOrDefault("size", "%")}&color=${param.getOrDefault("color", "%")}&sex=${param.getOrDefault("sex", "%")}"><fmt:message
                        key="by_date_low" bundle="${bundle}"/></a></li>
                <li><a class="dropdown-item sortCards"
                       href="${url}/mainPage?sortBy=id&order=desc&page=${param.getOrDefault("page", "1")}&category=${param.getOrDefault("category", "%")}&size=${param.getOrDefault("size", "%")}&color=${param.getOrDefault("color", "%")}&sex=${param.getOrDefault("sex", "%")}"><fmt:message
                        key="by_date_high" bundle="${bundle}"/></a></li>
            </ul>
        </div>
        <br/>

        <div class="row">
            <c:forEach items="${products}" var="product">
                <div class="col-md-3 my-3">
                    <div class="card w-100">
                        <img class="card-img-top" src="/././product-image/${product.img}"
                             alt="Card image cap" style="max-height: 300px;
                                                         min-height: 300px;
                                                         height: auto;
                                                         width: auto;">
                        <div class="card-body">
                            <h1 class="name" hidden>${product.name}</h1>
                            <h1 class="id" hidden>${product.id}</h1>
                            <h5 class="card-title">${product.name}</h5>
                            <h6><fmt:message key="price" bundle="${bundle}"/>:
                                <span class="price">${pageContext.request.getSession(false).getAttribute("lang") == 'ukr' ? 26 * product.price : product.price}</span>
                                <fmt:message key="currency" bundle="${bundle}"/></h6>
                            <div class="mt-3 d-flex justify-content-between">
                                <form method="post"
                                      action="${url}/addToCart">
                                    <input type="text" name="productId" value="${product.id}" hidden>
                                    <button type="submit"
                                            class="btn btn-dark"><fmt:message
                                            key="add_to_cart"
                                            bundle="${bundle}"/>
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <c:forEach items="${requestScope.pages}" var="page">
                    <li class="page-item">
                        <a class="page-link"
                           href="${url}/mainPage?page=${page}&category=${param.getOrDefault("category", "%")}&size=${param.getOrDefault("size", "%")}&color=${param.getOrDefault("color", "%")}&sex=${param.getOrDefault("sex", "%")}">${page}</a>
                    </li>
                </c:forEach>
            </ul>
        </nav>
    </div>
</main>
</body>
</html>