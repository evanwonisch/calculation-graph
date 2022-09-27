/**
 * Modelliert eine Variable
 */
public class Variable extends Expression{
    private String varname;

    /**
     * Erzeugt eine Variable im Baum
     * @param varname Der Name der Variable
     */
    public Variable(String varname){
        this.varname = varname;
    }

    /**
     * Evaluiert den Berechnungsbaum
     * @param context Das Context Objekt für Variablenwerte
     * @return Das Ergebnis
     */
    public float evaluate(Context context){
        return context.getValue(varname);
    }

    /**
     * Leitet den Baum ab
     * @param varname Der Name der Variable, nach der abgeleitet werden soll.
     * @return Der Ergebnisbaum
     */
    public Expression differentiate(String varname){
        if(this.varname == varname){
            return new Constant(1);
        } else {
            return new Constant(0);
        }
    }

    /**
     * Vereinfacht den Baum mathematisch
     * @return der vereinfachte Baum
     */
    public Expression simplify(){
        return this;
    }

    /**
     * Bestimmt ob dieser Ausdruck zu einem anderen gleich ist
     * @return der Wahrheitswert
     */
    public boolean equals(Expression exp){
        if (exp instanceof Variable && ((Variable)exp).varname == varname){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gibt den Baum als String aus
     */
    public String toString(){
        return varname;
    }
}
