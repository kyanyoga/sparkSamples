package coursera_week2

object exercise2 {
  def sum(f: Int => Int, a: Int, b: Int) = {
  	def loop(a: Int, acc: Int): Int =
  		if (a > b) acc
  		else loop (a +1, f(a) + acc)
  	loop(a, 0)
  }                                               //> sum: (f: Int => Int, a: Int, b: Int)Int
  sum( x => x*x, 3, 4)                            //> res0: Int = 25
  
  def product(f: Int => Int)(a: Int, b: Int): Int =
  	if (a > b) 1
  	else f(a) * product(f)(a+1,b)             //> product: (f: Int => Int)(a: Int, b: Int)Int
  product(x => x * x)(4,5)                        //> res1: Int = 400
  
  def fact(n: Int) = product(x => x)(1, n)        //> fact: (n: Int)Int
  fact(5)                                         //> res2: Int = 120
  	
  import math.abs
  val tolerance = 0.0001                          //> tolerance  : Double = 1.0E-4
  def isCloseEnough(x: Double, y: Double) =
  	abs(( x - y) / x) / x < tolerance         //> isCloseEnough: (x: Double, y: Double)Boolean
  def fixedPoint(f: Double => Double)(firstGuess: Double) = {
  	def iterate(guess: Double): Double = {
  	  println("guess = " + guess)
  		val next = f(guess)
  		if (isCloseEnough(guess, next)) next
  		else iterate(next)
  		}
  		iterate(firstGuess)
 	}                                         //> fixedPoint: (f: Double => Double)(firstGuess: Double)Double
 	fixedPoint(x => 1 + x/2)(1)               //> guess = 1.0
                                                  //| guess = 1.5
                                                  //| guess = 1.75
                                                  //| guess = 1.875
                                                  //| guess = 1.9375
                                                  //| guess = 1.96875
                                                  //| guess = 1.984375
                                                  //| guess = 1.9921875
                                                  //| guess = 1.99609375
                                                  //| guess = 1.998046875
                                                  //| guess = 1.9990234375
                                                  //| guess = 1.99951171875
                                                  //| res3: Double = 1.999755859375
 	
 	// old: def sqrt(x: Double) = fixedPoint(y => (y + x / y) / 2)(1)
 	// sqrt(2)
  // stabalizing by averaging - averageDamp
  def averageDamp(f: Double => Double)(x: Double) = (x + f(x))/2
                                                  //> averageDamp: (f: Double => Double)(x: Double)Double
  
  def sqrt(x: Double) =
  	fixedPoint(averageDamp(y => x /y))(1)     //> sqrt: (x: Double)Double
  sqrt(2)                                         //> guess = 1.0
                                                  //| guess = 1.5
                                                  //| guess = 1.4166666666666665
                                                  //| guess = 1.4142156862745097
                                                  //| res4: Double = 1.4142135623746899
                                                    	
 	// curry test
  def filter(xs: List[Int], p: Int => Boolean): List[Int] =
    if (xs.isEmpty) xs
    else if (p(xs.head)) xs.head :: filter(xs.tail, p)
    else filter(xs.tail, p)                       //> filter: (xs: List[Int], p: Int => Boolean)List[Int]

  def modN(n: Int)(x: Int) = ((x % n) == 0)       //> modN: (n: Int)(x: Int)Boolean

  val nums = List(1, 2, 3, 4, 5, 6, 7, 8)         //> nums  : List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8)
  println(filter(nums, modN(2)))                  //> List(2, 4, 6, 8)
  println(filter(nums, modN(3)))                  //> List(3, 6)
  
  val b = List(2,4,6,8)                           //> b  : List[Int] = List(2, 4, 6, 8)
  println(filter(nums, modN(3)))                  //> List(3, 6)
  
  // gcd
  def gcd(a: Int, b: Int): Int =
   // println(s"a: $a")
   if (b == 0) a
   else gcd(b, a % b)                             //> gcd: (a: Int, b: Int)Int
   
  gcd(70,49)                                      //> res5: Int = 7
  gcd(49,21)                                      //> res6: Int = 7
  gcd(21, 7)                                      //> res7: Int = 7
  gcd(7, 0)                                       //> res8: Int = 7
  
	
}