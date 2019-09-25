package studentinfo.servlet;

import studentinfo.dao.StudentInfoDao;
import studentinfo.dao.StudentInfoDaoImpl;
import studentinfo.domain.StudentInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/stuInfoServlet")
public class StuInfoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         * 一开始的时候，一定要设置一下请求的编码，解决中文乱码
         */

        request.setCharacterEncoding("UTF-8");
        /**
         *这个部分是整个前端访问路径跳转的控制中心，
         *
         */
        //1，获取method参数
        /*
        根据method的值：
        list:列表
        toAdd:去到新增页面
        toEdit:去到编辑页面
        add:新增一条记录
        edit:更新一条记录
        remove:删除记录

         */
        String method = request.getParameter("method");
        System.out.println("method:"+method);
        if("list".equals(method)){
            //查询学生信息列表
            list(request,response);
        }else if("toAdd".equals(method)){
            toAdd(request,response);//跳转到新增页面
        }else if("add".equals(method)){
            add(request,response);//新增的页面点击保存，表单提交的方法
        }else if("toEdit".equals(method)){
            toEdit(request,response);//跳转到编辑页面
        }else if("edit".equals(method)){
            edit(request,response);//编辑页面点击保存，表单提交的方法
        }else if("remove".equals(method)){
            remove(request,response);//逻辑删除记录
        }




    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1,获取参数
        String id = request.getParameter("id");
        //2,根据id查询学生信息，在编辑页面显示
        StudentInfoDao infoDao = new StudentInfoDaoImpl();
        //根据id拿到学生的对象
        StudentInfo stuInfo = infoDao.getStuById(Integer.parseInt(id));
        //设置到request域里面
        request.setAttribute("stuInfo",stuInfo);
        //需要传递数据，所以这里用的转发
        request.getRequestDispatcher("/studentinfo/stuInfoEdit.jsp").forward(request,response);
    }

    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/studentinfo/stuInfoAdd.jsp").forward(request,response);
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1,获取参数
        /*
         使用getParameterValues,是因为等下需要批量删除，会吧参数的值封装一个数组
         不管是1个还是多个，都用数组存储
         */
        String[] uids = request.getParameterValues("uid");
        System.out.println("uids:"+uids);
        //调用DAO层的方法，
        StudentInfoDao infoDao = new StudentInfoDaoImpl();
        //调用删除方法的时候，直接将获取的参数的值的数组，传递过去
        int result = infoDao.delete(uids);

        if(result>0){
            list(request,response);
        }else{
            response.sendRedirect(request.getContextPath()+"/studentinfo/error.jsp");
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1,获取参数
        //编辑的时候，一定要获取到页面的id,然后根据id更新
        String id = request.getParameter("id");

        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        System.out.println("sex:"+sex);
        String age = request.getParameter("age");
        String ys = request.getParameter("ys");
        String className = request.getParameter("className");
        String hireDate = request.getParameter("hireDate");

        String tel = request.getParameter("tel");
        String jg = request.getParameter("jg");
        String flag = request.getParameter("flag");
        //2,封装对象
        StudentInfo stuInfo = new StudentInfo();
        stuInfo.setId(Integer.parseInt(id));
        stuInfo.setName(name);
        stuInfo.setSex(sex);
        stuInfo.setAge(Integer.parseInt(age));//转化一下
        stuInfo.setYs(ys);
        stuInfo.setClassName(className);
        stuInfo.setHireDate(hireDate);
        stuInfo.setTel(tel);
        stuInfo.setJg(jg);
        stuInfo.setFlag(flag);

        //调用DAO层的相应的更新方法
        StudentInfoDao infoDao = new StudentInfoDaoImpl();
        //返回一个受影响的行数
        int result = infoDao.update(stuInfo);
        if(result>0){
            //跳转到列表页面
            list(request,response);
        }else{
            response.sendRedirect(request.getContextPath()+"/studentinfo/error.jsp");
        }
    }


    /**
     * 新增一条记录
     * @param request
     * @param response
     */
    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

/*        private int id;
        private String name;
        private String sex;
        private int age;
        private String ys;
        private String className;
        private Date hireDate;
        private String tel;
        private String jg;
        private String flag;*/

//        request.setCharacterEncoding("utf-8");

        //1,获取参数
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        System.out.println("sex:"+sex);
        String age = request.getParameter("age");
        String ys = request.getParameter("ys");
        String className = request.getParameter("className");
        String hireDate = request.getParameter("hireDate");

        String tel = request.getParameter("tel");
        String jg = request.getParameter("jg");
        //2,封装对象
        StudentInfo stuInfo = new StudentInfo();
        stuInfo.setName(name);
        stuInfo.setSex(sex);
        stuInfo.setAge(Integer.parseInt(age));//转化一下
        stuInfo.setYs(ys);
        stuInfo.setClassName(className);
        stuInfo.setHireDate(hireDate);
        stuInfo.setTel(tel);
        stuInfo.setJg(jg);

        //调用DAO层的相应的新增的方法
        StudentInfoDao infoDao = new StudentInfoDaoImpl();
        //受影响的行数
        int result = infoDao.add(stuInfo);
        if(result>0){
            //表示保存成功，跳转到列表页面
            list(request,response);
        }else{
            //重定向的时候，记得加上项目访问路径
            response.sendRedirect(request.getContextPath()+"/studentinfo/error.jsp");
        }


    }

    /**
     * 获取学生信息列表
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1,获取学生信息列表
        StudentInfoDao infoDao = new StudentInfoDaoImpl();
        List<StudentInfo> list = infoDao.getList();
        //2,放到request域中
        request.setAttribute("stuList",list);

        //转发到页面
        //这里面涉及到数据的共享，
        request.getRequestDispatcher("/studentinfo/stuInfoList.jsp").forward(request,response);

    }






}
