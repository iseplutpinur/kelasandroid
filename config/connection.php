<?php
$SERVER = "localhost:3306";
$USERBD = "root";
$PASSDB = "";
$DBNAME = "db_invoiceapi";

$_AUTH = mysqli_connect($SERVER, $USERBD, $PASSDB, $DBNAME);

if ($_AUTH) {
    // $response["message"] = "Akses kedatabase berhasil";
    // $response["code"] = 200;
    // $response["status"] = true;
} else {
    // $response["message"] = "Akses kedatabase gagal, silahkan coba lagi atau cek konfigurasi";
    // $response["code"] = 400;
    // $response["status"] = false;
}

// echo json_encode($response);

function responseSender($response = [])
{
    header("Access-Control-Allow-Origin: *");
    header("Content-Type: application/json; charset=UTF-8");
    $code = isset($response['code']) ? $response['code'] : 500;
    // unset($response['code']);
    http_response_code($code);
    echo json_encode($response);
}

function queryFetchArray($query = "")
{
    global $_AUTH;
    $sum = 0;
    $data = mysqli_query($_AUTH, $query);
    if ($data) {
        $rows = [];
        while ($row = mysqli_fetch_array($data, MYSQLI_ASSOC)) {
            $rows[] = $row;
            $sum++;
        }
        return ['count' => $sum, 'result' => $rows];
    } else return ['count' => $sum, 'result' => []];
}

function queryFetch($query = "")
{
    global $_AUTH;
    $data = mysqli_query($_AUTH, $query);
    if ($data) return mysqli_fetch_array($data, MYSQLI_ASSOC);
    else return [];
}
