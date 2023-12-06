<?php
include 'conexion.php';

$nombre_usuario = $_GET["nombre_usuario"];

// Preparar la consulta con una sentencia preparada
$query = "SELECT * FROM usuarios WHERE usuario = ?";
$stmt = mysqli_prepare($conexion, $query);

// Vincular el parámetro y ejecutar la consulta
mysqli_stmt_bind_param($stmt, "s", $nombre_usuario);
mysqli_stmt_execute($stmt);

$resultado = mysqli_stmt_get_result($stmt);

if ($resultado) {
    $destinos = array();
    while ($fila = mysqli_fetch_assoc($resultado)) {
        $destino = array(
            'id' => $fila['id'],
            'usuario' => $fila['usuario'],
            'correo' => $fila['correo'],
            'password' => $fila['password']
        );
        $destinos[] = $destino;
    }

    echo json_encode($destinos);
} else {
    $respuesta = array(
        "exito" => false,
        "mensaje" => "Error al obtener datos de destinos: " . mysqli_error($conexion)
    );
    echo json_encode($respuesta);
}

mysqli_close($conexion);
?>
