package team.yjcollege.matchproject.sinvoicelib;
import android.util.Log;

public class LogHelper {
    private static final String ROOT_TAG = "SinVoice";

    public static final int d(String classTag, String privateTag, String msg) {
        return Log.d(String.format("%s %s %s", ROOT_TAG, classTag, privateTag), msg);
    }

    public static final int d(String classTag, String msg) {
        return d(classTag, "", msg);
    }

    public static final int i(String classTag, String privateTag, String msg) {
        return Log.i(String.format("%s %s %s", ROOT_TAG, classTag, privateTag), msg);
    }

    public static final int i(String classTag, String msg) {
        return i(classTag, "", msg);
    }

    public static final int e(String classTag, String privateTag, String msg) {
        return Log.e(String.format("%s %s %s", ROOT_TAG, classTag, privateTag), msg);
    }

    public static final int e(String classTag, String msg) {
        return e(classTag, "", msg);
    }

    public static final int v(String classTag, String privateTag, String msg) {
        return Log.v(String.format("%s %s %s", ROOT_TAG, classTag, privateTag), msg);
    }

    public static final int v(String classTag, String msg) {
        return v(classTag, "", msg);
    }
}
