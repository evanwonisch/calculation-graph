/**
 * Stellt eine Exponentierung dar.
 */
public class Power extends Expression{
    private Expression base;
    private Expression exponent;

    /**
     * Erzeugt eine Exponentialfunktion zur gegebenen Basis
     * @param base Die Basis
     * @param exponent Der Exponent
     */
    public Power(Expression base, Expression exponent){
        this.exponent = exponent;
        this.base = base;
    }

    /**
     * Evaluiert den Berechnungsbaum
     * @param context Das Context Objekt für Variablenwerte
     * @return Das Ergebnis
     */
    public float evaluate(Context context){
        return (float)Math.pow(base.evaluate(context), exponent.evaluate(context));
    }

    /**
     * Vereinfacht den Baum mathematisch
     * @return der vereinfachte Baum
     */
    public Expression simplify(){
        Expression s_base = base.simplify();
        Expression s_exponent = exponent.simplify();

        return new Power(s_base, s_exponent);
    }

    /**
     * Leitet den Baum ab
     * @param varname Der Name der Variable, nach der abgeleitet werden soll.
     * @return Der Ergebnisbaum
     */
    public Expression differentiate(String varname){
        return new Sum(new Expression[]{
            new Product(new Expression[]{
                new Power(base, new Sum(new Expression[]{exponent, new Constant(-1)})),
                exponent,
                base.differentiate(varname)
            }),
            new Product(new Expression[]{
                this,
                new Log(base),
                exponent.differentiate(varname)
            })
        });
    }

    /**
     * Bestimmt ob dieser Ausdruck zu einem anderen gleich ist
     * @return der Wahrheitswert
     */
    public boolean equals(Expression exp){
        if(exp instanceof Power && ((Power)exp).exponent.equals(this.exponent) && ((Power)exp).base.equals(this.base)){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gibt den Baum als String aus
     */
    public String toString(){
        return "(** " + base.toString()+ " " + exponent.toString() + ")";
    }
}
