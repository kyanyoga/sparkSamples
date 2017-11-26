package proginscala

object chapter1 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(71); 
  println("Chapter 1 WorkSheet");$skip(66); 
  
  var capital = Map("US" -> "Washington", "France" -> "Paris");System.out.println("""capital  : scala.collection.immutable.Map[String,String] = """ + $show(capital ));$skip(34); 
  capital += ("Japan" -> "Tokyo");$skip(29); 
  println(capital("France"));$skip(80); 
  
	def factorial(x: BigInt): BigInt =
		if(x == 0) 1 else x * factorial(x - 1);System.out.println("""factorial: (x: BigInt)BigInt""");$skip(20); val res$0 = 
  
  factorial(30);System.out.println("""res0: BigInt = """ + $show(res$0))}
  
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
