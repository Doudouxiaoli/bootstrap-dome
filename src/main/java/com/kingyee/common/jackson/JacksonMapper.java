package com.kingyee.common.jackson;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.*;
import com.kingyee.common.modal.IJson;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Jackson用于转换的核心类ObjectMapper无需每次都new一个object
 *
 * @author fyq
 * @version 2018年02月07日  15:29
 * @zhl 202009
 */
public class JacksonMapper implements IJson {

    private static Logger logger = LoggerFactory.getLogger(JacksonMapper.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        // 解决实体未包含字段反序列化时抛出异常
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 对于空的对象转json的时候不抛出错误
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        // 允许属性名称没有引号
        mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        // 允许单引号
        mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
    }

    private JacksonMapper() {

    }

    public static ObjectMapper getInstance() {
        return mapper;
    }

    /**
     * 将String类型的json数据转换为JsonNode
     */
    public static JsonNode parse(String jsonStr) {
        try {
            return getInstance().readTree(jsonStr);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return getInstance().createObjectNode();
        }
    }

    /**
     * 成功的json<br>
     * <code>{success:true}</code>
     *
     * @return JsonElement
     */
    public static JsonNode newSuccessInstance() {
        Map<String, Object> map = new HashMap<>();
        map.put(SUCCESS, true);
        map.put(CODE, 0);
        return toJsonNode(map);
    }

    /**
     * 包含错误信息的json<br>
     * <code>{success:false,msg:$msg}</code>
     *
     * @param msg 错误信息
     * @return JsonElement
     */
    public static JsonNode newErrorInstance(String msg) {
        Map<String, Object> map = new HashMap<>();
        map.put(SUCCESS, false);
        map.put(MSG, msg == null ? "" : msg);
        return toJsonNode(map);
    }

    /**
     * 包含错误信息的json<br>
     * <code>{success:false,msg:$msg}</code>
     *
     * @param msg 错误信息
     * @return JsonElement
     */
    public static JsonNode newErrorInstance(String msg, int code) {
        Map<String, Object> map = new HashMap<>();
        map.put(SUCCESS, false);
        map.put(CODE, code);
        map.put(MSG, msg == null ? "" : msg);
        return toJsonNode(map);
    }

    /**
     * 包含数据的json
     *
     * @param data JsonElement或普通的对象
     * @return JsonElement
     */
    public static JsonNode newDataInstance(Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put(SUCCESS, true);
        map.put(CODE, 0);
        map.put(DATA, data == null ? "" : data);
        return toJsonNode(map);
    }

    /**
     * 包含count属性的json,一般前台的grid需要
     *
     * @param data  Object
     * @param count long
     * @return JsonElement
     */
    public static JsonNode newCountInstance(Object data, long count) {
        Map<String, Object> map = new HashMap<>();
        map.put(SUCCESS, true);
        map.put(CODE, 0);
        map.put(DATA, data == null ? "" : data);
        map.put(COUNT, count);
        return toJsonNode(map);
    }

    /**
     * 包含count属性的json,一般前台的grid需要
     *
     * @param pageInfo IPageInfo<?>
     * @return JsonElement
     */
    public static JsonNode newCountInstance(IPage<?> pageInfo) {
        return newCountInstance(pageInfo.getRecords(), pageInfo.getTotal());
    }

    /**
     * 包含count属性的json,一般前台的grid需要
     *
     * @param pageInfo IPageInfo<?>
     * @return JsonElement
     */
    public static JsonNode newPageInfo(IPage<?> pageInfo) {
        Map<String, Object> map = new HashMap<>();
        map.put(SUCCESS, true);
        map.put(CODE, 0);
        ///数据
        map.put(DATA, pageInfo.getRecords() == null ? "" : pageInfo.getRecords());
        //总数
        map.put(COUNT, pageInfo.getTotal());
        //总页数
        map.put(PAGES, pageInfo.getPages());
        //当前页
        map.put(PAGE, pageInfo.getCurrent());
        //每页条数
        map.put(SIZE, pageInfo.getSize());
        return toJsonNode(map);
    }

    /**
     * 生成纯json
     *
     * @param data Object
     * @return JsonElement
     */
    public static JsonNode newJson(Object data) {
        return toJsonNode(data);
    }


    /**
     * str json转 db
     *
     * @param json
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T jsonToBean(String json, Class<T> valueType) {
        try {
            return getInstance().readValue(json, valueType);
        } catch (Exception e) {
            logger.error("jsonToBean 错误", e);
        }
        return null;
    }

    /**
     * json转 db
     *
     * @param json
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T jsonToBean(JsonNode json, Class<T> valueType) {
        try {
            return jsonToBean(getInstance().writeValueAsString(json), valueType);
        } catch (Exception e) {
            logger.error("jsonToBean 错误", e);
        }
        return null;
    }

    /**
     * map转 db
     *
     * @param map
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> valueType) {
        try {
            if (map == null) {
                return null;
            }
            return jsonToBean(getInstance().writeValueAsString(map), valueType);
        } catch (Exception e) {
            logger.error("mapToBean 错误", e);
        }
        return null;
    }

    /**
     * map转 db
     *
     * @param list
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> List<T> mapToBeanList(List<Map<String, Object>> list, Class<T> valueType) {
        try {
            if (list == null) {
                return null;
            }
            return jsonToList(getInstance().writeValueAsString(list), valueType);
        } catch (Exception e) {
            logger.error("mapToBeanList 错误", e);
        }
        return null;
    }

    /**
     * str json转 List
     *
     * @param json
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonToList(String json, Class<T> valueType) {
        try {
            return getInstance().readValue(json, getCollectionType(List.class, valueType));
        } catch (Exception e) {
            logger.error("jsonToList 错误", e);
        }
        return null;
    }


    /**
     * jsonnode 转 map
     *
     * @param node
     * @return
     */
    public static Map<String, Object> toMap(JsonNode node) {
        return getInstance().convertValue(node == null ? "" : node, Map.class);
    }

    /**
     * str 转 map
     *
     * @param json
     * @return
     */
    public static Map<String, Object> newJsonMap(String json) {
        if (StringUtils.isEmpty(json)) {
            return new HashMap<>();
        }
        return toMap(parse(json));
    }

    /**
     * javaType
     *
     * @param collectionClass
     * @param elementClasses
     * @return
     */
    private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return getInstance().getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * @param data
     * @return
     */
    private static JsonNode toJsonNode(Object data) {
        return getInstance().valueToTree(data);
    }

    public static void main(String[] args) {
        System.out.println(newJson(null));
    }
}