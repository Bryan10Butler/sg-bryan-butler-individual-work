<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Factorizor</title>
    </head>
    <body>
        <h1>Factorizor</h1>
        <P>
            Please enter the number that you want to factor:
        </P>
        <P>
        <!--plain old HTML form-->
        <!--method post, action is factorizor servlet which
        is our url that we registered with tom cat-->
        <form method="POST" action="FactorizorServlet">
            <!--user input-->
            <input type="text" name="numberToFactor"/><br/>
            <!--button-->
            <input type="submit" value="Find Factors!!"/>
        </form>
        </P>
    </body>
</html>
