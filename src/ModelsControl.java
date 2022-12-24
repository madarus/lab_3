import models.Coctail;
import models.Ingredient;

import java.io.*;
import java.util.Scanner;

public class ModelsControl extends Main{
    static void addModels(int n){
        AddDataToArray data = new AddDataToArray();
        lists = data.coctailsData(n,Boolean.parseBoolean(prop.getProperty("logging")));
        for(Coctail st:lists){
            System.out.println();
            System.out.println("Название: "+ st.name);
            System.out.println("Ингредиенты: "+st.Ingredients);
            System.out.println("Действия: "+st.cooking);
            System.out.println("Объем: "+st.amount);
        }
        data.remove(n,Boolean.parseBoolean(prop.getProperty("logging")));
        data.toFile();
        System.out.println("\nLinkedList");
        AddDataToLinked data2 = new AddDataToLinked();
        lists2 = data2.coctailsData(n,Boolean.parseBoolean(prop.getProperty("logging")));
        for (Coctail st:lists2){
            System.out.println();
            System.out.println("Название: "+ st.name);
            System.out.println("Ингредиенты: "+st.Ingredients);
            System.out.println("Действия: "+st.cooking);
            System.out.println("Объем: "+st.amount);
        }
        data2.remove(n,Boolean.parseBoolean(prop.getProperty("logging")));
    }
    

    static void readModelsFromFile(int n) throws IOException {
        int k = 1;
        String name= "";
        String mtd = null;
        String name2 = null;
        int amount = 0;
        int strength = 0;
        try {
            File file = new File("./modelsdb.txt");
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            name2 = line;
            while (line != null) {
                if(k==4){
                    Ingredient ing = new Ingredient(name,amount,strength,mtd);
                    testlist.add(new Coctail(name2,ing));
                    System.out.println(line);
                    k=0;
                }
                // считываем остальные строки в цикле
                line = reader.readLine();
                if(k==0){
                    System.out.println(line);
                    name2 = line;}
                if(k==1){
                    System.out.println(line);
                    amount = Integer.parseInt (line);}
                if(k==2){
                    System.out.println(line);
                    mtd = line;

                }
                if(k==3){
                    System.out.println(line);
                    name = line;
                }
                k++;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        switchMenu();
    }

    static void deleteModel(int n) throws IOException {
        testlist.remove(n);
        switchMenu();
        updateModelsFile();
    }

    static void addModelstoFile()throws java.io.IOException {
        boolean Result = false;
        FileWriter dataOut = null;
        try {
            dataOut = new FileWriter("modelsdb.txt", true);
            for (Coctail t : testlist) {
                dataOut.append(t.name + "\n");
                dataOut.append(String.valueOf(t.amount)+"\n");
                dataOut.append(String.valueOf(t.cooking.get(0))+"\n");
                dataOut.append(String.valueOf(t.Ingredients.get(0))+"\n");
                Result = true;
            }
            dataOut.close();
        }
        catch(IOException exc ){
            System.err.println(exc.getMessage());
        }
        finally{
            dataOut.close();
        }
        switchMenu();
    }

    static void updateModelsFile()throws java.io.IOException {
        boolean Result = false;
        FileWriter dataOut = null;
        try {
            dataOut = new FileWriter("modelsdb.txt", false);
            for (Coctail t : testlist) {
                dataOut.append(t.name + "\n");
                dataOut.append(String.valueOf(t.amount)+"\n");
                dataOut.append(String.valueOf(t.cooking.get(0))+"\n");
                dataOut.append(String.valueOf(t.Ingredients.get(0))+"\n");
                Result = true;
            }
            dataOut.close();
        }
        catch(IOException exc ){
            System.err.println(exc.getMessage());
        }
        finally{
            dataOut.close();
        }
        switchMenu();
    }

    static void printModels() throws IOException {
        for(Coctail st:testlist){
            System.out.println("Название: "+st.name+" Ингредиенты "+st.Ingredients+" Действия по приготовлению: "+ st.cooking +" Объем: "+st.amount+"мл");
        }
        switchMenu();
    }

    public static void createModel(int n) throws IOException {
        for (int i=0;i<n;i++){
            Scanner in1 = new Scanner(System.in);
            System.out.print("Введите ингредиент, метод приготовления, название коктейля, объем и крепость : \n");
            String name = in1.nextLine();
            String mtd = in1.nextLine();
            String name2 = in1.nextLine();
            int amount = in1.nextInt();
            int strength = in1.nextInt();
            Ingredient ing = new Ingredient(name,amount,strength,mtd);
            testlist.add(new Coctail(name2,ing));
        }
        switchMenu();
    }
   static void updateModel(int n) throws IOException {
        Scanner in1 = new Scanner(System.in);
       System.out.print("Введите ингредиент, метод приготовления, название коктейля, объем и крепость : \n");
       String name = in1.nextLine();
       String mtd = in1.nextLine();
       String name2 = in1.nextLine();
       int amount = in1.nextInt();
       int strength = in1.nextInt();
        testlist.get(n).name = name2;
        testlist.get(n).amount = amount;
        testlist.get(n).cooking.set(0,mtd);
        testlist.get(n).Ingredients.set(0,name2);
        switchMenu();
        updateModelsFile();
    }

}
