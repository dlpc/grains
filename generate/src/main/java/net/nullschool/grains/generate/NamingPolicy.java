/*
 * Copyright 2013 Cameron Beccario
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.nullschool.grains.generate;

import net.nullschool.grains.GrainTools;

import java.util.EnumMap;
import java.util.Map;


/**
 * 2013-05-09<p/>
 *
 * A class that centralizes the naming patterns used for code generation.
 *
 * @author Cameron Beccario
 */
final class NamingPolicy {

    /**
     * The (case-insensitive) name a property must have to be considered a special "id" property.
     */
    static final String ID_PROPERTY_NAME = "id";

    enum Name {
        grain,
        builder,
        factory,
        grainImpl,
        grainProxy,
        builderImpl,
    }

    /**
     * Returns the fully qualified name of the class pattern for the specified schema.
     */
    String getName(Class<?> schema, Name name) {
        String prefix = GrainTools.targetPackageOf(schema) + '.' + schema.getSimpleName();
        switch (name) {
            case grain:       return prefix + "Grain";
            case builder:     return prefix + "Builder";
            case factory:     return prefix + "Factory";
            case grainImpl:   return prefix + "Factory$" + schema.getSimpleName() + "GrainImpl";
            case grainProxy:  return prefix + "Factory$" + schema.getSimpleName() + "GrainProxy";
            case builderImpl: return prefix + "Factory$" + schema.getSimpleName() + "BuilderImpl";
            default:
                throw new IllegalStateException(String.valueOf(name));
        }
    }

    /**
     * Returns the short name of the class pattern for the specified schema.
     */
    String getSimpleName(Class<?> schema, Name name) {
        String prefix = schema.getSimpleName();
        switch (name) {
            case grain:       return prefix + "Grain";
            case builder:     return prefix + "Builder";
            case factory:     return prefix + "Factory";
            case grainImpl:   return prefix + "GrainImpl";
            case grainProxy:  return prefix + "GrainProxy";
            case builderImpl: return prefix + "BuilderImpl";
            default:
                throw new IllegalStateException(String.valueOf(name));
        }
    }

    /**
     * Get all full names for the specified schema.
     */
    Map<Name, String> getNames(Class<?> schema) {
        Map<Name, String> result = new EnumMap<>(Name.class);
        for (Name name : Name.values()) {
            result.put(name, getName(schema, name));
        }
        return result;
    }

    /**
     * Get all simple names for the specified schema.
     */
    Map<Name, String> getSimpleNames(Class<?> schema) {
        Map<Name, String> result = new EnumMap<>(Name.class);
        for (Name name : Name.values()) {
            result.put(name, getSimpleName(schema, name));
        }
        return result;
    }
}
