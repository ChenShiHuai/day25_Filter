package studentinfo.domain;

/**
 * 实体类
 */
public class StudentInfo {




    private int id;
    private String name;
    private String sex;
    private int age;
    private String ys;
    private String className;
    private String hireDate;
    private String tel;
    private String jg;
    private String flag;


    @Override
    public String toString() {
        return "StudentInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", ys='" + ys + '\'' +
                ", className='" + className + '\'' +
                ", hireDate=" + hireDate +
                ", tel='" + tel + '\'' +
                ", jg='" + jg + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }

    public StudentInfo() {
    }

    public StudentInfo(int id, String name, String sex, int age, String ys, String className, String hireDate, String tel, String jg, String flag) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.ys = ys;
        this.className = className;
        this.hireDate = hireDate;
        this.tel = tel;
        this.jg = jg;
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getYs() {
        return ys;
    }

    public void setYs(String ys) {
        this.ys = ys;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getJg() {
        return jg;
    }

    public void setJg(String jg) {
        this.jg = jg;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
