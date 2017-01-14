
public class Fraction extends Number implements Comparable<Fraction>{
	private Integer numerator;
	private Integer denominator;
	
    public static void main(String[] args) {
        Fraction f1 = new Fraction(1,10);
        Fraction f2 = new Fraction(2);

        System.out.println(f1.mul(f2));
        System.out.println(f1.add(1));
        System.out.println(f1.equals(new Fraction(1,10)));
    }
	
	public Fraction(Integer top, Integer bottom){
		numerator = top;
		denominator = bottom;
	}
	public Fraction(Integer top){
		numerator = top;
		denominator = 1;
	}
	
	public Fraction mul(Fraction otherFrac){
		Integer newNum, newDen, common;
		
		newNum = this.getNumerator() * otherFrac.getNumerator();
		newDen = this.getDenominator() * otherFrac.getDenominator();
		
		common= gcd(newNum,newDen);
		return new Fraction(newNum/common,newDen/common);
	}
	
	public Fraction mul(Integer other){
		return this.mul(new Fraction(other));
	}
	
	private static int gcd(int a, int b)
	{
	  if(a == 0 || b == 0) return a+b; // base case
	  return gcd(b,a%b);
	}
	
	public Fraction add(Fraction otherFrac){
		Integer newNum, newDen, common;
		
		newNum = otherFrac.getDenominator() * this.numerator + this.denominator * otherFrac.getNumerator();
		newDen = this.denominator * otherFrac.getDenominator();
		common = gcd(newNum,newDen);
		return new Fraction(newNum/common, newDen/common);
		
	}
	
	public Fraction add(Integer other){
		return add(new Fraction(other));
	}
	
	public Integer getNumerator(){
		return this.numerator;
	}
	
	public Integer getDenominator(){
		return this.denominator;
	}
	
	public void setNumerator(Integer numerator){
		this.numerator = numerator;
	}
	
	public void setDenominator(Integer denominator){
		this.denominator = denominator;
	}
	
	public String toString(){
		return this.numerator.toString() + "/" + this.denominator.toString();
	}

	public boolean equals(Fraction other){
		return this.numerator * other.getDenominator() == this.denominator * other.getNumerator();
	}
	
	public double doubleValue() {
	    return numerator.doubleValue() / denominator.doubleValue();
	}


	public float floatValue() {
	    return numerator.floatValue() / denominator.floatValue();
	}


	public int intValue() {
	    return numerator.intValue() / denominator.intValue();
	}


	public long longValue() {
	    return numerator.longValue() / denominator.longValue();
	}
	
	public int compareTo(Fraction other){
		Integer num1 = numerator * other.denominator;
		Integer num2 = denominator * other.numerator;
		return num1 - num2;
	}
}

