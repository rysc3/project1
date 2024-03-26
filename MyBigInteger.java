public class MyBigInteger {
  IntegerNode head;
  IntegerNode tail;
  int size;

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
    int end = (sign == 1) ? 0 : 1;
    int currValue = 0;
    int multiplier = 1;
    int numOfDigitsCounted = 0;
    this.size = 0; // don't include head node

    // create head node
    this.head = new IntegerNode(sign);
    IntegerNode currNode = this.head;

    // iterate over A[1:len] because A[0] is the sign
    for (int i = len - 1; i >= end; i--) {
      currValue += multiplier * Integer.parseInt(chars[i] + "");
      multiplier *= 10;
      numOfDigitsCounted++;

      if (numOfDigitsCounted == 4) { // added four numbers
        // add a new node
        currNode.addNextNode(currValue);
        this.size++;
        currNode = currNode.next;
        this.tail = currNode;
        //System.out.println(currNode.digits);
        // reset variables
        currValue = 0;
        multiplier = 1;
        numOfDigitsCounted = 0;
      }
    }

    if (currValue != 0) {
      currNode.addNextNode(currValue);
      this.size++;
      this.tail = currNode;
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
    this.size = bigInt.size;

    this.head.digits = bigIntNode.digits;
    bigIntNode = bigIntNode.next;

    while (bigIntNode != null) {
      currNode.addNextNode(bigIntNode.digits);
      currNode = currNode.next;
      this.tail = currNode;
      bigIntNode = bigIntNode.next;
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
      b1 = b1.next;
      b2 = b2.next;
    }

    return b1 == null && b2 == null;
  }

  public String formattedString(){
    StringBuilder str = new StringBuilder();
    IntegerNode currNode = this.head;
    while (currNode != null) {
      if (currNode.next == null) {
        str.append(String.format("[ %d ]", currNode.digits));
      } else {
        str.append(String.format("[ %d ]->", currNode.digits));
      }
      currNode = currNode.next;
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
    currNode = currNode.next;

    while (currNode != null) {
      str = currNode.digits + str;
      currNode = currNode.next;
    }
    return sign+ str;
  }

  public static MyBigInteger add(MyBigInteger b1, MyBigInteger b2) {
    int b1Sign = b1.head.digits;
    int b2Sign = b2.head.digits;
    MyBigInteger result = new MyBigInteger(b1);

    int finalSign = b1Sign; // assume b1 is the final sign

    // if signs are different check what final sign will be
    if(b1Sign != b2Sign) {
      boolean b2IsBigger = b2.size > result.size;
      boolean sameSize = b1.size == b2.size;
      boolean b2TailIsGreater = b2.tail.digits > b1.tail.digits;
      boolean tailsAreEqual = b2.tail.digits == b1.tail.digits;
      // b2 is larger than b1 => change sign
      if(b2IsBigger) finalSign = b2Sign;
      // both lists are the same size
      else if(sameSize && b2TailIsGreater) finalSign =  b2Sign;

      else if(sameSize && tailsAreEqual) {
        IntegerNode N1 = result.head.next;
        IntegerNode N2 = b2.head.next;
        while(N1.next != null && N2.next != null) { 
          // does not work, add an extra condition to flip it back
          // make sure it doesn't do anything when N2.digits == N1.digits
          if(N2.digits > N1.digits) {
            finalSign = b2Sign;
          }
          else if(N1.digits > N2.digits) {
            finalSign = b1Sign;
          }
          // if N1.digits > N2.digits, then leave it
          N1 = N1.next;
          N2 = N2.next;
        }
      }
    }

    recursiveAdd(result.head.next, b2.head.next, b1Sign, b2Sign, 0, finalSign!=b1Sign);
    
    result.head.digits = finalSign;
    
    // clean up loop
    IntegerNode currNode = result.head.next;
    IntegerNode validNode = currNode;

    while(currNode != null){
      if (currNode.digits != 0) {
        validNode = currNode;
      }
      currNode = currNode.next;
    }
    validNode.next = null;

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
   * @param  tensComp tells the algorithm to use tens comp
   */
  public static void recursiveAdd(IntegerNode N1, IntegerNode N2, int S1, int S2, int carry, boolean tensComp) {
    int d1 = N1.digits;
    int d2 = N2.digits;
    int sum = 0;

    if(tensComp) {
      // compute tens comp of d2
      sum = d1+(10_000-d2);
      if(sum > 10000){
        // 20_000 removes carry and computes tens comp again
        sum = (20_000 - sum) + carry;
        carry = -1;
      }
      else {
        sum = (10_000 - sum) + carry;
        carry = 0;
      }
    }
    else {
      sum = Math.abs(S1*d1+S2*d2)+carry;
      carry = 0;
    }

    // add sum to N1
    if (sum > 9999) {
      N1.digits = sum - 10_000;
      carry = 1;
    }
    else N1.digits = sum;

    // base case: both nodes are null
    if (N1.next == null && N2.next == null) {
      // add carry as a new node
      if (carry == 1) {
        N1.addNextNode(1);
      }
      // return final sign value
      return;
    }
    // B2 is longer than B1
    else if (N1.next == null) {  
      N2 = N2.next;
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
        N1 = N1.next;
        N2 = N2.next;
      }
      // return the sign of S2 because its larger
      return;
    }
    
    // B1 is longer than B2 and we still have a carry
    else if (N2.next == null && carry == 1) {
      N1 = N1.next;
      // iterate over the rest of B1 while carry is 1
      // necessary in case we have a "cascade" of carrys
      while (N1 != null && carry == 1){
        d1 = N1.digits;
        sum = d1 + carry;
        if(sum > 9999) {
          sum -= 10_000;
          carry = 1;
        }
        else carry = 0;
        N1.digits = sum;
        N1 = N1.next;
      }
      // return the sign of S1 because its larger
      return;
    }

    // recursive case
    recursiveAdd(N1.next, N2.next, S1, S2, carry, tensComp);
  }
}

class IntegerNode {
  int digits;
  IntegerNode next;

  public IntegerNode(int digits) {
    this.digits = digits;
    next = null;
  }

  public void addNextNode(int digits) {
    this.next = new IntegerNode(digits);
  }
}
