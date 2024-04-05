<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST'){

    require_once("db.php");

    $idCliente = $_POST['idCliente'];
    $nombreCliente = $_POST['nombreCliente'];
    $cantidadJugos = $_POST['cantidadJugos'];
    $valor = $_POST['valor'];

    $query = "INSERT INTO pedido (idCliente, nombreCliente, cantidadJugos, valor) 
                VALUES ('$idCliente', '$nombreCliente', '$cantidadJugos', '$valor')";
    $result = $mysql->query($query);

    if ($result === TRUE){
        echo "el usuario fue creado exitosamente.";
    }else{
        echo "Error: " . $mysql->error; // Imprimir el error si hubo algún problema en la inserción
    }

    $mysql->close();
} else {
    echo "No POST data received."; // Mensaje si no se reciben datos POST
}
