<?php
include 'conexion.php';

if (isset($_GET["id_destino"])) {
    $id_destino = $_GET["id_destino"];

    // Realizar la consulta para eliminar el destino con el ID proporcionado
    $queryEliminar = "DELETE FROM destinos WHERE id = '$id_destino'";
    $resultadoEliminar = mysqli_query($conexion, $queryEliminar);

    if ($resultadoEliminar) {
        $respuesta = array(
            "exito" => true,
            "mensaje" => "Destino eliminado correctamente"
        );
        echo json_encode($respuesta);
    } else {
        $respuesta = array(
            "exito" => false,
            "mensaje" => "Error al eliminar el destino: " . mysqli_error($conexion)
        );
        echo json_encode($respuesta);
    }
} else {
    $respuesta = array(
        "exito" => false,
        "mensaje" => "ID de destino no proporcionado"
    );
    echo json_encode($respuesta);
}

mysqli_close($conexion);
?>
