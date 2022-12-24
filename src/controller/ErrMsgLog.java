package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ErrMsgLog extends Exception{
    private static ArrayList<Exception> ErrList;
    private static Logger log;

    public ErrMsgLog() throws IOException {
        ErrList = new ArrayList();
        LogManager.getLogManager().readConfiguration(ErrMsgLog.class.getResourceAsStream("logging.properties"));
        log = Logger.getLogger(ErrMsgLog.class.getName());
    }

    public int addErr(Exception e) {
        ErrList.add(e);
        return ErrList.size();
    }


    public int addErrWithLog(Exception e) {
        ErrList.add(e);
        log.log(Level.FINE, e.getMessage());
        return ErrList.size();
    }

    public int getErrCount() {
        return ErrList.size();
    }

    public void showErrText(Exception e) {
        System.err.println(e.getMessage());
    }

    public Exception makeErr(Exception e) {
        addErr(e);
        return new Exception(e);
    }
}
