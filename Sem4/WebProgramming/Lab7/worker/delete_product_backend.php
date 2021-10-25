<?php

$sku = $_POST["productSku"];
echo $sku;
$conn = mysqli_connect("localhost", "alex", "laura", "acme_eccommerce");
if (!$conn) {
    die("Could not establish conn: " . mysqli_connect_error());
}

$query = sprintf("DELETE FROM \"Products\" WHERE sku = '%d'", $sku);
if ($conn->query($query)) {
    $conn->close();
    header("Location: worker.html");
}

echo "An error has occurred: " . $conn->error;
$conn->close();

