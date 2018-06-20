<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lucky Sevens</title>
    </head>
    <body>
        <h2>Lucky Sevens</h2>
        <form method = "POST" action="LuckySevensJspServlet">
            <p>
                Instructions:
            </p>
            <p>
                Please enter your bet:
                <input type="text" name="userBet" />
            </p>
            <p>
            <input type="submit" value="Place Bet"/>
            </p>
        </form>
    </body>
</html>
