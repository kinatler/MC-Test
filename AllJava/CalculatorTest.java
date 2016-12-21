package simplejavacalculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import simplejavacalculator.Calculator.BiOperatorModes;
import simplejavacalculator.Calculator.MonoOperatorModes;

public class CalculatorTest {
	Calculator cal;

	@Before
	public void setUp() {
		cal = new Calculator();
	}

	@Test
	public void testNormal() throws IllegalArgumentException,
			IllegalAccessException, NoSuchFieldException, SecurityException,
			NoSuchMethodException, InvocationTargetException {
		final Field field1 = cal.getClass().getDeclaredField("num1");
		field1.setAccessible(true);
		final Double num_1 = 12.0;
		final Double num_2 = Double.POSITIVE_INFINITY;
		field1.set(cal, num_1);
		final Field field2 = cal.getClass().getDeclaredField("num2");
		field2.setAccessible(true);
		field2.set(cal, num_2);
		final Field field3 = cal.getClass().getDeclaredField("mode");
		field3.setAccessible(true);
		field3.set(cal, BiOperatorModes.normal);
		final Method method = cal.getClass().getDeclaredMethod(
				"calculateBiImpl", null);
		method.setAccessible(true);
		final Double res = (Double) method.invoke(cal, null);
		System.out.println(res);
		assertEquals(num_2, res, 0);
	}

	@Test
	public void testAdd() throws IllegalArgumentException,
			IllegalAccessException, NoSuchFieldException, SecurityException,
			NoSuchMethodException, InvocationTargetException {
		final Double num_1 = 10.1;
		final Double num_2 = Double.POSITIVE_INFINITY;
		final Field field1 = cal.getClass().getDeclaredField("num1");
		field1.setAccessible(true);
		field1.set(cal, num_1);
		final Field field2 = cal.getClass().getDeclaredField("num2");
		field2.setAccessible(true);
		field2.set(cal, num_2);
		final Field field3 = cal.getClass().getDeclaredField("mode");
		field3.setAccessible(true);
		field3.set(cal, BiOperatorModes.add);
		final Method method = cal.getClass().getDeclaredMethod(
				"calculateBiImpl", null);
		method.setAccessible(true);
		final Double res = (Double) method.invoke(cal, null);
		System.out.println(res);
		assertEquals(num_1 + num_2, res, 0);
	}

	@Test
	public void testBiImpl() throws IllegalArgumentException,
			IllegalAccessException, NoSuchFieldException, SecurityException,
			NoSuchMethodException, InvocationTargetException {
		final Field field1 = cal.getClass().getDeclaredField("mode");
		field1.setAccessible(true);
		field1.set(cal, BiOperatorModes.normal);
		final Field field2 = cal.getClass().getDeclaredField("num1");
		field2.setAccessible(true);
		final Double num1 = 100.0;
		field2.set(cal, num1);
		final BiOperatorModes newmode = BiOperatorModes.minus;
		final Double num = Double.POSITIVE_INFINITY;
		cal.calculateBi(newmode, num);
		final Double num_2 = 7.0;
		final Field field3 = cal.getClass().getDeclaredField("num2");
		field3.setAccessible(true);
		field3.set(cal, num_2);
		final Method method = cal.getClass().getDeclaredMethod(
				"calculateBiImpl", null);
		method.setAccessible(true);
		final Double res = (Double) method.invoke(cal, null);
		System.out.println(res);
		assertEquals(num - num_2, res, 0);
	}

	@Test
	public void testBi() throws NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException {
		final Field field1 = cal.getClass().getDeclaredField("mode");
		field1.setAccessible(true);
		field1.set(cal, BiOperatorModes.add);
		final Field field2 = cal.getClass().getDeclaredField("num1");
		field2.setAccessible(true);
		final Field field3 = cal.getClass().getDeclaredField("num2");
		field3.setAccessible(true);
		final Double num1 = 10.0;
		field2.set(cal, num1);
		final BiOperatorModes newmode = BiOperatorModes.minus;
		final Double num = Double.POSITIVE_INFINITY;
		final Double res = cal.calculateBi(newmode, num);
		System.out.println(res);
		assertEquals(num1 + num, res, 0); // return num1=calculateBi()?
		assertEquals(num, (Double) field3.get(cal), 0); // num2=num?
		if (newmode != (BiOperatorModes) field1.get(cal)) {
			fail("mode is not set");
		}

	}

	@Test
	public void testEqual() {
		cal.calculateBi(BiOperatorModes.add, 2.0);
		assertEquals(8.0, cal.calculateBi(BiOperatorModes.add, 6.0), 0.0);
		assertEquals(8.0, cal.calculateBi(BiOperatorModes.add, 0.0), 0.0);
		assertEquals(9.1, cal.calculateBi(BiOperatorModes.add, 1.1), 0.0);
		assertEquals(Double.POSITIVE_INFINITY,
				cal.calculateBi(BiOperatorModes.add, Double.POSITIVE_INFINITY),
				0.0);
	}

	@Test
	public void testReset() throws IllegalArgumentException,
			IllegalAccessException, NoSuchFieldException, SecurityException,
			NoSuchMethodException, InvocationTargetException {

		cal.calculateBi(BiOperatorModes.add, 2.0);
		assertEquals(5.0, cal.calculateBi(BiOperatorModes.add, 3.0), 0.0);
		assertEquals(Double.NaN, cal.reset(), 0.0);

		final Field field1 = cal.getClass().getDeclaredField("num1");
		field1.setAccessible(true);
		final Field field2 = cal.getClass().getDeclaredField("num2");
		field2.setAccessible(true);
		final Field field3 = cal.getClass().getDeclaredField("mode");
		field3.setAccessible(true);
		final Double num_1 = (Double) field1.get(cal);
		final Double num_2 = (Double) field2.get(cal);
		final BiOperatorModes MODE = (BiOperatorModes) field3.get(cal);
		assertEquals(0.0, num_1, 0.0);
		assertEquals(0.0, num_2, 0.0);
		assertEquals(BiOperatorModes.normal, MODE);
	}

	@Test
	public void testSquare() {
		assertEquals(4.0, cal.calculateMono(MonoOperatorModes.square, 2.0), 0.0);
		assertEquals(0.0, cal.calculateMono(MonoOperatorModes.square, 0.0), 0.0);
		assertEquals(1.21, cal.calculateMono(MonoOperatorModes.square, 1.1),
				0.01);
		assertEquals(Double.POSITIVE_INFINITY, cal.calculateMono(
				MonoOperatorModes.square, Double.POSITIVE_INFINITY), 0.0);
	}

	@Test
	public void testSquareRoot() {
		assertEquals(8,
				cal.calculateMono(MonoOperatorModes.squareRoot, (double) 64),
				0.0);
		assertEquals(0,
				cal.calculateMono(MonoOperatorModes.squareRoot, (double) 0),
				0.0);
		assertEquals(Double.NaN,
				cal.calculateMono(MonoOperatorModes.squareRoot, (double) -64),
				0.0);
		assertEquals(Double.POSITIVE_INFINITY,
				cal.calculateMono(MonoOperatorModes.squareRoot,
						(double) Double.POSITIVE_INFINITY), 0.0);
	}

	@Test
	public void testOneDevidedBy() {
		assertEquals(Double.POSITIVE_INFINITY,
				cal.calculateMono(MonoOperatorModes.oneDevidedBy, 0.0), 0.0);
		assertEquals(0.2,
				cal.calculateMono(MonoOperatorModes.oneDevidedBy, 5.0), 0.0);
		assertEquals(5.0,
				cal.calculateMono(MonoOperatorModes.oneDevidedBy, 0.2), 0.0);
		assertEquals(0.0, cal.calculateMono(MonoOperatorModes.oneDevidedBy,
				Double.POSITIVE_INFINITY), 0.0);
	}

	@Test
	public void testCos() {
		assertEquals(1.0, cal.calculateMono(MonoOperatorModes.cos, 0.0), 0.0);
		assertEquals(0.540, cal.calculateMono(MonoOperatorModes.cos, 1.0),
				0.001);
		assertEquals(0.0,
				cal.calculateMono(MonoOperatorModes.cos, Math.PI / 2), 0.001);
		assertEquals(Double.NaN, cal.calculateMono(MonoOperatorModes.cos,
				Double.POSITIVE_INFINITY), 0.001);
	}

	@Test
	public void testSin() {
		assertEquals(0.0, cal.calculateMono(MonoOperatorModes.sin, 0.0), 0.0);
		assertEquals(0.841, cal.calculateMono(MonoOperatorModes.sin, 1.0),
				0.001);
		assertEquals(1.0,
				cal.calculateMono(MonoOperatorModes.sin, Math.PI / 2), 0.001);
		assertEquals(Double.NaN, cal.calculateMono(MonoOperatorModes.sin,
				Double.POSITIVE_INFINITY), 0.001);
	}

	@Test
	public void testTan() {
		assertEquals(0.0, cal.calculateMono(MonoOperatorModes.tan, 0.0), 0.0);
		assertEquals(1.557, cal.calculateMono(MonoOperatorModes.tan, 1.0),
				0.001);
		assertEquals(Double.POSITIVE_INFINITY,
				cal.calculateMono(MonoOperatorModes.tan, Math.PI / 2), 0.001);
		assertEquals(Double.NaN, cal.calculateMono(MonoOperatorModes.tan,
				Double.POSITIVE_INFINITY), 0.001);
	}
}
