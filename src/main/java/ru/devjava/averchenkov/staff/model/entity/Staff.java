package ru.devjava.averchenkov.staff.model.entity;

import ru.devjava.averchenkov.staff.service.parse.ServiceParse;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Persistence сотрудника.
 *
 * @author Averchenkov R.A.
 */
@Entity
@Table(name = "Staff")
public class Staff implements Serializable {
    private Long stfId;
    private String stfName;
    private String stfSurname;
    private String stfPatronymic;
    private String stfPost;
    private Date stfBirthday;

    @Id
    @Column(name = "stf_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getStfId() {
        return stfId;
    }
    public void setStfId(Long stfId) {
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
    public Date getStfBirthday() {
        return stfBirthday;
    }
    public void setStfBirthday(Date stfBirthday) {
        this.stfBirthday = stfBirthday;
    }

    public Staff() {}
    public Staff(String stfName, String stfSurname,
                 String stfPatronymic, String stfPost,
                 Date stfBirthday) {
        this.stfName = stfName;
        this.stfSurname = stfSurname;
        this.stfPatronymic = stfPatronymic;
        this.stfPost = stfPost;
        this.stfBirthday = stfBirthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Staff staff = (Staff) o;

        if (stfId != null ? !stfId.equals(staff.stfId) : staff.stfId != null) return false;
        if (stfName != null ? !stfName.equals(staff.stfName) : staff.stfName != null) return false;
        if (stfSurname != null ? !stfSurname.equals(staff.stfSurname) : staff.stfSurname != null) return false;
        if (stfPatronymic != null ? !stfPatronymic.equals(staff.stfPatronymic) : staff.stfPatronymic != null)
            return false;
        if (stfPost != null ? !stfPost.equals(staff.stfPost) : staff.stfPost != null) return false;
        return stfBirthday != null ? stfBirthday.equals(staff.stfBirthday) : staff.stfBirthday == null;

    }

    @Override
    public int hashCode() {
        int result = stfId != null ? stfId.hashCode() : 0;
        result = 31 * result + (stfName != null ? stfName.hashCode() : 0);
        result = 31 * result + (stfSurname != null ? stfSurname.hashCode() : 0);
        result = 31 * result + (stfPatronymic != null ? stfPatronymic.hashCode() : 0);
        result = 31 * result + (stfPost != null ? stfPost.hashCode() : 0);
        result = 31 * result + (stfBirthday != null ? stfBirthday.hashCode() : 0);
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
                ", stfBirthday=" + stfBirthday +
                '}';
    }
}
