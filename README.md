#361 Project 1

Ryan Scherbarth, Maisy Dunlavy, Xavier Barr, Sumaya Mohamed

Implementation for storing large integers using a singly linked list. 

The head node of our list will be the sign digit, either `0` or `-1`, to represent positivity. Each node in the list can contain at most 4 digits, and the final node will point to nil to show the end of the integer. 

Large Integers can be created by passing the number in to the constructor as a string, which will then format it in this linked list accordingly. There is also the `add` function, capible of adding positive and negative Large Integers. 

The overriden `equals` method is made such that it will properly verify if two big integers are equal, and `toString` is also overridden to format the output according to the requirements. 
