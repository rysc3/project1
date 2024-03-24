public class main {
  public static void main(String[] args) {
    MyBigInteger test1 = new MyBigInteger("-2048");
    MyBigInteger test2 = new MyBigInteger("2048");
    MyBigInteger test3 = new MyBigInteger("-2415763190");
    // Tests from the word document
    MyBigInteger test4 = new MyBigInteger("18364000098463281009282");
    MyBigInteger test5 = new MyBigInteger("-9382361766839928276166829");
    MyBigInteger test6 = new MyBigInteger("839947462729219484028272");
    MyBigInteger test7 = new MyBigInteger("-839947462729219484028000");
    MyBigInteger test8 = new MyBigInteger("-25634837829208474747382992822");
    MyBigInteger test9 = new MyBigInteger("-6382927634646483929283733883");
    // System.out.println(test1);
    // System.out.println(test2);
    // System.out.println(test3);
    // System.out.println(test4);
    // System.out.println(test5);
    //System.out.println(test6);
    // System.out.println(test7);
     //System.out.println(test8);
    //System.out.println(test9);

    // 1 & 2
    System.out.println("--- Given Tests ---");
    MyBigInteger num1 = new MyBigInteger("18364000098463281009282");
    MyBigInteger num2 = new MyBigInteger("-9382361766839928276166829");
    MyBigInteger test10 = MyBigInteger.add(num1, num2);
    System.out.print("Test 1:");
    if (test10.equals("-9363997766741464995157547")){
      System.out.print(" PASS" + '\n');
    }else{
      System.out.print(" FAIL" + '\n');
    }
    // 3 & 4
    MyBigInteger num3 = new MyBigInteger("839947462729219484028272");
    MyBigInteger num4 = new MyBigInteger("-839947462729219484028000");
    MyBigInteger test11 = MyBigInteger.add(num3, num4);
    System.out.print("Test 2:");
    if (test10.equals("272")){
      System.out.print(" PASS" + '\n');
    }else{
      System.out.print(" FAIL" + '\n');
    }

    // 5 & 6
    MyBigInteger num5 = new MyBigInteger("-25634837829208474747382992822");
    MyBigInteger num6 = new MyBigInteger("-6382927634646483929283733883");
    MyBigInteger test12 = MyBigInteger.add(num5, num6);
    System.out.print("Test 2:");
    if (test10.equals("-32017765463854958676666726705")){
      System.out.print(" PASS" + '\n');
    }else{
      System.out.print(" FAIL" + '\n');
    }
    System.out.println("-------------------");
  }

}