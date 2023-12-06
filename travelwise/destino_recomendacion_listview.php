<?php
include 'conexion.php';

$idDestino = $_GET['id_destino']; // Obtener el ID del destino desde la solicitud GET

    $query = "SELECT dr.id as idDestinoRecomendacion, dr.recomendacion AS titulo_recomendacion
    FROM destinos_recomendaciones dr WHERE dr.id_destino = $idDestino"; // Filtrar por el ID del destino

$resultado = mysqli_query($conexion, $query);

if ($resultado) {
    $recomendaciones = array();
    while ($fila = mysqli_fetch_assoc($resultado)) {
        $recomendacion = array(
            'idDestinoRecomendacion' => $fila['idDestinoRecomendacion'],
            'titulo_recomendacion' => $fila['titulo_recomendacion']
        );
        $recomendaciones[] = $recomendacion;
    }

    echo json_encode($recomendaciones);
} else {
    $respuesta = array(
        "exito" => false,
        "mensaje" => "Error al obtener datos de recomendaciones: " . mysqli_error($conexion)
    );
    echo json_encode($respuesta);
}

mysqli_close($conexion);
?>

?>
