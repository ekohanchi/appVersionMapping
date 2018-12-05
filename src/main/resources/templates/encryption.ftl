<!DOCTYPE HTML>
<html>
<head>
<title>Encryption</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
    <h2>Encrypt Text</h2>
    <form name="encryption" action="encryptText" method="post">
        Text to encrypt:<br> <input size="40" maxlength="50" type="text" name="orgText">
        <br> <input type="submit">
    </form>
    <br> Original Text: ${originalText!"NULL"}
    <br> Encrypted Text:
    <br> ${encText!"NULL"}
    <br> Decrypted Text:
    <br> ${decText!"NULL"}

</body>
</html>