-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: localhost
-- Üretim Zamanı: 30 May 2021, 12:54:38
-- Sunucu sürümü: 8.0.18
-- PHP Sürümü: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `oto_yedek_parca_db`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `araba`
--

CREATE TABLE `araba` (
  `id` int(11) NOT NULL,
  `marka` varchar(15) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `model` varchar(15) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `araba`
--

INSERT INTO `araba` (`id`, `marka`, `model`) VALUES
(1, 'Mercedes', 'C180'),
(2, 'Mercedes', 'C220'),
(3, 'Mercedes', 'E180'),
(4, 'Mercedes', 'E220'),
(5, 'Mercedes', 'E200'),
(6, 'AUDI', 'A4'),
(7, 'AUDI', 'A5'),
(8, 'AUDI', 'A6'),
(9, 'AUDI', 'A7'),
(10, 'AUDI', 'RS8'),
(11, 'Wolksvagen', 'Passat'),
(12, 'Wolksvagen', 'Jetta'),
(13, 'Wolksvagen', 'cc'),
(14, 'Wolksvagen', 'Arteon'),
(15, 'Wolksvagen', 'Golf'),
(16, 'BMW', '4.20'),
(17, 'BMW', '5.20'),
(18, 'BMW', '3.20'),
(19, 'BMW', 'X5'),
(20, 'BMW', 'X6'),
(21, 'Volvo', 'V40'),
(22, 'Volvo', 'S60'),
(23, 'Volvo', 'S90'),
(24, 'Volvo', 'XC60'),
(25, 'Volvo', 'XC90');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `müsteri`
--

CREATE TABLE `müsteri` (
  `id` int(11) NOT NULL,
  `ad` varchar(70) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `tarih` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `müsteri`
--

INSERT INTO `müsteri` (`id`, `ad`, `tarih`) VALUES
(1, 'Ecevit Köksal', '2020-12-22'),
(2, 'Turan Bakış', '2020-11-18'),
(3, 'Batuhan Avcıoğlu', '2021-12-12'),
(7, 'Fatih kök', '2021-05-30'),
(8, 'Gökhan Altan', '2021-05-30');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `parca`
--

CREATE TABLE `parca` (
  `id` int(11) NOT NULL,
  `kategori` varchar(30) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `parca` varchar(30) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `raf` varchar(10) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `fiyat` int(6) NOT NULL,
  `adet` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `parca`
--

INSERT INTO `parca` (`id`, `kategori`, `parca`, `raf`, `fiyat`, `adet`) VALUES
(1, 'Motor', 'Buji', 'Raf-M1', 2000, 5),
(2, 'Motor', 'Radyatör', 'Raf-M2', 750, 5),
(3, 'Motor', 'Eksantirik Mili', 'Raf-M3', 50, 5),
(4, 'Motor', 'Manifolt', 'Raf-M4', 1500, 5),
(5, 'Motor', 'Üst Kapak', 'Raf-M5', 500, 5),
(6, 'Kaporta', 'Kaput', 'Raf-K1', 2000, 5),
(7, 'Kaporta', 'Bagaj', 'Raf-K2', 1500, 5),
(8, 'Kaporta', 'Sağ Ön Çamurluk', 'Raf-K3', 1000, 5),
(9, 'Kaporta', 'Sol Ön Kapı', 'Raf-K4', 1500, 5),
(10, 'Kaporta', 'Ön Tampon', 'Raf-K5', 750, 5),
(11, 'Elektrik', 'Arka Silecek', 'Raf-E1', 20, 5),
(12, 'Elektrik', 'Sis Farı', 'Raf-E2', 45, 5),
(13, 'Elektrik', 'Akü', 'Raf-E3', 1000, 5),
(14, 'Elektrik', 'Müşür', 'Raf-E4', 50, 5),
(15, 'Elektrik', 'Far Sinyal Kolu', 'Raf-E5', 75, 5),
(16, 'Yürür Aksam', 'Rot Başı ', 'Raf-Y1', 100, 5),
(17, 'Yürür Aksam', 'Süspansiyon Direği', 'Raf-Y2', 300, 5),
(18, 'Yürür Aksam', 'Salıncak', 'Raf-Y3', 500, 5),
(19, 'Yürür Aksam', 'Amartisör', 'Raf-Y4', 500, 5),
(20, 'Yürür Aksam', 'Direksiyon', 'Raf-Y5', 150, 5),
(21, 'Güç Aktarma', 'Şaft', 'Raf-G1', 90, 5),
(22, 'Güç Aktarma', 'Diferansiyel', 'Raf-G2', 18000, 5),
(23, 'Güç Aktarma', 'Debriyaj', 'Raf-G3', 350, 5),
(24, 'Güç Aktarma', 'Vites Kutusu', 'Raf-G4', 1000, 5),
(25, 'Güç Aktarma', 'Aks Mili ', 'Raf-G5', 3000, 5);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `personel`
--

CREATE TABLE `personel` (
  `id` int(11) NOT NULL,
  `ad` varchar(30) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `soyad` varchar(30) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `telefon` varchar(11) COLLATE utf8_turkish_ci NOT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `adres` varchar(70) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `sifre` varchar(40) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `personel`
--

INSERT INTO `personel` (`id`, `ad`, `soyad`, `telefon`, `email`, `adres`, `sifre`) VALUES
(32, 'Fatih', 'Erkoç', '05487985241', 'asdasd', 'fatiherkoc@gmail.com', 'fatih123');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `araba`
--
ALTER TABLE `araba`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`,`marka`,`model`);

--
-- Tablo için indeksler `müsteri`
--
ALTER TABLE `müsteri`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `parca`
--
ALTER TABLE `parca`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `personel`
--
ALTER TABLE `personel`
  ADD PRIMARY KEY (`id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `araba`
--
ALTER TABLE `araba`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- Tablo için AUTO_INCREMENT değeri `müsteri`
--
ALTER TABLE `müsteri`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Tablo için AUTO_INCREMENT değeri `parca`
--
ALTER TABLE `parca`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=105;

--
-- Tablo için AUTO_INCREMENT değeri `personel`
--
ALTER TABLE `personel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
