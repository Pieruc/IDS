<%--
  Created by IntelliJ IDEA.
  User: romin
  Date: 14/05/2024
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Registrati</title>

    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>

</head>
<body>
<div class="container">
    <div class="row text-center" style="color: tomato;">
        <h2>Pagine di registrazione</h2>
    </div>
    <hr>
    <div class="row col-md-10 col-md-offset-3">

        <div class="card card-body">

            <h2>Form di registrazione</h2>
            <div class="col-md-8 col-md-offset-3">

                <form action="<%=request.getContextPath()%>/register" method="post">

                    <div class="form-group">
                        <label for="ruolo">Ruolo:</label> <input type="text"
                                                                      class="form-control" id="ruolo" placeholder="Turista / Contributor"
                                                                      name="ruolo" required>
                    </div>

                    <div class="form-group">
                        <label for="nome">Nome:</label> <input type="text"
                                                                     class="form-control" id="nome" placeholder="Nome"
                                                                     name="Nome" required>
                    </div>

                    <div class="form-group">
                        <label for="email">User Name:</label> <input type="email"
                                                                     class="form-control" id="email" placeholder="E-Mail"
                                                                     name="email" required>
                    </div>

                    <div class="form-group">
                        <label for="password">Password:</label> <input type="password"
                                                                    class="form-control" id="password" placeholder="Password"
                                                                    name="password" required>
                    </div>

                    <button type="submit" class="btn btn-primary">Submit</button>

                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
