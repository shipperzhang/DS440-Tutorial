<?php
    // get data from html form using post method
    $firstname = $_POST["firstname"];
    $lastname = $_POST["lastname"];
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
    $stmt = "INSERT INTO Demo.Users (username, password, firstname, lastname) VALUES (?,?,?,?);";
    $params = array($username, $password, $firstname, $lastname);
    $result = sqlsrv_query($conn, $stmt, $params);
    if ($result == FALSE)
        die (var_dump(sqlsrv_errors()));
    echo "Registration Successfully... <br/>";
    echo "<a href='login.html'>Back to Login</a>";
    sqlsrv_free_stmt($result);
    sqlsrv_close($conn);
?>