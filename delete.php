<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    require_once("db.php");

    // Obtener ID del cliente a eliminar
    $idCliente = $_POST['idCliente'];

    // Consulta SQL para eliminar el registro
    $query = "DELETE FROM pedido WHERE idCliente = '$idCliente'";
    
    // Ejecutar la consulta
    $result = $mysql->query($query);

    if ($result === TRUE) {
        echo " El usuario se eliminó correctamente";
    } else {
        echo "Error al eliminar el usuario";
    }

    // Cerrar la conexión a la base de datos
    $mysql->close();
}
?>
