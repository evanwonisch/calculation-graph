/**
 * Modelliert eine Konstante im Berechnungsbaum
 */
public class Constant extends Expression{
    private float value;

    /**
     * Erzeugt eine Konstante
     * @param value Der Wert der Konstante
     */
    public Constant(float value){
        this.value = value;
    }


    /**
     * Evaluiert den Berechnungsbaum
     * @param context Das Context Objekt für Variablenwerte
     * @return Das Ergebnis
     */
    public float evaluate(Context context){
        return value;
    }

    /**
     * Leitet den Baum ab
     * @param varname Der Name der Variable, nach der abgeleitet werden soll.
     * @return Der Ergebnisbaum
     */
    public Expression differentiate(String varname){
        return new Constant(0);
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
        if (exp instanceof Constant && exp.evaluate(null) == value){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gibt den Baum als String aus
     */
    public String toString(){
        return String.valueOf(value);
    }
}
