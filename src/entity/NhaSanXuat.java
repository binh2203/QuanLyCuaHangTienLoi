package entity;

public class NhaSanXuat {
    private String maNhaSX;
    private String tenNhaSX;

    public NhaSanXuat() {
        super();
    }

    public NhaSanXuat(String maNhaSX){
        super();
        this.maNhaSX = maNhaSX;
    }

    public NhaSanXuat(String maNhaSX, String tenNhaSX) {
        super();
        this.maNhaSX = maNhaSX;
        this.tenNhaSX = tenNhaSX;
    }

    public String getMaNhaSX() {
        return maNhaSX;
    }

    public void setMaNhaSX(String maNhaSX) {
        this.maNhaSX = maNhaSX;
    }

    public String getTenNhaSX() {
        return tenNhaSX;
    }

    public void setTenNhaSX(String tenNhaSX) {
        this.tenNhaSX = tenNhaSX;
    }

    @Override
    public String toString() {
        return "NhaSanXuat [maNhaSX=" + maNhaSX + ", tenNhaSX=" + tenNhaSX + "]";
    }


}
