package com.changing.bg.service.impl;

import com.changing.bg.model.entity.cookbook.CookBookDO;
import com.changing.bg.model.vo.cookbook.CookBookRandomColVO;
import com.changing.bg.model.vo.cookbook.CookBookRandomVO;
import com.changing.bg.model.vo.cookbook.CookBookSaveRamdomVO;
import com.changing.bg.reposity.cookbook.CookBookReposity;
import com.changing.bg.service.CookBookService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenjun
 * @version V1.0
 * @since 2020-08-15 14:22
 */
@Service
public class CookBookServiceImpl implements CookBookService {

    private static final int WEEK_DAYS = 7;
    private static final int MAP_INIT_SIZE = 16;

    @Autowired
    private CookBookReposity cookBookReposity;

    @Override
    public List<CookBookRandomColVO> randomCookBook() {
        Map<String, String> selectedData = new HashMap<>(MAP_INIT_SIZE);
        List<CookBookRandomColVO> resultData = new ArrayList<>();

        // 中午-肉菜
        List<CookBookRandomVO> lunchMeetList = this.random(selectedData, "1", Boolean.TRUE);
        CookBookRandomColVO lunchMeet = new CookBookRandomColVO();
        lunchMeet.setTimeType("午餐");
        lunchMeet.setMonday(lunchMeetList.get(0).getDishName() + "(荤)");
        lunchMeet.setTuesday(lunchMeetList.get(1).getDishName() + "(荤)");
        lunchMeet.setWednesday(lunchMeetList.get(2).getDishName() + "(荤)");
        lunchMeet.setThursday(lunchMeetList.get(3).getDishName() + "(荤)");
        lunchMeet.setFriday(lunchMeetList.get(4).getDishName() + "(荤)");
        lunchMeet.setSaturday(lunchMeetList.get(5).getDishName() + "(荤)");
        lunchMeet.setSunday(lunchMeetList.get(6).getDishName() + "(荤)");
        resultData.add(lunchMeet);
        // 中午-素菜
        List<CookBookRandomVO> lunchVegetablesList = this.random(selectedData, "2", Boolean.TRUE);
        CookBookRandomColVO lunchVegetables = new CookBookRandomColVO();
        lunchVegetables.setTimeType("午餐");
        lunchVegetables.setMonday(lunchVegetablesList.get(0).getDishName() + "(素)");
        lunchVegetables.setTuesday(lunchVegetablesList.get(1).getDishName() + "(素)");
        lunchVegetables.setWednesday(lunchVegetablesList.get(2).getDishName() + "(素)");
        lunchVegetables.setThursday(lunchVegetablesList.get(3).getDishName() + "(素)");
        lunchVegetables.setFriday(lunchVegetablesList.get(4).getDishName() + "(素)");
        lunchVegetables.setSaturday(lunchVegetablesList.get(5).getDishName() + "(素)");
        lunchVegetables.setSunday(lunchVegetablesList.get(6).getDishName() + "(素)");
        resultData.add(lunchVegetables);
        // 中午-汤
        List<CookBookRandomVO> lunchSoupList = this.random(selectedData, "3", Boolean.FALSE);
        CookBookRandomColVO lunchSoup = new CookBookRandomColVO();
        lunchSoup.setTimeType("午餐");
        lunchSoup.setMonday(lunchSoupList.get(0).getDishName() + "(汤)");
        lunchSoup.setTuesday(lunchSoupList.get(1).getDishName() + "(汤)");
        lunchSoup.setWednesday(lunchSoupList.get(2).getDishName() + "(汤)");
        lunchSoup.setThursday(lunchSoupList.get(3).getDishName() + "(汤)");
        lunchSoup.setFriday(lunchSoupList.get(4).getDishName() + "(汤)");
        lunchSoup.setSaturday(lunchSoupList.get(5).getDishName() + "(汤)");
        lunchSoup.setSunday(lunchSoupList.get(6).getDishName() + "(汤)");
        resultData.add(lunchSoup);

        // 晚餐-肉菜
        List<CookBookRandomVO> dinnerMeetList = this.random(selectedData, "1", Boolean.TRUE);
        CookBookRandomColVO dinnerMeet = new CookBookRandomColVO();
        dinnerMeet.setTimeType("晚餐");
        dinnerMeet.setMonday(dinnerMeetList.get(0).getDishName() + "(荤)");
        dinnerMeet.setTuesday(dinnerMeetList.get(1).getDishName() + "(荤)");
        dinnerMeet.setWednesday(dinnerMeetList.get(2).getDishName() + "(荤)");
        dinnerMeet.setThursday(dinnerMeetList.get(3).getDishName() + "(荤)");
        dinnerMeet.setFriday(dinnerMeetList.get(4).getDishName() + "(荤)");
        dinnerMeet.setSaturday(dinnerMeetList.get(5).getDishName() + "(荤)");
        dinnerMeet.setSunday(dinnerMeetList.get(6).getDishName() + "(荤)");
        resultData.add(dinnerMeet);
        // 晚餐-素菜
        List<CookBookRandomVO> dinnerVegetablesList = this.random(selectedData, "2", Boolean.TRUE);
        CookBookRandomColVO dinnerVegetables = new CookBookRandomColVO();
        dinnerVegetables.setTimeType("晚餐");
        dinnerVegetables.setMonday(dinnerVegetablesList.get(0).getDishName() + "(素)");
        dinnerVegetables.setTuesday(dinnerVegetablesList.get(1).getDishName() + "(素)");
        dinnerVegetables.setWednesday(dinnerVegetablesList.get(2).getDishName() + "(素)");
        dinnerVegetables.setThursday(dinnerVegetablesList.get(3).getDishName() + "(素)");
        dinnerVegetables.setFriday(dinnerVegetablesList.get(4).getDishName() + "(素)");
        dinnerVegetables.setSaturday(dinnerVegetablesList.get(5).getDishName() + "(素)");
        dinnerVegetables.setSunday(dinnerVegetablesList.get(6).getDishName() + "(素)");
        resultData.add(dinnerVegetables);
        // 晚餐-汤
        List<CookBookRandomVO> dinnerSoupList = this.random(selectedData, "3", Boolean.FALSE);
        CookBookRandomColVO dinnerSoup = new CookBookRandomColVO();
        dinnerSoup.setTimeType("晚餐");
        dinnerSoup.setMonday(dinnerSoupList.get(0).getDishName() + "(汤)");
        dinnerSoup.setTuesday(dinnerSoupList.get(1).getDishName() + "(汤)");
        dinnerSoup.setWednesday(dinnerSoupList.get(2).getDishName() + "(汤)");
        dinnerSoup.setThursday(dinnerSoupList.get(3).getDishName() + "(汤)");
        dinnerSoup.setFriday(dinnerSoupList.get(4).getDishName() + "(汤)");
        dinnerSoup.setSaturday(dinnerSoupList.get(5).getDishName() + "(汤)");
        dinnerSoup.setSunday(dinnerSoupList.get(6).getDishName() + "(汤)");
        resultData.add(dinnerSoup);

        return resultData;
    }

    @Override
    public void saveCookBook(CookBookSaveRamdomVO param) {

    }

    @Override
    public void saveRandomCookBook(List<CookBookSaveRamdomVO> param) {

    }

    @Override
    public Integer getLastSelectedSerial() {
        return null;
    }

    private List<CookBookRandomVO> random(Map<String, String> selectedData, String dishTag, Boolean unRepeat) {

        CookBookDO cookBookDO = new CookBookDO();
        // 未被选择的菜品
        cookBookDO.setDishSelectStatus(0);
        cookBookDO.setDishTag(dishTag);
        List<CookBookDO> cookBookDOList = cookBookReposity.listCookBook(cookBookDO);
        List<CookBookRandomVO> resultList = new ArrayList<>();
        for (int i = 0; i < WEEK_DAYS; i++) {
            int doSize = cookBookDOList.size();
            int randomIndex = new Random().nextInt(doSize);
            CookBookDO randomCookBookDO = cookBookDOList.get(randomIndex);
            String repeatKey = randomCookBookDO.getDishName();

            if (unRepeat) {
                while (selectedData.containsKey(repeatKey)) {
                    randomIndex = new Random().nextInt(doSize);
                    repeatKey = cookBookDOList.get(randomIndex).getDishName();
                }

                // 添加到已选择菜品中
                String dishName = randomCookBookDO.getDishName();
                selectedData.put(dishName, dishName);
            }

            CookBookRandomVO cookBookRandomVO = new CookBookRandomVO();
            cookBookRandomVO.setId(randomCookBookDO.getId());
            cookBookRandomVO.setDishName(randomCookBookDO.getDishName());
            cookBookRandomVO.setDishTag(randomCookBookDO.getDishTag());
            resultList.add(cookBookRandomVO);
        }

        return resultList;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(new Random().nextInt(10));
        }
    }
}