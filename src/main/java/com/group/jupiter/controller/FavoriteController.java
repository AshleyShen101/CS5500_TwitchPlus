package com.group.jupiter.controller;


import com.group.jupiter.entity.db.Item;
import com.group.jupiter.entity.request.FavoriteRequestBody;
import com.group.jupiter.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
public class FavoriteController {

    private final FavoriteService favoriteService;

    @Autowired
    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @RequestMapping(value = "/favorite", method = RequestMethod.POST)
    public void setFavoriteItem(@RequestBody FavoriteRequestBody requestBody, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (Objects.isNull(session)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String userId = (String) session.getAttribute("user_id");
        favoriteService.setFavoriteItem(userId, requestBody.getFavoriteItem());
    }

    @RequestMapping(value = "/favorite", method = RequestMethod.DELETE)
    public void unsetFavoriteItem(@RequestBody FavoriteRequestBody requestBody, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (Objects.isNull(session)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String userId = (String) session.getAttribute("user_id");
        String itemId = requestBody.getFavoriteItem().getId();
        favoriteService.unsetFavoriteItem(userId, itemId);
    }


    @RequestMapping(value = "/favorite", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, List<Item>> getFavoriteItem(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return Collections.emptyMap();
        }

        String userId = (String) session.getAttribute("user_id");
        return favoriteService.getFavoriteItems(userId);
    }
}
