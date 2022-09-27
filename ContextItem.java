/**
 * Modelliert eine Variable-Wert Beziehung
 */
public class ContextItem {
    private String varname;
    private float value;

    /**
     * Erzeugt ein ContextItem Objekt
     * @param varname Name der betroffenen Variable
     * @param value Wert für die Variable
     */
    public ContextItem(String varname, float value){
        this.varname = varname;
        this.value = value;
    }

    /**
     * Gibt die Value zurück
     * @return Der Wert
     */
    public float getValue(){
        return value;
    }

    /**
     * Setzt den Wert Value
     * @param value Der neue Wert
     */
    public void setValue(float value){
        this.value = value;
    }

    /**
     * Gibt den Namen der Variable an
     * @return Der Name
     */
    public String getVarName(){
        return varname;
    }

    /**
     * Setzt den neuen Namen der Variable
     * @return Der Name
     */
    public void setVarName(String name){
        this.varname = name;
    }
}
