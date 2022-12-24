import models.Coctail;

import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AddDataToLinked
{
    private final Logger Log2 = Logger.getLogger(Main.class.getName()+1);


    private void handle(int n){
        FileHandler handler2=null;
        try {
            handler2 = new FileHandler("ArrayLinked"+n+".log",true);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();

        }
        handler2.setFormatter(new SimpleFormatter());
        Log2.setUseParentHandlers(false);
        Log2.addHandler(handler2);
    }
    private static int yCount = 0;
    public static long[][] YsAdd = new long[2][5];
    public static long[][] YsRem = new long[2][5];
    LinkedList<Coctail>coctaillist = new LinkedList<Coctail>();

    LinkedList<Coctail> coctailsData(int n, boolean isLogging )
    {
        if(isLogging){
            long time=0, allTime2=0, allTime1=0;
            Log2.log(Level.INFO,"LinkedList");
            this.handle(n);

            for(int i=0;i<=n; i++){
                time = System.nanoTime();
                coctaillist.add(new Coctail());
                time = System.nanoTime() - time;
                Log2.log(Level.INFO, "Add, ID={0}, {1}", new Object[] {i, time});
                allTime1+=time;
            }
            Log2.log(Level.INFO, "AddTotalCount = {0}", n);
            Log2.log(Level.INFO, "AddTotalTime = {0}", (new Long(allTime1)).toString());
            Log2.log(Level.INFO, "AddMedianTime = {0}", (new Long(allTime1/n)).toString());
            YsAdd[0][yCount]=allTime1/n;
            yCount++;
        }
        else {
            for(int i=0;i<=n; i++){
                coctaillist.add(new Coctail());
            }
        }


        return coctaillist;
    }
    public void remove(int n, boolean isLogging){
        if(isLogging){
            long time=0, allTime2=0, allTime1=0;
            int Perc = n/10, j=(int) (Math.random()*n);
            boolean[] isDeleted = new boolean[n];
            for(int i = 0; i<Perc; i++){

                while(isDeleted[j])
                    j = (int) (Math.random()*coctaillist.size());

                isDeleted[j] = true;
                time = System.nanoTime();
                coctaillist.remove(j);
                time = System.nanoTime() - time;
                Log2.log(Level.INFO, "Remove, ID={0}, {1}", new Object[] {j, time});
                allTime1+=time;
            }
            Log2.log(Level.INFO, "RemoveTotalCount = {0}", Perc);
            Log2.log(Level.INFO, "RemoveTotalTime = {0}", allTime1);
            Log2.log(Level.INFO, "RemoveMedianTime = {0}", allTime1/Perc);
            YsRem[0][yCount]=allTime1/n;
        }
        else {
            int Perc = n/10, j=(int) (Math.random()*n);
            boolean[] isDeleted = new boolean[n];
            for(int i = 0; i<Perc; i++){
                while(isDeleted[j])
                    j = (int) (Math.random()*coctaillist.size());
                isDeleted[j] = true;
                coctaillist.remove(j);

            }
        }

    }

}
