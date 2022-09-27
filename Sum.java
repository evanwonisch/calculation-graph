/**
 * Modelliert eine Summe von Expressions
 */
public class Sum extends Expression{
    private Expression[] parts;

    public Sum(Expression[] parts){
        this.parts = parts;
    }

    /**
     * Evaluiert den Berechnungsbaum
     * @param context Das Context Objekt für Variablenwerte
     * @return Das Ergebnis
     */
    public float evaluate(Context context){
        float acc = 0;
        for(Expression part : parts){
            acc += part.evaluate(context);
        }
        return acc;
    }

    /**
     * Leitet den Baum ab
     * @param varname Der Name der Variable, nach der abgeleitet werden soll.
     * @return Der Ergebnisbaum
     */
    public Expression differentiate(String varname){
        Expression[] result = new Expression[parts.length];
        for(int i = 0; i < parts.length; i++){
            result[i] = parts[i].differentiate(varname);
        }
        return new Sum(result);
    }

    /**
     * Vereinfacht den Baum mathematisch
     * @return der vereinfachte Baum
     */
    public Expression simplify(){
        Expression[] s_parts = new Expression[parts.length];
        float constant_acc = 0;
        int constant_count = 0;
        for(int i = 0; i < parts.length; i++){
            s_parts[i] = parts[i].simplify();

            //add up constants and count them
            if(s_parts[i] instanceof Constant){
                constant_acc += s_parts[i].evaluate(null);
                constant_count++;
            }
        }

        Expression[] return_parts;


        if(constant_acc == 0){
            return_parts = new Expression[s_parts.length - constant_count];
        } else {
            return_parts = new Expression[s_parts.length - constant_count + 1];
            return_parts[return_parts.length - 1] = new Constant(constant_acc);
        }

        
        int fill_index = 0;
        for(int i = 0; i < s_parts.length; i++){
            if(s_parts[i] instanceof Constant == false){
                return_parts[fill_index] = s_parts[i];
                fill_index++;
            }
        }

        return new Sum(return_parts);
    }

    /**
     * Gibt den Baum als String aus
     */
    public String toString(){
        String acc = "(+ ";
        for(int i = 0; i < parts.length; i++){
            acc += parts [i].toString();
            if(i < parts.length -1){
                acc += " ";
            }
        }
        acc += ")";
        return acc;
    }
}
