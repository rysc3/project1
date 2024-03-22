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
        System.out.println(currNode.digits);
        // reset variables
        currValue = 0;
        multiplier = 1;
        numOfDigitsCounted = 0;
      }
    }

    if (currValue != 0) {
      currNode.addNextNode(currValue);
      System.out.println(currNode.digits);
    }
  }

  /**
   * Creates a new MyBigInteger by copying bigInt.
   * 
   * @param bigInt the MyBigInteger object to be copied
   */
  public MyBigInteger(MyBigInteger bigInt) {
    IntegerNode currNode = new IntegerNode(bigInt.head.digits);
    this.head = currNode;

    while (bigInt.head.nextPos != null) {

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
    StringBuilder str = new StringBuilder();
    IntegerNode currNode = this.head;

    while (currNode != null) {
      if (currNode.nextPos == null) {
        str.append(String.format("[ %d ]", currNode.digits));
      } else {
        str.append(String.format("[ %d ]->", currNode.digits));
      }
      currNode = currNode.nextPos;
    }
    return str.toString();
  }

  public static MyBigInteger add(MyBigInteger b1, MyBigInteger b2) {
    int b1Sign = b1.head.digits;  // set b1 sign 
    IntegerNode b1Node = b1.head.nextPos;

    int b2Sign = b2.head.digits;  // set b2 sign 
    IntegerNode b2Node = b2.head.nextPos;

    // Create our resulting node 
    IntegerNode result;

    // Check which sign we should initially set
    if(b1Sign > 0 && b2Sign > 0){   // both positive
      result = new IntegerNode(b1Sign);
    } else if (b1Sign < 0 && b2Sign < 0){   // Both negative
      result = new IntegerNode(b1Sign);
    } else {    // One posiitve, one Negative
      // TODO Just choose a random value for now, since we will determine which number was larger and update the head later?
      result = new IntegerNode(b1Sign);
    }

    // recurisve call


    return null;
  }
  public static void recursiveAdd(IntegerNode N1, IntegerNode N2, IntegerNode NR, int S1, int S2, int carry) {
    if(N1.digits*S1 + N2.digits*S2 + carry < 10000){
        NR.addNextNode(N1.digits*S1 + N2.digits*S2 + carry);
        carry = 0;
    }
    else {
      NR.addNextNode((N1.digits*S1 + N2.digits*S2));
      carry = 1;
    }
    if (N1.nextPos != null && N2.nextPos != null) {
      if (carry == 0) return;
      else NR.addNextNode(1);
    }else if (N1.nextPos == null) {
        //set sign to N2 sign
      N2 = N2.nextPos;
      // recursiveAdd(0, N2, NR, S1, S2, carry);
    }else if (N2.nextPos == null) {
      //set sign to N1 sign
      N1 = N1.nextPos;
        // recursiveAdd(N1, 0, NR, S1, S2, carry);
    }
    N1 = N1.nextPos;
     N2 = N2.nextPos;
    // recursiveAdd(N1, N2, NR, S1, S2, carry);
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
