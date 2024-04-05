<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    require_once("db.php");

    // Obtener datos del formulario
    $idCliente = $_POST['idCliente'];
    $nombreCliente = $_POST['nombreCliente'];
    $cantidadJugos = $_POST['cantidadJugos'];
    $valor = $_POST['valor'];

    // Consulta SQL para actualizar el registro
    $query = "UPDATE pedido SET nombreCliente = '$nombreCliente', cantidadJugos = '$cantidadJugos', valor = '$valor' WHERE idCliente = '$idCliente'";
    
    // Ejecutar la consulta
    $result = $mysql->query($query);

    if ($result === TRUE) {
        echo "El usuario se modificó correctamente";
    } else {
        echo "Error al modificar el usuario";
    }

    // Cerrar la conexión a la base de datos
    $mysql->close();
}
?>
