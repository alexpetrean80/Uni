<?php
include "utils/repo.php";

$name = $_POST["productName"];
$description = $_POST["productDescription"];
$price = $_POST["productPrice"];
$quantity = 0;

$conn = new mysqli("localhost", "alex", "laura", "acme_eccommerce");
if (!$conn) {
    die("Could not establish connection: " . $conn->error);
}

$query = sprintf("INSERT INTO Products (name, description, quantity, price) VALUES (\"%s\",\"%s\",\"%d\", \"%d\")",
    $name,
    $description,
    $quantity,
    $price);

if ($conn->query($query)) {
    $conn->close();
    header("Location: worker.html");
}
echo "An error has occurred: " . $conn->error;
$conn->close();

