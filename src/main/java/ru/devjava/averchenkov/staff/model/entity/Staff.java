package ru.devjava.averchenkov.staff.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Persistence сотрудника.
 *
 * @author Averchenkov R.A.
 */
@Entity
@Table(name = "staff")
public class Staff implements Serializable {
    private long stfId;
    private String stfName;
    private String stfSurname;
    private String stfPatronymic;
    private String stfPost;
    private Date birthday;

    @Id
    @Column(name = "stf_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getStfId() {
        return stfId;
    }
    public void setStfId(long stfId) {
        this.stfId = stfId;
    }

    @Basic
    @Column(name = "stf_name", nullable = false)
    public String getStfName() {
        return stfName;
    }
    public void setStfName(String stfName) {
        this.stfName = stfName;
    }

    @Basic
    @Column(name = "stf_surname", nullable = false)
    public String getStfSurname() {
        return stfSurname;
    }
    public void setStfSurname(String stfSurname) {
        this.stfSurname = stfSurname;
    }

    @Basic
    @Column(name = "stf_patronymic")
    public String getStfPatronymic() {
        return stfPatronymic;
    }
    public void setStfPatronymic(String stfPatronymic) {
        this.stfPatronymic = stfPatronymic;
    }

    @Basic
    @Column(name = "stf_post", nullable = false)
    public String getStfPost() {
        return stfPost;
    }
    public void setStfPost(String stfPost) {
        this.stfPost = stfPost;
    }

    @Basic
    @Column(name = "stf_birthday")
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Staff() {}
    public Staff(String stfName, String stfSurname,
                 String stfPatronymic, String stfPost,
                 Date birthday) {
        this.stfName = stfName;
        this.stfSurname = stfSurname;
        this.stfPatronymic = stfPatronymic;
        this.stfPost = stfPost;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Staff staff = (Staff) o;

        if (stfId != staff.stfId) return false;
        if (stfName != null ? !stfName.equals(staff.stfName) : staff.stfName != null) return false;
        if (stfSurname != null ? !stfSurname.equals(staff.stfSurname) : staff.stfSurname != null) return false;
        if (stfPatronymic != null ? !stfPatronymic.equals(staff.stfPatronymic) : staff.stfPatronymic != null)
            return false;
        if (stfPost != null ? !stfPost.equals(staff.stfPost) : staff.stfPost != null) return false;
        return birthday != null ? birthday.equals(staff.birthday) : staff.birthday == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (stfId ^ (stfId >>> 32));
        result = 31 * result + (stfName != null ? stfName.hashCode() : 0);
        result = 31 * result + (stfSurname != null ? stfSurname.hashCode() : 0);
        result = 31 * result + (stfPatronymic != null ? stfPatronymic.hashCode() : 0);
        result = 31 * result + (stfPost != null ? stfPost.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "stfId=" + stfId +
                ", stfName='" + stfName + '\'' +
                ", stfSurname='" + stfSurname + '\'' +
                ", stfPatronymic='" + stfPatronymic + '\'' +
                ", stfPost='" + stfPost + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
