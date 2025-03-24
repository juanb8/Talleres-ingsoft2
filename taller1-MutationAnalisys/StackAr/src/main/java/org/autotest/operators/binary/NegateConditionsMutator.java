package org.autotest.operators.binary;

import org.autotest.helpers.BinaryOperatorKindToString;
import org.autotest.operators.MutationOperator;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.declaration.CtElement;

import java.util.Arrays;
import java.util.List;

/**
 * Operador de mutación basado en https://pitest.org/quickstart/mutators/#NEGATE_CONDITIONALS
 *
 * Este operador de mutación reemplaca los condicionales por sus opuestos.
 * Por ejemplo, reemplaza "a > b" por "a <= b".
 */
public class NegateConditionsMutator extends MutationOperator {
    @Override
    public boolean isToBeProcessed(CtElement candidate) {
        if (!(candidate instanceof CtBinaryOperator)) {
            return false;
        }

        CtBinaryOperator op = (CtBinaryOperator)candidate;
        List<BinaryOperatorKind> targetOperations = Arrays.asList(
                BinaryOperatorKind.EQ, // ==
                BinaryOperatorKind.NE, // !=
                BinaryOperatorKind.LE, // <=
                BinaryOperatorKind.GE, // >=
                BinaryOperatorKind.LT, // <
                BinaryOperatorKind.GT // >
        );
        return targetOperations.contains(op.getKind());
    }

    @Override
    public void process(CtElement candidate) {
        CtBinaryOperator op = (CtBinaryOperator)candidate;
        op.setKind(getReplacement(op.getKind()));
    }
    public BinaryOperatorKind getReplacement(BinaryOperatorKind kind) {
        switch (kind) {
            case EQ:
                return BinaryOperatorKind.NE;
            case NE:
                return BinaryOperatorKind.EQ;
            case LE:
                return BinaryOperatorKind.GT;
            case GE:
                return BinaryOperatorKind.LT;
            case LT:
                return BinaryOperatorKind.GE;
            case GT:
                return BinaryOperatorKind.LT;
        }
        return null;
    }
    @Override
    public String describeMutation(CtElement candidate) {
        CtBinaryOperator op = (CtBinaryOperator)candidate;
        return this.getClass().getSimpleName() + ": Se reemplazó " +
                BinaryOperatorKindToString.get(op.getKind()) + " por " + BinaryOperatorKindToString.get(getReplacement(op.getKind())) +
                " en la línea " + op.getPosition().getLine() + ".";
    }
}
