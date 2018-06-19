package com.jzli.json;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.jzli.json.entity.Person;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * =======================================================
 *
 * @Company 技术中心-共享服务部-后端服务部
 * @Date ：2018/6/19
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：
 * ========================================================
 */
public class JsonDemo {

    @Test
    public void test() {
        String json;
        String path = "data[]";
        String value1 = "name.chineseName";
        String value2 = "name.englishName";
        String value3 = "age";


        List<HashMap<String, String>> list = new LinkedList<>();

        List<Person> personList = new LinkedList<>();
        for (int i = 1; i < 11; i++) {
            Person person = new Person("中-" + i, "Eng-" + i, i + "");
            personList.add(person);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", personList);
        json = jsonObject.toString();
//        System.out.println(json);

        jsonObject = new JSONObject(json);
        JSONArray data = jsonObject.getJSONArray("data");
        for (int i = 0, n = data.size(); i < n; i++) {
            JSONObject o = (JSONObject) data.get(i);
            JSONObject name = (JSONObject) o.get("name");
            String chineseName = name.get("chineseName", String.class);
            String englishName = name.get("englishName", String.class);
            String age = o.get("age", String.class);

            HashMap<String, String> map = new HashMap<>();
            map.put("chineseName", chineseName);
            map.put("englishName", englishName);
            map.put("age", age);

            list.add(map);
        }

        System.out.println(list);

        list = getValue(path, new String[]{value1, value2, value3}, json);

        System.out.println(list);
    }

    public List<HashMap<String, String>> getValue(String path, String[] keys, String json) {
        List<HashMap<String, String>> list = new LinkedList<>();
        JSONObject jsonObject = new JSONObject(json);
        String[] split = path.split("\\.");
        for (String s : split) {
            if (s.contains("[]")) {
                String str = s.replace("[]", "");
                JSONArray jsonArray = jsonObject.getJSONArray(str);
                for (int i = 0, n = jsonArray.size(); i < n; i++) {
                    jsonObject = (JSONObject) jsonArray.get(i);
                    HashMap<String, String> map = new HashMap<>();
                    for (String key : keys) {
                        String value = getSingleValue(key, jsonObject);
                        map.put(key, value);
                    }
                    list.add(map);
                }
                break;
            } else {
                jsonObject = jsonObject.getJSONObject(s);
            }
        }
        return list;
    }

    private String getSingleValue(String key, JSONObject jsonObject) {
        JSONObject object = jsonObject;
        String[] split = key.split("\\.");
        for (int i = 0, n = split.length; i < n; i++) {
            if (i == n - 1) {
                String value = object.get(split[i], String.class);
                return value;
            } else {
                object = object.getJSONObject(split[i]);
            }
        }
        return null;
    }


}
