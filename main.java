public class main {
  public static void main(String[] args) {
    MyBigInteger test1 = new MyBigInteger("2048");
    MyBigInteger test2 = new MyBigInteger("2048");
    // MyBigInteger test3 = new MyBigInteger("-2415763190");
    // // Tests from the word document
    // MyBigInteger test4 = new MyBigInteger("18364000098463281009282");
    // MyBigInteger test5 = new MyBigInteger("-9382361766839928276166829");
    MyBigInteger test6 = new MyBigInteger("839947462729219484028272");
    MyBigInteger test7 = new MyBigInteger("-839947462729219484028000");
    // MyBigInteger test8 = new MyBigInteger("-25634837829208474747382992822");
    // MyBigInteger test9 = new MyBigInteger("-6382927634646483929283733883");
    // System.out.println(test1);
    // System.out.println(test2);
    // System.out.println(test3);
    // System.out.println(test4);
    // System.out.println(test5);
    //System.out.println(test6);
    // System.out.println(test7);
     //System.out.println(test8);
    //System.out.println(test9);

    //MyBigInteger test10 = MyBigInteger.add(test6, test7);
    //System.out.println(test10);
    System.out.println(passingTest1());
    System.out.println(passingTest2());
    System.out.println(passingTest3());
  }

  public static boolean passingTest1(){
    System.out.println("====================== TEST 1 ========================");
    MyBigInteger num1 = new MyBigInteger("18364000098463281009282");
    MyBigInteger num2 = new MyBigInteger("-9382361766839928276166829");
    MyBigInteger sum = MyBigInteger.add(num1, num2);
    System.out.println("Actual: "+sum);
    //System.out.println(sum.formattedString());
    System.out.println("Expected: -9363997766741464995157547");
    MyBigInteger expected = new MyBigInteger("-9363997766741464995157547");
    System.out.println("Formatted");
    System.out.println(num1.formattedString());
    System.out.println(num2.formattedString());
    System.out.println(sum.formattedString());
    System.out.println(expected.formattedString());
    return sum.toString().equals("-9363997766741464995157547");

  }

  public static boolean passingTest2() {
    System.out.println("====================== TEST 2 ========================");
    MyBigInteger num1 = new MyBigInteger("2048");
    MyBigInteger num2 = new MyBigInteger("2048");
    MyBigInteger sum = MyBigInteger.add(num1, num2);
    MyBigInteger expected = new MyBigInteger("4096");
    System.out.println("Actual: "+sum);
    System.out.println("Expected: 4096");
    System.out.println("Formatted");
    System.out.println(sum.formattedString());
    System.out.println(expected.formattedString());
    return sum.toString().equals("4096");
  }

  public static boolean passingTest3() {
    System.out.println("====================== TEST 3 ========================");
    MyBigInteger num1 = new MyBigInteger("-25634837829208474747382992822");
    MyBigInteger num2 = new MyBigInteger("-6382927634646483929283733883");
    MyBigInteger expected = new MyBigInteger("-32017765463854958676666726705");
    MyBigInteger sum = MyBigInteger.add(num1, num2);
    System.out.println("Actual: "+sum);
    System.out.println("Expected: -32017765463854958676666726705");
    System.out.println("Formatted");
    System.out.println(sum.formattedString());
    System.out.println(expected.formattedString());
    return sum.toString().equals("-32017765463854958676666726705");
  }
}