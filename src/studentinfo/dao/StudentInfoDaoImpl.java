package studentinfo.dao;

import studentinfo.domain.StudentInfo;
import studentinfo.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentInfoDaoImpl implements StudentInfoDao {
    /**
     * 获取记录列表
     * @return
     */
    @Override
    public List<StudentInfo> getList() {

        List<StudentInfo> infoList = new ArrayList<>();

        //1,定义SQL
        //只查询删除状态为0的，没有逻辑删除过的记录
        String sql = "select * from studentinfo where flag='0'";

        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        try {
            //2,获取数据库连接
            conn = DBUtils.getConnection();
            //3,创建执行SQL语句的对象
            pstmt = conn.prepareStatement(sql);
            //4,执行SQL
            rs = pstmt.executeQuery();
            //处理结果集
            while(rs.next()){
                 /*

    CREATE TABLE `studentinfo` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `name` varchar(64) DEFAULT NULL,
      `sex` char(1) DEFAULT NULL,
      `age` int(11) DEFAULT NULL,
      `ys` varchar(32) DEFAULT NULL,
      `class` varchar(64) DEFAULT NULL,
      `hiredate` date DEFAULT NULL,
      `tel` varchar(32) DEFAULT NULL,
      `jg` varchar(32) DEFAULT NULL,
      `flag` varchar(255) DEFAULT NULL,
      PRIMARY KEY (`id`)
    )
     */
                //逐列获取
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                int age = rs.getInt("age");
                String ys = rs.getString("ys");
                String aClass = rs.getString("class");
                //java.sql.date
                //Date hiredate = rs.getDate("hiredate");
                String hiredate = rs.getString("hiredate");
                String tel = rs.getString("tel");
                String jg = rs.getString("jg");
                String flag = rs.getString("flag");

                //将数据表的记录封装到对象
                StudentInfo stu = new StudentInfo();

                stu.setId(id);
                stu.setName(name);
                stu.setSex(sex);
                stu.setAge(age);
                stu.setYs(ys);
                stu.setClassName(aClass);
                stu.setHireDate(hiredate);
                stu.setTel(tel);
                stu.setJg(jg);
                stu.setFlag(flag);

                infoList.add(stu);//将对象放入到集合中

            }

            return infoList;


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭连接
            DBUtils.closeConnection(rs,pstmt,conn);
        }


        return null;
    }
    /**
     * 新增一条记录
     * @param studentInfo
     * @return
     */
    @Override
    public int add(StudentInfo studentInfo) {
        //1,定义SQL
        //id主键列，数据库里面设置为自增，我们这里插入数据的时候，就不再设值了
        String sql = "insert into studentinfo(name,sex,age,ys,class,hiredate,tel,jg,flag) " +
                "values(?,?,?,?,?,?,?,?,?)";
        //
        Connection conn = null;
        PreparedStatement pstmt =null;
        try {
            //2,获取数据库连接
            conn = DBUtils.getConnection();
            //3,
            pstmt = conn.prepareStatement(sql);
            //设置参数
            pstmt.setString(1,studentInfo.getName());
            pstmt.setString(2,studentInfo.getSex());
            pstmt.setInt(3,studentInfo.getAge());
            pstmt.setString(4,studentInfo.getYs());
            pstmt.setString(5,studentInfo.getClassName());
            pstmt.setString(6,studentInfo.getHireDate());
            pstmt.setString(7,studentInfo.getTel());
            pstmt.setString(8,studentInfo.getJg());
            pstmt.setString(9,"0");//默认状态

            //4,执行SQL
            int result = pstmt.executeUpdate();
            System.out.println("受影响的行数："+result);
            return result;


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeConnection(null,pstmt,conn);
        }
        return -1;
    }

    /**
     * 根据id查询一条记录
     * @param id
     * @return
     */
    @Override
    public StudentInfo getStuById(int id) {
        List<StudentInfo> infoList = new ArrayList<>();

        //1,定义SQL
        String sql = "select * from studentinfo where id=?";
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        try {
            //2,获取数据库连接
            conn = DBUtils.getConnection();
            //3,
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            //4,执行SQL
            rs = pstmt.executeQuery();
            //处理结果集
            while(rs.next()){
                //逐列获取
                int idColumn = rs.getInt("id");
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                int age = rs.getInt("age");
                String ys = rs.getString("ys");
                String aClass = rs.getString("class");
                //java.sql.date
                //Date hiredate = rs.getDate("hiredate");
                String hiredate = rs.getString("hiredate");
                String tel = rs.getString("tel");
                String jg = rs.getString("jg");
                String flag = rs.getString("flag");


                //封装对象
                StudentInfo stu = new StudentInfo();

                stu.setId(id);
                stu.setName(name);
                stu.setSex(sex);
                stu.setAge(age);
                stu.setYs(ys);
                stu.setClassName(aClass);
                stu.setHireDate(hiredate);
                stu.setTel(tel);
                stu.setJg(jg);
                stu.setFlag(flag);
                return stu;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭连接
            DBUtils.closeConnection(rs,pstmt,conn);
        }


        return null;
    }

    /**
     * 修改一条记录，修改的时候，studentinfo对象一定要有id
     * @param studentInfo
     * @return
     */
    @Override
    public int update(StudentInfo studentInfo) {
        //1,定义SQL
        String sql = "update studentinfo set name=?,sex=?,age=?,ys=?,class=?,hiredate=?,tel=?,jg=?,flag=? where id=?";
        //
        Connection conn = null;
        PreparedStatement pstmt =null;
        try {
            //2,获取数据库连接
            conn = DBUtils.getConnection();
            //3,
            pstmt = conn.prepareStatement(sql);
            //设置参数
            pstmt.setString(1,studentInfo.getName());
            pstmt.setString(2,studentInfo.getSex());
            pstmt.setInt(3,studentInfo.getAge());
            pstmt.setString(4,studentInfo.getYs());
            pstmt.setString(5,studentInfo.getClassName());
            pstmt.setString(6,studentInfo.getHireDate());
            pstmt.setString(7,studentInfo.getTel());
            pstmt.setString(8,studentInfo.getJg());
            pstmt.setString(9,studentInfo.getFlag());
            //最后一个参数是ID，这个是关键的参数
            pstmt.setInt(10,studentInfo.getId());

            //4,执行SQL
            int result = pstmt.executeUpdate();
            System.out.println("受影响的行数："+result);
            return result;


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeConnection(null,pstmt,conn);
        }
        return -1;
    }

    /**
     * 删除记录
     * @param ids 用数组存储多个id,可以批量删除
     * @return
     */
    @Override
    public int delete(String[] ids) {
        /*
        逻辑删除：更新一下flag字段的值，从0变为1，查询的时候，不要查询出来1状态的数据


         */
        StringBuffer sb = new StringBuffer();
        String idStr = null;
        if(ids!=null){
            for(String id:ids){
                sb.append(id).append(",");//1,2,3,
            }
            //拼接完，会多一个,
            idStr = sb.toString().substring(0, sb.length()-1);
            //1,2,3
        }
        System.out.println("批量删除选中的id,idStr:"+idStr);


        //1,定义SQL
        //update studentinfo set flag='1' where id in (1,2,3);最终的参数是：1,2,3
        String sql = "update studentinfo set flag=? where id in ("+idStr+")";

        System.out.println("打印一下拼接的sql；"+sql);
        //
        Connection conn = null;
        PreparedStatement pstmt =null;
        try {
            //2,获取数据库连接
            conn = DBUtils.getConnection();
            //3,
            pstmt = conn.prepareStatement(sql);
            //设置参数
            pstmt.setString(1,"1");//设置逻辑删除状态
            //4,执行SQL
            int result = pstmt.executeUpdate();
            System.out.println("受影响的行数："+result);
            return result;


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeConnection(null,pstmt,conn);
        }
        return -1;
    }
}
