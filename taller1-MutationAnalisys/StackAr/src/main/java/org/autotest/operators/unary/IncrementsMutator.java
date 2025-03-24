package org.autotest.operators.unary;

import org.autotest.helpers.UnaryOperatorKindToString;
import org.autotest.operators.MutationOperator;
import spoon.reflect.code.*;
import spoon.reflect.declaration.CtElement;

import java.util.Arrays;
import java.util.List;

/**
 * Operador de mutación basado en https://pitest.org/quickstart/mutators/#INCREMENTS
 *
 * Este operador de mutación reemplaza los operadores de incremento y decremento de variables locales (variables de pila).
 */
public class IncrementsMutator extends MutationOperator {
    @Override
    public boolean isToBeProcessed(CtElement candidate) {
        if(!(candidate instanceof CtUnaryOperator)){
            return false;
        }
        CtUnaryOperator op = (CtUnaryOperator)candidate;
        List<UnaryOperatorKind> targetOperations = Arrays.asList(
                UnaryOperatorKind.POSTDEC,
                UnaryOperatorKind.POSTINC,
                UnaryOperatorKind.PREDEC,
                UnaryOperatorKind.PREINC
        );
        return targetOperations.contains(op.getKind());
    }

    @Override
    public void process(CtElement candidate) {
        CtUnaryOperator op = (CtUnaryOperator)candidate;
        op.setKind(getReplacement(op.getKind()));
    }
    public UnaryOperatorKind getReplacement(UnaryOperatorKind kind) {
        switch (kind) {
            case POSTDEC:
                return UnaryOperatorKind.POSTINC;
            case POSTINC:
                return UnaryOperatorKind.POSTDEC;
            case PREDEC:
                return UnaryOperatorKind.PREINC;
            case PREINC:
                return UnaryOperatorKind.PREDEC;
        }
        return null;
    }

    @Override
    public String describeMutation(CtElement candidate) {
        CtUnaryOperator op = (CtUnaryOperator)candidate;
        return this.getClass().getSimpleName() + ": Se reemplazó " +
                UnaryOperatorKindToString op.getKind().toString() + " por " + getReplacement(op.getKind()).toString() +
                " en la línea " + op.getPosition().getLine() + ".";
    }

}
