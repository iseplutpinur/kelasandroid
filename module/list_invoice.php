<?php
include '../config/connection.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {
    $query = queryFetchArray("SELECT tbl_invoice.no_invoice, tbl_invoice.tanggal_dibuat, tbl_invoice.tgl_jatuhtempo, tbl_staff.no_staff, tbl_staff.nama_staff, tbl_staff.posisi FROM tbl_invoice JOIN tbl_staff ON tbl_staff.no_staff=tbl_invoice.no_staff");

    if ($query['count'] == 0) {
        $response["message"] = "Data tidak tersedia didatabase";
        $response["code"] = 404;
        $response["status"] = false;
    } else {
        $response["message"] = "Congrat!, Data list invoice tersedia didatabase";
        $response["code"] = 200;
        $response["status"] = true;

        // query database
        $response['invoicelist'] = $query['result'];
        $response["totaldata"] = $query['count'];
    }
    responseSender($response);
} else {
    responseSender([
        "message" => "Belum memenuhi syarat, ubah method menjadi POST",
        "code" => 401,
        "status" => false
    ]);
}
