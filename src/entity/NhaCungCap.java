package entity;

public class NhaCungCap {
    private String maNhaCungCap;
    private String tenNhaCungCap;
    private String email;
    private String Sdt;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(String maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String Sdt) {
        this.Sdt = Sdt;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public NhaCungCap() {
        super();
    }

    public NhaCungCap(String maNhaCungCap, String tenNhaCungCap, String email, String Sdt) {
        super();
        this.maNhaCungCap = maNhaCungCap;
        this.tenNhaCungCap = tenNhaCungCap;
        this.email = email;
        this.Sdt = Sdt;
    }

    public NhaCungCap(String maNhaCungCap) {
	super();
	this.maNhaCungCap = maNhaCungCap;
    }

    public NhaCungCap(String maNhaCungCap,String tenNhaCungCap) {
    	super();
    	this.maNhaCungCap = maNhaCungCap;
    	this.tenNhaCungCap = tenNhaCungCap;
        }

    @Override
	public String toString() {
		return "NhaCungCap [maNCC=" + maNhaCungCap + ", tenNCC=" + tenNhaCungCap
				+ ", email=" + email + ", sDT=" + Sdt + "]";
	}

}
