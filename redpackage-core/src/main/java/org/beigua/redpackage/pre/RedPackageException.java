package org.beigua.redpackage.pre;

/**
 * Created by smzdm on 2017/7/31.
 */
public class RedPackageException extends RuntimeException {
    public RedPackageException() {
    }

    public RedPackageException(String message) {
        System.out.println(message);
    }

    public RedPackageException(String message, Throwable cause) {
        super(message, cause);
    }
}
