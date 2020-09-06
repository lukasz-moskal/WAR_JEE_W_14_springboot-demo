package pl.coderslab.springbootdemo.exception;

public class ImageReadException extends RuntimeException {

    private final String fileName;

    public ImageReadException(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    // wyjątek nie zawiera stacktrace'a
    /*@Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }*/
}
