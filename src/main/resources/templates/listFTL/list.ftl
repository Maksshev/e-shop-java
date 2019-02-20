<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Shop</title>
    <link rel="stylesheet" href="list.css">
</head>
<body>
<div class="container">
    <header>
        <div class="hello">
            Nice to see you, ${user.firstName}!
        </div>
        <form action="/cartgen" method="get">
            <button class="tocart" type="submit">To cart</button>
        </form>
        <form action="/auth" method="get">
            <button class="logout" type="submit">Log out</button>
        </form>
    </header>

<#list items as item>
    <form class="item" action="/tocart?commodityId=${item.id}&op=add" method="post">
        <span>${item.name}</span>
        <span>${item.price}$</span>
        <button type="submit">Add to cart</button>
    </form>
</#list>
    <div>
</body>
</html>