package fina.student.securechat;

public class Friend {
    public Friend(String name, String img, String email, String phone, String public_key) {
        this.name = name;
        this.img = img;
        this.email = email;
        this.phone = phone;
        this.public_key = public_key;
    }

    String name;
    String img;
    String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPublic_key() {
        return public_key;
    }

    public void setPublic_key(String public_key) {
        this.public_key = public_key;
    }

    String phone;
    String public_key;
}
