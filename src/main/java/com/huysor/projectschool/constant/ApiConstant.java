package com.huysor.projectschool.constant;

public class  ApiConstant {
    public static final String ImagePath="/api/images/";

    // auth
    public static final String ApiRegister="/auth/register";
    public static final String ApiLogin="/auth/authentication";
    public static final String ApiUserPermission="/auth/user/permission";
    public static final String ApiRole="/auth/roles";
    public static final String ApiRolePermission="/auth/role/permission";



    // category
    public static final String ApiCategory="/api/category";
    public static final String ApiCategoryAll = "/api/category/all";
    public static final String ApiCategoryID="/api/category/{id}";
    public static final String ApiCategoryCount="/api/category/count";

    // course
    public static final String ApiCourse="/api/course";
    public static final String ApiCourseCount="/api/course/count";

    // student
    public static final String ApiStudent="/api/student";
    public static final String ApiStudentCount="/api/student/count";
    public static final String ApiStudentAll="/api/student/all";
}
