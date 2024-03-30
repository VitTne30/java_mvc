-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 30, 2024 at 05:47 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.0.28

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
  `tong_tien` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_chitietdonhang`
--

INSERT INTO `tbl_chitietdonhang` (`id_chitiet`, `id_donhang`, `id_sanpham`, `soluong`, `tong_tien`) VALUES
(20, 1, 3, 2, 140000),
(21, 1, 2, 3, 870000),
(22, 2, 3, 1, 70000),
(23, 2, 2, 2, 580000),
(24, 3, 3, 2, 140000),
(25, 3, 2, 2, 580000);

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
  `id_cus` int(11) DEFAULT NULL,
  `ngaylap` date DEFAULT NULL,
  `tong_tien` int(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_donhang`
--

INSERT INTO `tbl_donhang` (`id_donhang`, `id_cus`, `ngaylap`, `tong_tien`) VALUES
(1, 7, '2024-03-30', 1010000);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_khachhang`
--

CREATE TABLE `tbl_khachhang` (
  `id_cus` int(11) NOT NULL,
  `hoten` varchar(50) NOT NULL,
  `sdt` varchar(10) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_khachhang`
--

INSERT INTO `tbl_khachhang` (`id_cus`, `hoten`, `sdt`, `email`) VALUES
(1, 'Báo an ', '1234567897', 'an@gmail.com'),
(2, 'vanh', '1235647899', 'Vanh@gmail.com'),
(3, 'nam', '1234567899', 'nam@gmail.com'),
(5, 'Sơn ngu', '2645481321', 'Son@gmail.com'),
(6, 'luong', '1233232343', 'luong@gmail.com'),
(7, 'Nguyễn Văn Sơn', '0123446789', 'dunge5hat@gmail.com'),
(8, 'Trương Ngọc Trang', '0123229398', 'trang@gmail.com'),
(9, 'Hà Tiểu Nha', '0123456789', 'dung@edu.com'),
(10, 'Hà', '0123456789', 'ha@gmail.com'),
(11, 'Trang', '0123235296', 'hell@gmail.com'),
(12, 'haf', '0123456789', 'ha@gmai.com'),
(13, 'Nhân', '0123456789', 'dung@gmail.com'),
(15, 'ma', '0987654321', 'ma@gmail.com'),
(16, 'haii', '0123456789', 'haii@gmail.com'),
(17, 'haiii', '0123456789', 'hai@gmail.com'),
(18, 'nguyễn văn sơn', '0123456789', 'dung5@gmail.com');

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
(9, 'Phụ nữ', '01223344498', 'Quận 12, Hà Nộii');

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
(2, 'Thiên nga trắng', 6, 7, 20, 290000, 'Nicholas Nassim Taleb'),
(3, 'Tuổi thơ', 6, 8, 20, 70000, 'Nguyễn Nhật Ánh'),
(4, 'Tôi thấy hoa vàng', 3, 6, 10, 20000, 'Nguyễn Nhật Ánh');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_taikhoan`
--

CREATE TABLE `tbl_taikhoan` (
  `id` int(11) NOT NULL,
  `hoten` varchar(100) NOT NULL,
  `diachi` text NOT NULL,
  `sdt` varchar(10) NOT NULL,
  `email` varchar(100) NOT NULL,
  `tentaikhoan` varchar(100) NOT NULL,
  `matkhau` varchar(100) NOT NULL,
  `vaitro` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_taikhoan`
--

INSERT INTO `tbl_taikhoan` (`id`, `hoten`, `diachi`, `sdt`, `email`, `tentaikhoan`, `matkhau`, `vaitro`) VALUES
(1, 'hell', 'ha noi', '0123456789', 'hell@gmail.com', '1', '2', 'Admin'),
(7, 'hai', 'ha noi', '0123456789', 'hai@gmail.com', 'haitk', 'haimk1234', 'Staff');

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
  ADD PRIMARY KEY (`id_cus`);

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
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_chitietdonhang`
--
ALTER TABLE `tbl_chitietdonhang`
  MODIFY `id_chitiet` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `tbl_chitietphieu`
--
ALTER TABLE `tbl_chitietphieu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_danhmuc`
--
ALTER TABLE `tbl_danhmuc`
  MODIFY `id_danhmuc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `tbl_donhang`
--
ALTER TABLE `tbl_donhang`
  MODIFY `id_donhang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `tbl_khachhang`
--
ALTER TABLE `tbl_khachhang`
  MODIFY `id_cus` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `tbl_nxb`
--
ALTER TABLE `tbl_nxb`
  MODIFY `id_nxb` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `tbl_phieu`
--
ALTER TABLE `tbl_phieu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_sanpham`
--
ALTER TABLE `tbl_sanpham`
  MODIFY `id_sanpham` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tbl_taikhoan`
--
ALTER TABLE `tbl_taikhoan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

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
