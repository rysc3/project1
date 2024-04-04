import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {

    int successfulTests = 0; 
    ArrayList<Boolean> tests = new ArrayList<Boolean>();
    tests.add(passingTest1());
    tests.add(passingTest2());
    tests.add(passingTest3());
    //tests.add(passingTest4()); //uncomment this line to run test 4, optional additional test case
    for(boolean test : tests) {
      if(test == true) successfulTests++;
    }
    System.out.printf("\nSuccessful cases: %d/%d\n\n",successfulTests, tests.size());
  }

  public static boolean passingTest1(){
    System.out.println("====================== TEST 1 ========================\n");
    MyBigInteger num1 = new MyBigInteger("18364000098463281009282");
    MyBigInteger num2 = new MyBigInteger("-9382361766839928276166829");
    MyBigInteger sum = MyBigInteger.add(num1, num2);
    System.out.println("The sum is " + sum + ".");
    return sum.toString().equals("-9363997766741464995157547");
  }

  public static boolean passingTest2() {
    System.out.println("\n====================== TEST 2 ========================\n");
    MyBigInteger num1 = new MyBigInteger("839947462729219484028272");
    MyBigInteger num2 = new MyBigInteger("-839947462729219484028000");
    MyBigInteger sum = MyBigInteger.add(num1, num2);
    System.out.println("The sum is " + sum + ".");
    return sum.toString().equals("272");
  }

  public static boolean passingTest3() {
    System.out.println("\n====================== TEST 3 ========================\n");
    MyBigInteger num1 = new MyBigInteger("-25634837829208474747382992822");
    MyBigInteger num2 = new MyBigInteger("-6382927634646483929283733883");
    MyBigInteger sum = MyBigInteger.add(num1, num2);
    System.out.println("The sum is " + sum + ".");
    return sum.toString().equals("-32017765463854958676666726705");
  }


  /* flips the order of test 2, this test case was constructed to make sure our implementation of the add method
  is not dependent on the order of the arguments */
  public static boolean passingTest4() {
    System.out.println("====================== TEST 4 ========================");
    MyBigInteger num1 = new MyBigInteger("-839947462729219484028272");
    MyBigInteger num2 = new MyBigInteger("839947462729219484028000");
    MyBigInteger sum = MyBigInteger.add(num1, num2);
    System.out.println("The sum is " + sum + ".");
    return sum.toString().equals("-272");
  }
}