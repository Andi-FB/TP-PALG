package pablosz.app.gson.config;

import pablosz.app.SessionListener;

public class Listener implements Runnable  {

    private SessionListener listener;

    protected long ttl;
    protected long key;

    public Listener(){}

    public Listener(SessionListener listener){
        this.listener = listener;
    }

    public void setListener(SessionListener listener){
        this.listener = listener;
    }

    public SessionListener getListener(){
        return this.listener;
    }

    public void createSession(long ttl, long key){
        this.ttl = ttl;
        this.key = key;



    }

    @Override
    public void run() {

        this.listener.sessionOpened(key);
        boolean keepOnGoing = true;
        while (keepOnGoing) {
            try {
                Thread.sleep(1000);
                listener.sessionStillOpened(key);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ttl = ttl - 1000;

            if (ttl <= 0) {
                listener.sessionClosed(key);
                keepOnGoing = false;
            }
        }
        listener.sessionStillClosed(key);
    }
}
