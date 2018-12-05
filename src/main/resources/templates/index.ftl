<!DOCTYPE HTML>
<html>
<head>
<title>API Services Applications</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/styles.css" />
</head>
<body>
    <div class="container container-navbar">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">API Services
                Applications</a>
        </div>
    </div>
    <div class="container api-container">

        <div class="row row-offcanvas row-offcanvas-right">

            <!-- Vertical Nav. -->
            <div
                class="col-xs-2 col-sm-2 sidebar-offcanvas vertical-navbar">
                <ul>
                    <#list projects as project>
                    <li><a href="${project.link}"
                        class="vertical-navbar-active">${project.name!"NULL"}</a>
                        <ul>
                            <li><a href="${project.link}#mashery">Mashery
                                    Section</a></li>
                        </ul></li> </#list>
                </ul>
            </div>

        </div>
        <!--  Features -->
        <br>
        <h3>Upcoming features...</h3>
        <ul>
            <li>Response time reporting per each hit</li>
            <li>Request/Response headers reporting</li>
        </ul>

    </div>
    <!-- /Vertical Nav. -->
</body>
</html>