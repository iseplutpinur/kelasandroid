<?php
include '../config/connection.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $cari_noinvoice = isset($_POST['no_invoice']) ? $_POST['no_invoice'] : null;
    $response = [];

    // 2. query Header Invoice berdasarkan No Invoice
    $query = queryFetch("SELECT tbl_invoice.no_invoice, tbl_invoice.tanggal_dibuat, tbl_invoice.mata_uang, tbl_invoice.no_suratjalan, tbl_po.no_po, tbl_po.tanggal_po, tbl_rek_perusahaan.kode_bank, tbl_staff.no_staff, tbl_staff.nama_staff, tbl_staff.posisi FROM tbl_transaksi JOIN tbl_po ON tbl_po.no_po=tbl_transaksi.no_po JOIN tbl_invoice ON tbl_invoice.no_invoice=tbl_transaksi.no_invoice JOIN tbl_rek_perusahaan ON tbl_rek_perusahaan.kode_bank=tbl_invoice.kode_bank JOIN tbl_staff ON tbl_staff.no_staff=tbl_invoice.no_staff WHERE tbl_invoice.no_invoice = '$cari_noinvoice'");

    if (!$query) {
        $response["message"] = "Maaf, data invoice tidak ada";
        $response["code"] = 404;
        $response["status"] = false;
    } else {
        $response["message"] = "Data dengan no invoice " . $cari_noinvoice . " berhasil di tampilkan";
        $response["code"] = 200;
        $response["status"] = true;

        // 2. Module Header Invoice berdasarkan No Invoice
        $response["header_invoice"] = $query;

        // 3. Module body invoice berdasarkan no invoice 
        $response['data_patner'] = queryFetch("select tbl_partner.kode_partner, tbl_partner.nama_partner ,  tbl_partner.alamat_partner, concat( tbl_partner.kota, ' - ', tbl_partner.kode_pos) as kodepos_kota from tbl_transaksi  join tbl_partner on tbl_partner.kode_partner  = tbl_transaksi.kode_partner where tbl_transaksi.no_invoice = '$cari_noinvoice'");

        // 4. List produk berdasarlam mo invoice
        $query = queryFetchArray("SELECT tbl_produk.kode_produk, tbl_produk.produk, tbl_produk.diskon, tbl_produk.minimum_request, tbl_produk.harga_satuan, tbl_produk.satuan, tbl_transaksi.jml_qty as jml_order, (tbl_produk.harga_satuan * tbl_transaksi.jml_qty) as jumlah_rupiah, IF(tbl_transaksi.jml_qty>=tbl_produk.minimum_request, 'Diskon', 'Tidak Diskon') as keterangan, IF(tbl_transaksi.jml_qty>=tbl_produk.minimum_request, ROUND(((tbl_produk.harga_satuan * tbl_transaksi.jml_qty)*tbl_produk.diskon)/100 , 0) , 0) as potongan_rupiah, (tbl_produk.harga_satuan * tbl_transaksi.jml_qty) - IF(tbl_transaksi.jml_qty>=tbl_produk.minimum_request, ROUND(((tbl_produk.harga_satuan * tbl_transaksi.jml_qty)*tbl_produk.diskon)/100 , 0) , 0) as total_harga FROM tbl_transaksi JOIN tbl_produk ON tbl_produk.kode_produk = tbl_transaksi.kode_produk WHERE tbl_transaksi.no_invoice = '$cari_noinvoice'");
        $response['listpo_produk'] = $query['result'];
        $response['jumlah_produk'] = $query['count'];

        // 5. Menampilkan Total, PPN dan ongkir
        $subtotal = queryFetch("SELECT sum((tbl_produk.harga_satuan * tbl_transaksi.jml_qty) - IF(tbl_transaksi.jml_qty>=tbl_produk.minimum_request, ROUND(((tbl_produk.harga_satuan * tbl_transaksi.jml_qty)*tbl_produk.diskon)/100 , 0) , 0)) as subtotal FROM tbl_transaksi JOIN tbl_produk ON tbl_produk.kode_produk = tbl_transaksi.kode_produk WHERE tbl_transaksi.no_invoice = 'FT/12/02/2021'")['subtotal'];

        $response['datatotalpo'] = [
            'subtotal' => (int)$subtotal,
            'ppn' => getOngkirDanPpn($subtotal)['ppn'],
            'ongkir' => getOngkirDanPpn($subtotal)['ongkir']
        ];
    }
    responseSender($response);
} else {
    responseSender([
        "message" => "Sory, API ini memerlukan method POST",
        "code" => 401,
        "status" => false
    ]);
}

function getOngkirDanPpn($subtotal)
{
    $a = [];
    $subtotal = (int) $subtotal;

    if ($subtotal >= 50000000) {
        $a = [
            'ppn' => 50,
            'ongkir' => 18
        ];
    } else if ($subtotal >= 45000000 && $subtotal < 50000000) {
        $a = [
            'ppn' => 45,
            'ongkir' => 15
        ];
    } else if ($subtotal >= 40000000 && $subtotal < 45000000) {
        $a = [
            'ppn' => 40,
            'ongkir' => 12
        ];
    } else if ($subtotal >= 35000000 && $subtotal < 40000000) {
        $a = [
            'ppn' => 35,
            'ongkir' => 9
        ];
    } else if ($subtotal >= 30000000 && $subtotal < 35000000) {
        $a = [
            'ppn' => 30,
            'ongkir' => 6
        ];
    } else if ($subtotal >= 25000000 && $subtotal < 30000000) {
        $a = [
            'ppn' => 25,
            'ongkir' => 3
        ];
    } else {
        $a = [
            'ppn' => 10,
            'ongkir' => 5
        ];
    }

    return [
        'ppn' => round($subtotal * ($a['ppn'] / 100)),
        'ongkir' => round($subtotal * ($a['ongkir'] / 100))
    ];
}
