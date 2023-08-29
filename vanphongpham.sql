-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 22, 2023 at 01:20 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `vanphongpham`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `maTaiKhoan` int(11) NOT NULL,
  `sDT` int(11) NOT NULL,
  `passWord` varchar(100) NOT NULL,
  `level` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`maTaiKhoan`, `sDT`, `passWord`, `level`) VALUES
(1, 1, '1', 1),
(2, 2, '2', 2),
(3, 3, '3', 3),
(4, 918765432, 'qwerty', 2),
(5, 1233, '12', 1),
(6, 976543210, 'abc123', 2),
(7, 965432109, '2123', 1),
(8, 987654320, 'qwerty123', 2),
(9, 909090909, 'abcabc', 1),
(10, 918181818, '123qwe', 2),
(11, 965432109, 'password123', 3),
(12, 965432109, 'password123', 1),
(13, 965432109, 'password123', 1),
(14, 965432109, 'password123', 1),
(15, 355181325, 'thanh', 1);

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `maLoai` int(11) NOT NULL,
  `tenLoai` varchar(100) NOT NULL,
  `trangThai` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`maLoai`, `tenLoai`, `trangThai`) VALUES
(1, 'Dụng cụ học tập', '1'),
(2, 'Dụng cụ cá nhân', '1');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `maKhachHang` int(11) NOT NULL,
  `hoTen` varchar(100) NOT NULL,
  `sDT` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`maKhachHang`, `hoTen`, `sDT`) VALUES
(1, 'Hoang Nam', '123456'),
(2, 'Hoang Dang', '586997983'),
(3, 'Nguyễn Văn A', '0901234567'),
(4, 'Trần Thị B', '0909876543'),
(5, 'Phạm Văn C', '0904567890'),
(6, 'Lê Thị D', '0912345678'),
(7, 'Hoàng Văn E', '0918765432'),
(8, 'Đỗ Thị F', '0987654321'),
(9, 'Vũ Văn G', '0976543210'),
(10, 'Mai Thị H', '0987654321'),
(11, 'Trương Văn I', '0909876543'),
(12, 'Phan Thị K', '0901234567'),
(13, 'Lê Văn L', '0976543210'),
(14, 'Nguyễn Thị M', '0912345678'),
(15, 'Đặng Văn N', '0901234567'),
(16, 'Lê Thị O', '0918765432'),
(17, 'Phạm Văn P', '0909876543'),
(18, 'Nguyễn Thị Q', '0976543210'),
(19, 'Trần Văn R', '0987654321'),
(20, 'Hoàng Thị S', '0901234567'),
(21, 'Lê Văn Thanh', '912345678'),
(22, 'dsadassdadsa', '586997983'),
(23, 'Hoàng Thị S', '901234567'),
(24, 'Vũ Văn G', '976543210');

-- --------------------------------------------------------

--
-- Table structure for table `function`
--

CREATE TABLE `function` (
  `id_function` int(11) NOT NULL,
  `name_function` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `function`
--

INSERT INTO `function` (`id_function`, `name_function`) VALUES
(1, 'dashboard'),
(2, 'account'),
(3, 'customer'),
(4, 'staff'),
(5, 'category'),
(6, 'product'),
(7, 'sell'),
(8, 'invoice'),
(9, 'import'),
(10, 'provider'),
(11, 'role');

-- --------------------------------------------------------

--
-- Table structure for table `import`
--

CREATE TABLE `import` (
  `maNhapHang` int(11) NOT NULL,
  `ngayNhap` date NOT NULL,
  `maNhaCungCap` int(11) NOT NULL,
  `maNhanVien` int(11) NOT NULL,
  `tongTien` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `import`
--

INSERT INTO `import` (`maNhapHang`, `ngayNhap`, `maNhaCungCap`, `maNhanVien`, `tongTien`) VALUES
(1, '2023-05-19', 1, 37, 2400),
(2, '2023-05-19', 1, 37, 1500),
(3, '2023-05-19', 1, 37, 600);

-- --------------------------------------------------------

--
-- Table structure for table `importdetail`
--

CREATE TABLE `importdetail` (
  `maChiTietNhapHang` int(11) NOT NULL,
  `maNhapHang` int(11) NOT NULL,
  `maSanPham` int(11) NOT NULL,
  `soLuong` int(11) NOT NULL,
  `thanhTien` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE `invoice` (
  `maHoaDon` int(11) NOT NULL,
  `ngayBan` date NOT NULL,
  `maNhanVien` int(11) NOT NULL,
  `maKhachHang` int(11) NOT NULL,
  `tongTien` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`maHoaDon`, `ngayBan`, `maNhanVien`, `maKhachHang`, `tongTien`) VALUES
(2, '2023-05-19', 37, 22, 120000),
(3, '2023-05-19', 37, 1, 1500);

-- --------------------------------------------------------

--
-- Table structure for table `invoicedetail`
--

CREATE TABLE `invoicedetail` (
  `maChiTietHoaDon` int(11) NOT NULL,
  `maHoaDon` int(11) NOT NULL,
  `maSanPham` int(11) NOT NULL,
  `soLuong` int(11) NOT NULL,
  `thanhTien` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `level`
--

CREATE TABLE `level` (
  `maLevel` int(11) NOT NULL,
  `tenLevel` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `level`
--

INSERT INTO `level` (`maLevel`, `tenLevel`) VALUES
(1, 'Nhan Vien'),
(2, 'Admin'),
(3, 'Quan ly');

-- --------------------------------------------------------

--
-- Table structure for table `per_detail`
--

CREATE TABLE `per_detail` (
  `id_per_detail` int(50) NOT NULL,
  `maLevel` int(50) NOT NULL,
  `id_function` int(11) NOT NULL DEFAULT 1,
  `action_code` varchar(50) NOT NULL,
  `check_action` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `per_detail`
--

INSERT INTO `per_detail` (`id_per_detail`, `maLevel`, `id_function`, `action_code`, `check_action`) VALUES
(1, 1, 1, 'add', 1),
(2, 1, 1, 'update', 0),
(3, 1, 1, 'delete', 0),
(4, 1, 1, 'find', 1),
(5, 2, 1, 'add', 0),
(6, 2, 1, 'update', 1),
(7, 2, 1, 'delete', 1),
(8, 2, 1, 'find', 1),
(9, 3, 1, 'add', 1),
(10, 3, 1, 'update', 1),
(11, 3, 1, 'delete', 1),
(12, 3, 1, 'find', 1),
(13, 1, 2, 'add', 0),
(14, 1, 2, 'update', 0),
(15, 1, 2, 'delete', 0),
(16, 1, 2, 'find', 0),
(17, 2, 2, 'add', 1),
(18, 2, 2, 'update', 0),
(19, 2, 2, 'delete', 0),
(20, 2, 2, 'find', 0),
(21, 3, 2, 'add', 1),
(22, 3, 2, 'update', 1),
(23, 3, 2, 'delete', 1),
(24, 3, 2, 'find', 1),
(25, 1, 3, 'add', 0),
(26, 1, 3, 'update', 0),
(27, 1, 3, 'delete', 1),
(28, 1, 3, 'find', 1),
(29, 2, 3, 'add', 1),
(30, 2, 3, 'update', 1),
(31, 2, 3, 'delete', 1),
(32, 2, 3, 'find', 1),
(33, 3, 3, 'add', 1),
(34, 3, 3, 'update', 1),
(35, 3, 3, 'delete', 1),
(36, 3, 3, 'find', 1),
(37, 1, 4, 'add', 0),
(38, 1, 4, 'update', 0),
(39, 1, 4, 'delete', 1),
(40, 1, 4, 'find', 1),
(41, 2, 4, 'add', 1),
(42, 2, 4, 'update', 1),
(43, 2, 4, 'delete', 1),
(44, 2, 4, 'find', 1),
(45, 3, 4, 'add', 1),
(46, 3, 4, 'update', 1),
(47, 3, 4, 'delete', 1),
(48, 3, 4, 'find', 1),
(49, 1, 5, 'add', 0),
(50, 1, 5, 'update', 0),
(51, 1, 5, 'delete', 1),
(52, 1, 5, 'find', 1),
(53, 2, 5, 'add', 1),
(54, 2, 5, 'update', 1),
(55, 2, 5, 'delete', 1),
(56, 2, 5, 'find', 1),
(57, 3, 5, 'add', 1),
(58, 3, 5, 'update', 1),
(59, 3, 5, 'delete', 1),
(60, 3, 5, 'find', 1),
(61, 1, 6, 'add', 0),
(62, 1, 6, 'update', 0),
(63, 1, 6, 'delete', 1),
(64, 1, 6, 'find', 1),
(65, 2, 6, 'add', 1),
(66, 2, 6, 'update', 1),
(67, 2, 6, 'delete', 1),
(68, 2, 6, 'find', 1),
(69, 3, 6, 'add', 1),
(70, 3, 6, 'update', 1),
(71, 3, 6, 'delete', 1),
(72, 3, 6, 'find', 1),
(73, 1, 7, 'add', 0),
(74, 1, 7, 'update', 0),
(75, 1, 7, 'delete', 1),
(76, 1, 7, 'find', 1),
(77, 2, 7, 'add', 1),
(78, 2, 7, 'update', 1),
(79, 2, 7, 'delete', 1),
(80, 2, 7, 'find', 1),
(81, 3, 7, 'add', 1),
(82, 3, 7, 'update', 1),
(83, 3, 7, 'delete', 1),
(84, 3, 7, 'find', 1),
(85, 1, 8, 'add', 0),
(86, 1, 8, 'update', 0),
(87, 1, 8, 'delete', 1),
(88, 1, 8, 'find', 1),
(89, 2, 8, 'add', 1),
(90, 2, 8, 'update', 1),
(91, 2, 8, 'delete', 1),
(92, 2, 8, 'find', 1),
(93, 3, 8, 'add', 1),
(94, 3, 8, 'update', 1),
(95, 3, 8, 'delete', 1),
(96, 3, 8, 'find', 1),
(97, 1, 9, 'add', 0),
(98, 1, 9, 'update', 0),
(99, 1, 9, 'delete', 1),
(100, 1, 9, 'find', 1),
(101, 2, 9, 'add', 1),
(102, 2, 9, 'update', 1),
(103, 2, 9, 'delete', 1),
(104, 2, 9, 'find', 1),
(105, 3, 9, 'add', 1),
(106, 3, 9, 'update', 1),
(107, 3, 9, 'delete', 1),
(108, 3, 9, 'find', 1),
(109, 1, 10, 'add', 0),
(110, 1, 10, 'update', 0),
(111, 1, 10, 'delete', 1),
(112, 1, 10, 'find', 1),
(113, 2, 10, 'add', 1),
(114, 2, 10, 'update', 1),
(115, 2, 10, 'delete', 1),
(116, 2, 10, 'find', 1),
(117, 3, 10, 'add', 1),
(118, 3, 10, 'update', 1),
(119, 3, 10, 'delete', 1),
(120, 3, 10, 'find', 1),
(121, 1, 11, 'add', 0),
(122, 1, 11, 'update', 0),
(123, 1, 11, 'delete', 1),
(124, 1, 11, 'find', 1),
(125, 2, 11, 'add', 1),
(126, 2, 11, 'update', 1),
(127, 2, 11, 'delete', 1),
(128, 2, 11, 'find', 1),
(129, 3, 11, 'add', 1),
(130, 3, 11, 'update', 1),
(131, 3, 11, 'delete', 1),
(132, 3, 11, 'find', 1),
(265, 1, 1, 'ẩn', 0),
(266, 1, 2, 'ẩn', 0),
(267, 1, 3, 'ẩn', 0),
(268, 1, 4, 'ẩn', 0),
(269, 1, 5, 'ẩn', 0),
(270, 1, 6, 'ẩn', 0),
(271, 1, 7, 'ẩn', 0),
(272, 1, 8, 'ẩn', 0),
(273, 1, 9, 'ẩn', 0),
(274, 1, 10, 'ẩn', 0),
(275, 1, 11, 'ẩn', 0),
(276, 2, 1, 'ẩn', 0),
(277, 2, 2, 'ẩn', 0),
(278, 2, 3, 'ẩn', 0),
(279, 2, 4, 'ẩn', 0),
(280, 2, 5, 'ẩn', 0),
(281, 2, 6, 'ẩn', 0),
(282, 2, 7, 'ẩn', 0),
(283, 2, 8, 'ẩn', 0),
(284, 2, 9, 'ẩn', 0),
(285, 2, 10, 'ẩn', 0),
(286, 2, 11, 'ẩn', 0),
(287, 3, 1, 'ẩn', 0),
(288, 3, 2, 'ẩn', 0),
(289, 3, 3, 'ẩn', 0),
(290, 3, 4, 'ẩn', 0),
(291, 3, 5, 'ẩn', 0),
(292, 3, 6, 'ẩn', 0),
(293, 3, 7, 'ẩn', 0),
(294, 3, 8, 'ẩn', 0),
(295, 3, 9, 'ẩn', 0),
(296, 3, 10, 'ẩn', 0),
(297, 3, 11, 'ẩn', 0);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `maSanPham` int(11) NOT NULL,
  `tenSanPham` varchar(100) NOT NULL,
  `soLuong` int(10) NOT NULL,
  `donGia` float NOT NULL,
  `dVT` varchar(100) DEFAULT NULL,
  `moTa` varchar(100) DEFAULT NULL,
  `maLoai` int(11) NOT NULL,
  `trangThai` varchar(10) NOT NULL,
  `maNhaCungCap` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`maSanPham`, `tenSanPham`, `soLuong`, `donGia`, `dVT`, `moTa`, `maLoai`, `trangThai`, `maNhaCungCap`) VALUES
(28, 'Bút bi', 0, 5000, 'Cây', 'Bút viết', 1, 'true', 1),
(31, 'Bút chì', 123, 7000, 'Cây', 'Bút viết', 1, 'true', 1),
(32, 'Thức kẻ', 123, 12, 'Cây', 'Thước kẻ', 1, 'true', 1),
(33, 'Bút lông', 123, 12, 'Cây', 'Bút viết', 1, '1', 1),
(35, 'eke', 0, 12, 'Cây', 'Thước kẻ', 1, 'lock', 2),
(36, 'Compa', 0, 11, 'Cây', 'vẽ', 1, 'lock', 1),
(37, 'Balo ', 200, 12, 'Túi', 'Bỏ đồ', 1, 'true', 2),
(39, 'Hộp bút', 2312, 1, 'Hộp', 'Hộp', 1, '1', 2),
(40, 'Bút Highline', 2, 12000, 'Cây', 'Bút viết', 1, 'true', 1),
(41, 'Bút đỏ', 2302, 1, 'Cây', 'Bút Viết', 2, 'false', 1),
(42, 'Máy tính bỏ túi', 2312, 1200, 'CHiếc', 'Tính toán', 2, 'false', 1),
(43, 'abc', 1, 10000, 'abc', 'abc', 1, '1', 1),
(45, 'Thức kẻ', 50, 12, 'Cây', 'Thước kẻ', 1, 'true', 1),
(46, 'Bút đỏ', 0, 1, 'Cây', 'Bút Viết', 1, 'true', 1),
(47, 'eke', 0, 12, 'Cây', 'Thước kẻ', 1, 'false', 1),
(48, 'eke', 0, 12, 'Cây', 'Thước kẻ', 1, 'false', 1),
(49, 'Hộp bút', 0, 1, 'Hộp', 'Hộp', 1, 'false', 1),
(50, 'Máy tính bỏ túi', 0, 1200, 'CHiếc', 'Tính toán', 1, 'true', 1),
(51, 'Bút Highline', 0, 12000, 'Cây', 'Bút viết', 1, 'false', 1),
(52, 'giỏ sách', 0, 10, 'chiếc ', 'ko', 1, 'false', 1);

-- --------------------------------------------------------

--
-- Table structure for table `provider`
--

CREATE TABLE `provider` (
  `maNhaCungCap` int(11) NOT NULL,
  `tenNhaCungCap` varchar(100) NOT NULL,
  `sDT` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `diaChi` varchar(100) NOT NULL,
  `ghiChu` varchar(100) DEFAULT NULL,
  `trangThai` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `provider`
--

INSERT INTO `provider` (`maNhaCungCap`, `tenNhaCungCap`, `sDT`, `email`, `diaChi`, `ghiChu`, `trangThai`) VALUES
(1, 'Hoa Sen SG', 978456123, 'hoasen@gmail.com', '273 An Dương Vương, quận 5, tpHCM', 'Tư nhân', '1'),
(2, 'Thăng Long', 674853129, 'thanglong@gmail.com', '12,Nguyễn Trãi, Quận 5, tp HCM', 'Nhà nước', '1');

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `maNhanVien` int(11) NOT NULL,
  `hoTen` varchar(100) NOT NULL,
  `sDT` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `diaChi` varchar(100) DEFAULT NULL,
  `maLevel` int(11) NOT NULL,
  `trangThai` varchar(10) NOT NULL,
  `maTaiKhoan` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`maNhanVien`, `hoTen`, `sDT`, `email`, `diaChi`, `maLevel`, `trangThai`, `maTaiKhoan`) VALUES
(1, 'Nguyen Van A', '901234567', 'nguyenvana@gmail.com', '123 Nguyen Van Cu', 1, '0', 1),
(2, 'Tran Thi Bc', '909876543', 'tranthib@gmail.com', '456 Le Loi', 1, '1', 2),
(3, 'Pham Van a', '912345678', 'phamvanc@gmail.com', '789 Vo Van Tan', 1, '1', 3),
(4, 'Le Thi D', '918765432', 'lethid@gmail.com', '234 Ly Thuong Kiet', 2, '1', 4),
(5, 'Tran Van E', '987654321', 'tranvane@gmail.com', '345 Tran Phu', 1, '0', 5),
(6, 'Nguyen Thi F', '976543210', 'nguyenthif@gmail.com', '567 Nguyen Hue', 2, '1', 6),
(7, 'Pham Van G', '965432109', 'phamvang@gmail.com', '789 Vo Van Tan', 1, '0', 7),
(8, 'Le Van H', '987654320', 'levanh@gmail.com', '234 Ly Thuong Kiet', 2, '1', 8),
(9, 'Nguyen Van I', '909090909', 'nguyenvani@gmail.com', '345 Tran Phu', 1, '0', 9),
(10, 'Tran Thi Jdsadas', '918181818', 'tranthij@gmail.com', '567 Nguyen Hue', 2, '0', 10),
(11, 'Pham Van C', '912345678', 'phamvanc@gmail.com', '789 Vo Van Tan', 1, '0', 3),
(12, 'Le Thi D', '918765432', 'lethid@gmail.com', '234 Ly Thuong Kiet', 2, '1', 4),
(13, 'Tran Thi Bc', '909876543', 'tranthib@gmail.com', '456 Le Loi', 1, '0', 2),
(14, 'Tran Thi Bc', '909876543', 'tranthib@gmail.com', '456 Le Loi', 1, '0', 2),
(15, 'Tran Thi Bcds', '909876543', 'tranthib@gmail.com', '456 Le Loi', 1, '0', 2),
(16, 'Nguyen Thi F', '976543210', 'nguyenthif@gmail.com', '567 Nguyen Hue', 2, '1', 6),
(17, 'Tran Thi Bcddsas', '909876543', 'tranthib@gmail.com', '456 Le Loi', 1, '0', 2),
(18, 'Nguyen Thi F', '976543210', 'nguyenthif@gmail.com', '567 Nguyen Hue', 2, '0', 6),
(19, 'Nguyen Thi F', '976543210', 'nguyenthif@gmail.com', '567 Nguyen Hue', 3, '0', 6),
(20, 'Le Thi D', '918765432', 'lethid@gmail.com', '234 Ly Thuong Kiet', 1, '1', 4),
(21, 'Nguyen Thi F', '976543210', 'nguyenthif@gmail.com', '567 Nguyen Hue', 1, '0', 6),
(22, 'Nguyen Thi F', '976543210', 'nguyenthif@gmail.com', '567 Nguyen Hue', 1, '1', 6),
(23, 'Tran Thi Bcdsa', '909876543', 'tranthib@gmail.com', '456 Le Loi', 1, '1', 2),
(24, 'Nguyen Van A', '901234567', 'nguyenvana@gmail.com', '123 Nguyen Van Cu', 1, '0', 1),
(25, 'Nguyen Van A', '901234567', 'nguyenvana@gmail.com', '123 Nguyen Van Cu', 1, '0', 1),
(26, 'Tran Van E', '987654325', 'tranvane@gmail.com', '345 Tran Phu', 2, '0', 5),
(37, 'ád', '355181308', 'hj@gmail.com', '', 1, '1', 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`maTaiKhoan`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`maLoai`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`maKhachHang`);

--
-- Indexes for table `function`
--
ALTER TABLE `function`
  ADD PRIMARY KEY (`id_function`);

--
-- Indexes for table `import`
--
ALTER TABLE `import`
  ADD PRIMARY KEY (`maNhapHang`),
  ADD KEY `D_IMPORT_STAFF` (`maNhanVien`),
  ADD KEY `D_IMPORT_PROVIDER` (`maNhaCungCap`);

--
-- Indexes for table `importdetail`
--
ALTER TABLE `importdetail`
  ADD PRIMARY KEY (`maChiTietNhapHang`),
  ADD KEY `D_IMPORTDETAIL_IMPORT` (`maNhapHang`),
  ADD KEY `D_IMPORTDETAIL_PRODUCT` (`maSanPham`);

--
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`maHoaDon`),
  ADD KEY `D_INVOICE_STAFF` (`maNhanVien`),
  ADD KEY `D_INVOICE_CUSTOMER` (`maKhachHang`);

--
-- Indexes for table `invoicedetail`
--
ALTER TABLE `invoicedetail`
  ADD PRIMARY KEY (`maChiTietHoaDon`),
  ADD KEY `D_INVOICE_INVOICE` (`maHoaDon`),
  ADD KEY `D_INVOICE_PRODUCT` (`maSanPham`);

--
-- Indexes for table `level`
--
ALTER TABLE `level`
  ADD PRIMARY KEY (`maLevel`);

--
-- Indexes for table `per_detail`
--
ALTER TABLE `per_detail`
  ADD PRIMARY KEY (`id_per_detail`),
  ADD KEY `maLevel` (`maLevel`),
  ADD KEY `maFunc` (`id_function`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`maSanPham`),
  ADD KEY `D_PRODUCT_CATEGORY` (`maLoai`),
  ADD KEY `D_PRODUCT_PROVIDER` (`maNhaCungCap`);

--
-- Indexes for table `provider`
--
ALTER TABLE `provider`
  ADD PRIMARY KEY (`maNhaCungCap`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`maNhanVien`),
  ADD KEY `D_STAFF_ACCOUNT` (`maTaiKhoan`),
  ADD KEY `D_STAFF_LEVEL` (`maLevel`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `maTaiKhoan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `maLoai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `maKhachHang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `function`
--
ALTER TABLE `function`
  MODIFY `id_function` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `import`
--
ALTER TABLE `import`
  MODIFY `maNhapHang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `importdetail`
--
ALTER TABLE `importdetail`
  MODIFY `maChiTietNhapHang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `invoice`
--
ALTER TABLE `invoice`
  MODIFY `maHoaDon` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `invoicedetail`
--
ALTER TABLE `invoicedetail`
  MODIFY `maChiTietHoaDon` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `level`
--
ALTER TABLE `level`
  MODIFY `maLevel` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `per_detail`
--
ALTER TABLE `per_detail`
  MODIFY `id_per_detail` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=298;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `maSanPham` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT for table `provider`
--
ALTER TABLE `provider`
  MODIFY `maNhaCungCap` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `staff`
--
ALTER TABLE `staff`
  MODIFY `maNhanVien` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `import`
--
ALTER TABLE `import`
  ADD CONSTRAINT `D_IMPORT_PROVIDER` FOREIGN KEY (`maNhaCungCap`) REFERENCES `provider` (`maNhaCungCap`),
  ADD CONSTRAINT `D_IMPORT_STAFF` FOREIGN KEY (`maNhanVien`) REFERENCES `staff` (`maNhanVien`);

--
-- Constraints for table `importdetail`
--
ALTER TABLE `importdetail`
  ADD CONSTRAINT `D_IMPORTDETAIL_IMPORT` FOREIGN KEY (`maNhapHang`) REFERENCES `import` (`maNhapHang`),
  ADD CONSTRAINT `D_IMPORTDETAIL_PRODUCT` FOREIGN KEY (`maSanPham`) REFERENCES `product` (`maSanPham`);

--
-- Constraints for table `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `D_INVOICE_CUSTOMER` FOREIGN KEY (`maKhachHang`) REFERENCES `customer` (`maKhachHang`),
  ADD CONSTRAINT `D_INVOICE_STAFF` FOREIGN KEY (`maNhanVien`) REFERENCES `staff` (`maNhanVien`);

--
-- Constraints for table `invoicedetail`
--
ALTER TABLE `invoicedetail`
  ADD CONSTRAINT `D_INVOICE_INVOICE` FOREIGN KEY (`maHoaDon`) REFERENCES `invoice` (`maHoaDon`),
  ADD CONSTRAINT `D_INVOICE_PRODUCT` FOREIGN KEY (`maSanPham`) REFERENCES `product` (`maSanPham`);

--
-- Constraints for table `per_detail`
--
ALTER TABLE `per_detail`
  ADD CONSTRAINT `maFunc` FOREIGN KEY (`id_function`) REFERENCES `function` (`id_function`),
  ADD CONSTRAINT `maLevel` FOREIGN KEY (`maLevel`) REFERENCES `level` (`maLevel`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `D_PRODUCT_CATEGORY` FOREIGN KEY (`maLoai`) REFERENCES `category` (`maLoai`),
  ADD CONSTRAINT `D_PRODUCT_PROVIDER` FOREIGN KEY (`maNhaCungCap`) REFERENCES `provider` (`maNhaCungCap`);

--
-- Constraints for table `staff`
--
ALTER TABLE `staff`
  ADD CONSTRAINT `D_STAFF_ACCOUNT` FOREIGN KEY (`maTaiKhoan`) REFERENCES `account` (`maTaiKhoan`),
  ADD CONSTRAINT `D_STAFF_LEVEL` FOREIGN KEY (`maLevel`) REFERENCES `level` (`maLevel`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
