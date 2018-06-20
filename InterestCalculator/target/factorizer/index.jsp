<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Interest Calculator</title>
    </head>
<body>
    <h2>Interest Calculator</h2>
    <form method="POST" action="InterestCalculator">
        <p>
            Annual interest rate:
            <input type="text" name="interestRate"/>
        </p>
        <p>
            Initial amount of principle:
            <input type="text" name="principleAmount"/>
        </p>
        <p>
            Number of years to age:
            <input type="text" name="yearsToAge"/>
        </p>
        <input type="submit" value="Calculate"/>
    </form>

</body>
</html>
