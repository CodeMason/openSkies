/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.skittishSloth.openSkies.testUtils;

import com.google.common.base.Defaults;
import fj.test.Arbitrary;
import java.beans.PropertyDescriptor;
import static org.junit.Assert.*;
import static fj.test.Arbitrary.*;
import fj.test.Rand;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.beanutils.PropertyUtils;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author mcory01
 */
public abstract class SimpleBeanTests {
    
    protected static final Object[] EMPTY_PARAMS = new Object[] {};
    
    @Before
    public void setup() {
        arbitraryInstanceMap.clear();
        registerDefaultArbitraryInstances();
        
        clazz = getClassForTest();
    }
 
    @Test
    public final void ensureDefaultConstructorInitializesNothing() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
        Constructor<?> defCtor = findDefaultConstructor();
        
        assertNotNull(clazz.getSimpleName() + " does not have a no-parameter constructor.", defCtor);
        final Object object = defCtor.newInstance(EMPTY_PARAMS);
        
        final PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(clazz);
        for (final PropertyDescriptor pd : propertyDescriptors) {
            final Method readMethod = pd.getReadMethod();
            if (readMethod == null) {
                System.err.println("Read method for " + pd.getName() + " was null.");
                continue;
            }
            
            if (readMethod.getDeclaringClass() != clazz) {
                continue;
            }
            
            final Class<?> returnType = readMethod.getReturnType();
            final Object value = readMethod.invoke(object, EMPTY_PARAMS);
            if (returnType.isPrimitive()) {
                assertEquals(Defaults.defaultValue(returnType), value);
            } else if (returnType.isArray()) {
                if (value == null) {
                    continue;
                }
                
                final Field lengthField = returnType.getField("length");
                final int length = lengthField.getInt(object);
                assertEquals(0, length);
            } else {
                assertNull(value);
            }
        }
    }
    
    @Test
    public final void ensureSettersWorkProperly() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        final Constructor<?> defCtor = findDefaultConstructor();
        
        assertNotNull(clazz.getSimpleName() + " does not have a no-parameter constructor.", defCtor);
        final Object object = defCtor.newInstance(EMPTY_PARAMS);
        final PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(clazz);
        for (final PropertyDescriptor pd : propertyDescriptors) {
            final Method writeMethod = pd.getWriteMethod();
            if (writeMethod == null) {
                System.err.println("Write method for " + pd.getName() + " was null.");
                continue;
            }
            
            if (writeMethod.getDeclaringClass() != clazz) {
                continue;
            }
            
            if (writeMethod.getParameterCount() != 1) {
                System.err.println("Write method for " + pd.getName() + " was not a single parameter.");
                continue;
            }
            
            final Method readMethod = pd.getReadMethod();
            if (readMethod == null) {
                System.err.println("Read method for " + pd.getName() + " was null.");
                continue;
            }
            
            if (readMethod.getDeclaringClass() != clazz) {
                continue;
            }
            
            final Class<?> paramType = writeMethod.getParameterTypes()[0];
            final Object arbValue;
            if (paramType.isPrimitive()) {
                final Arbitrary<?> arb = getPrimitiveArbitraryInstance(paramType);
                arbValue = arb.gen.gen(100, Rand.standard);
            } else if (paramType.isArray()) {
                // work with arrays.
                final Class<?> componentType = paramType.getComponentType();
                if (componentType.isPrimitive()) {
                    final Arbitrary<?> componentArb = getPrimitiveArbitraryInstance(componentType);
                    arbValue = arbArray(componentArb).gen.gen(100, Rand.standard);
                } else {
                    final Arbitrary<?> componentArb = getObjectArbitraryInstance(componentType);
                    arbValue = arbArray(componentArb).gen.gen(100, Rand.standard);
                }
            } else {
                final Arbitrary<?> arb = getObjectArbitraryInstance(paramType);
                if (arb != null) {
                    arbValue = arb.gen.gen(100, Rand.standard);
                } else {
                    final Object val = createObjectInstance(paramType);
                    if (val == null) {
                        // try and get the value with just a default ctor if possible.
                        final Constructor<?> valCtor = findDefaultConstructor(paramType);
                        if (valCtor == null) {
                            arbValue = createObjectInstance(paramType);
                        } else {
                            arbValue = valCtor.newInstance(EMPTY_PARAMS);
                        }
                    } else {
                        arbValue = val;
                    }
                }
            }
            
            writeMethod.invoke(object, arbValue);
            
            final Object fromRead = readMethod.invoke(object, EMPTY_PARAMS);
            assertEquals(arbValue, fromRead);
        }
    }
    
    protected Object createObjectInstance(final Class<?> type) {
        if (type == Collection.class) {
            return new ArrayList<>();
        }
        return null;
    }
    
    protected final Constructor<?> findDefaultConstructor() throws SecurityException {
        return findDefaultConstructor(clazz);
    }

    protected final Constructor<?> findDefaultConstructor(final Class<?> theClass) throws SecurityException {
        final Constructor<?>[] constructors = theClass.getDeclaredConstructors();
        Constructor<?> defCtor = null;
        for (final Constructor<?> ctor : constructors) {
            if ((ctor.getParameterCount() == 0) && (!ctor.isSynthetic())) {
                defCtor = ctor;
                break;
            }
        }
        return defCtor;
    }

    protected final Arbitrary<?> getPrimitiveArbitraryInstance(final Class<?> type) {
        // work with primitive types
        final Arbitrary<?> arb;
        if (type == Integer.TYPE) {
            arb = arbInteger;
        } else if (type == Float.TYPE) {
            arb = arbFloat;
        } else if (type == Double.TYPE) {
            arb = arbDouble;
        } else if (type == Long.TYPE) {
            arb = arbLong;
        } else if (type == Character.TYPE) {
            arb = arbCharacter;
        } else if (type == Byte.TYPE) {
            arb = arbByte;
        } else if (type == Boolean.TYPE) {
            arb = arbBoolean;
        } else {
            arb = arbShort;
        }
        
        return arb;
    }
    
    protected final Arbitrary<?> getObjectArbitraryInstance(final Class<?> type) {
        final Arbitrary<?> res = arbitraryInstanceMap.get(type);
        if (res == null) {
            System.err.println("No Arbitrary<?> instance found for class " + type.getName());
        }
        
        return res;
    }
    
    protected final void registerArb(final Class<?> clazz, final Arbitrary<?> arbInstance) {
        arbitraryInstanceMap.put(clazz, arbInstance);
    }
    
    protected abstract Class<?> getClassForTest();
    
    private void registerDefaultArbitraryInstances() {
        registerArb(String.class, arbString);
        registerArb(Date.class, arbDate);
    }
    
    private Class<?> clazz;
    private final Map<Class<?>, Arbitrary<?>> arbitraryInstanceMap = new HashMap<>();
}
