<?php
    // get Data from HTML form using post
    $username = $_POST["username"];
    $password = $_POST["password"];

    // connect to database
    $serverName = "localhost";
    $connectionOptions = array(
        "Database" => "DemoDB",
        "Uid" => "sa",
        "PWD" => "DS440@Demo"
    );
    $conn = sqlsrv_connect($serverName, $connectionOptions);

    if (!$conn)
        die (var_dump(sqlsrv_errors()));

    // Perform Query
    $stmt = "SELECT * FROM Demo.Users WHERE username = ? AND password = ?;";
    $params = array($username, $password);
    $result = sqlsrv_query($conn, $stmt, $params);
    if ($result == FALSE)
        die (var_dump(sqlsrv_errors()));
    if (sqlsrv_has_rows($result)){
        $row = sqlsrv_fetch_array($result, SQLSRV_FETCH_ASSOC);
        echo "Welcome!" . " " . $row["firstname"] . " " . $row["lastname"] . ".<br/>";
    } else {
        echo "Login failed... <br/>";
    }
    echo "<a href='login.html'>Back to Login</a>";
    sqlsrv_free_stmt($result);
    sqlsrv_close($conn);
?>