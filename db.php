<?php

$mysql = new mysqli(
    "localhost",
    "root",
    "",
    "ventas"
);

if ($mysql->connect_error){
    die("Failed to connect: " . $mysql->connect_error);
} 
// else {
//     echo "Connected successfully to the database.";
// }
