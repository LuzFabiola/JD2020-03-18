package by.it.gutkovsky.calc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Parser {

    Var calc(String expresion) {
        //2+2;
        String[] parts = expresion.split(Patterns.OPERATION, 2);
        Var right = Var.createVar(parts[1]);
        if (expresion.contains("=")){
            return Var.saveVar(parts[0], right);
        }

        Var left = Var.createVar(parts[0]);
        if (parts.length == 1) {
            return left;
        }

        if (left == null || right == null) {
            return null; // в этом месте нужно будет генерироавть ошибку
        }

        Matcher matcherOp = Pattern.compile(Patterns.OPERATION).matcher(expresion);
        if (matcherOp.find()) {
            String operation = matcherOp.group();
            switch (operation) {
                case "+":
                    return left.add(right);
                case "-":
                    return left.sub(right);
                case "*":
                    return left.mul(right);
                case "/":
                    return left.div(right);
            }
        }
        return null;
    }
}
