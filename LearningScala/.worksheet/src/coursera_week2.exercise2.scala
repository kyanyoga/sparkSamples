package coursera_week2

object exercise2 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(194); 
  def sum(f: Int => Int, a: Int, b: Int) = {
  	def loop(a: Int, acc: Int): Int =
  		if (a > b) acc
  		else loop (a +1, f(a) + acc)
  	loop(a, 0)
  };System.out.println("""sum: (f: Int => Int, a: Int, b: Int)Int""");$skip(23); val res$0 = 
  sum( x => x*x, 3, 4);System.out.println("""res0: Int = """ + $show(res$0));$skip(104); 
  
  def product(f: Int => Int)(a: Int, b: Int): Int =
  	if (a > b) 1
  	else f(a) * product(f)(a+1,b);System.out.println("""product: (f: Int => Int)(a: Int, b: Int)Int""");$skip(27); val res$1 = 
  product(x => x * x)(4,5);System.out.println("""res1: Int = """ + $show(res$1));$skip(46); 
  
  def fact(n: Int) = product(x => x)(1, n);System.out.println("""fact: (n: Int)Int""");$skip(10); val res$2 = 
  fact(5)
  	
  import math.abs;System.out.println("""res2: Int = """ + $show(res$2));$skip(47); 
  val tolerance = 0.0001;System.out.println("""tolerance  : Double = """ + $show(tolerance ));$skip(81); 
  def isCloseEnough(x: Double, y: Double) =
  	abs(( x - y) / x) / x < tolerance;System.out.println("""isCloseEnough: (x: Double, y: Double)Boolean""");$skip(259); 
  def fixedPoint(f: Double => Double)(firstGuess: Double) = {
  	def iterate(guess: Double): Double = {
  	  println("guess = " + guess)
  		val next = f(guess)
  		if (isCloseEnough(guess, next)) next
  		else iterate(next)
  		}
  		iterate(firstGuess)
 	};System.out.println("""fixedPoint: (f: Double => Double)(firstGuess: Double)Double""");$skip(30); val res$3 = 
 	fixedPoint(x => 1 + x/2)(1);System.out.println("""res3: Double = """ + $show(res$3));$skip(193); 
 	
 	// old: def sqrt(x: Double) = fixedPoint(y => (y + x / y) / 2)(1)
 	// sqrt(2)
  // stabalizing by averaging - averageDamp
  def averageDamp(f: Double => Double)(x: Double) = (x + f(x))/2;System.out.println("""averageDamp: (f: Double => Double)(x: Double)Double""");$skip(68); 
  
  def sqrt(x: Double) =
  	fixedPoint(averageDamp(y => x /y))(1);System.out.println("""sqrt: (x: Double)Double""");$skip(10); val res$4 = 
  sqrt(2);System.out.println("""res4: Double = """ + $show(res$4));$skip(236); 
                                                    	
 	// curry test
  def filter(xs: List[Int], p: Int => Boolean): List[Int] =
    if (xs.isEmpty) xs
    else if (p(xs.head)) xs.head :: filter(xs.tail, p)
    else filter(xs.tail, p);System.out.println("""filter: (xs: List[Int], p: Int => Boolean)List[Int]""");$skip(45); 

  def modN(n: Int)(x: Int) = ((x % n) == 0);System.out.println("""modN: (n: Int)(x: Int)Boolean""");$skip(43); 

  val nums = List(1, 2, 3, 4, 5, 6, 7, 8);System.out.println("""nums  : List[Int] = """ + $show(nums ));$skip(33); 
  println(filter(nums, modN(2)));$skip(33); 
  println(filter(nums, modN(3)));$skip(27); 
  
  val b = List(2,4,6,8);System.out.println("""b  : List[Int] = """ + $show(b ));$skip(33); 
  println(filter(nums, modN(3)));$skip(108); 
  
  // gcd
  def gcd(a: Int, b: Int): Int =
   // println(s"a: $a")
   if (b == 0) a
   else gcd(b, a % b);System.out.println("""gcd: (a: Int, b: Int)Int""");$skip(17); val res$5 = 
   
  gcd(70,49);System.out.println("""res5: Int = """ + $show(res$5));$skip(13); val res$6 = 
  gcd(49,21);System.out.println("""res6: Int = """ + $show(res$6));$skip(13); val res$7 = 
  gcd(21, 7);System.out.println("""res7: Int = """ + $show(res$7));$skip(12); val res$8 = 
  gcd(7, 0);System.out.println("""res8: Int = """ + $show(res$8))}
  
	
}
