package com.zepal.seckill.util;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.zepal.seckill.po.SeckillPO;

/**
 * <p> 序列化工具
 * @author zepal
 * */
public class SerializationUtils {

    
    /**缓存Schema**/
    private static Map<Class<?>, Schema<?>> schemaCache = new ConcurrentHashMap<>();

    /**
     * <p>序列化方法，把指定对象序列化成字节数组
     * @param obj 待序列化对象
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> byte[] serialize(T obj) {
        Class<T> clazz = (Class<T>) obj.getClass();
        Schema<T> schema = getSchema(clazz);
        return ProtostuffIOUtil.toByteArray(obj, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
    }

    /**
     * <p>反序列化方法，将字节数组反序列化成指定Class类型
     * @param data
     * @param clazz 反序列化类型
     * @param <T>
     * @return
     */
    public static <T> T deserialize(byte[] data, Class<T> clazz) {
        Schema<T> schema = getSchema(clazz);
        T obj = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(data, obj, schema);
        return obj;
    }

    @SuppressWarnings("unchecked")
    private static <T> Schema<T> getSchema(Class<T> clazz) {
        Schema<T> schema = (Schema<T>) schemaCache.get(clazz);
        if (Objects.isNull(schema)) {
            schema = RuntimeSchema.getSchema(clazz);
            if (Objects.nonNull(schema)) {
                schemaCache.put(clazz, schema);
            }
        }
        return schema;
    }
    
    /**
     * <p> 测试 可删
     * */
    public static void main(String[] args) {
		SeckillPO seckillPO = new SeckillPO();
		seckillPO.setName("红牛");
		byte[] serialize = SerializationUtils.serialize(seckillPO);
		
		System.out.println("序列化成功");
		
		SeckillPO seckillPO2 = SerializationUtils.deserialize(serialize, SeckillPO.class);
		System.out.println(seckillPO2.toString());
	}
}
