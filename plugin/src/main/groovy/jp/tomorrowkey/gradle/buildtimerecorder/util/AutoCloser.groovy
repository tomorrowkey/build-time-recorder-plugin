package jp.tomorrowkey.gradle.buildtimerecorder.util;

class AutoCloser {
    static void run(closeable, closure) {
        try {
            closure.call(closeable)
        }finally{
            AutoCloser.closeQuietly(closeable)
        }
    }

    static void closeQuietly(closeable) {
        if(closeable == null) {
            return
        }

        try {
            closeable.close()
        } catch(IOException e) {
            // ignore
        }
    }
}
