package org.autotest.operators.conditionals;

import org.autotest.helpers.BinaryOperatorKindToString;
import org.autotest.operators.MutationOperator;
import spoon.reflect.code.*;
import spoon.reflect.declaration.CtElement;

/**
 * Operador de mutación basado en https://pitest.org/quickstart/mutators/#REMOVE_CONDITIONALS
 *
 * Este operador reemplaza los valores en las condiciones de guardas por false.
 */
public class FalseConditionalsMutator extends MutationOperator {
    @Override
    public boolean isToBeProcessed(CtElement candidate) {
        if (!(candidate instanceof  CtIf)){
            return false;
        }
        CtIf op = (CtIf) candidate;
        return !(op.getCondition().toString().equals("false"));
    }

    @Override
    public void process(CtElement candidate) {
        CtIf op = (CtIf)candidate;
        CtLiteral<Boolean> set_to_false = op.getFactory().Code().createLiteral(false);
        op.setCondition(set_to_false);
    }

    @Override
    public String describeMutation(CtElement candidate) {
        CtIf op = (CtIf) candidate;
        return this.getClass().getSimpleName() + ": Se reemplazó " +
                op.getCondition().toString() + " por " + op.getFactory().Code().createLiteral(false).toString() +
                " en la línea " + op.getPosition().getLine() + ".";
    }

}
