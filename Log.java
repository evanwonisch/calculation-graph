/**
 * Modelliert den natürlichen Logarithmus
 */
public class Log extends Expression{
    private Expression inner;

    /**
     * Erzeugt einen natürlichen Logarithmus
     * @param inner Das Innere
     */
    public Log(Expression inner){
        this.inner = inner;
    }

    /**
     * Evaluiert den Berechnungsbaum
     * @param context Das Context Objekt für Variablenwerte
     * @return Das Ergebnis
     */
    public float evaluate(Context context){
        return (float)Math.log(inner.evaluate(context));
    }

    /**
     * Leitet den Baum ab
     * @param varname Der Name der Variable, nach der abgeleitet werden soll.
     * @return Der Ergebnisbaum
     */
    public Expression differentiate(String varname){
        return new Product(new Expression[]{
            inner.differentiate(varname),
            new Power(inner, new Constant(-1))
        });
    }

    /**
     * Bestimmt ob dieser Ausdruck zu einem anderen gleich ist
     * @return der Wahrheitswert
     */
    public boolean equals(Expression exp){
        if(exp instanceof Log && ((Log)exp).inner.equals(this.inner)){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Vereinfacht den Baum mathematisch
     * @return der vereinfachte Baum
     */
    public Expression simplify(){
        Expression s_inner = inner.simplify();

        if(s_inner instanceof Constant && s_inner.evaluate(null) == 1){
            return new Constant(0);
        } else {
            return new Log(s_inner);
        }
    }

    /**
     * Gibt den Baum als String aus
     */
    public String toString(){
        return "(ln " + inner.toString() + ")";
    }
}
