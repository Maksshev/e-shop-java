<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Shop</title>
    <link rel="stylesheet" href="list.css">
</head>
<body>
<div>
    Nice to see you, ${user.firstName}!
</div>
<form action="/auth" method="get">
    <button type="submit">Log out</button>
</form>
<#list items as item>
    <form action="/cart?commodityId=${item.id}&op=add" method="post">
        ${item.name}
        ${item.price}
        <button type="submit">Add to cart</button>
    </form>
</#list>
</body>
</html>