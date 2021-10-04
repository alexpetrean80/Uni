<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update product</title>
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
            echo $row['sku'] . ' ' . $row['name'] . ' ' . $row['description'] . ' ' . $row['price'] . ' ' . $row['quantity'] . '<br>';
        }
    }

    $connection->close();
    ?>
    <form action="update_product_backend.php" method="post">
        <label>
            Product SKU
            <input type="text" name="productSku" placeholder="SKU">
        </label>
        <label>
            Product Name
            <input type="text" name="productName" placeholder="Name">
        </label>
        <label>
            Product Description
            <input type="text" name="productDescription" placeholder="Description">
        </label>
        <label>
            Product Price
            <input type="text" name="productPrice" placeholder="Price">
        </label>
        <label>
            Product Quantity
            <input type="text" name="productQuantity" placeholder="Quantity">
        </label>
        <input type="submit" value="Update product">
        <button type="reset">Cancel</button>
    </form>
</body>

</html>