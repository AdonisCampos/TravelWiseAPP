<?php
include 'conexion.php';

$query = "SELECT id, nombre_pais FROM paises";

$resultado = mysqli_query($conexion, $query);

if ($resultado) {
    $paises = array();
    while ($fila = mysqli_fetch_assoc($resultado)) {
        $pais = array(
            'id' => $fila['id'],
            'nombre_pais' => $fila['nombre_pais']
        );
        $paises[] = $pais;
    }

    echo json_encode($paises);
} else {
    $respuesta = array(
        "exito" => false,
        "mensaje" => "Error al obtener datos de países: " . mysqli_error($conexion)
    );
    echo json_encode($respuesta);
}

mysqli_close($conexion);
?>
