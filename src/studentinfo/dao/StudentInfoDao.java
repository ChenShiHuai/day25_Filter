package studentinfo.dao;

import studentinfo.domain.StudentInfo;

import java.util.List;

public interface StudentInfoDao {

    /**
     * 获取记录列表
     * @return
     */
    List<StudentInfo> getList();

    /**
     * 新增一条记录
     * @param studentInfo
     * @return
     */
    int add(StudentInfo studentInfo);

    /**
     * 根据id查询一条记录
     * @param id
     * @return
     */
    StudentInfo getStuById(int id);

    /**
     * 修改一条记录
     * @param studentInfo
     * @return
     */
    int update(StudentInfo studentInfo);

    /**
     * 删除记录
     * @param ids 用数组存储多个id,可以批量删除
     * @return
     */
    int delete(String[] ids);



}
