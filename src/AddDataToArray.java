

import models.Coctail;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class AddDataToArray {
    private final Logger Log1 = Logger.getLogger(Main.class.getName()+1);
    private void handle(int n){
        FileHandler handler1=null;
        try {
            handler1 = new FileHandler("ArrayList"+n+".log",true);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
        handler1.setFormatter(new SimpleFormatter());
        Log1.setUseParentHandlers(false);
        Log1.addHandler(handler1);
    }

    private static int yCount = 0;
    public static long[][] YsAdd = new long[2][5];
    public static long[][] YsRem = new long[2][5];
    ArrayList<Coctail>coctaillist = new ArrayList<Coctail>();
    ArrayList<Coctail>coctailsData(int n, boolean isLogging)
    {
        if(isLogging){
            long time=0, allTime2=0, allTime1=0;
            Log1.log(Level.INFO,"ArrayList");
            this.handle(n);

            for(int i=0;i<=n; i++){
                time = System.nanoTime();
                coctaillist.add(new Coctail());
                time = System.nanoTime() - time;
                Log1.log(Level.INFO, "Add, ID={0}, {1}", new Object[] {i, time});
                allTime1+=time;
            }
            Log1.log(Level.INFO, "AddTotalCount = {0}", n);
            Log1.log(Level.INFO, "AddTotalTime = {0}", (new Long(allTime1)).toString());
            Log1.log(Level.INFO, "AddMedianTime = {0}", (new Long(allTime1/n)).toString());
            YsAdd[0][yCount]=allTime1/n;
            yCount++;
        }
        else{
            for(int i=0;i<=n; i++){
                coctaillist.add(new Coctail());
            }
        }


        return coctaillist;
    }


    public static void toFile(){
        try {
            Files.delete(Paths.get("Add.txt"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try(FileWriter writer = new FileWriter("Add.txt", true))
        {
            for(int i = 0; i<5; i++)
                writer.write(YsAdd[0][i]+" "+YsAdd[1][i]+System.lineSeparator());
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        try {
            Files.delete(Paths.get("Remove.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(FileWriter writer = new FileWriter("Remove.txt", true))
        {

            for(int i = 0; i<5; i++)
                writer.write(YsRem[0][i]+" "+YsRem[1][i]+System.lineSeparator());
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public void remove(int n, boolean isLogging){
        if(isLogging){
            System.out.println(n);
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
                allTime1+=time;
            }
            Log1.log(Level.INFO, "RemoveTotalCount = {0}", Perc);
            Log1.log(Level.INFO, "RemoveTotalTime = {0}", allTime1);
            Log1.log(Level.INFO, "RemoveMedianTime = {0}", allTime1/Perc);
            YsRem[0][yCount]=allTime1/n;
        }
        else {
            System.out.println(n);
            long time=0, allTime2=0, allTime1=0;
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
