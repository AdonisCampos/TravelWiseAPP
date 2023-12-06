<?php
include 'conexion.php';
$nombre_usuario = $_GET["nombre_usuario"];

$query = "SELECT d.id as idDestino,d.titulo AS titulo_destino, u.usuario AS nombre_usuario
FROM destinos d
INNER JOIN usuarios u ON d.id_usuario = u.id where u.usuario = '$nombre_usuario'" ;

$resultado = mysqli_query($conexion, $query);

if ($resultado) {
    $destinos = array();
    while ($fila = mysqli_fetch_assoc($resultado)) {
        $destino = array(
            'idDestino' => $fila['idDestino'],
            'titulo_destino' => $fila['titulo_destino'],
            'nombre_usuario' => $fila['nombre_usuario']
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
