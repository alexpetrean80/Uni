#!/usr/bin/env php

$uname = $_POST['uname'];
$passwd = $_POST['passwd'];

if (!isset($uname) || !isset($passwd)) {
    echo 'Invalid data.';
    header('Location: index.html');
}

if (!ctype_alnum($uname)) {
    echo 'Invalid username.';
    header('Location: index.html');
}
if (!ctype_alnum($passwd)) {
    echo 'Invalid username.';
    header('Location: index.html');
}

$conn = mysqli_connect('localhost', 'db_user', 'my_secret_password', 'app_db');

$query = mysqli_prepare("SELECT id FROM users WHERE uname = ? and passwd = ?");

$query->bind_param("ss", $uname, $passwd);

$query->execute();

$res = query->get_result();

$id = $res->fetch_assoc();
