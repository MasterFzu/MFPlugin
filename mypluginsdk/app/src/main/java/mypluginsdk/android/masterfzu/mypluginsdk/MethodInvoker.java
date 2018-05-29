/*
 * Copyright (C) 2005-2017 Qihoo 360 Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed To in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package mypluginsdk.android.masterfzu.mypluginsdk;

import java.lang.reflect.Method;

/**
 * method-invoker的封装
 *
 * @author RePlugin Team
 */

public class MethodInvoker {

    private static final String TAG = "MethodInvoker";

    private ClassLoader mLoader;

    private String mClassName;

    private String mMethodName;

    private Class<?>[] mParamTypes;

    private Method mMethod;

    private boolean mInitialized;

    private boolean mAvailable;

    public MethodInvoker(ClassLoader loader, String className, String methodName, Class<?>[] paramTypes) {
        mLoader = loader;
        mClassName = className;
        mMethodName = methodName;
        mParamTypes = paramTypes;
        mMethod = null;
        mInitialized = false;
        mAvailable = false;
    }

    public Object call(Object... methodParamValues) {
        if (!mInitialized) {
            try {
                mInitialized = true;
                mMethod = ReflectUtils.getMethod(mLoader, mClassName, mMethodName, mParamTypes);
                mAvailable = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (mMethod != null) {
            try {
                return ReflectUtils.invokeMethod(mMethod, null, methodParamValues);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public boolean isAvailable() {
        return mAvailable;
    }
}
