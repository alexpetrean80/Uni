<?php

require_once "utils/repo.php";
$name = $_POST["productName"];
$description = $_POST["productDescription"];
$price = $_POST["productPrice"] + 0;
$quantity = $_POST["productQuantity"] + 0;
$sku = $_POST["productSku"] + 0;

$conn = new mysqli("localhost", "alex", "laura", "acme_eccommerce");
if (!$conn) {
    die("Could not establish conn: " . $conn->error);
}

$query = sprintf("UPDATE Products SET name = \"%s\", description = \"%s\", price = \"%s\", quantity = \"%s\" WHERE sku = \"%s\"",
    $name,
    $description,
    $price,
    $quantity,
    $sku);

if ($conn->query($query)) {
    $conn->close();
    header("Location: worker.html");
}

echo "An error has occurred: " . $conn->error;
$conn->close();

