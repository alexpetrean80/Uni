<?php
$sku = $_POST["productSku"] + 0;
$quantity = $_POST["productQuantity"] + 0;


$connection =  mysqli_connect("localhost", "alex", "laura", "acme_eccommerce");
if (!$connection) {
    die("Could not establish connection: " . mysqli_connect_error());
}

$query = "SELECT * FROM Products WHERE sku = \"$sku\"";

$res = $connection->query($query);
$product = $res->fetch_assoc();
if ($quantity > $product["quantity"]) {
    echo "The stock is not large enough.";
}

$bucket = fopen("bucket.csv", "a") or die("Unable to open file.");

$csv = $product["sku"] . "," . $product["name"] . "," . $product["quantity"] . "," . $product["quantity"] * $product["price"];

fwrite($bucket, $csv);
fwrite($bucket, "\n");
fclose($bucket);
$query = "UPDATE Products SET quantity = " . --$product["quantity"]  . " where sku = $sku";
if (!$connection->query($query)) {
    echo "An error has occurred: " . $connection->error;
    $connection->close();
} else {
    $connection->close();
    header("Location: buy_products.php");
}
