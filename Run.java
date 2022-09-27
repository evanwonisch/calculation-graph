public class Run {
    public static void main(String[] argv){
        new Run();
    }

    public Run(){
        Expression[] parts = new Expression[]{
            new Exp(new Variable("x")),
            new Variable("x"),
            new Variable("x"),
            new Variable("y"),
            new Constant(3)
        };
        Expression tree = new Product(parts);
    

        System.out.println(tree);
        // System.out.println("\n\n");
        // System.out.println(tree.differentiate("x"));
        System.out.println("\n\n");
        System.out.println(tree.differentiate("x").simplify());


        //Variablenkontext
        String[] varnames = new String[]{"x", "y"};
        Context context = new Context(varnames);
        context.setValue("x", 3);
        context.setValue("y", 2);
        System.out.println(tree.differentiate("x").evaluate(context));
    }
}
