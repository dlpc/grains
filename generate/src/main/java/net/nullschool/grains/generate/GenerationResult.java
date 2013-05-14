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

import java.util.Collection;

/**
 * 2013-03-24<p/>
 *
 * An object to hold the results of one code generating operation.
 *
 * @author Cameron Beccario
 */
class GenerationResult {

    private final String text;
    private final Collection<String> errors;

    public GenerationResult(String text, Collection<String> errors) {
        this.text = text;
        this.errors = errors;
    }

    /**
     * Returns the generated code.
     */
    public String getText() {
        return text;
    }

    /**
     * Returns error messages encountered during code generation, or an empty collection if none.
     */
    public Collection<String> getErrors() {
        return errors;
    }
}
