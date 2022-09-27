/**
 * Stellt die Exponentialfunktion dar.
 */
public class Exp extends Expression{
    private Expression exponent;

    /**
     * Erzeugt eine Exponentialfunktion
     * @param exponent Der Exponent
     */
    public Exp( Expression exponent){
        this.exponent = exponent;
    }

    /**
     * Evaluiert den Berechnungsbaum
     * @param context Das Context Objekt für Variablenwerte
     * @return Das Ergebnis
     */
    public float evaluate(Context context){
        return (float)Math.exp(exponent.evaluate(context));
    }

    /**
     * Leitet den Baum ab
     * @param varname Der Name der Variable, nach der abgeleitet werden soll.
     * @return Der Ergebnisbaum
     */
    public Expression differentiate(String varname){
        return new Product(new Expression[]{
            this,
            exponent.differentiate(varname)
        });
    }

    /**
     * Vereinfacht den Baum mathematisch
     * @return der vereinfachte Baum
     */
    public Expression simplify(){
        Expression s_exponent = exponent.simplify();

        if(s_exponent instanceof Constant && s_exponent.evaluate(null) == 0){
            return new Constant(1);
        } else {
            return new Exp(s_exponent);
        }
    }

    /**
     * Bestimmt ob dieser Ausdruck zu einem anderen gleich ist
     * @return der Wahrheitswert
     */
    public boolean equals(Expression exp){
        if(exp instanceof Exp && ((Exp)exp).exponent.equals(this.exponent)){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gibt den Baum als String aus
     */
    public String toString(){
        return "(exp " + exponent.toString() + ")";
    }
}
