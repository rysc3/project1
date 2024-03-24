public class MyBigInteger {
  IntegerNode head;

  /**
   * Builds a new MyBigInteger from scratch using the string "number".
   * 
   * Runtime is Theta(n) because we only go over the string once.
   * 
   * @param number the string to be converted into a BigInteger
   */
  public MyBigInteger(String number) {
    char[] chars = number.toCharArray();
    int len = chars.length;
    int sign = (chars[0] == '-') ? -1 : 1;
    int currValue = 0;
    int multiplier = 1;
    int numOfDigitsCounted = 0;

    // create head node
    this.head = new IntegerNode(sign);
    IntegerNode currNode = this.head;

    // iterate over A[1:len] because A[0] is the sign
    for (int i = len - 1; i >= 0; i--) {
      if (chars[i] != '-') {
        currValue += multiplier * Integer.parseInt(chars[i] + "");
        multiplier *= 10;
        numOfDigitsCounted++;
      }

      if (numOfDigitsCounted == 4) { // added four numbers
        // add a new node
        currNode.addNextNode(currValue);
        currNode = currNode.nextPos;
        //System.out.println(currNode.digits);
        // reset variables
        currValue = 0;
        multiplier = 1;
        numOfDigitsCounted = 0;
      }
    }

    if (currValue != 0) {
      currNode.addNextNode(currValue);
      //System.out.println(currNode.digits);
    }
  }

  /**
   * Creates a new MyBigInteger by copying bigInt.
   * 
   * @param bigInt the MyBigInteger object to be copied
   */
  public MyBigInteger(MyBigInteger bigInt) {
    IntegerNode bigIntNode = bigInt.head;
    IntegerNode currNode = new IntegerNode(bigInt.head.digits);
    this.head = currNode;

    this.head.digits = bigIntNode.digits;
    bigIntNode = bigIntNode.nextPos;

    while (bigIntNode != null) {
      currNode.addNextNode(bigIntNode.digits);
      currNode = currNode.nextPos;
      bigIntNode = bigIntNode.nextPos;
    }
  }

  /* ====================== OVERRIDDEN METHODS =================== */

  /**
   * Checks if two BigIntegers are equal by looping
   * through each node in the lists.
   * 
   * @param obj the BigInteger being compared to "this".
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }

    if (!(obj instanceof MyBigInteger)) {
      return false;
    }

    MyBigInteger bigInt = (MyBigInteger) obj;
    IntegerNode b1 = bigInt.head;
    IntegerNode b2 = this.head;

    while (b1 != null && b2 != null) {
      if (b1.digits != b2.digits)
        return false;
      b1 = b1.nextPos;
      b2 = b2.nextPos;
    }

    return b1 == null && b2 == null;
  }

  @Override
  public String toString() {

    String sign = "";
    String str = "";
    IntegerNode currNode = this.head;

    if (currNode.digits == -1) {
      sign = "-";
      currNode = currNode.nextPos;
    }

    while (currNode != null) {
      str = currNode.digits + str;
      currNode = currNode.nextPos;
    }
    return sign+ str;
  }

  public static MyBigInteger add(MyBigInteger b1, MyBigInteger b2) {
    int b1Sign = b1.head.digits;
    int b2Sign = b2.head.digits;
    MyBigInteger result = new MyBigInteger(b1);
    if(b1.head.digits == b2.head.digits) {
      b1Sign = 1;
      b2Sign = 1;
    }
    if (b1.head.digits == 0) b1Sign = 1;
    if (b2.head.digits == 0) b2Sign = 1;

    int sign = recursiveAdd(b1.head.nextPos, b2.head.nextPos, result.head.nextPos, b1Sign, b2Sign, 0);
    if(b1.head.digits == b2.head.digits && b1.head.digits == -1) {
      sign = -1;
    }
    result.head.setSign(sign);

    
    IntegerNode currNode = result.head.nextPos;
    IntegerNode validNode = currNode;
    while(currNode != null){
      if (currNode.digits != 0) {
        validNode = currNode;
      }
      currNode = currNode.nextPos;
    }
    validNode.nextPos = null;

    return result;
  }
  public static int recursiveAdd(IntegerNode N1, IntegerNode N2, IntegerNode NR, int S1, int S2, int carry) {
    int sum = Math.abs(N1.digits*S1 + N2.digits*S2) + carry;

    if (N1.nextPos == null && N2.nextPos == null) {

      if (sum > 9999) {
        NR.digits =  (sum)  - 10000;
        NR.addNextNode(1);
      }
      else NR.digits = sum + carry;
      return (sum < 0 ? -1 : 0);

    }else if (N1.nextPos == null) {
      if (sum > 9999) {
        NR.digits =  (sum)  - 10000;
        carry = 1;
      }
      else NR.digits = sum + carry;

      while (N2.nextPos != null){
        if (NR.nextPos == null) {
          NR.addNextNode(Math.abs(N2.nextPos.digits) + carry);
        }else{
          NR.nextPos.digits = Math.abs(N2.nextPos.digits) + carry;
        }
        NR = NR.nextPos;
        N2 = N2.nextPos;
      }
      return S2 == 1 ? 0 : -1;

    }else if (N2.nextPos == null) {
      if (sum > 9999) {
        NR.digits =  (sum)  - 10000;
        carry = 1;
      }
      else NR.digits = sum + carry;

      while (N1.nextPos != null){
        if (NR.nextPos == null) {
          NR.addNextNode(Math.abs(N1.nextPos.digits) + carry);
        }else{
          NR.nextPos.digits = Math.abs(N1.nextPos.digits) + carry;
        }
        NR = NR.nextPos;
        N1 = N1.nextPos;
      }
      return S1 == 1 ? 0 : -1;
    }

    //calculation for NR digits 
    if (sum > 9999) {
      NR.digits = (sum) - 10000;
      carry = 1;
    }
    else NR.digits = sum;

    return recursiveAdd(N1.nextPos, N2.nextPos, NR.nextPos, S1, S2, carry);
  }
  
}

class IntegerNode {
  int digits;
  IntegerNode nextPos;

  public IntegerNode(int digits) {
    this.digits = digits;
    nextPos = null;
  }

  public void addNextNode(int digits) {
    this.nextPos = new IntegerNode(digits);
  }
  public void setSign(int sign) {
    this.digits = sign;
  }
}
