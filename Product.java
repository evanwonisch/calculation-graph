/**
 * Modelliert ein Produkt im Berechnungsbaum
 */
public class Product extends Expression{
    private Expression[] parts;

    /**
     * Erzeugt ein Produkt
     * @param parts Die Faktoren
     */
    public Product(Expression[] parts){
        this.parts = parts;
    }

    /**
     * Evaluiert den Berechnungsbaum
     * @param context Das Context Objekt für Variablenwerte
     * @return Das Ergebnis
     */
    public float evaluate(Context context){
        float acc = 1;
        for(Expression part : parts){
            acc *= part.evaluate(context);
        }
        return acc;
    }

    /**
     * Leitet den Baum ab
     * @param varname Der Name der Variable, nach der abgeleitet werden soll.
     * @return Der Ergebnisbaum
     */
    public Expression differentiate(String varname){
        Expression[] summands = new Expression[parts.length];

        for(int i = 0; i < parts.length; i++){
            Expression[] factors = new Expression[parts.length];

            for(int j = 0; j < parts.length; j++){
                if(i != j){
                    factors[j] = parts[j];
                } else {
                    factors[j] = parts[j].differentiate(varname);
                }
            }
            summands[i] = new Product(factors);
        }
        return new Sum(summands);
    }

    /**
     * Vereinfacht den Baum mathematisch
     * @return der vereinfachte Baum
     */
    public Expression simplify(){
        Expression[] s_parts = new Expression[parts.length];
        float constant_acc = 1;
        int constant_count = 0;
        for(int i = 0; i < parts.length; i++){
            s_parts[i] = parts[i].simplify();

            //add up constants and count them
            if(s_parts[i] instanceof Constant){
                constant_acc *= s_parts[i].evaluate(null);
                constant_count++;
            }
        }

        if(constant_acc == 0) return new Constant(0);

        Expression[] return_parts = new Expression[s_parts.length - constant_count + 1];
        int fill_index = 0;
        for(int i = 0; i < s_parts.length; i++){
            if(s_parts[i] instanceof Constant == false){
                return_parts[fill_index] = s_parts[i];
                fill_index++;
            }
        }

        return_parts[return_parts.length - 1] = new Constant(constant_acc);

        return new Product(return_parts);
    }

    /**
     * Gibt den Baum als String aus
     */
    public String toString(){
        String acc = "(* ";
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
