# Taller #1 - Mutation Analysis

## Instrucciones
Completar este documento con las respuestas correspondientes a los ejercicios planteados en el enunciado del taller.

---

## Ejercicio 1: Resultados de generación de mutantes

1. ¿Cuántos mutantes se generaron en total?
   - Respuesta: 75. 

2. ¿Qué operador de mutación generó más mutantes? ¿Cuántos y por qué?
   - Respuesta:
	   - TrueConditionalsMutator y FalseConditionalMutator.
	   - 10.
	   - Porque las estructuras condicionales son más comunes.   

3. ¿Qué operador de mutación generó menos mutantes? ¿Cuántos y por qué?
   - Respuesta:
	   - ConditionalBoundaryMutator, NullReturnsMutator, IncrementsMutator, EmptyReturnsMutator
	   - 3.
	   - Por que son condiciones específicas y poco comunes. 

---

## Ejercicio 2: Evaluación de test suites

1. ¿Cuántos mutantes vivos y muertos encontraron cada uno de los test suites?
   - **StackTests1**:
     - Mutantes vivos: 56
     - Mutantes muertos: 19 
   - **StackTests2**:
     - Mutantes vivos: 38
     - Mutantes muertos: 37

2. ¿Cuál es el mutation score de cada test suite?
   - **StackTests1**: 25%
   - **StackTests2**: 49%

---

## Ejercicio 3: Mejora del test suite

1. ¿Cuál es el mutation score logrado para los tests de StackTests3?
   - Respuesta:

2. ¿Cuántos mutantes vivos y muertos encontraron?
   - Mutantes vivos:
   - Mutantes muertos:

3. Comente cuáles son todos los mutantes vivos que quedaron y por qué son equivalentes al programa original (si no lo fueran, todavía es posible mejorar el mutation score).
   - Respuesta:

4. ¿Cuál es el instruction coverage promedio que lograron para las clases mutadas?
   - Respuesta:

5. ¿Cuál es el peor instruction coverage que lograron para una clase mutada? ¿Por qué creen que sucede esto?
   - Respuesta:
