package proginscala

object chapter1 {
  println("Chapter 1 WorkSheet")                  //> Chapter 1 WorkSheet
  
  var capital = Map("US" -> "Washington", "France" -> "Paris")
                                                  //> capital  : scala.collection.immutable.Map[String,String] = Map(US -> Washing
                                                  //| ton, France -> Paris)
  capital += ("Japan" -> "Tokyo")
  println(capital("France"))                      //> Paris
  
	def factorial(x: BigInt): BigInt =
		if(x == 0) 1 else x * factorial(x - 1)
                                                  //> factorial: (x: BigInt)BigInt
  
  factorial(30)                                   //> res0: BigInt = 265252859812191058636308480000000
  
  // import java.math.BigInteger
  
  // def factorial2(x: BigInteger): BigInteger =
  //	if (x == BigInterger.ZERO)
  //		BigInteger.ONE
  //	else
  //		x.multiply(factorial(x.subtract(BigInteger.ONE)))
  		
}

// java class
// class MyClass {

//  private int index;
//	private String name;
	
//	public MyClass(int index, String name) {
	
//		this.index = index;
//		this.name = name;
	
//	}
// }

// class MyClass(index: Int, name: String)