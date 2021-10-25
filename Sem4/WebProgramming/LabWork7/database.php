<html>
<head></head>
<body>
test...<br />
<?php 
    $addr = "localhost";
    $uname = "";
    $passwd = "";

    $conn = new mysqli($addr, $uname, $passwd);

    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }
    echo "Connected successfully.";

    $res = $conn->query("SELECT * FROM test_table");
    
    if ($res->num_rows === 0) {
        echo "No result";
    }

    while ($row = $res->fetch_assoc()) {
        echo $row;
        echo '\n';
    }
    $conn->close();
?>
</body>
</html> 