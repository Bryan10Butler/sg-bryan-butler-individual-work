<%--
  Created by IntelliJ IDEA.
  User: n0280208
  Date: 3/13/2018
  Time: 6:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type"
          content="text/html; charset=UTF-8">
    <title>Tip Calculator</title>
</head>
<body>
    <h1>Result</h1>
    <p>
        This is the bill amount: ${inputBillAmount}
    </p>
    <p>
        This is the tip amount: ${inputPercentageAmount}
    </p>
    <p>
        This is what you are tipping: ${tipAmount}
    </p>
    <p>
        This is the total bill: ${totalBill}
    </p>
    <a href="index.jsp">Calculate Again!</a>

</body>
</html>
