package com.idv.lili.ecshop;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idv.lili.ecshop.entity.Category;
import com.idv.lili.ecshop.entity.Goods;
import com.idv.lili.ecshop.entity.Menu;
import com.idv.lili.ecshop.entity.Users;
import com.idv.lili.ecshop.repository.CategoryRepository;
import com.idv.lili.ecshop.repository.GoodsRepository;
import com.idv.lili.ecshop.repository.MenuRepository;
import com.idv.lili.ecshop.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
@Slf4j
public class EcShopApplication {

    @Value("classpath:static/category.json")
    private Resource categoryResource;

    @Value("classpath:static/menu.json")
    private Resource menuResource;

    @Value("classpath:static/goods.json")
    private Resource goodsResource;

    @Value("classpath:static/users.json")
    private Resource usersResource;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UsersRepository usersRepository;

    public static void main(String[] args) {
        SpringApplication.run(EcShopApplication.class, args);

    }


    private String getJsonData(String fileName) {
        try {
            InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("static/" + fileName);
            if (null != resourceAsStream) {
                return IOUtils.toString(resourceAsStream, StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            e.getStackTrace();
            log.error(e+e.getMessage());
        }
        return null;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initProjectData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        String categoryJSON = getJsonData("category.json");
        List<Category> categoryList = objectMapper.readValue(categoryJSON, new TypeReference<List<Category>>() {
        });
        categoryRepository.saveAll(categoryList);

        String menuJSON = getJsonData("menu.json");
        List<Menu> menuList = objectMapper.readValue(menuJSON, new TypeReference<List<Menu>>() {
        });
        menuRepository.saveAll(menuList);

        String goodsJSON = getJsonData("goods.json");
        List<Goods> goodsList = objectMapper.readValue(goodsJSON, new TypeReference<List<Goods>>() {
        });
        goodsRepository.saveAll(goodsList);

        String userJSON = getJsonData("users.json");
        List<Users> usersList = objectMapper.readValue(userJSON, new TypeReference<List<Users>>() {
        });
        usersRepository.saveAll(usersList);


//
//		String categoryJSON = FileUtils.readFileToString(categoryResource.getFile(), "utf-8");
//		List<Category> categoryList = objectMapper.readValue(categoryJSON, new TypeReference<List<Category>>() {
//		});
//		categoryRepository.saveAll(categoryList);
//
//		String menuJSON = FileUtils.readFileToString(menuResource.getFile(), "utf-8");
//		List<Menu> menuList = objectMapper.readValue(menuJSON, new TypeReference<List<Menu>>() {
//		});
//		menuRepository.saveAll(menuList);
//
//		String goodsJSON = FileUtils.readFileToString(goodsResource.getFile(), "utf-8");
//		List<Goods> goodsList = objectMapper.readValue(goodsJSON, new TypeReference<List<Goods>>() {
//		});
//
//		goodsRepository.saveAll(goodsList);
//
//		String userJSON = FileUtils.readFileToString(usersResource.getFile(), "utf-8");
//		List<Users> usersList = objectMapper.readValue(userJSON, new TypeReference<List<Users>>() {
//		});
//
//		usersRepository.saveAll(usersList);

    }

}
