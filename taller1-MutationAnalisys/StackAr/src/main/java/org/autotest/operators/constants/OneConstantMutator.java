package org.autotest.operators.constants;

import org.autotest.operators.MutationOperator;
import spoon.reflect.code.CtLiteral;
import spoon.reflect.declaration.CtElement;

import java.util.Arrays;
import java.util.List;

/**
 * Operador de mutación basado en https://pitest.org/quickstart/mutators/#EXPERIMENTAL_CRCR
 *
 * Este operador reemplaza los valores de las constantes por uno.
 */
public class OneConstantMutator extends MutationOperator {
    @Override
    public boolean isToBeProcessed(CtElement candidate) {
        if (!(candidate instanceof CtLiteral)) {
            return false;
        }

        CtLiteral op = (CtLiteral)candidate;
        String type = getLiteralType(op);
        List<String> targetTypes = Arrays.asList(
                "int"
        );

        if (!targetTypes.contains(type)) {
            return false;
        }

        String number  = op.getValue().toString();
        if (number.equals("1")) {
            // Para evitar generar mutantes inválidos, ignoramos los literales que ya son 1.
            // ¿Porqué? Para no volver a generar el mismo test
            return false;
        }
        return true;
    }

    @Override
    public void process(CtElement candidate) {
        CtLiteral op = (CtLiteral)candidate;
        op.setValue(op.getFactory().Code().createLiteral(1));
    }

    private static String getLiteralType(CtLiteral op) {
        return op.getType().toString();
    }

    @Override
    public String describeMutation(CtElement candidate) {
        CtLiteral op = (CtLiteral)candidate;
        return this.getClass().getSimpleName() + ": Se reemplazó " +
                op.getValue().toString() + " por 1" +
                " en la línea " + op.getPosition().getLine() + ".";
    }
}
