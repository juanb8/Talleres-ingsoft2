package org.autotest.operators.returns;

import org.autotest.operators.MutationOperator;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtLiteral;
import spoon.reflect.code.CtReturn;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtTypeInformation;

import java.util.Arrays;
import java.util.List;

/**
 * Operador de mutación basado en https://pitest.org/quickstart/mutators/#NULL_RETURNS
 *
 * Este operador reemplaza los valores de retorno de las funciones que devuelven una variable de tipo no-primitivo por null.
 */
public class NullReturnsMutator extends MutationOperator {
    @Override
    public boolean isToBeProcessed(CtElement candidate) {
        if (!(candidate instanceof CtReturn)) {
            return false;
        }
        CtReturn op = (CtReturn) candidate;

        String type = getReturnedExpressionType(op);
        List<String> targetTypes = Arrays.asList(
                "byte",
                "short",
                "char",
                "long",
                "float",
                "double",
                "boolean",
                "int"
        );
        if (targetTypes.contains(type)){
            return false;
        }
        return !(op.getReturnedExpression().toString().equals("null"));


    }

    private static String getReturnedExpressionType(CtReturn op) {
        return op.getReturnedExpression().getType().toString();
    }
    @Override
    public void process(CtElement candidate) {
        CtReturn cr = (CtReturn) candidate;
        cr.setReturnedExpression(getReturnedExpression(cr));

    }
    public CtExpression getReturnedExpression(CtElement candidate){
        return candidate.getFactory().Code().createLiteral(null);
    }
    @Override
    public String describeMutation(CtElement candidate) {
        CtReturn op = (CtReturn)candidate;
        return this.getClass().getSimpleName() + ": Se reemplazó " +
                op.getReturnedExpression().toString() + " por " + getReturnedExpression(op).toString() +
                " en la línea " + op.getPosition().getLine() + ".";
    }
}
