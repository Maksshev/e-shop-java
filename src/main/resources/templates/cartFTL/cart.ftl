<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cart</title>
    <link rel="stylesheet" href="cart.css">
</head>
<body>
<div>
    Nice to see you, ${user.firstName}!
</div>
<a href="/list">To list</a>
<form action="/auth" method="get">
    <button type="submit">Log out</button>
</form>
<#list items as item>
    <form action="/cartgen?add_commodity=${item.id}" method="post">
        ${item.name}
        ${item.price}
        <button type="submit">+</button>
    </form>
    ${item.amount}
    <form action="/cartgen?remove_commodity=${item.id}" method="post">
        <button type="submit">-</button>
    </form>
</#list>
<div>Total sum: ${sum}</div>
</body>
</html>

