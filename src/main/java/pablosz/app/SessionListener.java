package pablosz.app;

public interface SessionListener {

    void sessionStillOpened(long key);
    void sessionOpened(long key);
    void sessionClosed(long key);
    void sessionStillClosed(long key);

}
