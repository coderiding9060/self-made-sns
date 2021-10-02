package self.selfmadesns.members.domain;

import java.sql.Date;

public class Member {
    private Long no;
    private String id;
    private String pw;
    private String name;
    private String email;
    private String gender;
    private String profileImage;
    private Date reg_date;

    public Member() {
    }

    public Member(Long no, String id, String pw, String name, String email, String gender, String profileImage, Date reg_date) {
        this.no = no;
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.profileImage = profileImage;
        this.reg_date = reg_date;
    }

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    @Override
    public String toString() {
        return "Member{" +
                "no=" + no +
                ", id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", profileImage='" + profileImage + '\'' +
                ", reg_date=" + reg_date +
                '}';
    }
}
