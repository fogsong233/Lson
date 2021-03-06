package cn.luern0313.lson.annotation.field;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;

import cn.luern0313.lson.annotation.LsonDefinedAnnotation;
import cn.luern0313.lson.util.DataProcessUtil;
import cn.luern0313.lson.util.TypeUtil;

/**
 * 用指定的连接符拼接字符串数组。
 *
 * <p>反序列化中：输入任意类型，输出{@code String}类型。
 * <p>序列化中：输入{@code String}类型，输出任意类型。
 *
 * @author luern0313
 */

@LsonDefinedAnnotation(config = LsonJoinArray.LsonJoinArrayConfig.class, acceptableDeserializationType = LsonDefinedAnnotation.AcceptableType.NOT_HANDLE, acceptableSerializationType = LsonDefinedAnnotation.AcceptableType.STRING, isIgnoreArray = true, isIgnoreList = true, isIgnoreMap = true)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LsonJoinArray
{
    /**
     * 连接符，用于连接数组。
     *
     * @return 连接符。
     */
    String value() default "";

    class LsonJoinArrayConfig implements LsonDefinedAnnotation.LsonDefinedAnnotationConfig
    {
        @Override
        public Object deserialization(Object value, Annotation annotation, Object object)
        {
            TypeUtil typeUtil = new TypeUtil(value);
            if(typeUtil.isListTypeClass())
                return DataProcessUtil.join((ArrayList<?>) value, ((LsonJoinArray) annotation).value());
            else if(typeUtil.isArrayTypeClass())
                return DataProcessUtil.join((Object[]) value, ((LsonJoinArray) annotation).value());
            return null;
        }

        @Override
        public Object serialization(Object value, Annotation annotation, Object object)
        {
            return ((StringBuilder) value).toString().split(((LsonJoinArray) annotation).value());
        }
    }
}
