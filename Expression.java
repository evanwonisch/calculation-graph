/**
 * Oberklasse für Berechnungsknoten
 */
abstract public class Expression{
    /**
     * Evaluiert den Berechnungsbaum
     * @param context Das Context Objekt für Variablenwerte
     * @return Das Ergebnis
     */
    public abstract float evaluate(Context context);

    /**
     * Leitet den Baum ab
     * @param varname Der Name der Variable, nach der abgeleitet werden soll.
     * @return Der Ergebnisbaum
     */
    public abstract Expression differentiate(String varname);

    /**
     * Vereinfacht den Baum mathematisch
     * @return der vereinfachte Baum
     */
    public abstract Expression simplify();

    // /**
    //  * Bestimmt ob dieser Ausdruck zu einem anderen gleich ist
    //  * @return der Wahrheitswert
    //  */
    // public abstract boolean equals(Expression exp);

    /**
     * Gibt den Baum als String aus
     */
    public abstract String toString();

}