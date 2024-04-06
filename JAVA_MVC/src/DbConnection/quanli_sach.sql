-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 06, 2024 at 01:34 PM
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
  `tong_tien` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_chitietdonhang`
--

INSERT INTO `tbl_chitietdonhang` (`id_chitiet`, `id_donhang`, `id_sanpham`, `soluong`, `tong_tien`) VALUES
(1, 1, 13, 2, 460000),
(2, 1, 9, 2, 260000),
(3, 1, 9, 5, 650000),
(4, 2, 2, 5, 500000),
(6, 3, 13, 2, 460000),
(8, 4, 1, 2, 420000),
(9, 5, 3, 3, 540000),
(10, 6, 11, 2, 198000),
(13, 26, 10, 23, 6900000);

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
(8, 'Phiêu lưu', 'Kể về các câu chuyện phiêu lưu'),
(10, 'Văn học', 'Các tác phẩm của các nhà văn nhà thơ nổi tiếng');

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
(1, 20, '2024-04-06', 1370000),
(2, 2, '2024-04-06', 500000),
(3, 1, '2024-04-06', 460000),
(4, 10, '2024-04-06', 420000),
(5, 9, '2024-04-06', 540000),
(6, 11, '2024-04-06', 198000),
(7, 7, '2024-03-30', 1010000),
(8, 10, '2024-03-02', 700000),
(15, 11, '2023-02-02', 600000),
(16, 11, '2023-04-02', 500000),
(17, 11, '2023-08-02', 90000),
(18, 11, '2023-09-02', 100000),
(19, 11, '2023-11-02', 300000),
(20, 10, '2022-03-02', 500000),
(21, 10, '2022-11-02', 50000),
(24, 11, '2024-01-02', 250000),
(26, 20, '2024-04-06', 6900000);

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
(6, 'luong', '1233232343', 'luong@gmail.com'),
(7, 'Nguyễn Văn Sơn', '0123446789', 'dunge5hat@gmail.com'),
(8, 'Trương Ngọc Trang', '0123229398', 'trang@gmail.com'),
(9, 'Hà Tiểu Nha', '0123456789', 'dung@edu.com'),
(10, 'Hà', '0123456789', 'ha@gmail.com'),
(11, 'Trang', '0123235296', 'hell@gmail.com'),
(13, 'Nhân', '0123456789', 'dung@gmail.com'),
(15, 'ma', '0987654321', 'ma@gmail.com'),
(17, 'haiii', '0123456789', 'hai@gmail.com'),
(18, 'nguyễn văn sơn', '0123456789', 'dung5@gmail.com'),
(20, 'Tluong', '0987207803', 'thuyluongokoz@gmail.com'),
(21, 'son', '1231231231', 'son@gmail.com');

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
(1, 'Trẻ', '0123456789', 'Đống Đa, Hà Nội'),
(2, 'Kim Đồng', '0987654321', 'Thanh Xuân, Hà Nội'),
(3, 'Hội nhà văn Việt Nam', '0124456786', 'Quận 10, Hồ Chí Minh'),
(4, 'Lao động', '09882233771', 'Nghi Lộc, Nghệ An'),
(5, 'Tư nhân Nhã Nam', '0112229998', 'Hà Đông, Hà Nội'),
(6, 'Phụ nữ', '01223344498', 'Quận 12, Hà Nộii');

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
(1, 'Thiên nga đen', 3, 3, 5, 210000, 'Nassim Nicholas Taleb'),
(2, '1', 1, 2, 5, 100000, '1'),
(3, 'The Godfather', 7, 1, 97, 180000, 'Mario Puzo'),
(4, 'Rich dad Poor dad', 7, 2, 10, 150000, 'Robbert T.Kiyosaki'),
(5, 'Đắc nhân tâm', 7, 2, 100, 100000, 'Dale Carnegie'),
(6, 'Think and Grow rich', 7, 3, 10, 210000, 'Napoleon Hill'),
(7, 'How to stop worrying and living', 7, 2, 20, 120000, 'Dale Carnegie'),
(8, 'Tắt đèn', 10, 3, 10, 120000, 'Ngô Tất Tố'),
(9, 'Số đỏ', 10, 3, 95, 130000, 'Vũ Trọng Phụng'),
(10, 'Tuổi thơ dữ dội', 10, 3, 377, 300000, 'Phùng Quán'),
(11, 'Nhà giả kim', 10, 4, 8, 99000, 'Paulo Coelho'),
(12, 'Ông già và biển cả', 10, 5, 8, 108000, 'Ernest Hemingway'),
(13, 'Không gia đình', 10, 2, 993, 230000, 'Hector Malot'),
(14, 'Truyện Kiều', 10, 3, 10, 500000, 'Nguyễn Du');

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
(1, 'Ngô Thụy Lương', 'Ninh Bình', '0987207803', 'thuyluong.ap@gmail.com', '1', '1', 'Quản lý'),
(2, 'Việt Anh', 'Hà Nội', '2131231233', 'vanhpham3009@gmail.com', 'asd', 'asd', 'Quản lý'),
(3, 'Nguyễn Văn Sơn', 'Nghệ An', '1231231233', 'dolcat@gmail.com', '2', '2', 'Quản lý'),
(4, 'Hà Quảng An', 'Phú Thọ', '2122122122', 'luvTl@gmail.com', '3', '3', 'Quản lý'),
(5, 'Hà Quảng Sơn', 'Phú An', '1231231233', 'luvVanSon@gmail.com', 'son', 'son', 'Quản lý'),
(6, '3', 'Hà Nội', '3213213213', '3@gmail.com', '4', '4', 'Nhân viên');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_chitietdonhang`
--
ALTER TABLE `tbl_chitietdonhang`
  ADD PRIMARY KEY (`id_chitiet`);

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
  MODIFY `id_chitiet` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `tbl_danhmuc`
--
ALTER TABLE `tbl_danhmuc`
  MODIFY `id_danhmuc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `tbl_donhang`
--
ALTER TABLE `tbl_donhang`
  MODIFY `id_donhang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=71;

--
-- AUTO_INCREMENT for table `tbl_khachhang`
--
ALTER TABLE `tbl_khachhang`
  MODIFY `id_cus` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `tbl_nxb`
--
ALTER TABLE `tbl_nxb`
  MODIFY `id_nxb` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `tbl_sanpham`
--
ALTER TABLE `tbl_sanpham`
  MODIFY `id_sanpham` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `tbl_taikhoan`
--
ALTER TABLE `tbl_taikhoan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

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
