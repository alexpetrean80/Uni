<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
    <?php
    $connection =  mysqli_connect("localhost", "alex", "laura", "acme_eccommerce");
    if (!$connection) {
        die("Could not establish connection: " . mysqli_connect_error());
    }

    $query = "SELECT * from Products";

    $res = $connection->query($query);

    if ($res->num_rows > 0) {
        while ($row = $res->fetch_assoc()) {
            if ($row["quantity"] != 0) {
                echo $row['sku'] . ' ' . $row['name'] . ' ' . $row['description'] . ' ' . $row['price'] . ' ' . $row['quantity'] . '<br>';
            }
        }
    }

    $connection->close();
    ?>
    <form method="POST" action="buy_product_backend.php">
        <label>
            Enter the SKU of the product
            <input type="text" name="productSku" placeholder="SKU">
            <input type="text" name="productQuantity" placeholder="Quantity">
            <input type="submit" value="Buy">
        </label>
    </form>
</body>

</html>