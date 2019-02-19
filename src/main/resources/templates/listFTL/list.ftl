<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Shop</title>
    <link rel="stylesheet" href="list.css">
</head>
<body>
<#list items as item>
    <div>
        ${item.name}
        ${item.price}
    </div>
</#list>
</body>
</html>