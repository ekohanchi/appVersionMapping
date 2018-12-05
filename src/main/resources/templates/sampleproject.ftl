<!DOCTYPE HTML>
<html>
<head>
<title>Sample Project</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/styles.css" />
</head>
<body>
    <div class="container container-navbar">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Application Version Mapping</a>
        </div>
    </div>
    <div class="container api-container">

        <!-- Main content. -->
        <div class="table-content">
            <ol class="breadcrumb">
                <li><a href="/">Home</a></li>
                <li class="active">Sample Project</li>
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
            <div class="envs">STAGE | PROD (Origin)</div>
            <table class="striped">
                <thead>
                    <tr>
                        <th>Application</th>
                        <th>STAGE</th>
                        <th>PROD</th>
                    </tr>
                </thead>
                <tbody>
                    <#list applicationsStageProd as application>
                    <tr>
                        <td rowspan="2"><b>${application.name}</b></td>
                        <td class="vertext">${application.stage1version!"NULL"}</td>
                        <td class="vertext">${application.prod1version!"NULL"}</td>
                    </tr>
                    <tr>
                        <td class="vertext">${application.stage2version!"NULL"}</td>
                        <td class="vertext">${application.prod2version!"NULL"}</td>
                    </tr>
                    </#list>
                </tbody>
            </table>
            <br>
            <div id="mashery" class="envs">DEV | QA | UAT
                (Mashery)</div>
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
                    <#list applicationsMashery as application>
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
            <div class="envs">STAGE | PROD (Mashery)</div>
            <table class="striped">
                <thead>
                    <tr>
                        <th>Application</th>
                        <th>STAGE</th>
                        <th>PROD</th>
                    </tr>
                </thead>
                <tbody>
                    <#list applicationsStageProdMashery as application>
                    <tr>
                        <td rowspan="2"><b>${application.name}</b></td>
                        <td class="vertext">${application.stageversion!"NULL"}</td>
                        <td class="vertext">${application.prodversion!"NULL"}</td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
        <!-- /Main content. -->
    </div>
</body>
</html>