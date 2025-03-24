package org.autotest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StackTests3 extends MutationAnalysisRunner {
    @Override
    protected boolean useVerboseMode() {
        return false;
    }

    // Tests de StackTests2.java
    public void testSizeIncreasesByOne() throws Exception {
        Stack stack = createStack();
        assertEquals(0, stack.size());
        stack.push(42);
        assertEquals(1, stack.size());
    }

    public void testDefaultConstructor() throws Exception {
        Stack stack = createStack();
        assertTrue(stack.isEmpty());
    }

    public void testConstructorWithSpecifiedCapacity() throws Exception {
        Stack stack = createStack(5);
    }

    public void testConstructorWithNegativeCapacity() {
        assertThrows(IllegalArgumentException.class, () -> {
            Stack stack = createStack(-1);
        });
    }

    public void testIsEmptyMethod() throws Exception {
        Stack stack = createStack();
        assertTrue(stack.isEmpty());
        stack.push(42);
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    public void testIsFullMethod() throws Exception {
        Stack stack = createStack(1);
        assertFalse(stack.isFull());
        stack.push(42);
        assertTrue(stack.isFull());
        stack.pop();
        assertFalse(stack.isFull());
    }

    public void testToStringMethod() throws Exception {
        Stack stack = createStack(2);
        assertEquals("[]", stack.toString());
        stack.push(42);
        assertEquals("[42]", stack.toString());
        stack.push(43);
        assertEquals("[42,43]", stack.toString());
    }

    public void  testEqualsMethod() throws Exception {
        Stack stack1 = createStack(2);
        Stack stack2 = createStack(2);
        stack1 .push(42);
        assertEquals(stack1, stack1);
        stack2 .push(42);
        assertEquals(stack1, stack2);
        stack1 .push(43);
        stack2.push(44);
        assertNotEquals(stack1, stack2);
    }
  // TODO:  public void testHashCodeMethod() throws Exception {}
    public void testTopMethod() throws Exception {
        Stack stack = createStack();
        assertThrows(IllegalStateException.class, () -> {
            stack.top();
        });
        stack.push(42);
        assertEquals(42, stack.top());
    }
    // Esta es equivalente?
    public void testConstructorWithCapacity1() throws Exception {
        Stack stack = createStack(1);
    }
    public void testConstructorWithCapacity0() throws Exception {
        Stack stack = createStack(0);
    }
}
