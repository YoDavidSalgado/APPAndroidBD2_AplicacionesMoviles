<?php
require_once("db.php");

// Verificar si se recibió un ID válido
if(isset($_GET['id'])) {
    // Obtener el ID del cliente
    $idCliente = $_GET['id'];

    // Consulta SQL para seleccionar el nombreCliente asociado al ID proporcionado
    $query = "SELECT nombreCliente FROM pedido WHERE idCliente = $idCliente";

    // Ejecutar la consulta
    $result = $mysql->query($query);

    // Verificar si se encontró el cliente con el ID proporcionado
    if ($result->num_rows > 0) {
        $row = $result->fetch_assoc();
        $nombreCliente = $row['nombreCliente'];
        // Mostrar el nombre del cliente
        echo json_encode(array("nombreCliente" => $nombreCliente));
    } else {
        // Si no se encontró ningún cliente con el ID proporcionado
        echo json_encode(array("message" => "No se encontró ningún cliente con el ID proporcionado"));
    }
} else {
    // Si no se proporcionó ningún ID válido
    echo json_encode(array("message" => "Se requiere un ID de cliente"));
}

// Cerrar la conexión a la base de datos
$mysql->close();
?>





