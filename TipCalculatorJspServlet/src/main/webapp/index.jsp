<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tip Calculator</title>
    </head>
<body>
    <h2>Tip Calculator</h2>
    <form method="POST" action="TipCalculatorJspServlet">
        <p>
            Please enter the amount of the bill:
            <input type="text" name="amountOnBill" />
        </p>
        <p>
            Please enter the tip percentage:
            <input type="text" name="tipPercentage"/>
        </p>
        <p>
            <input type="submit" value="Calculate"/>
        </p>

    </form>
</body>
</html>
