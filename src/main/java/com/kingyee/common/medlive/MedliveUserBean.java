package com.kingyee.common.medlive;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.lang3.StringUtils;

public class MedliveUserBean {
    //用户id
    private Long user_id;
    //昵称
    private String nick;
    //是否修改过昵称
    private String is_nick_upd;
    //是否修改过密码
    private String is_pwd_upd;
    //用户真实姓名
    private String name;
    //性别
    private String gender;
    //邮箱
    private String email;
    //手机号
    private String mobile;
    //省份
    private String province;
    //城市
    private String city;
    //区
    private String district;
    //头像url
    private String thumb;
    //等级
    private String level;
    //经验
    private String experience;
    //专业详细
    private JsonNode profession;
    //专业名称
    private String profession_name;
    //第一级职称
    private String carclass;
    //第二级职称
    private String carclass2;
    //单位
    private String company_name;
    //积分
    private Integer score;
    //被锁定积分
    private Integer scorelocked;
    //今年麦粒
    private Integer maili;
    //麦粒锁定
    private Integer maili_locked;
    //去年麦粒
    private Integer maili_lastyear;
    //注册来源(medlive 等)
    private String reg_from;
    //认证类型:expert专家，doctor医生，student医学生，apothecary药师，nurse护师，technician技师，other其他
    private String certify_flg;
    //是否正在认证(Y/N)
    private String is_certifing;
    //本月是否修改过(Y/N)
    private String is_edit;
    //注册时间 2008-07-04 15:37:14
    private String reg_time;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getIs_nick_upd() {
        return is_nick_upd;
    }

    public void setIs_nick_upd(String is_nick_upd) {
        this.is_nick_upd = is_nick_upd;
    }

    public String getIs_pwd_upd() {
        return is_pwd_upd;
    }

    public void setIs_pwd_upd(String is_pwd_upd) {
        this.is_pwd_upd = is_pwd_upd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getThumb() {
        if (StringUtils.isNotEmpty(thumb)) {
            return thumb.replaceFirst("http:", "");
        }
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public JsonNode getProfession() {
        return profession;
    }

    public void setProfession(JsonNode profession) {
        this.profession = profession;
    }

    public String getProfession_name() {
        return profession_name;
    }

    public void setProfession_name(String profession_name) {
        this.profession_name = profession_name;
    }

    public String getCarclass() {
        return carclass;
    }

    public void setCarclass(String carclass) {
        this.carclass = carclass;
    }

    public String getCarclass2() {
        return carclass2;
    }

    public void setCarclass2(String carclass2) {
        this.carclass2 = carclass2;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getScorelocked() {
        return scorelocked;
    }

    public void setScorelocked(Integer scorelocked) {
        this.scorelocked = scorelocked;
    }

    public Integer getMaili() {
        return maili;
    }

    public void setMaili(Integer maili) {
        this.maili = maili;
    }

    public Integer getMaili_locked() {
        return maili_locked;
    }

    public void setMaili_locked(Integer maili_locked) {
        this.maili_locked = maili_locked;
    }

    public Integer getMaili_lastyear() {
        return maili_lastyear;
    }

    public void setMaili_lastyear(Integer maili_lastyear) {
        this.maili_lastyear = maili_lastyear;
    }

    public String getReg_from() {
        return reg_from;
    }

    public void setReg_from(String reg_from) {
        this.reg_from = reg_from;
    }

    public String getCertify_flg() {
        return certify_flg;
    }

    public void setCertify_flg(String certify_flg) {
        this.certify_flg = certify_flg;
    }

    public String getIs_certifing() {
        return is_certifing;
    }

    public void setIs_certifing(String is_certifing) {
        this.is_certifing = is_certifing;
    }

    public String getIs_edit() {
        return is_edit;
    }

    public void setIs_edit(String is_edit) {
        this.is_edit = is_edit;
    }

    public String getReg_time() {
        return reg_time;
    }

    public void setReg_time(String reg_time) {
        this.reg_time = reg_time;
    }

    @Override
    public String toString() {
        return "MedliveUserBean{" +
                "user_id=" + user_id +
                ", nick='" + nick + '\'' +
                ", is_nick_upd='" + is_nick_upd + '\'' +
                ", is_pwd_upd='" + is_pwd_upd + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", thumb='" + getThumb() + '\'' +
                ", level='" + level + '\'' +
                ", experience='" + experience + '\'' +
                ", profession=" + profession +
                ", profession_name='" + profession_name + '\'' +
                ", carclass='" + carclass + '\'' +
                ", carclass2='" + carclass2 + '\'' +
                ", company_name='" + company_name + '\'' +
                ", score=" + score +
                ", scorelocked=" + scorelocked +
                ", maili=" + maili +
                ", maili_locked=" + maili_locked +
                ", maili_lastyear=" + maili_lastyear +
                ", reg_from='" + reg_from + '\'' +
                ", certify_flg='" + certify_flg + '\'' +
                ", is_certifing='" + is_certifing + '\'' +
                ", is_edit='" + is_edit + '\'' +
                ", reg_time='" + reg_time + '\'' +
                '}';
    }
}
