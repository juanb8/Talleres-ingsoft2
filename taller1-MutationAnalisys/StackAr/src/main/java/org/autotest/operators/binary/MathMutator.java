package org.autotest.operators.binary;

import org.autotest.helpers.BinaryOperatorKindToString;
import org.autotest.operators.MutationOperator;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.declaration.CtElement;

import java.util.Arrays;
import java.util.List;

/**
 * Operador de mutación basado en https://pitest.org/quickstart/mutators/#MATH
 *
 * Este operador de mutación reemplaza las operaciones aritméticas binarias para enteros o punto flotante con otra operación.
 */
public class MathMutator extends MutationOperator {
    @Override
    public boolean isToBeProcessed(CtElement candidate) {
        if (!(candidate instanceof CtBinaryOperator)) {
            return false;
        }

        CtBinaryOperator op = (CtBinaryOperator)candidate;
        List<BinaryOperatorKind> targetOperations = Arrays.asList(
                BinaryOperatorKind.PLUS, // +
                BinaryOperatorKind.MINUS, // -
                BinaryOperatorKind.MUL, // *
                BinaryOperatorKind.DIV, // /
                BinaryOperatorKind.MOD, // %
               // BinaryOperatorKind.AND, // $
               // BinaryOperatorKind.OR, // |
                BinaryOperatorKind.BITXOR, // ^
                BinaryOperatorKind.BITOR,  // |
                BinaryOperatorKind.BITAND, // /
                BinaryOperatorKind.SL,  // <<
                BinaryOperatorKind.SR // >>
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
            case PLUS:
                return BinaryOperatorKind.MINUS;
            case MINUS:
                return BinaryOperatorKind.PLUS;
            case MUL:
                return BinaryOperatorKind.DIV;
            case DIV:
                return BinaryOperatorKind.MUL;
            case MOD:
                return BinaryOperatorKind.MUL;
            //case AND:
             //   return BinaryOperatorKind.OR;
            //case OR:
             //   return BinaryOperatorKind.AND;
            case BITAND:
                return BinaryOperatorKind.BITOR;
            case BITOR:
                return BinaryOperatorKind.BITAND;
            case BITXOR:
                return BinaryOperatorKind.BITAND;
            case SL:
                return BinaryOperatorKind.SR;
            case SR:
                return BinaryOperatorKind.SL;
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
