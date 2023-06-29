<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Form</title>
</head>
<body>
    <p> Who are you?</p>

    <a href="Emp-findById?id=1">1</a>
    <a href="Emp-findById?id=2">2</a>
    <a href="Emp-findById?id=3">3</a>
    <a href="Emp-findById?id=4">4</a>
    <a href="Emp-findById?id=5">5</a>


    <form action="Emp-save" method="post">
        <input type="text" name="nom">
        <input type="submit" value="ok">
    </form>
</body>
</html>