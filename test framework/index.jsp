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


    <form action="Emp-save" method="post" enctype="multipart/form-data">
        <input type="text" name="nom">
        <input type="number" name="numero">
        <input type="file" name="file">
        <input type="submit" value="ok">
    </form>
</body>
</html>