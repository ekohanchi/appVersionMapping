<!DOCTYPE HTML>
<html>
<head>
<title>{PROJECT PLACEHOLDER}</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/styles.css" />
</head>
<body>
    <div class="container container-navbar">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">{PROJECT PLACEHOLDER}</a>
        </div>
    </div>
    <div class="container api-container">

        <!--<div class="row row-offcanvas row-offcanvas-right">-->
        <!-- Main content. -->
        <!--<div class="col-xs-10 col-sm-10 table-content">-->
        <div class="table-content">
            <ol class="breadcrumb">
                <li><a href="/">Home</a></li>
                <li class="active">{PROJECT PLACEHOLDER}</li>
                <li class="pull-right no-bar"><a href="#mashery"
                    class="pull-right">Jump to Mashery Section</a></li>
            </ol>
            <!-- Tables. -->
            <div class="envs">DEV | QA | UAT (Origin)</div>
            <table class="striped">
                <thead>
                    <tr>
                        <th>Application</th>
                        <th>DEV</th>
                        <th>QA</th>
                        <th>UAT</th>

                    </tr>
                </thead>
                <tbody>
                    <#list applications as application>
                    <tr>
                        <td><b>${application.name}</b></td>
                        <td class="vertext">${application.devversion!"NULL"}</td>
                        <td class="vertext">${application.qaversion!"NULL"}</td>
                        <td class="vertext">${application.uatversion!"NULL"}</td>
                    </tr>
                    </#list>
                </tbody>
            </table>
            <br>
        </div>
        <!--  /Main content. -->
        <!--</div>-->
    </div>
</body>
</html>