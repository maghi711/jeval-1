/*
 * Copyright 2002-2007 Robert Breidecker.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.sourceforge.jeval.function.math;

import net.sourceforge.jeval.EvaluationConstants;
import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.*;

import java.util.ArrayList;

/**
 * This class is a function that executes within Evaluator. The function returns
 * the remainder operation on two arguments as prescribed by the IEEE 754
 * standard. See the Math.IEEERemainder(double, double) method in the JDK for a
 * complete description of how this function works.
 */
public class IEEEremainder implements Function {
    /**
     * Returns the name of the function - "IEEEremainder".
     *
     * @return The name of this function class.
     */
    public String getName() {
        return "IEEEremainder";
    }

    /**
     * Executes the function for the specified argument. This method is called
     * internally by Evaluator.
     *
     * @param evaluator An instance of Evaluator.
     * @param arguments A string argument that will be converted into two double
     *                  values and evaluated.
     * @return The the remainder operation on two arguments as prescribed by the
     * IEEE 754 standard.
     * @throws FunctionException Thrown if the argument(s) are not valid for this function.
     */
    public FunctionResult execute(final Evaluator evaluator, final String arguments)
            throws FunctionException {
        Double result = null;

        ArrayList numbers = FunctionHelper.getDoubles(arguments,
                EvaluationConstants.FUNCTION_ARGUMENT_SEPARATOR);

        if (numbers.size() != 2) {
            throw new FunctionException("Two numeric arguments are required.");
        }

        try {
            double argumentOne = ((Double) numbers.get(0)).doubleValue();
            double argumentTwo = ((Double) numbers.get(1)).doubleValue();
            result = new Double(Math.IEEEremainder(argumentOne, argumentTwo));
        } catch (Exception e) {
            throw new FunctionException("Two numeric arguments are required.", e);
        }

        return new FunctionResult(result.toString(),
                FunctionConstants.FUNCTION_RESULT_TYPE_NUMERIC);
    }
}