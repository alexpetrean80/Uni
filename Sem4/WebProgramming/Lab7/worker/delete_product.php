<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Delete Product</title>
</head>

<body>
    <?php
    $connection =  new mysqli("localhost", "alex", "laura", "acme_eccommerce");
    if (!$connection) {
        die("Could not establish connection: " . mysqli_connect_error());
    }

    $query = "SELECT * from Products";

    $res = $connection->query($query);

    if ($res->num_rows > 0) {
        while ($row = $res->fetch_assoc()) {
            echo $row['sku'] . ' ' . $row['name'] . '<br>';
        }
    }

    $connection->close();
    ?>
    <form method="POST" action="delete_product_backend.php">
        <label>SKU of the product to delete
            <input type="text" name="productSku" placeholder="SKU">
        </label>
        <input type="submit" value="Delete product">
    </form>
</body>

</html>