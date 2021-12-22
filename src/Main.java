import java.io.*;
import java.util.*;
class Good {
  String name;
  int rate;
//name of the goodies and its rate
  public Good(String name, int rate) {
    this.name = name;
    this.rate = rate;
  }
//displaying name and rate 
  public String toString() { 
      return this.name + ": " + this.rate;
  }
}

public class Main {
  public static void main(String[] args) throws Exception {
      //reading from the file 
    FileInputStream fis=new FileInputStream("input.txt");       
    Scanner sc=new Scanner(fis);
    int no_of_emp = Integer.parseInt(sc.nextLine().split(": ")[1]);
    sc.nextLine(); sc.nextLine(); sc.nextLine();

    ArrayList<Good> goodies_lists = new ArrayList<Good>();

    while(sc.hasNextLine())  
    {
      String current[] = sc.nextLine().split(": ");
      goodies_lists.add(new Good(current[0], Integer.parseInt(current[1])));
    }
    sc.close();

    Collections.sort(goodies_lists, new Comparator<Good>(){
      public int compare(Good a, Good b) { 
        return a.rate - b.rate; 
      } 
    });

    int min_diff = goodies_lists.get(goodies_lists.size()-1).rate;
    int min_index = 0;
    for(int i=0;i<goodies_lists.size()-no_of_emp+1;i++) {
      int diff = goodies_lists.get(no_of_emp+i-1).rate-goodies_lists.get(i).rate;

      if(diff<=min_diff) {
        min_diff = diff;
        min_index = i;
      }
    }
    
    

    FileWriter fw = new FileWriter("output.txt");
    //writing to a output file
    fw.write("The goodies selected for distribution are:\n\n");
    for(int i=min_index;i<min_index + no_of_emp; i++) {
      fw.write(goodies_lists.get(i).toString() + "\n");
    }

    fw.write("\nAnd the difference between the chosen goodie with highest rate and the lowest rate is " + min_diff);
	  fw.close();
  }
}
