<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart</title>
</head>

<body>
    <h1>Shopping Cart</h1>
    <table border="3px solid black">
        <th>
        <td>Product SKU</td>
        <td>Product Name</td>
        <td>Quantity</td>
        <td>TotalPrice</td>
        </th>
        <?php
        $basket = fopen("bucket.csv", "r");
        while ($line = fgets($basket)) {
            $product = explode(",", $line);
            echo "<tr>";
            echo "<td></td>";
            echo "<td>" . $product[0] . "</td>"; // sku
            echo "<td>" . $product[1] . "</td>"; // name
            echo "<td>" . $product[2] . "</td>"; // quantity
            echo "<td>" . $product[3] . "</td>"; // total
            echo "</tr>";
        }
        ?>
    </table>
    <form method="POST" action="finish_shopping.php">
        <input type="submit" value="Buy" />
    </form>
</body>

</html>