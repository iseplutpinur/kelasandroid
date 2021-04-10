CREATE DATABASE db_invoice;

USE db_invoiceapi;

SHOW TABLES;

-- Drop Relasi table Staff dari table Transaksi
ALTER TABLE
    tbl_transaksi DROP FOREIGN KEY fk_staff2transaksi;

-- Drop Field no_staff dari table Transaksi
DESC tbl_transaksi;

ALTER TABLE
    `db_invoice`.`tbl_transaksi` DROP PRIMARY KEY,
ADD
    PRIMARY KEY (
        `id_transaksi`,
        `no_invoice`,
        `no_po`,
        `kode_partner`,
        `kode_produk`
    );

ALTER TABLE
    tbl_transaksi DROP no_staff;

-- Tambah Field no_staff pada table invoice & set primary key 
ALTER TABLE
    tbl_invoice
ADD
    COLUMN no_staff VARCHAR(60) NOT NULL;

DESC tbl_invoice;

-- Edit data pada table invoice 
UPDATE
    tbl_invoice
SET
    no_staff = '8912371237'
WHERE
    no_invoice = 'FT/30/01/2021';

UPDATE
    tbl_invoice
SET
    no_staff = '2801923813'
WHERE
    no_invoice = 'FT/12/02/2021';

DESC tbl_invoice;

ALTER TABLE
    `db_invoice`.`tbl_invoice` DROP PRIMARY KEY,
ADD
    PRIMARY KEY (
        `no_invoice`,
        `kode_bank`,
        `no_suratjalan`,
        `kode_manager`,
        `no_staff`
    );

-- Membuat Relasi table staff ke table invoice
ALTER TABLE
    tbl_invoice
ADD
    CONSTRAINT fk_staff2invoice FOREIGN KEY (no_staff) REFERENCES tbl_staff (no_staff);

SELECT
    tbl_invoice.no_invoice,
    tbl_invoice.tanggal_dibuat,
    tbl_invoice.mata_uang,
    tbl_invoice.no_suratjalan,
    tbl_po.no_po,
    tbl_po.tanggal_po,
    tbl_rek_perusahaan.kode_bank,
    tbl_staff.no_staff,
    tbl_staff.nama_staff,
    tbl_staff.posisi
FROM
    tbl_transaksi
    JOIN tbl_po ON tbl_po.no_po = tbl_transaksi.no_po
    JOIN tbl_invoice ON tbl_invoice.no_invoice = tbl_transaksi.no_invoice
    JOIN tbl_rek_perusahaan ON tbl_rek_perusahaan.kode_bank = tbl_invoice.kode_bank
    JOIN tbl_staff ON tbl_staff.no_staff = tbl_invoice.no_staff
WHERE
    tbl_invoice.no_invoice = '$cari_noinvoice';

SELECT
    tbl_produk.kode_produk,
    tbl_produk.produk,
    tbl_produk_diskon,
    tbl_produk.minimum_request,
    tbl_produk.harga_satuan,
    tbl_produk.satuan,
    tbl_transaksi.jml_qty as 'jml_order',
FROM
    tbl_transaksi
    JOIN tbl_produk ON tbl_produk.kode_produk = tbl_transaksi.kode_produk
WHERE
    tbl_transaksi.no_invoice = 'FT/12/02/2021'