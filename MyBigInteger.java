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

  public String formattedString(){
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
  @Override
  public String toString() {

    String sign = "";
    String str = "";
    IntegerNode currNode = this.head;

    if (currNode.digits == -1) {
      sign = "-";
      
    }
    currNode = currNode.nextPos;

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

    int finalSign = recursiveAdd(result.head.nextPos, b2.head.nextPos, b1Sign, b2Sign, 0);
    
    if(b1.head.digits == b2.head.digits && b1.head.digits == -1) {
      finalSign = -1;
    }
    result.head.setSign(finalSign);
    
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

    /**
   * Recursively adds two IntegerNodes and updates the carry.
   *
   * @param  N1    the first IntegerNode
   * @param  N2    the second IntegerNode
   * @param  S1    the sign of B1
   * @param  S2    the sign of B2
   * @param  carry the carry value
   * @return       the sign of the final result
   */
  public static int recursiveAdd(IntegerNode N1, IntegerNode N2, int S1, int S2, int carry) {
    int d1 = N1.digits;
    int d2 = N2.digits;
    int sum = Math.abs(d1*S1 + d2*S2) + carry;
    int finalSign = S1;

    if(S1 > S2) {
      sum = ((9999-d1)+1) + d2 + carry;
    }

    // change final sign value if d2 overpowers d1
    if(S1 != S2 && d2 > d1) {
      finalSign = S2;
    }

    // base case: both nodes are null
    if (N1.nextPos == null && N2.nextPos == null) {
      // add carry as a new node
      if (sum > 9999) {
        N1.digits = sum - 10_000;
        N1.addNextNode(1);
      }
      else N1.digits = sum;
      // return final sign value
      return finalSign;
    }
    // B2 is longer than B1
    else if (N1.nextPos == null) {
      // add sum to N1
      if (sum > 9999) {
        N1.digits = sum - 10_000;
        carry = 1;
      }
      else {
        N1.digits = sum;
        carry = 0;
      }
      
      N2 = N2.nextPos;

      // iterate over the rest of B2
      while (N2 != null){
        // handle carrying
        d2 = N2.digits;
        sum = d2 + carry;
        if(sum > 9999) {
          sum -= 10_000;
          carry = 1;
        }
        else carry = 0;

        N1.addNextNode(sum);
        N1 = N1.nextPos;
        N2 = N2.nextPos;
      }
      // return the sign of S2 because its larger
      return S2 == 1 ? 0 : -1;
    }
    
    // B1 is longer than B2 and we still have a carry
    else if (N2.nextPos == null && carry == 1) {
      if (sum > 9999) {
        N1.digits = sum - 10_000;
        carry = 1;
      }
      else {
        N1.digits = sum;
        carry = 0;
      }

      N1 = N1.nextPos;

      while (N1 != null && carry == 1){
        d1 = N1.digits;
        sum = d1 + carry;
        if(sum > 9999) {
          sum -= 10_000;
          carry = 1;
        }
        else carry = 0;
        N1.addNextNode(sum);
        N1 = N1.nextPos;
      }
      return S1 == 1 ? 0 : -1;
    }

    //calculation for NR digits 
    if (sum > 9999) {
      N1.digits = sum - 10_000;
      carry = 1;
    }
    else {
      N1.digits = sum;
      carry = 0;
    }

    return recursiveAdd(N1.nextPos, N2.nextPos, S1, S2, carry);
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
