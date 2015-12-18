package com.simache.fileexplorer.utils;

import android.util.Log;

public class LogUtil {
    //quite dangerous to rely on BuildConfig.DEBUG and ADT to do the right thing, 
    //because there are bugs in the build system 
    //that cause exported signed release builds to be built with BuildConfig.DEBUG set to true!
    public static final boolean IS_DEBUG = true;//BuildConfig.DEBUG

    public static void v(String tag, String msg) {
        if (IS_DEBUG) {
            String message = createMessage(msg);
            Log.v(tag, message);
        }
    }

    public static void d(String tag, String msg) {
        if (IS_DEBUG) {
            String message = createMessage(msg);
            Log.d(tag, message);
        }
    }

    public static void i(String tag, String msg) {
        if (IS_DEBUG) {
            String message = createMessage(msg);
            Log.i(tag, message);
        }
    }

    public static void w(String tag, String msg) {
        if (IS_DEBUG) {
            String message = createMessage(msg);
            Log.w(tag, message);
        }
    }

    public static void e(String tag, String msg) {
        if (IS_DEBUG) {
            String message = createMessage(msg);
            Log.e(tag, message);
        }
    }

    public static void i(String tag, Object obj) {
        if(obj!=null) {
            i(tag, obj.toString());
        }
    }

    public static void e(String tag, Object obj) {
        if(obj!=null) {
            e(tag, obj.toString());
        }
    }

    private static String createMessage(String msg) {
        String functionName = getFunctionName();
        String message = (functionName == null ? msg : (functionName + " - " + msg));
        return message;
    }

    private static String getFunctionName() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();

        if (sts == null) {
            return null;
        }

        for (StackTraceElement st : sts) {
            if (st.isNativeMethod()) {
                continue;
            }

            if (st.getClassName().equals(Thread.class.getName())) {
                continue;
            }

            if (st.getClassName().equals(LogUtil.class.getName())) {
                continue;
            }

            return "[" + Thread.currentThread().getName() + "(" + Thread.currentThread().getId()
                    + "): " + st.getFileName() + ":" + st.getLineNumber() + "]";
        }

        return null;
    }
}