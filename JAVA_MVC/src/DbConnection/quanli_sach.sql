-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 28, 2024 at 06:54 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.1.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `quanli_sach`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_chitietdonhang`
--

CREATE TABLE `tbl_chitietdonhang` (
  `id_chitiet` int(11) NOT NULL,
  `id_donhang` int(11) NOT NULL,
  `id_sanpham` int(11) NOT NULL,
  `soluong` int(11) NOT NULL,
  `tongtien` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_chitietdonhang`
--

INSERT INTO `tbl_chitietdonhang` (`id_chitiet`, `id_donhang`, `id_sanpham`, `soluong`, `tongtien`) VALUES
(1, 1, 1, 3, 150000),
(2, 1, 2, 10, 2900000),
(3, 2, 2, 10, 2900000),
(4, 2, 3, 5, 35000);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_chitietphieu`
--

CREATE TABLE `tbl_chitietphieu` (
  `id` int(11) NOT NULL,
  `ma_phieu` int(11) NOT NULL,
  `soluong` int(11) NOT NULL,
  `id_nxb` int(11) NOT NULL,
  `tien` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_danhmuc`
--

CREATE TABLE `tbl_danhmuc` (
  `id_danhmuc` int(11) NOT NULL,
  `tendanhmuc` varchar(100) NOT NULL,
  `mota` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_danhmuc`
--

INSERT INTO `tbl_danhmuc` (`id_danhmuc`, `tendanhmuc`, `mota`) VALUES
(1, 'Trinh thám', 'Kể về các câu chuyện liên quan các vụ án, bí ẩn kỳ lạ...'),
(2, 'Khoa học', 'Giải thích mọi thứ bằng khoa học'),
(3, 'Lãng mạn - tình cảm', 'Kể về các câu chuyện liên quan đến tình yêu và tình cảm của mọi thứ...'),
(4, 'Kinh tế', 'Nói về những câu chuyện liên quan đến tiền, tài chính và các lý thuyết kinh doanh'),
(5, 'Triết học', 'Đắm chìm trong những suy tưởng của các triết gia về mọi thứ quanh ta và bên trong ta'),
(6, 'Giáo dục', 'Học tập theo đó kiến thức, kỹ năng, được trao truyền từ thế hệ này sang thế hệ khác thông qua giảng '),
(7, 'Kỹ năng sống', 'Những kỹ năng cơ bản và cần thiết'),
(8, 'Phiêu lưu', 'Kể về các câu chuyện phiêu lưu');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_donhang`
--

CREATE TABLE `tbl_donhang` (
  `id_donhang` int(11) NOT NULL,
  `id_khachhang` int(11) NOT NULL,
  `ngaylap` datetime NOT NULL,
  `tien` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_donhang`
--

INSERT INTO `tbl_donhang` (`id_donhang`, `id_khachhang`, `ngaylap`, `tien`) VALUES
(1, 1, '2024-03-14 23:03:16', 200000),
(2, 2, '2024-03-08 23:04:08', 300000),
(3, 6, '2024-03-13 00:30:19', 600000),
(4, 3, '2024-03-23 00:43:36', 100000);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_khachhang`
--

CREATE TABLE `tbl_khachhang` (
  `id` int(11) NOT NULL,
  `hoten` varchar(50) NOT NULL,
  `sdt` varchar(10) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_khachhang`
--

INSERT INTO `tbl_khachhang` (`id`, `hoten`, `sdt`, `email`) VALUES
(1, 'Báo an ', '1234567897', 'an@gmail.com'),
(2, 'vanh', '1235647899', 'Vanh@gmail.com'),
(3, 'nam', '1234567899', 'nam@gmail.com'),
(6, 'luong', '1233232343', 'luong@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_muon`
--

CREATE TABLE `tbl_muon` (
  `id_muon` int(11) NOT NULL,
  `id_ban` int(10) NOT NULL,
  `id_sach` int(10) NOT NULL,
  `num` int(100) NOT NULL,
  `ngayMuon` date NOT NULL,
  `tinhTrang` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_muon`
--

INSERT INTO `tbl_muon` (`id_muon`, `id_ban`, `id_sach`, `num`, `ngayMuon`, `tinhTrang`) VALUES
(1, 1, 1, 123, '2024-03-05', 'chưa duyệt');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_nxb`
--

CREATE TABLE `tbl_nxb` (
  `id_nxb` int(11) NOT NULL,
  `tennxb` varchar(100) NOT NULL,
  `sdt` varchar(100) NOT NULL,
  `diachi` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_nxb`
--

INSERT INTO `tbl_nxb` (`id_nxb`, `tennxb`, `sdt`, `diachi`) VALUES
(4, 'Trẻ', '0123456789', 'Đống Đa, Hà Nội'),
(5, 'Kim Đồng', '0987654321', 'Thanh Xuân, Hà Nội'),
(6, 'hội nhà văn Việt Nam', '0124456786', 'Quận 10, Hồ Chí Minh'),
(7, 'Lao động', '09882233771', 'Nghi Lộc, Nghệ An'),
(8, 'Tư nhân Nhã Nam', '0112229998', 'Hà Đông, Hà Nội'),
(9, 'Phụ nữ', '01223344499', 'Quận 12, Hà Nội');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_phieu`
--

CREATE TABLE `tbl_phieu` (
  `id` int(11) NOT NULL,
  `ma` int(11) NOT NULL,
  `ngaylap` datetime NOT NULL,
  `id_taikhoan` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_sach`
--

CREATE TABLE `tbl_sach` (
  `id_sach` int(10) NOT NULL,
  `soLuong` int(100) NOT NULL,
  `tinhTrang` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_sanpham`
--

CREATE TABLE `tbl_sanpham` (
  `id_sanpham` int(11) NOT NULL,
  `ten_sanpham` varchar(100) NOT NULL,
  `id_danhmuc` int(11) DEFAULT NULL,
  `id_nxb` int(11) DEFAULT NULL,
  `so_luong` int(11) DEFAULT NULL,
  `gia_tien` int(20) NOT NULL,
  `tac_gia` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_sanpham`
--

INSERT INTO `tbl_sanpham` (`id_sanpham`, `ten_sanpham`, `id_danhmuc`, `id_nxb`, `so_luong`, `gia_tien`, `tac_gia`) VALUES
(1, 'Tôi tự học', 6, 6, 20, 50000, 'Nguyễn Duy Cần'),
(2, 'Thiên nga trắng', 6, 7, 20, 290000, 'Nicholas Nassim Taleb'),
(3, 'Tuổi thơ', 6, 8, 20, 70000, 'Nguyễn Nhật Ánh');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_taikhoan`
--

CREATE TABLE `tbl_taikhoan` (
  `id` int(11) NOT NULL,
  `hoten` varchar(100) NOT NULL,
  `diachi` text DEFAULT NULL,
  `sdt` varchar(10) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `tentaikhoan` varchar(100) NOT NULL,
  `matkhau` varchar(100) NOT NULL,
  `vaitro` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_taikhoan`
--

INSERT INTO `tbl_taikhoan` (`id`, `hoten`, `diachi`, `sdt`, `email`, `tentaikhoan`, `matkhau`, `vaitro`) VALUES
(1, 'asd', 'asd', '1231231231', 'asd', 'asd', 'asd', 'Admin'),
(2, 'Vanh', 'Hà Nội', '1234569877', 'Vanh@gmail.com', '2', '2', 'Staff'),
(3, 'Ngo Thuy Luong', 'Ninh Binh', '0987207803', 'Thuyluong.ap@gmail.com', '1', '1', 'Admin'),
(4, 'Ha Quang An', 'Phu Tho', '4567891266', 'An@gmail.com', 'an', 'an', 'Staff');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_chitietdonhang`
--
ALTER TABLE `tbl_chitietdonhang`
  ADD PRIMARY KEY (`id_chitiet`);

--
-- Indexes for table `tbl_chitietphieu`
--
ALTER TABLE `tbl_chitietphieu`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_danhmuc`
--
ALTER TABLE `tbl_danhmuc`
  ADD PRIMARY KEY (`id_danhmuc`);

--
-- Indexes for table `tbl_donhang`
--
ALTER TABLE `tbl_donhang`
  ADD PRIMARY KEY (`id_donhang`);

--
-- Indexes for table `tbl_khachhang`
--
ALTER TABLE `tbl_khachhang`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_muon`
--
ALTER TABLE `tbl_muon`
  ADD PRIMARY KEY (`id_muon`);

--
-- Indexes for table `tbl_nxb`
--
ALTER TABLE `tbl_nxb`
  ADD PRIMARY KEY (`id_nxb`);

--
-- Indexes for table `tbl_phieu`
--
ALTER TABLE `tbl_phieu`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_sach`
--
ALTER TABLE `tbl_sach`
  ADD PRIMARY KEY (`id_sach`);

--
-- Indexes for table `tbl_sanpham`
--
ALTER TABLE `tbl_sanpham`
  ADD PRIMARY KEY (`id_sanpham`),
  ADD KEY `fk_id_danhmuc` (`id_danhmuc`),
  ADD KEY `fk_id_nhaxb` (`id_nxb`);

--
-- Indexes for table `tbl_taikhoan`
--
ALTER TABLE `tbl_taikhoan`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `tentaikhoan` (`tentaikhoan`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_chitietdonhang`
--
ALTER TABLE `tbl_chitietdonhang`
  MODIFY `id_chitiet` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tbl_chitietphieu`
--
ALTER TABLE `tbl_chitietphieu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_danhmuc`
--
ALTER TABLE `tbl_danhmuc`
  MODIFY `id_danhmuc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `tbl_donhang`
--
ALTER TABLE `tbl_donhang`
  MODIFY `id_donhang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tbl_khachhang`
--
ALTER TABLE `tbl_khachhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `tbl_muon`
--
ALTER TABLE `tbl_muon`
  MODIFY `id_muon` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_nxb`
--
ALTER TABLE `tbl_nxb`
  MODIFY `id_nxb` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `tbl_phieu`
--
ALTER TABLE `tbl_phieu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_sach`
--
ALTER TABLE `tbl_sach`
  MODIFY `id_sach` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_sanpham`
--
ALTER TABLE `tbl_sanpham`
  MODIFY `id_sanpham` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tbl_taikhoan`
--
ALTER TABLE `tbl_taikhoan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_sanpham`
--
ALTER TABLE `tbl_sanpham`
  ADD CONSTRAINT `fk_id_danhmuc` FOREIGN KEY (`id_danhmuc`) REFERENCES `tbl_danhmuc` (`id_danhmuc`),
  ADD CONSTRAINT `fk_id_nhaxb` FOREIGN KEY (`id_nxb`) REFERENCES `tbl_nxb` (`id_nxb`),
  ADD CONSTRAINT `fk_id_nxb` FOREIGN KEY (`id_danhmuc`) REFERENCES `tbl_danhmuc` (`id_danhmuc`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
