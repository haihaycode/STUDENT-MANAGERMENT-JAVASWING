package models;

public class Student {
	private String ID;
	private String maSV;
	private String hoTen;
	private String email;
	private String gioiTinh;
	private String diaChi;
	private String hinhAnh;
	private float diemTiengAnh;
	private float diemTinHoc;
	private float diemGiaoDucTheChat;
	private int sdt;

	public Student(String maSV, String hoTen, String email, int sdt, String gioiTinh, String diaChi, String hinhAnh) {
		this.maSV = maSV;
		this.hoTen = hoTen;
		this.email = email;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.hinhAnh = hinhAnh;
		this.sdt = sdt;
	}

	public Student(String iD, String maSV, String hoTen, float diemTiengAnh, float diemTinHoc,
			float diemGiaoDucTheChat) {
		this.ID = iD;
		this.maSV = maSV;
		this.hoTen = hoTen;
		this.diemTiengAnh = diemTiengAnh;
		this.diemTinHoc = diemTinHoc;
		this.diemGiaoDucTheChat = diemGiaoDucTheChat;
	}

	public Student(String iD, String maSV, String hoTen, String email, String gioiTinh, String diaChi, String hinhAnh,
			float diemTiengAnh, float diemTinHoc, float diemGiaoDucTheChat, int sdt) {
		this.ID = iD;
		this.maSV = maSV;
		this.hoTen = hoTen;
		this.email = email;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.hinhAnh = hinhAnh;
		this.diemTiengAnh = diemTiengAnh;
		this.diemTinHoc = diemTinHoc;
		this.diemGiaoDucTheChat = diemGiaoDucTheChat;
		this.sdt = sdt;
	}

	public String getID() {
		return this.ID;
	}

	public void setID(String iD) {
		this.ID = iD;
	}

	public String getMaSV() {
		return this.maSV;
	}

	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}

	public String getHoTen() {
		return this.hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGioiTinh() {
		return this.gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getDiaChi() {
		return this.diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getHinhAnh() {
		return this.hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public float getDiemTiengAnh() {
		return this.diemTiengAnh;
	}

	public void setDiemTiengAnh(float diemTiengAnh) {
		this.diemTiengAnh = diemTiengAnh;
	}

	public float getDiemTinHoc() {
		return this.diemTinHoc;
	}

	public void setDiemTinHoc(float diemTinHoc) {
		this.diemTinHoc = diemTinHoc;
	}

	public float getDiemGiaoDucTheChat() {
		return this.diemGiaoDucTheChat;
	}

	public void setDiemGiaoDucTheChat(float diemGiaoDucTheChat) {
		this.diemGiaoDucTheChat = diemGiaoDucTheChat;
	}

	public int getSdt() {
		return this.sdt;
	}

	public void setSdt(int sdt) {
		this.sdt = sdt;
	}
}