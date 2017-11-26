package coursera_week2

object rationals {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(219); 
  // val x = new Rational(1, 2)
  // x.numer
  // x.denom

  // val y = new Rational(2, 3)
  // x.add(y)
	// Dry Principal : Dont repeat yourself
	
	val x  = new Rational(1, 3);System.out.println("""x  : coursera_week2.Rational = """ + $show(x ));$skip(29); 
	val y  = new Rational(5, 7);System.out.println("""y  : coursera_week2.Rational = """ + $show(y ));$skip(29); 
	val z  = new Rational(3, 2);System.out.println("""z  : coursera_week2.Rational = """ + $show(z ));$skip(31); val res$0 = 
	// x.sub(y).sub(z)
	x - y - z;System.out.println("""res0: coursera_week2.Rational = """ + $show(res$0));$skip(20); val res$1 = 
	// y.add(y)
	x + y;System.out.println("""res1: coursera_week2.Rational = """ + $show(res$1));$skip(7); val res$2 = 
	x < y;System.out.println("""res2: Boolean = """ + $show(res$2));$skip(9); val res$3 = 
	x max y;System.out.println("""res3: coursera_week2.Rational = """ + $show(res$3));$skip(214); val res$4 = 
	// infix notation x.max(y) becomes x max y
	// check pre-condition or require
	// val strange = new Rational(1,0)
	// strange.add(strange)
	// ** you can also check the assert within the function
	new Rational(2);System.out.println("""res4: coursera_week2.Rational = """ + $show(res$4));$skip(20); val res$5 = 
	new Rational(4/12);System.out.println("""res5: coursera_week2.Rational = """ + $show(res$5))}
	
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
