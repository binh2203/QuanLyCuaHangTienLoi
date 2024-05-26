CREATE DATABASE QuanLyCuaHangTienLoi
GO
USE QuanLyCuaHangTienLoi
GO
CREATE TABLE SanPham(
	maSP CHAR(10) PRIMARY KEY NOT NULL,
	tenSP nVARCHAR(100) NOT NULL,
	maNCC char(10) NOT NULL,
	soLuongTon int NOT NULL,
	donVi nVARCHAR(20) NOT NULL,
	giaNhap float NOT NULL, 
	maNSX char(10) NOT NULL
)
GO
CREATE TABLE NhaCungCap(
	maNCC char(10) PRIMARY KEY NOT NULL,
	tenNCC  nvarchar(50) NOT NULL,
	email varchar(40) NOT NULL,
	phone varchar(10) NOT NULL
)
GO 
CREATE TABLE NhaSanXuat(
	maNSX char(10) PRIMARY KEY NOT NULL,
	tenNSX nvarchar(50) NOT NULL
)
GO 
CREATE TABLE CT_HoaDon(
	maHD char(10) NOT NULL,
	maSP char(10) NOT NULL,
	soLuong int NOT NULL
)
GO
CREATE TABLE HoaDon(
	maHD char(10) NOT NULL,
	maNV char(10) NOT NULL,
	maKH char(10) NOT NULL,
	ngayLapHoaDon Date NOT NULL,
	tienKhachDua float NOT NULL
)
GO 
CREATE TABLE KhachHang(
	maKH char(10) PRIMARY KEY NOT NULL,
	tenKH nvarchar(50) NOT NULL,
	gioiTinh bit NOT NULL,
	ngaySinh Date NOT NULL,
	phone varchar(10) NOT NULL,
	diaChi nvarchar(50) NOT NULL,
	email varchar(50) NOT NULL
)
GO
CREATE TABLE PhieuDatHang(
	maPDH char(10) PRIMARY KEY NOT NULL,
	maNV char(10) NOT NULL,
	maKH char(10),
	ngayLapPDH Date NOT NULL,
	ngayLayHang Date NOT NULL,
	trangThai bit NOT NULL,
	thanhTien float NOT NULL,
	tienKhachDua float NOT NULL
)
GO
CREATE TABLE CT_PhieuDatHang(
	maPDH char(10) NOT NULL,
	maSP char(10) NOT NULL,
	soLuong int NOT NULL
)
GO
CREATE TABLE NhanVien(
	maNV char(10) PRIMARY KEY NOT NULL,
	tenNV nvarchar(50) NOT NULL,
	gioiTinh bit NOT NULL,
	ngaySinh Date NOT NULL,
	phone char(10) NOT NULL,
	diaChi nvarchar(50) NOT NULL,
	email varchar(50) NOT NULL
)
GO
CREATE TABLE TaiKhoan(
	tenDangNhap varchar(20) check (len(tenDangNhap) >= 5) NOT NULL,
	matKhau varchar(20) check (len(matKhau) >= 8) NOT NULL,
	maNV char(10) NOT NULL,
	chucVu bit NOT NULL
)
-- ADD PRIMARY KEY
GO
ALTER TABLE TaiKhoan
ADD CONSTRAINT PK_TaiKhoan PRIMARY KEY(tenDangNhap)
GO
ALTER TABLE CT_PhieuDatHang
ADD CONSTRAINT PK_CT_PDH PRIMARY KEY(maPDH, maSP)
GO
ALTER TABLE HoaDon
ADD CONSTRAINT PK_HD PRIMARY KEY(maHD)
GO
ALTER TABLE CT_HoaDon
ADD CONSTRAINT PK_CT_HD PRIMARY KEY (maHD, maSP)
GO
-- ADD FOREIGN KEY
ALTER TABLE CT_PhieuDatHang
ADD CONSTRAINT FR_CT_PDH_PDH
FOREIGN KEY (maPDH)
REFERENCES PhieuDatHang(maPDH)
GO
ALTER TABLE CT_PhieuDatHang
ADD CONSTRAINT FR_CT_PDH_SP
FOREIGN KEY (maSP)
REFERENCES SanPham(maSP)
GO
ALTER TABLE PhieuDatHang
ADD CONSTRAINT FR_PDH_NV
FOREIGN KEY (maNV)
REFERENCES NhanVien(maNV)
GO
ALTER TABLE PhieuDatHang
ADD CONSTRAINT FR_PDH_KH
FOREIGN KEY (maKH)
REFERENCES KhachHang(maKH)
GO
ALTER TABLE HoaDon
ADD CONSTRAINT FR_HD_KH
FOREIGN KEY (maKH)
REFERENCES KhachHang(maKH)
GO
ALTER TABLE HoaDon
ADD CONSTRAINT FR_HD_NV
FOREIGN KEY (maNV)
REFERENCES NhanVien(maNV)
GO
ALTER TABLE TaiKhoan
ADD CONSTRAINT FR_TK_NV
FOREIGN KEY (maNV)
REFERENCES NhanVien(maNV)
GO
ALTER TABLE CT_HoaDon
ADD CONSTRAINT FR_CT_HD_HD
FOREIGN KEY (maHD)
REFERENCES HoaDon(maHD)
GO
ALTER TABLE CT_HoaDon
ADD CONSTRAINT FR_CT_HD_SP
FOREIGN KEY (maSP)
REFERENCES SanPham(maSP)
GO
ALTER TABLE SanPham
ADD CONSTRAINT FR_SP_NCC
FOREIGN KEY (maNCC)
REFERENCES NhaCungCap(maNCC)
GO
ALTER TABLE SanPham
ADD CONSTRAINT FR_SP_NSX
FOREIGN KEY (maNSX)
REFERENCES NhaSanXuat(maNSX)
GO
-- Nhập dữ liệu vào các bảng

GO
INSERT INTO NhanVien
VALUES ('NV001', N'Nguyễn Tuấn Anh', 0, '2002-03-22', '0853387787', N'Gò Vấp - TPHCM', 'nguyentuananh@gmail.com'),
('NV002', N'Diêm Công Bình', 0, '2002-03-22', '0853387787', N'Gò Vấp - TPHCM', 'diemcongbinh@gmail.com'),
('NV003', N'Nguyễn Tấn Đắt', 0, '2004-10-11', '0383547085', N'Q12 - TPHCM', 'nguyentandat@gmail.com'),
('NV004', N'Nguyễn Văn Kha', 0, '2004-10-11', '0383547085', N'Q12 - TPHCM', 'nguyenvankha@gmail.com')
GO
INSERT INTO TaiKhoan
VALUES ('nguyentuananh', '12345678', 'NV001', 1),
('diemcongbinh', '12345678','NV002',1),
('nguyentandat', '12345678', 'NV003', 0),
('nguyenvankha', '12345678', 'NV004', 0)

GO 
INSERT INTO KhachHang
VALUES 
('KH001',N'Bùi Minh Anh',1,'2002-04-05','0940856853',N'Hóc Môn, TP HCM','buiminhanh@gmail.com'),
('KH002',N'Hồ Nguyễn Trâm Anh',1,'2003-04-18','0941856854',N'Củ Chi, TP HCM','honguyentramanh@gmail.com'),
('KH003',N'Nguyễn Hoàng Phương Anh',1,'2003-11-05','0942856855',N'Gò Vấp, TP HCM','nguyenhoangphuonganh@gmail.com'),
('KH004',N'Nguyễn Thị Phương Anh',1,'2003-03-26','0943856856',N'Bình Thạnh, TP HCM','nguyenthiphuonganh@gmail.com'),
('KH005',N'Lý Nguyễn Trọng Bảo',0,'2003-08-20','0944856857',N'Bình Thạnh, TP HCM','lynguyentrongbao@gmail.com'),
('KH006',N'Mai Thị Mộng Bình',1,'2003-02-12','0945856858',N'Quận 4, TP HCM','maithimongbinh@gmail.com'),
('KH007',N'Đỗ Đức Cảnh',0,'2002-11-20','0946856859',N'Củ Chi, TP HCM','doduccanh@gmail.com'),
('KH008',N'Trần Nguyễn Ngọc Châu',1,'2003-09-12','0947856860',N'Quận 4, TP HCM','trannguyenngocchau@gmail.com'),
('KH009',N'Nguyễn Thị Phương Đài',1,'2003-09-26','0948856861',N'Quận 1, TP HCM','nguyenthiphuongdai@gmail.com'),
('KH010',N'Vương Quốc Đạt',0,'2002-03-21','0949856862',N'Quận 6, TP HCM','vuongquocdat@gmail.com'),
('KH011',N'Mai Thị Diệu',1,'2003-11-20','0941956863',N'Hóc Môn, TP HCM','maithidieu@gmail.com'),
('KH012',N'Phạm Trần Công Định',0,'2003-02-12','0942056864',N'Quận 10, TP HCM','phamtrancongdinh@gmail.com'),
('KH013',N'Phạm Du',0,'2003-05-12','0921856865',N'Quận 10, TP HCM','phamdu@gmail.com'),
('KH014',N'Lê Trung Đức',0,'2003-05-16','0942256866',N'Củ Chi, TP HCM','letrungduc@gmail.com'),
('KH015',N'Ngô Thế Duy',0,'2003-11-25','0942356867',N'Quận 1, TP HCM','ngotheduy@gmail.com'),
('KH016',N'Nguyễn Phạm Khánh Duy',0,'2003-06-29','0942456868',N'Quận 9, TP HCM','nguyenphamkhanhduy@gmail.com'),
('KH017',N'Bùi Thị Mỹ Duyên',1,'2003-09-24','094056869',N'Quận 11, TP HCM','buithimyduyen@gmail.com'),
('KH018',N'Bùi Đức Hải',0,'2003-05-24','0940856870',N'Bình Tân, TP HCM','buiduchai@gmail.com'),
('KH019',N'Huỳnh Anh Hào',0,'2003-01-14','0940856871',N'Cần Giờ, TP HCM','huynhanhhao@gmail.com'),
('KH020',N'Nguyễn Văn Hiền',0,'2003-02-16','0940856872',N'Quận 6, TP HCM','nguyenvanhien@gmail.com'),
('KH021',N'Lại Chí Hiếu',0,'2003-10-13','0940856873',N'Phú Nhuận, TP HCM','laichihieu@gmail.com'),
('KH022',N'Nguyễn Văn Hoan',0,'2003-10-02','0940856874',N'Gò Vấp, TP HCM','nguyenvanhoan@gmail.com'),
('KH023',N'Lê Vũ Hoàng',0,'2003-08-14','0940856875',N'Quận 6, TP HCM','levuhoang@gmail.com'),
('KH024',N'Ngô Thư Hoàng',0,'2003-09-16','0940856876',N'Quận 4, TP HCM','ngothuhoang@gmail.com'),
('KH025',N'Lâm Tính Huy',0,'2003-08-10','0940856877',N'Gò Vấp, TP HCM','lamtinhhuy@gmail.com'),
('KH026',N'Nguyễn Kim Quang Huy',0,'2003-03-27','0940856878',N'Quận 11, TP HCM','nguyenkimquanghuy@gmail.com'),
('KH027',N'Phạm Lê Huy',0,'2003-09-01','0940856879',N'Quận 6, TP HCM','phamlehuy@gmail.com'),
('KH028',N'Phạm Quang Huy',0,'2003-04-30','0940856880',N'Quận 8, TP HCM','phamquanghuy@gmail.com'),
('KH029',N'Võ Hoàng Huy',0,'2003-03-12','0940856881',N'Quận 5, TP HCM','vohoanghuy@gmail.com'),
('KH030',N'Nguyễn Thị Kim Huyền',1,'2003-02-10','0940856882',N'Quận 2, TP HCM','nguyenthikimhuyen@gmail.com'),
('KH031',N'Lê Thị Minh Khánh',1,'2002-07-23','0940856883',N'Quận 5, TP HCM','lethiminhkhanh@gmail.com'),
('KH032',N'Lê Anh Khôi',0,'2003-01-04','0940856884',N'Bình Tân, TP HCM','leanhkhoi@gmail.com'),
('KH033',N'Chung Gia Kiệt',0,'2003-09-23','0940856885',N'Bình Chánh, TP HCM','chunggiakiet@gmail.com'),
('KH034',N'Đoàn Ngọc Tuấn Kiệt',0,'2003-11-10','0940856886',N'Tân Bình, TP HCM','doanngoctuankiet@gmail.com'),
('KH035',N'Trịnh Ngọc Tuấn Kiệt',0,'2003-11-20','0940856887',N'Bình Tân, TP HCM','trinhngoctuankiet@gmail.com'),
('KH036',N'Huỳnh Nhật Lê',1,'2003-06-14','0940856888',N'Quận 9, TP HCM','huynhnhatle@gmail.com'),
('KH037',N'Mai Văn Linh',0,'2003-01-26','0940856889',N'Quận 1, TP HCM','maivanlinh@gmail.com'),
('KH038',N'Phạm Nguyễn Thu Loan',1,'2002-10-17','0940856890',N'Quận 11, TP HCM','phamnguyenthuloan@gmail.com'),
('KH039',N'Võ Hồng Loan',1,'2000-06-16','0940856891',N'Quận 8, TP HCM','vohongloan@gmail.com'),
('KH040',N'Nguyễn Đình Long',0,'2002-12-30','0940856892',N'Quận 4, TP HCM','nguyendinhlong@gmail.com'),
('KH041',N'Trần Hoàng Long',0,'2003-06-23','0940856893',N'Thủ Đức, TP HCM','tranhoanglong@gmail.com'),
('KH042',N'Mai Văn Luân',0,'2003-04-03','0940856894',N'Quận 1, TP HCM','maivanluan@gmail.com'),
('KH043',N'Đinh Hoài Nam',0,'2003-06-29','0940856895',N'Cần Giờ, TP HCM','dinhhoainam@gmail.com'),
('KH044',N'Bùi Thị Nha Nha',0,'2003-05-02','0940856896',N'Quận 5, TP HCM','buithinhanha@gmail.com'),
('KH045',N'Nghiêm Tường Nhi',1,'2003-09-03','0940856897',N'Quận 6, TP HCM','nghiemtuongnhi@gmail.com'),
('KH046',N'Đào Mộng Huỳnh Như',1,'2002-12-25','0940856898',N'Quận 2, TP HCM','daomonghuynhnhu@gmail.com'),
('KH047',N'Nguyễn Thị Huỳnh Như',1,'2003-12-28','0940856899',N'Quận 8, TP HCM','nguyenthihuynhnhu@gmail.com'),
('KH048',N'Hồ Ngọc Anh Phi',0,'2003-06-23','0940856100',N'Nhà Bè, TP HCM','hongocanhphi@gmail.com'),
('KH049',N'Nguyễn Hoàng Nhật Phong',0,'2003-04-21','0940856101',N'Bình Tân, TP HCM','nguyenhoangnhatphong@gmail.com'),
('KH050',N'Đặng Hoàng Phúc',0,'2003-04-22','0940856102',N'Củ Chi, TP HCM','danghoangphuc@gmail.com'),
('KH051',N'Nguyễn Văn Phúc',0,'2003-10-13','0940856103',N'Bình Chánh, TP HCM','nguyenvanphuc@gmail.com'),
('KH052',N'Lê Hồng Phương',1,'2003-06-24','0940856104',N'Củ Chi, TP HCM','lehongphuong@gmail.com'),
('KH053',N'Mai Thị Như Quỳnh',1,'2003-04-25','0940856105',N'Quận 10, TP HCM','maithinhuquynh@gmail.com'),
('KH054',N'Phan Thị Ly Sa',1,'2003-04-07','0940856106',N'Quận 7, TP HCM','phanthilysa@gmail.com'),
('KH055',N'Trần Thị Bích Sa',1,'2003-01-24','0940856107',N'Quận 12, TP HCM','tranthibichsa@gmail.com'),
('KH056',N'Nguyễn Ngọc Sơn',0,'2003-10-29','0940856108',N'Quận 3, TP HCM','nguyenngocson@gmail.com'),
('KH057',N'Trần Tấn Tài',0,'2003-02-27','0940856109',N'Hóc Môn, TP HCM','trantantai@gmail.com'),
('KH058',N'Nguyễn Phương Tâm',1,'2001-03-11','0940856110',N'Quận 11, TP HCM','nguyenphuongtam@gmail.com'),
('KH059',N'Văn Nhật Tân',0,'2001-06-15','0940856111',N'Tân Bình, TP HCM','vannhattan@gmail.com'),
('KH060',N'Đặng Quang Thái',0,'2003-03-06','0940856112',N'Bình Tân, TP HCM','dangquangthai@gmail.com'),
('KH061',N'Huỳnh Hiếu Thảo',0,'2003-06-15','0940856113',N'Quận 5, TP HCM','huynhhieuthao@gmail.com'),
('KH062',N'Lê Thị Thu Thảo',1,'2003-06-09','0940856115',N'Quận 8, TP HCM','lethithuthao@gmail.com'),
('KH063',N'Nguyễn Đức Thiện',0,'2003-10-20','0940856114',N'Bình Thạnh, TP HCM','nguyenducthien@gmail.com'),
('KH064',N'Phạm Minh Thông',0,'2003-10-31','0940856116',N'Quận 12, TP HCM','phamminhthong@gmail.com'),
('KH065',N'Vũ Thị Hồng Thu',1,'2003-09-22','0940856117',N'Phú Nhuận, TP HCM','vuthihongthu@gmail.com'),
('KH066',N'Nguyễn Quốc Tính',0,'2003-01-21','0940856118',N'Quận 7, TP HCM','nguyenquoctinh@gmail.com'),
('KH067',N'Nguyễn Minh Trí',0,'2003-12-31','0940856119',N'Quận 2, TP HCM','nguyenminhtri@gmail.com'),
('KH068',N'Lê Thị Phương Trinh',1,'2003-05-15','0940856120',N'Gò Vấp, TP HCM','lethiphuongtrinh@gmail.com'),
('KH069',N'Lê Minh Tuấn',0,'2003-05-10','0940856121',N'Tân Bình, TP HCM','leminhtuan@gmail.com'),
('KH070',N'Lê Văn Túc',0,'2002-07-11','0940856122',N'Quận 8, TP HCM','levantuc@gmail.com'),
('KH071',N'Hồ Thị Tuyền',1,'2003-10-01','0940856123',N'Quận 4, TP HCM','hothituyen@gmail.com'),
('KH072',N'Lê Thị Ngân Uyển',1,'2003-08-09','0940856124',N'Phú Nhuận, TP HCM','lethinganuyen@gmail.com'),
('KH073',N'Nguyễn Thị Anh Vân',1,'2003-12-25','0940856125',N'Nhà Bè, TP HCM','nguyenthianhvan@gmail.com'),
('KH074',N'Hồ Trần Thị Thảo Vy',1,'2003-07-10','0940856126',N'Bình Tân, TP HCM','hotranthithaovy@gmail.com'),
('KH075',N'Nguyễn Vân Tường Vy',1,'2003-10-22','0978812401',N'Gò Vấp, TP HCM','pctbim@gmail.com'),
('KH076',N'Trần Hồ Hạnh Vy',1,'2003-10-14','0940856128',N'Quận 6, TP HCM','tranhohanhvy@gmail.com'),
('KH077',N'Nguyễn Thị Thanh Xuân',1,'2003-12-07','0940856129',N'Củ Chi, TP HCM','nguyenthithanhxuan@gmail.com'),
('KH078',N'Nguyễn Thị Hồng Yến',1,'2003-08-13','0940856130',N'Quận 8, TP HCM','nguyenthihongyen@gmail.com'),
('KH079',N'Nguyễn Thị Ngọc Yến',1,'2003-02-07','0940856131',N'Nhà Bè, TP HCM','nguyenthingocyen@gmail.com')
GO 
INSERT INTO NhaSanXuat
VALUES ('NSX001', 'Pepsi'),
('NSX002', 'Flexoffice'),
('NSX003', N'Nestle'),
('NSX004', N'Coca-Cola'),
('NSX005',N'Poca'),
('NSX006', N'Thọ Phát'),
('NSX007', N'Vĩnh Hảo'),
('NSX008', N'Thiên Long'),
('NSX009', N'Hòa Phát'),
('NSX010', N'Oreo')

GO 
INSERT INTO NhaCungCap
VALUES ('NCC001', N'Việt Hoàng', 'viethoang@gmail.com', '0845678123'),
('NCC002', N'An Phát', 'anphat@gmail.com', '0987654234'),
('NCC003', N'Thiên Long', 'thienlong@gmail.com', '0284562354'),
('NCC004', N'Thiên Phát', 'thienphat@gmail.com', '0381592358'),
('NCC005', N'Vạn Phát', 'vanphat@gmail.com', '0973542678'),
('NCC006', N'Becamex IDC', 'becamexIDC@gmail.com', '0287381234'),
('NCC007', N'Hòa Phát', 'hoaphat@gmail.com', '0986596969'),
('NCC008', N'Ngọc Trời', 'ngoctroi@gmail.com', '0961234567'),
('NCC009', N'Dakota', 'dakota@gmail.com', '0287896789'),
('NCC010', N'Phát Tài', 'phattai@gmail.com', '0975662345')
GO
INSERT INTO SanPham
VALUES 
('SP001', N'Nước suối Lavie 500ml', 'NCC001', 200, N'Chai', 5000, 'NSX001'),
('SP002', N'Nước suối Vinh Hảo 350ml', 'NCC002', 150, N'Chai', 4000, 'NSX002'),
('SP003', N'Nước ngọt Coca Cola 330ml', 'NCC003', 300, N'Lon', 8000, 'NSX003'),
('SP004', N'Trà xanh ướp hương hoa cúc', 'NCC004', 100, N'Gói', 10000, 'NSX004'),
('SP005', N'Bánh quy Gery Socola', 'NCC005', 120, N'Gói', 15000, 'NSX005'),
('SP006', N'Kẹo dẻo Hải Sản', 'NCC006', 80, N'Gói', 5000, 'NSX006'),
('SP007', N'Khoai tây chiên Lays 70g', 'NCC007', 200, N'Gói', 10000, 'NSX007'),
('SP008', N'Chè bưởi đá xay', 'NCC008', 50, N'Tách', 20000, 'NSX008'),
('SP009', N'Bánh mì sandwich thịt nguội', 'NCC009', 70, N'Cái', 25000, 'NSX009'),
('SP010', N'Pudding trái cây', 'NCC010', 90, N'Cốc', 12000, 'NSX010'),
('SP011', N'Sữa tươi Vinamilk 180ml', 'NCC001', 180, N'Gói', 6000, 'NSX001'),
('SP012', N'Nước ngọt Pepsi 500ml', 'NCC002', 220, N'Chai', 7000, 'NSX002'),
('SP013', N'Trà sữa trân châu đường đen', 'NCC003', 120, N'Cốc', 20000, 'NSX003'),
('SP014', N'Bánh gạo cuộn Oreo', 'NCC004', 110, N'Gói', 18000, 'NSX004'),
('SP015', N'Kẹo chocolate Snickers', 'NCC005', 100, N'Gói', 10000, 'NSX005'),
('SP016', N'Khoai tây nghiền Hula Hoops', 'NCC006', 130, N'Gói', 12000, 'NSX006'),
('SP017', N'Chè bưởi hạt lựu', 'NCC007', 80, N'Tách', 18000, 'NSX007'),
('SP018', N'Bánh mì sandwich gà viên', 'NCC008', 60, N'Cái', 22000, 'NSX008'),
('SP019', N'Yogurt trái cây', 'NCC009', 150, N'Gói', 9000, 'NSX009'),
('SP020', N'Pudding chocolate', 'NCC010', 100, N'Cốc', 14000, 'NSX010'),
('SP021', N'Chanh muối', 'NCC001', 80, N'Chai', 6000, 'NSX001'),
('SP022', N'Nước ép cam', 'NCC002', 120, N'Chai', 10000, 'NSX002'),
('SP023', N'Bò kho', 'NCC003', 50, N'Gói', 25000, 'NSX003'),
('SP024', N'Mì gói Hảo Hảo', 'NCC004', 200, N'Gói', 5000, 'NSX004'),
('SP025', N'Kẹo mút trái cây', 'NCC005', 90, N'Gói', 8000, 'NSX005'),
('SP026', N'Khoai tây chiên Pringles', 'NCC006', 150, N'Lon', 20000, 'NSX006'),
('SP027', N'Trà đào', 'NCC007', 70, N'Chai', 12000, 'NSX007'),
('SP028', N'Bánh mì hamburger', 'NCC008', 100, N'Cái', 18000, 'NSX008'),
('SP029', N'Yogurt trân châu', 'NCC009', 80, N'Cốc', 15000, 'NSX009'),
('SP030', N'Pudding hạnh nhân', 'NCC010', 110, N'Cốc', 16000, 'NSX010')
GO 
INSERT INTO HoaDon
VALUES 
('HD001','NV002','KH048','2024-01-02',60000),
('HD002','NV003','KH064','2024-01-15',27000),
('HD003','NV001','KH072','2024-01-18',250000),
('HD004','NV002','KH074','2024-01-24',100000),
('HD005','NV003','KH072','2024-01-26',46000),
('HD006','NV001','KH021','2024-01-27',242000),
('HD007','NV003','KH042','2024-02-03',17000),
('HD008','NV001','KH013','2024-02-06',44000),
('HD009','NV003','KH023','2024-02-08',44000),
('HD010','NV002','KH041','2024-02-09',13000),
('HD011','NV001','KH047','2024-02-09',11000),
('HD012','NV003','KH054','2024-02-10',16000),
('HD013','NV002','KH065','2024-02-15',165000),
('HD014','NV001','KH059','2024-02-25',6000),
('HD015','NV001','KH049','2024-02-28',141000),
('HD016','NV001','KH043','2024-03-03',200000),
('HD017','NV002','KH055','2024-03-25',78000),
('HD018','NV003','KH001','2024-03-26',22000),
('HD019','NV002','KH035','2024-04-01',15000),
('HD020','NV002','KH001','2024-04-02',50000),
('HD021','NV002','KH035','2024-04-01',72000),
('HD022','NV002','KH001','2024-04-02',115000),
('HD023','NV002','KH035','2024-04-01',54000),
('HD024','NV002','KH001','2024-04-02',180000)
GO
INSERT INTO CT_HoaDon
VALUES 
('HD001','SP019',3),
('HD001','SP023',1),
('HD002','SP001',1),
('HD002','SP008',1),
('HD003','SP002',1),
('HD003','SP018',10),
('HD004','SP013',3),
('HD004','SP009',1),
('HD005','SP019',3),
('HD005','SP005',1),
('HD006','SP002',1),
('HD006','SP018',10),
('HD007','SP024',3),
('HD008','SP022',4),
('HD009','SP015',4),
('HD010','SP010',1),
('HD011','SP007',1),
('HD012','SP011',1),
('HD012','SP003',1),
('HD013','SP005',1),
('HD013','SP020',8),
('HD013','SP023',1),
('HD014','SP001',1),
('HD015','SP002',1),
('HD015','SP014',7),
('HD016','SP012',10),
('HD016','SP017',4),
('HD016','SP025',4),
('HD017','SP016',6),
('HD018','SP024',3),
('HD018','SP006',1),
('HD019','SP021',2),
('HD020','SP015',4),
('HD020','SP001',1),
('HD021','SP021',1),
('HD021','SP026',3),
('HD022','SP027',4),
('HD022','SP028',1),
('HD022','SP026',2),
('HD023','SP001',10),
('HD024','SP028',3),
('HD024','SP029',1),
('HD024','SP030',6)
GO
INSERT INTO PhieuDatHang
VALUES
('PDH001','NV002','KH063','2024-03-01','2024-03-01', 0, 103500, 0),
('PDH002','NV003','KH062','2024-03-01','2024-03-02', 1, 58650, 60000),
('PDH003','NV003','KH002','2024-03-01','2024-03-04', 0, 18400, 0),
('PDH004','NV001','KH021','2024-03-02','2024-03-04', 1, 115000,120000),
('PDH005','NV001','KH079','2024-03-02','2024-03-02', 1, 143750, 150000),
('PDH006','NV001','KH053','2024-03-03','2024-03-03', 1, 23000,23000),
('PDH007','NV001','KH072','2024-03-03','2024-03-04', 0, 41400, 0),
('PDH008','NV001','KH034','2024-03-04','2024-03-05', 0, 230000, 0),
('PDH009','NV001','KH049','2024-03-05','2024-03-07', 0, 161000, 0),
('PDH010','NV002','KH079','2024-03-06','2024-03-10', 1, 34500, 35000),
('PDH011','NV002','KH078','2024-03-06','2024-03-08', 0, 57500, 0),
('PDH012','NV001','KH070','2024-03-07','2024-03-08', 0, 34500, 0),
('PDH013','NV003','KH051','2024-03-07','2024-03-16', 1, 48300, 50000),
('PDH014','NV001','KH030','2024-03-07','2024-03-20', 0, 317400, 0),
('PDH015','NV001','KH049','2024-03-08','2024-03-10', 0, 23000, 0),
('PDH016','NV002','KH061','2024-03-08','2024-03-12', 1, 80500, 85000),
('PDH017','NV003','KH037','2024-03-08','2024-03-13', 0, 48300, 0),
('PDH018','NV002','KH038','2024-03-08','2024-03-16', 1, 69000, 70000),
('PDH019','NV001','KH005','2024-03-08','2024-03-23', 1, 18400, 20000),
('PDH020','NV002','KH001','2024-03-08','2024-03-11', 0, 124200, 0)
GO
INSERT INTO CT_PhieuDatHang
VALUES
('PDH001','SP007',7),
('PDH001','SP004',2),
('PDH002','SP025',3),
('PDH002','SP019',3),
('PDH003','SP025',2),
('PDH004','SP008',5),
('PDH005','SP023',5),
('PDH006','SP002',5),
('PDH007','SP019',4),
('PDH008','SP007',8),
('PDH008','SP005',8),
('PDH009','SP008',7),
('PDH010','SP022',3),
('PDH011','SP001',10),
('PDH012','SP021',5),
('PDH013','SP020',3),
('PDH014','SP017',7),
('PDH014','SP023',6),
('PDH015','SP024',4),
('PDH016','SP004',7),
('PDH017','SP020',3),
('PDH018','SP010',5),
('PDH019','SP002',4),
('PDH020','SP018',4),
('PDH020','SP002',5)




