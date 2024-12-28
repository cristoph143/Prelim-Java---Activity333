
import java.util.Scanner;

public class Pharmacy {
    public static void main(String[] args) {
        
        char ans;
        int num;
        Medicines[] med;
        Scanner scan = new Scanner(System.in);
        
        med = new Medicines[10];
        
        System.out.print("How many medicines you want to be inputted: ");
        num = scan.nextInt();
        
        inputProducts(num,med);
        
        displayTable(num,med);
        while(true)
        {
            ans = displayMainMenu();
            switches(ans,num,med);
        }
    }
    public static void inputProducts(int num, Medicines med[])
    {
        int i, quantity;
        float price,total=0,grandTotal=0;
        String brandname,genericname;
        Scanner scan = new Scanner(System.in);
        
        for(i = 0; i < num; i++)
        {
            System.out.print("Enter Brand Name:");
            brandname = scan.next();
            System.out.print("Enter Generic Name:");
            genericname = scan.next();
            System.out.print("Enter Number of Stocks:");
            quantity = scan.nextInt();
            System.out.print("Enter Enter Price:");
            price = scan.nextFloat();
            total = price*quantity;
            grandTotal += total;
            
            med[i] = new Medicines(brandname, genericname, quantity, price,total, grandTotal);
        }
    }
    
    public static void displayTable(int num, Medicines med[])
    {
        int i;
//        float h;
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("Brand Name\tGeneric Name\tQuantity\tPrice\t\tTotal");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        for(i = 0; i < num; i++)
        {
            System.out.println(med[i].toString());
            
        }
//        h = med[i].toStrings();
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
//        System.out.println("\t\t\t\t\t\tTotal\t\t"+h);
    }
    
    public static char displayMainMenu()
    {
        String S;
        Scanner scan = new Scanner(System.in);
        char choice,news;
        
        System.out.print("Menu\n[A] dd quantity\n[C] hange Price\n[S] ell Medicine\n[D] isplay medicine Info\n[Q] uit\n[I] nput Answer: ");
        S = scan.next();
        choice = S.charAt(0);
        news = Character.toUpperCase(choice);

        return news;
    }
    public static void switches(char ans, int num, Medicines meds[])
    {
        int anser;
        int i;
        String medName;
        
        switch(ans){
            case 'A':   medName =  answerQues();
                        anser = checkMed(num, medName, meds);
                        i = answer(anser,num,meds);
                        addStocks(i,num,medName,meds);
                        break;
            case 'S':   medName =  answerQues();
                        anser = checkMed(num, medName, meds);
                        i = answer(anser,num,meds);
                        sellMed(anser,num, meds);
                        break;
            case 'C':   medName =  answerQues();
                        anser = checkMed(num, medName, meds);
                        i = answer(anser,num,meds);
                        changePrice(anser,num,meds);
                        break;
            case 'D':   displayTable(num,meds);
                        break;
            case 'Q':   System.out.println("Thank You for using This App!");
                        System.exit(0);    
                        break;
        } 
        System.out.println("Try Again");
        displayMainMenu();
        switches(ans,num,meds);
        
    }
    
    public static String answerQues()
    {
        String medName;
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Enter Brand Name of the Medicine to be Modified: ");
        medName = scan.next();
        return medName;
    }
    
   
    
    public static int checkMed(int num, String medName, Medicines meds[])
    {
        int i;
        int p = -1;
        for(i = 0; i < num;i++) {
           if(meds[i].getBrandName().equalsIgnoreCase(medName)) 
           {
               return i;
           }
       }
       return p;
    }
    
    public static int answer(int i,int num,Medicines meds[])
    {
        
        char ans;
        
        if(i == -1)
        {
            System.out.println("Medicine not found!\n");
            displayTable(num,meds);
            ans = displayMainMenu();
        }
            return i;
    }
    
    public static void addStocks(int anser,int num, String medName, Medicines meds[])
    {
        String S;
        Scanner scan = new Scanner(System.in);
        int addNum;
        char ans;
        
        System.out.print("Decrease or Increase? ");
        S = scan.next();
        char choice = S.charAt(0);
        char news = Character.toUpperCase(choice);
        System.out.print("Enter Number of Medicine to be Modified: ");
        addNum = scan.nextInt();
        
        switch(news)
        {
            case 'I' :  meds[anser].setQuantity(meds[anser].getQuantity()+addNum);
                        meds[anser].setTotal(meds[anser].getPrice()*meds[anser].getQuantity());
//                        meds[anser].setGrandTotal(meds[anser].getTotal()+meds[anser].getTotal());
                        break;
            case 'D' :  
                        if(meds[anser].getQuantity() < addNum)
                        {
                            System.out.println("Low Stock! Please try again!1");
                            displayTable(num,meds);
                            ans = displayMainMenu();
                            switches(ans,num,meds);
                        }
                        else
                        {
                            meds[anser].setQuantity(meds[anser].getQuantity()-addNum);
                            meds[anser].setTotal(meds[anser].getPrice()*meds[anser].getQuantity());
                            displayTable(num,meds);
                        }
                        break;
            default:    System.out.println("Error101! Please try again!1");
        }
        displayTable(num,meds);
        ans = displayMainMenu();
        switches(ans,num,meds);
    }
    
    public static void sellMed(int anser, int num, Medicines meds[])
    {
        Scanner scan = new Scanner(System.in);
        int addNum;
        float addMon;
        System.out.print("Enter Number of Medicine to be Modified: ");
        addNum = scan.nextInt();
        System.out.print("Enter your Money: ");
        addMon = scan.nextInt();
        
        if(addMon < meds[anser].getPrice())
        {
            System.out.print("Low Money! Please try again!1");
        }
        else if(meds[anser].getQuantity() < addNum)
        {
            System.out.print("Low Stock! Please try again!1");
        }
        else
        {
            meds[anser].setQuantity(meds[anser].getQuantity()-addNum);
            meds[anser].setTotal(meds[anser].getPrice()*meds[anser].getQuantity()); 
        }
    }
    
    public static void changePrice(int anser,int num, Medicines[] meds)
    {
        Scanner scan = new Scanner(System.in);
        float addMon;
        
        System.out.print("Enter Number of Money to be Modified: ");
        addMon = scan.nextInt();
        meds[anser].setPrice(meds[anser].getPrice(addMon));
        
    }
}
