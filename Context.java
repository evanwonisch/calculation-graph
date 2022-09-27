/**
 * Modelliert ein Variablen-Nachschlagewerk
 */
public class Context{
    private ContextItem[] items;

    /**
     * Erzeugt einen Variablenkontext mit allen Startwerten als 0
     * @param varnames Die Namen der Platzhalter
     */
    public Context(String[] varnames){
        items = new ContextItem[varnames.length];
        for(int i = 0; i < varnames.length; i++){
            items[i] = new ContextItem(varnames[i], 0);
        }
    }

    /**
     * Erzeugt einen Variablenkontext
     * @param items Die Variable-Wert Beziehungen
     */
    public Context(ContextItem[] items){
        this.items = items;
    }

    /**
     * Sucht die passende Variable und gibt den Wert zurück
     * @param varname Der Name der Variable
     * @return Der Wert der Variable
     */
    public float getValue(String varname){
        for(int i = 0; i < items.length; i++){
            if(items[i].getVarName().equals(varname)){
                return items[i].getValue();
            }
        }
        throw new IllegalArgumentException("Die Variable wurde im Kontext nicht gefunden");
    }

    /**
     * Sucht die passende Variable und setzt ihren Wert
     * @param varname
     * @param value
     */
    public void setValue(String varname, float value){
        boolean set = false;

        for(int i = 0; i < items.length; i++){
            if(items[i].getVarName().equals(varname)){
                items[i].setValue(value);
                set = true;
            }
        }

        if(!set) throw new IllegalArgumentException("Die Variable wurde im Kontext nicht gefunden");
    }
}