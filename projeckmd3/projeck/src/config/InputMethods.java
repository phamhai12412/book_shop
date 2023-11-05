package config;


import java.util.Scanner;

public class InputMethods {
    private static final String ERROR_ALERT = "===>> Định dạng không hợp lệ, hoặc ngoài phạm vi! Vui lòng thử lại....";
    public static final String EMPTY_ALERT = "===>> Trường nhập vào không thể để trống! Vui lòng thử lại....";
    public static final String ERROR_NUMBER = "===>> Vui lòng nhập số nguyên lớn hơn 0";
    public static final String ERROR_USENAME = "===>> Usename phải lớn hơn 5 kí tự và không chứa khoảng trắng giữa các kí tự";
    public static final String ERROR_PASS = "===>> password phải lớn hơn 5 kí tự và không chứa khoảng trắng giữa các kí tự";
    public static final String ERROR_PHONE = "===>> Số điện thoại bạn nhập không đúng định dạng";
    public static final String ERROR_EMAIL = "===>> Email không đúng định dạng";
    public static final String ERROR_MATL = "===>> Mã thể loại không đúng định dạng";
    public static final String ERROR_EVENT = "===>> Bạn chỉ nhập được % giảm từ 1-100 và là số nguyên";
    public static final String ERROR_EDITSL = "===>> Bạn phải nhập số nguyên >=0";
    public static final String ERROR_Chon = "===>> Bạn hãy nhập vào số nguyên theo yêu cầu";
    public static final String ERROR_vi = "===>> Bạn hãy nhập vào số nguyên lớn hơn hoặc bằng 50000";
    public static final String ERROR_TENSACH_TL = "===>> Ít nhất phải có 6 kí tự";
    public static final String ERROR_DIACHI = "===>> Ít nhất phải có 10 kí tự";
    public static final String ERROR_tiensach = "===>> bạn hãy nhập vào số nguyên lớn hơn 1000";


    /*========================================Input Method Start========================================*/

    /**
     * getString()  Return a String value from the user.
     */
    public static String getString() {

        while (true) {
            String result = getInput();
            if (result.trim().equals("")) {
                System.err.println(EMPTY_ALERT);
                continue;
            }
            return result;
        }
    }

    /**
     * getChar()  Return a Character value from the user.
     */
    public static char getChar() {
        return getString().charAt(0);
    }

    /**
     * getBoolean()  Return a Boolean value from the user.
     */
    public static boolean getBoolean() {
        String result = getString();
        return result.equalsIgnoreCase("true");
    }

    /**
     * getByte()  Return a Byte value from the user.
     */
    public static byte getByte() {
        while (true) {
            try {
                return Byte.parseByte(getString());
            } catch (NumberFormatException errException) {
                System.err.println(ERROR_ALERT);
            }
        }
    }

    /**
     * getShort()  Return a Short value from the user.
     */
    public static short getShort() {
        while (true) {
            try {
                return Short.parseShort(getString());
            } catch (NumberFormatException errException) {
                System.err.println(ERROR_ALERT);
            }
        }
    }

    /**
     * getInteger()  Return a Integer value from the user.
     */
    public static int getInteger() {
        while (true) {
            try {
                return Integer.parseInt(getString());
            } catch (NumberFormatException errException) {
                System.err.println(ERROR_ALERT);
            }
        }
    }

    /**
     * getLong()  Return a Long value from the user.
     */
    public static long getLong() {
        while (true) {
            try {
                return Long.parseLong(getString());
            } catch (NumberFormatException errException) {
                System.err.println(ERROR_ALERT);
            }
        }
    }

    /**
     * getFloat()  Return a Float value from the user.
     */
    public static float getFloat() {
        while (true) {
            try {
                return Float.parseFloat(getString());
            } catch (NumberFormatException errException) {
                System.err.println(ERROR_ALERT);
            }
        }
    }

    /**
     * getDouble()  Return a Double value from the user.
     */
    public static double getDouble() {
        while (true) {
            try {
                return Double.parseDouble(getString());
            } catch (NumberFormatException errException) {
                System.err.println(ERROR_ALERT);
            }
        }
    }
    /*========================================Input Method End========================================*/

    /**
     * getInput()  Return any String value from the user.
     */
    public static String getInput() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    /**
     * pressAnyKey()  Press any key to continue....
     */
    public static void pressAnyKey() {
        getInput();
    }

    //kiểm tra số nhập vào có lớn hon 0
    public static int getPositiveInteger() {
        while (true) {
            int result = getInteger();
            if (result > 0) {
                return result;
            }
            System.err.println(ERROR_NUMBER);
        }
    }

    // kiểm tra id có đúng định dạng không
    public static String getusename() {
        while (true) {
            String result = getString();
            if (result.trim().length() >= 5 && result.trim().replaceAll("\\s+", "").equals(result.trim())) {

                return result;
            }
            System.err.println(ERROR_USENAME);
        }
    }

    //kiểm tra password
    public static String getpassword() {
        while (true) {
            String result = getString();
            if (result.trim().length() >= 5 && result.trim().replaceAll("\\s+", "").equals(result.trim())) {
                return result;
            }
            System.err.println(ERROR_PASS);
        }
    }

    public static String getPhoneNumber() {
        while (true) {
            String result = getString();


            if (result.matches("^(\\+?84|0)\\d{9}$")) {
                // Hợp lệ nếu số điện thoại có đúng định dạng
                return result;
            }

            System.err.println(ERROR_PHONE);
        }
    }

    public static String getEmailAddress() {
        while (true) {
            String result = getString();

            if (result.matches("^[A-Za-z0-9+_.-]{3,}@[A-Za-z0-9.-]{3,}(\\.com?|\\.com.vn?|\\.vn)$")) {
                // Hợp lệ nếu địa chỉ email có đúng định dạng
                return result;
            }

            System.err.println(ERROR_EMAIL);
        }
    }

    public static String gettheloai() {
        while (true) {
            String result = getString();
            if (result.startsWith("MTL") && result.length() >= 6) {
                return result;
            }
            System.err.println(ERROR_MATL);
        }
    }

    public static int getEvensach() {
        while (true) {
            int result = getInteger();
            if (result > 0 && result < 100) {
                return result;
            }
            System.err.println(ERROR_EVENT);
        }
    }

    public static int getthaydoisoluongsach() {
        while (true) {
            int result = getInteger();
            if (result >= 0) {
                return result;
            }
            System.err.println(ERROR_EDITSL
            );
        }
    }

    public static int getchonxacnhan() {
        while (true) {
            int result = getInteger();
            if (result >= 1 && result <= 4) {
                return result;
            }
            System.err.println(ERROR_Chon
            );
        }
    }

    public static int getdanggiao() {
        while (true) {
            int result = getInteger();
            if (result >= 1 && result <= 3) {
                return result;
            }
            System.err.println(ERROR_Chon
            );
        }
    }

    public static double gettientrongvi() {
        while (true) {
            double result = getDouble();
            if (result >= 50000) {
                return result;
            }
            System.err.println(ERROR_vi);
        }
    }

    public static String gettentheloaivatensach() {
        while (true) {
            String result = getString();
            if (result.trim().length() >= 6) {

                return result;
            }
            System.err.println(ERROR_TENSACH_TL);
        }
    }
    public static String getdiachi() {
        while (true) {
            String result = getString();
            if (result.trim().length() >= 10) {

                return result;
            }
            System.err.println(ERROR_DIACHI);
        }
    }
    public static int getgiasach() {
        while (true) {
            int result = getInteger();
            if (result >= 1000) {
                return result;
            }
            System.err.println(ERROR_tiensach);
        }
    }
    /*========================================Other Method========================================*/
}

