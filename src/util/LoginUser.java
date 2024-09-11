package util;

public  class  LoginUser {
    private static String name;
    private static Integer id;

    public static void setLoginUser( Integer id) {
        LoginUser.id = id;
    }

    public static Integer getUserId() {
        return id;
    }


}