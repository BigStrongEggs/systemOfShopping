import java.util.Random;

public class PassWord {
    public static final String allow = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+={}|<>?/-.,`~\\";

    public static boolean isPassWordOk(String passWord) {
        boolean isLengthOk = passWord.length() > 8;
        boolean hasUpper = passWord.matches(".*[A-Z].*");
        boolean hasLower = passWord.matches(".*[a-z].*");
        boolean hasNum = passWord.matches(".*[0-9].*");
        boolean hasPunctuation = passWord.matches(".*\\p{Punct}.*");
        if (isLengthOk && hasUpper && hasLower && hasNum && hasPunctuation) {
            return true;
        } else if (!isLengthOk) {
            System.out.println("密码长度少于8");
            return false;
        } else if (!hasLower) {
            System.out.println("密码缺少小写字母");
            return false;
        } else if (!hasUpper) {
            System.out.println("密码缺少大写字母");
            return false;
        } else if (!hasPunctuation) {
            System.out.println("密码缺少标点符号");
            return false;
        } else {
            return false;
        }
    }

    public static String generateRandomPassWord() {
        Random rd = new Random();
        int lenOfPw = rd.nextInt(9) + 8;
        int numOfUpper = rd.nextInt(2) + 1;
        int numOfLower = rd.nextInt(2) + 1;
        int len = rd.nextInt(1) + 1;
        int lenofnum = lenOfPw - numOfLower - numOfUpper;
        String newPassword = "";
        for (int i = 0; i < numOfLower; i++) {
            newPassword += allow.charAt(rd.nextInt(25));
        }
        for (int i = 0; i < numOfUpper; i++) {
            newPassword += allow.charAt(rd.nextInt(25) + 26);
        }
        for (int i = 0; i < lenofnum; i++) {
            newPassword += allow.charAt(rd.nextInt(9) + 52);
        }
        for (int i = 0; i < len; i++) {
            newPassword += allow.charAt(rd.nextInt(26) + 62);
        }
        if (isPassWordOk(newPassword)) {
            return newPassword;
        }
        return null;
    }
}
