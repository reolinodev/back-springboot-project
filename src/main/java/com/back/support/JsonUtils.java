package com.back.support;

import com.back.admin.domain.MenuEntity;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;

public class JsonUtils {

    public static LinkedHashMap<String,Object> getJsonMenu() throws IOException, ParseException {
        LinkedHashMap<String,Object> data = new LinkedHashMap<>();

        List<MenuEntity> menuLv1List = new ArrayList<>();
        List<MenuEntity> menuLv2List = new ArrayList<>();
        MenuEntity menuUrl = new MenuEntity();

        JSONObject json = convertJsonFile("json/menu.json");
        JSONObject menuUrlObj = (JSONObject) json.get("menuMainUrl");
        ArrayList menuLv1ListArr = (ArrayList) json.get("menuLv1List");
        ArrayList menuLv2ListArr = (ArrayList) json.get("menuLv2List");

        for (Object o1 : menuLv1ListArr) {
            JSONObject obj = (JSONObject) o1;
            MenuEntity menuEntity = new MenuEntity();
            menuEntity.menu_id = obj.get("menu_id").toString();
            menuEntity.menu_nm = obj.get("menu_nm").toString();
            menuEntity.menu_lv = Integer.parseInt(obj.get("menu_lv").toString());
            menuEntity.prn_menu_id = obj.get("prn_menu_id").toString();
            menuEntity.url = obj.get("url").toString();
            menuEntity.prn_menu_nm = obj.get("prn_menu_nm").toString();
            menuLv1List.add(menuEntity);
        }

        for (Object o2 : menuLv2ListArr) {
            JSONObject obj = (JSONObject) o2;
            MenuEntity menuEntity = new MenuEntity();
            menuEntity.menu_id = obj.get("menu_id").toString();
            menuEntity.menu_nm = obj.get("menu_nm").toString();
            menuEntity.menu_lv = Integer.parseInt(obj.get("menu_lv").toString());
            menuEntity.prn_menu_id = obj.get("prn_menu_id").toString();
            menuEntity.url = obj.get("url").toString();
            menuEntity.prn_menu_nm = obj.get("prn_menu_nm").toString();
            menuLv2List.add(menuEntity);
        }

        menuUrl.menu_id = menuUrlObj.get("menu_id").toString();
        menuUrl.menu_nm = menuUrlObj.get("menu_nm").toString();
        menuUrl.menu_lv = Integer.parseInt(menuUrlObj.get("menu_lv").toString());
        menuUrl.prn_menu_id = menuUrlObj.get("prn_menu_id").toString();
        menuUrl.url = menuUrlObj.get("url").toString();
        menuUrl.prn_menu_nm = menuUrlObj.get("prn_menu_nm").toString();

        data.put("menuLv1List",menuLv1List);
        data.put("menuLv2List",menuLv2List);
        data.put("menuUrl",menuUrl);

        return data;
    }

    private static JSONObject convertJsonFile(String url) throws IOException, ParseException {
        ClassPathResource resource = new ClassPathResource(url);
        JSONObject json = (JSONObject) new JSONParser().parse(new InputStreamReader(resource.getInputStream(), "UTF-8"));
        return json;
    }
}
