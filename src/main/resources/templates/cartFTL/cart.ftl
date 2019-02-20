<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cart</title>
    <link rel="stylesheet" href="cart.css">
</head>
<body>
<div class="container">
    <header>
        <div>
            Nice to see you, ${user.firstName}!
        </div>
        <a href="/list">To list</a>
        <form action="/auth" method="get">
            <button type="submit">Log out</button>
        </form>
    </header>
<#list items as item>
<div class="item">
    <form action="/cartgen?add_commodity=${item.id}" method="post">
        <span>${item.name}</span>
        <span>${item.price}$</span>
        <button type="submit">+</button>
    </form>
    ${item.amount}
    <form action="/cartgen?remove_commodity=${item.id}" method="post">
        <button type="submit">-</button>
    </form>
</div>
</#list>
    <div class="sum">Total sum: ${sum}$</div>
</div>
</body>
</html>

