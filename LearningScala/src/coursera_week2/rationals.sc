package coursera_week2

object rationals {
  // val x = new Rational(1, 2)
  // x.numer
  // x.denom

  // val y = new Rational(2, 3)
  // x.add(y)
	// Dry Principal : Dont repeat yourself
	
	val x  = new Rational(1, 3)               //> x  : coursera_week2.Rational = 1/3
	val y  = new Rational(5, 7)               //> y  : coursera_week2.Rational = 5/7
	val z  = new Rational(3, 2)               //> z  : coursera_week2.Rational = 3/2
	// x.sub(y).sub(z)
	x - y - z                                 //> res0: coursera_week2.Rational = -79/42
	// y.add(y)
	x + y                                     //> res1: coursera_week2.Rational = 22/21
	x < y                                     //> res2: Boolean = true
	x max y                                   //> res3: coursera_week2.Rational = 5/7
	// infix notation x.max(y) becomes x max y
	// check pre-condition or require
	// val strange = new Rational(1,0)
	// strange.add(strange)
	// ** you can also check the assert within the function
	new Rational(2)                           //> res4: coursera_week2.Rational = 2/1
	new Rational(4/12)                        //> res5: coursera_week2.Rational = 0/1
	
}

class Rational(x: Int, y: Int) {
	require(y != 0, "denomitator must be nonzero")
	// second constructor - the first constructor is called: primary constructor
	def this(x: Int) = this(x, 1)
	
	private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd( b, a % b)
	// private val g = gcd(x, y)
	// def numer = x / gcd(x, y)
	// def denom = y / gcd(x, y)
	def numer = x
	def denom = y

	// val numer = x / gcd(x, y)
	// val denom = y / gcd(x, y)
	// Adv. Would be called only once as a Val.
	// Choosing different versions of the data without affecting the client is called
	// ** Data Abstraction **
	
	// using lcd
	// def less(that: Rational) = numer * that.denom < that.numer * denom
	// using this or self[python]
	
	// def max(that: Rational) = if (this.less(that)) that else this
	def max(that: Rational) = if (this < that) that else this
	// def less(that: Rational) = this.numer * that.denom < that.numer * this.denom
	def < (that: Rational) = this.numer * that.denom < that.numer * this.denom

	// def add(that: Rational) =
	def + (that: Rational) =
		new Rational(
			numer * that.denom + that.numer * denom,
			denom * that.denom)
	
	// def neg: Rational = new Rational(-numer, denom)
	def unary_- : Rational = new Rational(-numer, denom)
	
	// def sub(that: Rational) = add(that.neg)
	def - (that: Rational) = this + -that
	
	override def toString = numer / gcd(x, y) + "/" + denom / gcd(x, y)
}