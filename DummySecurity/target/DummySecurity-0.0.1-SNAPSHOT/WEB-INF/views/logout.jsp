<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
  // Clear any session variables or attributes
  session.invalidate();
%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Logout</title>
</head>
<body>
  <h1>Logout Successful</h1>
  <p>You have been logged out.</p>
  <p>Redirecting to the login page...</p>

  <%-- Redirect to the login page after a short delay --%>
  <script>
    setTimeout(function() {
      window.location.href = "login";
    }, 2000);
  </script>
</body>
</html>
